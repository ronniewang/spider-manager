package com.spider.playersheet.poi;

import com.spider.playersheet.entity.*;
import com.spider.playersheet.metadata.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by ronnie on 2016/4/26.
 * <p>
 * 从playersheet表中导入数据到数据库中，主要用POI实现
 *
 * @author ronnie
 */
public class Importer {

    //-------------------------- 表名常量 --------------------------------
    private static final String T_CAIEX_MATCH = "t_caiex_match";

    private static final String T_CAIEX_LEAGUE = "t_caiex_league";

    private static final String T_CAIEX_TEAM = "t_caiex_team";

    private static final String T_CAIEX_TABLE_ID = "t_caiex_table_id";

    private static final String T_CAIEX_PLAYER_BASIC_INFO = "t_caiex_player_basic_info";

    private static final String T_CAIEX_MATCH_STATISTIC = "t_caiex_match_statistic";

    private static final String T_CAIEX_MATCH_PLAYER = "t_caiex_match_player";
    //------------------------------------------------------------------

    //------------------------- 主客队标志 -------------------------------
    private static final int HOME_TEAM_TPYE = 1;

    private static final int AWAY_TEAM_TYPE = 2;
    //------------------------------------------------------------------

    private static PlayerSheetMetadata metadata = new DefaultPlayerSheetMetadata();

    private static DataSource dataSource;

    static {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/base_data?useUnicode=true&characterEncoding=utf8");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("root");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setInitialSize(100);
        dataSource = basicDataSource;
    }

    private static JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    public static void mainA(String[] args) throws IOException {

        String path = "C:\\workspace\\import-playersheet-mvn\\CSL_playersheet2016.xlsm";
        String sheetName = "重庆力帆";
        if (args.length < 3) {
            System.out.println("usage: java -jar xxx.jar path sheetName");
            System.out.println("参数不足，使用默认值");
        } else {
            path = args[1];
            sheetName = args[2];
        }
//        String sheetName = "石家庄永昌";
//        String sheetName = "上海绿地";
//        String sheetName = "江苏苏宁";
//        String sheetName = "天津泰达";
//        String sheetName = "上海上港";
//        String sheetName = "山东鲁能";
//        String sheetName = "广州富力";
//        String sheetName = "延边富德";
//        String sheetName = "北京国安";
        XSSFSheet sheet = getSheet(path, sheetName);
        long start = System.currentTimeMillis();
        executeImport(sheet);
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000 + "s");
    }

    private static XSSFSheet getSheet(String path, String sheetName) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        return workbook.getSheet(sheetName);
    }

    public static void executeImport(XSSFSheet sheet) throws IOException {

        if (sheet == null) {
            return;
        }
        RangeRowIndex contentRangeRowIndex = metadata.content();
        TCaiexTeamEntity currentTeamEntity = buildCurrentTeamEntity(sheet);
        persistTeamEntity(currentTeamEntity);//持久化球队信息

        Map<Integer/*column*/, TCaiexMatchPlayerEntity> playerEntitiesWithoutState = buildMatchPlayersWithoutState(sheet);

        //逐行遍历详细信息
        for (int r = contentRangeRowIndex.getStartRow(); r <= contentRangeRowIndex.getEndRow(); r++) {
            XSSFRow row = sheet.getRow(r);
            TCaiexTeamEntity teamEntity = buildTeamEntity(row);
            if (teamEntity == null) {
                return;
            }
            persistTeamEntity(teamEntity);//持久化对阵的球队信息
            Long teamId = getCurrentTeamId(currentTeamEntity);

            TCaiexLeagueEntity leagueEntity = buildLeagueEntity(row);
            persistLeagueEntity(leagueEntity);//持久化联赛信息

            TCaiexMatchEntity matchEntity = buildMatchInfo(row, currentTeamEntity);
            Long matchId = persistMatchEntity(matchEntity);//持久化比赛信息

            List<TCaiexMatchPlayerEntity> playerEntitiesWithState = buildMatchPlayers(row, playerEntitiesWithoutState);
            List<TCaiexPlayerBasicInfoEntity> basicInfoEntities = buildPlayerBasicInfos(playerEntitiesWithState, currentTeamEntity);
            persistBasicInfos(basicInfoEntities);//持久化球员基本信息

            setPlayerIdAndMatchIdAndTeamIdForMatchPlayers(playerEntitiesWithState, matchId, teamId);
            persistMatchPlayerEntities(playerEntitiesWithState);//持久化比赛中球员相关数据

            List<TCaiexMatchStatisticEntity> statisticEntities = buildMatchStatistics(row, matchId);
            persistStatisticEntites(statisticEntities);//持久化比赛统计信息
        }
    }

    private static Long getCurrentTeamId(TCaiexTeamEntity currentTeamEntity) {

        Long teamId = jdbcTemplate.queryForObject("select id from " + T_CAIEX_TEAM + " where name = ?", new Object[]{currentTeamEntity.getName()}, Long.class);
        return teamId;
    }

    private static void setPlayerIdAndMatchIdAndTeamIdForMatchPlayers(List<TCaiexMatchPlayerEntity> playerEntitiesWithState, Long matchId, Long teamId) {

        for (TCaiexMatchPlayerEntity playerEntity : playerEntitiesWithState) {
            playerEntity.setMatchId(matchId);
            playerEntity.setTeamId(teamId);
            try {
                Long playerId = jdbcTemplate.queryForObject(
                        "SELECT id FROM t_caiex_player_basic_info WHERE name = ?", new Object[]{playerEntity.getName()}, Long.class);
                playerEntity.setPlayerId(playerId);
            } catch (EmptyResultDataAccessException e) {
                throw new IllegalStateException("");
            }
        }

    }

    private static void persistMatchPlayerEntities(List<TCaiexMatchPlayerEntity> playerEntitiesWithState) {

        for (TCaiexMatchPlayerEntity mpe : playerEntitiesWithState) {
            try {
                Map<String, Object> map = jdbcTemplate.queryForMap(
                        "SELECT id, state FROM " + T_CAIEX_MATCH_PLAYER + " WHERE match_id = ? AND player_id = ?",
                        mpe.getMatchId(), mpe.getPlayerId());
                if (!mpe.getState().equals(map.get("state")) || !mpe.getTeamId().equals(map.get("team_id"))) {
                    //update
                    jdbcTemplate.update("UPDATE " + T_CAIEX_MATCH_PLAYER + " SET state = ?, team_id = ? WHERE match_id = ? AND player_id = ?",
                            mpe.getState(), mpe.getTeamId(), mpe.getMatchId(), mpe.getPlayerId());
                }
            } catch (DataAccessException e) {
                jdbcTemplate.update(
                        "INSERT INTO " + T_CAIEX_MATCH_PLAYER + " (match_id, player_id, team_id, number, position, name, state, update_time) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                        mpe.getMatchId(), mpe.getPlayerId(), mpe.getTeamId(), mpe.getNumber(), mpe.getPosition(), mpe.getName(), mpe.getState(), mpe.getUpdateTime());
                System.out.println("insert into " + T_CAIEX_MATCH_PLAYER + mpe + " success");
            }
        }
    }

    private static void persistStatisticEntites(List<TCaiexMatchStatisticEntity> statisticEntities) {

        for (TCaiexMatchStatisticEntity se : statisticEntities) {
            try {
                Map<String, Object> map = jdbcTemplate.queryForMap("SELECT `count` FROM " + T_CAIEX_MATCH_STATISTIC + " WHERE match_id = ? AND item = ? AND team = ?",
                        se.getMatchId(), se.getItem(), se.getTeam());
                Integer count = (Integer) map.get("count");
                if (!se.getCount().equals(count)) {
                    jdbcTemplate.update("UPDATE " + T_CAIEX_MATCH_STATISTIC + " SET `count` = ? WHERE match_id = ? AND item = ? AND team = ?",
                            se.getCount(), se.getMatchId(), se.getItem(), se.getTeam());
                    System.out.println("update " + se + ", change count to " + count);
                }
            } catch (DataAccessException e) {
                jdbcTemplate.update(
                        "INSERT INTO " + T_CAIEX_MATCH_STATISTIC + " (match_id, team, item, `count`, update_time) " +
                                "VALUES (?, ?, ?, ?, ?)",
                        se.getMatchId(), se.getTeam(), se.getItem(), se.getCount(), se.getUpdateTime());
                System.out.println("insert into " + T_CAIEX_MATCH_STATISTIC + " " + se.getMatchId() + " success");
            }
        }
    }

    private static void persistLeagueEntity(TCaiexLeagueEntity leagueEntity) {

        try {
            jdbcTemplate.queryForObject("SELECT id FROM " + T_CAIEX_LEAGUE + " WHERE name_abbr = ?", new Object[]{leagueEntity.getNameAbbr()}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            Long leagueId = generateId(T_CAIEX_LEAGUE);
            jdbcTemplate.update(
                    "INSERT  INTO " + T_CAIEX_LEAGUE + " (id, name, name_abbr, update_time) VALUES (?, ?, ?, ?)",
                    leagueId, leagueEntity.getName(), leagueEntity.getNameAbbr(), leagueEntity.getUpdateTime());
            System.out.println("insert into " + T_CAIEX_LEAGUE + " " + leagueEntity.getName() + " success");
        }
    }

    private static TCaiexLeagueEntity buildLeagueEntity(XSSFRow row) {

        TCaiexLeagueEntity leagueEntity = new TCaiexLeagueEntity();
        String leagueNameAbbr = row.getCell(metadata.league()).getStringCellValue();
        leagueEntity.setNameAbbr(leagueNameAbbr);
        leagueEntity.setUpdateTime(new Date());
        return leagueEntity;
    }

    private static TCaiexTeamEntity buildCurrentTeamEntity(XSSFSheet sheet) {

        CellIndex cellIndex = metadata.currentTeam();
        String homeTeamName = sheet.getRow(cellIndex.getRow()).getCell(cellIndex.getColumn()).getStringCellValue();
        TCaiexTeamEntity homeTeam = new TCaiexTeamEntity();
        homeTeam.setName(homeTeamName);
        homeTeam.setUpdateTime(new Date());
        return homeTeam;
    }

    private static TCaiexTeamEntity buildTeamEntity(XSSFRow row) {

        TCaiexTeamEntity teamEntity = new TCaiexTeamEntity();
        String awayTeamName = row.getCell(metadata.opponentTeam()).getStringCellValue();
        if (awayTeamName.trim().equals("")) {
            return null;
        }
        teamEntity.setName(awayTeamName);
        teamEntity.setUpdateTime(new Date());
        return teamEntity;
    }

    private static Long persistTeamEntity(TCaiexTeamEntity teamEntity) {

        Long teamId;
        try {
            teamId = jdbcTemplate.queryForObject("SELECT id FROM " + T_CAIEX_TEAM + " WHERE name = ?", new Object[]{teamEntity.getName()}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            teamId = generateId(T_CAIEX_TEAM);
            teamEntity.setId(teamId);
            jdbcTemplate.update(
                    "INSERT INTO " + T_CAIEX_TEAM + "(id, image_url, name, update_time) VALUES (?, ?, ?, ?)",
                    teamEntity.getId(), teamEntity.getName() + ".png", teamEntity.getName(), teamEntity.getUpdateTime());
            System.out.println("insert " + teamEntity.getName() + " success");
        }
        return teamId;
    }

    private static void persistBasicInfos(List<TCaiexPlayerBasicInfoEntity> basicInfoEntities) {

        for (TCaiexPlayerBasicInfoEntity basicInfoEntity : basicInfoEntities) {
            try {
                jdbcTemplate.queryForObject(
                        "SELECT id FROM " + T_CAIEX_PLAYER_BASIC_INFO + " WHERE name = ?", new Object[]{basicInfoEntity.getName()}, Long.class);
            } catch (EmptyResultDataAccessException e) {
                Long id = generateId(T_CAIEX_PLAYER_BASIC_INFO);
                jdbcTemplate.update(
                        "INSERT INTO " + T_CAIEX_PLAYER_BASIC_INFO + " (id, current_work_team_id, name, update_time) VALUES (?, ?, ?, ?)",
                        id, basicInfoEntity.getCurrentWorkTeamId(), basicInfoEntity.getName(), basicInfoEntity.getUpdateTime());
                System.out.println("insert " + basicInfoEntity.getName() + " success");
            }
        }
    }

    private static List<TCaiexPlayerBasicInfoEntity> buildPlayerBasicInfos(List<TCaiexMatchPlayerEntity> playerEntities, TCaiexTeamEntity currentTeamEntity) {

        List<TCaiexPlayerBasicInfoEntity> basicInfoEntities = new ArrayList<>(playerEntities.size());

        Long id = jdbcTemplate.queryForObject("SELECT id FROM t_caiex_team WHERE name = ?", new Object[]{currentTeamEntity.getName()}, Long.class);
        for (TCaiexMatchPlayerEntity playerEntity : playerEntities) {
            TCaiexPlayerBasicInfoEntity entity = new TCaiexPlayerBasicInfoEntity();
            entity.setName(playerEntity.getName());
            entity.setUpdateTime(new Date());
            entity.setCurrentWorkTeamId(id);
            basicInfoEntities.add(entity);
        }
        return basicInfoEntities;
    }

    private static Long generateId(String tableName) {

        Long currentId;
        try {
            currentId = jdbcTemplate.queryForObject("SELECT table_id FROM " + T_CAIEX_TABLE_ID + " WHERE table_name = ?", new Object[]{tableName}, Long.class);
            if (currentId != null) {
                currentId += HOME_TEAM_TPYE;
                jdbcTemplate.update("UPDATE " + T_CAIEX_TABLE_ID + " SET table_id = ? WHERE table_name = ?", currentId, tableName);
                System.out.println("update " + tableName + " id success");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException("尚无" + tableName + "的id信息");
        }
        return currentId;
    }

    private static Map<Integer, TCaiexMatchPlayerEntity> buildMatchPlayersWithoutState(XSSFSheet sheet) {

        Map<Integer, TCaiexMatchPlayerEntity> result = new HashMap<>();
        RangeColumnRowIndex playerIndex = metadata.playerIndex();

        int startRow = playerIndex.getStartRow();
        int endRow = playerIndex.getEndRow();
        for (int col = playerIndex.getStartColumn(); col <= playerIndex.getEndColumn(); col++) {
            TCaiexMatchPlayerEntity playerEntity = new TCaiexMatchPlayerEntity();
            playerEntity.setPosition(sheet.getRow(startRow).getCell(col).getStringCellValue());
            playerEntity.setNumber((int) sheet.getRow(startRow + HOME_TEAM_TPYE).getCell(col).getNumericCellValue());
            XSSFCell cell = sheet.getRow(endRow).getCell(col);
            if (cell != null) {
                playerEntity.setName(cell.getStringCellValue());
                playerEntity.setUpdateTime(new Date());
                result.put(col, playerEntity);
            }
        }
        return result;
    }

    private static List<TCaiexMatchPlayerEntity> buildMatchPlayers(XSSFRow row, Map<Integer, TCaiexMatchPlayerEntity> playerEntitiesWithoutState) {

        List<TCaiexMatchPlayerEntity> playerEntities = new ArrayList<>();
        for (Map.Entry<Integer, TCaiexMatchPlayerEntity> entry : playerEntitiesWithoutState.entrySet()) {
            TCaiexMatchPlayerEntity entity = entry.getValue();
            entity.setState(row.getCell(entry.getKey()).getStringCellValue());
            playerEntities.add(entity);
        }
        return playerEntities;
    }

    private static List<TCaiexMatchStatisticEntity> buildMatchStatistics(XSSFRow row, Long matchId) {

        List<TCaiexMatchStatisticEntity> statisticEntities = new ArrayList<>();
        TCaiexMatchStatisticEntity shootingAttempt = new TCaiexMatchStatisticEntity();
        shootingAttempt.setItem(StatisticItem.ShootingAttempt);
        shootingAttempt.setMatchId(matchId);
        shootingAttempt.setCount((int) row.getCell(metadata.shootingAttempt()).getNumericCellValue());
        shootingAttempt.setTeam(HOME_TEAM_TPYE);
        shootingAttempt.setUpdateTime(new Date());
        statisticEntities.add(shootingAttempt);

        TCaiexMatchStatisticEntity opponentShootingAttempt = new TCaiexMatchStatisticEntity();
        opponentShootingAttempt.setItem(StatisticItem.OpponentShootingAttempt);
        opponentShootingAttempt.setMatchId(matchId);
        opponentShootingAttempt.setCount((int) row.getCell(metadata.opponentShootingAttempt()).getNumericCellValue());
        opponentShootingAttempt.setTeam(AWAY_TEAM_TYPE);
        opponentShootingAttempt.setUpdateTime(new Date());
        statisticEntities.add(opponentShootingAttempt);

        TCaiexMatchStatisticEntity shootingOnTarget = new TCaiexMatchStatisticEntity();
        shootingOnTarget.setItem(StatisticItem.ShootingOnTarget);
        shootingOnTarget.setMatchId(matchId);
        shootingOnTarget.setCount((int) row.getCell(metadata.shootingOnTarget()).getNumericCellValue());
        shootingOnTarget.setTeam(HOME_TEAM_TPYE);
        shootingOnTarget.setUpdateTime(new Date());
        statisticEntities.add(shootingOnTarget);

        TCaiexMatchStatisticEntity opponentShootingOnTarget = new TCaiexMatchStatisticEntity();
        opponentShootingOnTarget.setItem(StatisticItem.OpponentShootingOnTarget);
        opponentShootingOnTarget.setMatchId(matchId);
        opponentShootingOnTarget.setCount((int) row.getCell(metadata.opponentShootingOnTarget()).getNumericCellValue());
        opponentShootingOnTarget.setTeam(AWAY_TEAM_TYPE);
        opponentShootingOnTarget.setUpdateTime(new Date());
        statisticEntities.add(opponentShootingOnTarget);

        TCaiexMatchStatisticEntity possession = new TCaiexMatchStatisticEntity();
        possession.setItem(StatisticItem.Possession);
        possession.setMatchId(matchId);
        possession.setCount((int) row.getCell(metadata.possession()).getNumericCellValue());
        possession.setTeam(HOME_TEAM_TPYE);
        possession.setUpdateTime(new Date());
        statisticEntities.add(possession);

        TCaiexMatchStatisticEntity opponentPossession = new TCaiexMatchStatisticEntity();
        opponentPossession.setItem(StatisticItem.OpponentPossession);
        opponentPossession.setMatchId(matchId);
        opponentPossession.setCount((int) row.getCell(metadata.opponentPossession()).getNumericCellValue());
        opponentPossession.setTeam(AWAY_TEAM_TYPE);
        opponentPossession.setUpdateTime(new Date());
        statisticEntities.add(opponentPossession);

        return statisticEntities;
    }

    private static TCaiexMatchEntity buildMatchInfo(XSSFRow infoRow, TCaiexTeamEntity currentTeamEntity) {

        String currentTeamName = currentTeamEntity.getName();
        Long currentTeamId;
        try {
            currentTeamId = jdbcTemplate.queryForObject(
                    "SELECT id FROM " + T_CAIEX_TEAM + " WHERE name = ?",
                    new Object[]{currentTeamName}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException(currentTeamName + " doesn't exists");
        }
        String opponentTeamName = infoRow.getCell(metadata.opponentTeam()).getStringCellValue();
        Long opponentTeamId;
        try {
            opponentTeamId = jdbcTemplate.queryForObject(
                    "SELECT id FROM " + T_CAIEX_TEAM + " WHERE name = ?",
                    new Object[]{opponentTeamName}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException(opponentTeamName + " doesn't exists");
        }
        String leagueNameAbbr = infoRow.getCell(metadata.league()).getStringCellValue();
        Long leagueId;
        try {
            leagueId = jdbcTemplate.queryForObject(
                    "SELECT id FROM " + T_CAIEX_LEAGUE + " WHERE name_abbr = ?",
                    new Object[]{leagueNameAbbr}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException(leagueNameAbbr + " doesn't exists");
        }
        TCaiexMatchEntity matchEntity = new TCaiexMatchEntity();
        matchEntity.setLeagueId(leagueId);
        String homeOrAway = infoRow.getCell(metadata.homeAwayFlag()).getStringCellValue();
        matchEntity.setIsNeutral(1);
        if (homeOrAway.equals("H")) {
            matchEntity.setHomeTeamId(opponentTeamId);
            matchEntity.setAwayTeamId(currentTeamId);
        } else if (homeOrAway.equals("A")) {
            matchEntity.setHomeTeamId(currentTeamId);
            matchEntity.setAwayTeamId(opponentTeamId);
        } else if (homeOrAway.equals("N")) {//N表示中立场
            matchEntity.setHomeTeamId(currentTeamId);
            matchEntity.setAwayTeamId(opponentTeamId);
            matchEntity.setIsNeutral(0);
        } else {
            throw new IllegalStateException("主客队标志位不在H/N/A中");
        }
        matchEntity.setMatchDate(infoRow.getCell(metadata.matchDate()).getDateCellValue());
        matchEntity.setHomeScore((int) infoRow.getCell(metadata.homeScore()).getNumericCellValue());
        matchEntity.setAwayScore((int) infoRow.getCell(metadata.awayScore()).getNumericCellValue());
        matchEntity.setUpdateTime(new Date());
        return matchEntity;
    }

    private static Long persistMatchEntity(TCaiexMatchEntity match) {

        Long matchId;
        try {
            matchId = jdbcTemplate.queryForObject(
                    "SELECT id, home_url, away_url FROM " + T_CAIEX_MATCH +
                            " WHERE league_id = ? AND match_date = ? AND ((home_team_id = ? AND away_team_id = ?) OR (home_team_id = ? AND away_team_id = ?))",
                    new Object[]{match.getLeagueId(), match.getMatchDate(), match.getHomeTeamId(), match.getAwayTeamId(), match.getAwayTeamId(), match.getHomeTeamId()}, Long.class);
        } catch (DataAccessException e) {
            matchId = generateId(T_CAIEX_MATCH);
            match.setId(matchId);
            jdbcTemplate.update(
                    "INSERT INTO t_caiex_match (id, league_id, home_team_id, away_team_id, match_date, home_score, away_score, update_time) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    match.getId(), match.getLeagueId(), match.getHomeTeamId(), match.getAwayTeamId(), match.getMatchDate(), match.getHomeScore(), match.getAwayScore(), match.getUpdateTime());
        }
        return matchId;
    }

    private static class StatisticItem {

        public static final String ShootingAttempt = "ShootingAttempt";

        public static final String OpponentShootingAttempt = "OpponentShootingAttempt";

        public static final String ShootingOnTarget = "ShootingOnTarget";

        public static final String OpponentShootingOnTarget = "OpponentShootingOnTarget";

        public static final String Possession = "Possession";

        public static final String OpponentPossession = "OpponentPossession";
    }
}
