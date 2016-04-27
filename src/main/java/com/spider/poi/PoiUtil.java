package com.spider.poi;

import com.spider.db.entity.caiex.*;
import com.spider.poi.metadata.*;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.Date;

/**
 * Created by ronnie on 2016/4/26.
 *
 * @author ronnie
 */
public class PoiUtil {

    private static final String T_CAIEX_MATCH = "t_caiex_match";

    private static final String T_CAIEX_LEAGUE = "t_caiex_league";

    private static final String T_CAIEX_TEAM = "t_caiex_team";

    private static final String T_CAIEX_TABLE_ID = "t_caiex_table_id";

    private static final String T_CAIEX_PLAYER_BASIC_INFO = "t_caiex_player_basic_info";

    private static final String T_CAIEX_MATCH_STATISTIC = "t_caiex_match_statistic";

    public static void main(String[] args) throws IOException {

        String path = "C:\\workspace\\spider-manager\\CSL_playersheet2016.xlsm";
        executeImport(path);
    }

    private static DataSource dataSource;

    static {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:mysql://182.92.148.240:3306/crawler?useUnicode=true&characterEncoding=utf8");
        basicDataSource.setUsername("caiex");
        basicDataSource.setPassword("12345678");
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setInitialSize(100);
        dataSource = basicDataSource;
    }

    private static PlayerSheetMetadata metadata = new DefaultPlayerSheetMetadata();

    private static JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

    public static void executeImport(String path) throws IOException {

        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        String sheetName = "广州恒大";
        XSSFSheet sheet = workbook.getSheet(sheetName);
        RangeRowIndex contentRangeRowIndex = metadata.content();
        TCaiexTeamEntity currentTeamEntity = buildCurrentTeamEntity(sheet);//应在循环外
        persistTeamEntity(currentTeamEntity);
        for (int r = contentRangeRowIndex.getStartRow(); r <= contentRangeRowIndex.getEndRow(); r++) {
            XSSFRow row = sheet.getRow(r);
            TCaiexTeamEntity teamEntity = buildTeamEntity(row);
            persistTeamEntity(teamEntity);

            Map<Integer, TCaiexMatchPlayerEntity> playerEntitiesWithoutState = buildMatchPlayersWithoutState(sheet);
            List<TCaiexMatchPlayerEntity> playerEntitiesWithState = buildMatchPlayers(row, playerEntitiesWithoutState);
            List<TCaiexPlayerBasicInfoEntity> basicInfoEntities = buildPlayerBasicInfos(playerEntitiesWithState);
            persistBasicInfos(basicInfoEntities);

            TCaiexLeagueEntity leagueEntity = buildLeagueEntity(row);
            persistLeagueEntity(leagueEntity);

            TCaiexMatchEntity matchEntity = buildMatchInfo(row, currentTeamEntity);
            Long matchId = persistMatchEntity(matchEntity);

            List<TCaiexMatchStatisticEntity> statisticEntities = buildMatchStatistics(row, matchId);
            persistStatisticEntites(statisticEntities);

            setMatchId(matchId, matchEntity, playerEntitiesWithState);
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000 + "s");
    }

    private static void persistStatisticEntites(List<TCaiexMatchStatisticEntity> statisticEntities) {

        for (TCaiexMatchStatisticEntity se : statisticEntities) {
            try {
                //如果统计信息有变化，这里检测不出来，看需求吧
                jdbcTemplate.queryForObject("select match_id from " + T_CAIEX_MATCH_STATISTIC + " where match_id = ? AND item = ?",
                        new Object[]{se.getMatchId(), se.getItem()}, Long.class);
            } catch (EmptyResultDataAccessException e) {
                jdbcTemplate.update(
                        "INSERT INTO " + T_CAIEX_MATCH_STATISTIC + " (match_id, team, item, `count`, update_time) " +
                                "VALUES (?, ?, ?, ?, ?)",
                        new Object[]{se.getMatchId(), se.getTeam(), se.getItem(), se.getCount(), se.getUpdateTime()});
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
        teamEntity.setName(awayTeamName);
        teamEntity.setUpdateTime(new Date());
        return teamEntity;
    }

    private static void persistTeamEntity(TCaiexTeamEntity teamEntity) {

        try {
            jdbcTemplate.queryForObject("SELECT id FROM " + T_CAIEX_TEAM + " WHERE name = ?", new Object[]{teamEntity.getName()}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            teamEntity.setId(generateId(T_CAIEX_TEAM));
            jdbcTemplate.update(
                    "INSERT INTO " + T_CAIEX_TEAM + "(id, name, update_time) VALUES (?, ?, ?)",
                    new Object[]{teamEntity.getId(), teamEntity.getName(), teamEntity.getUpdateTime()});
            System.out.println("insert " + teamEntity.getName() + " success");
        }
    }

    private static void persistBasicInfos(List<TCaiexPlayerBasicInfoEntity> basicInfoEntities) {

        for (TCaiexPlayerBasicInfoEntity basicInfoEntity : basicInfoEntities) {
            try {
                jdbcTemplate.queryForObject(
                        "SELECT id FROM " + T_CAIEX_PLAYER_BASIC_INFO + " WHERE name = ?", new Object[]{basicInfoEntity.getName()}, Long.class);
            } catch (EmptyResultDataAccessException e) {
                jdbcTemplate.update(
                        "INSERT INTO " + T_CAIEX_PLAYER_BASIC_INFO + " (name, update_time) VALUES (?, ?)", basicInfoEntity.getName(), basicInfoEntity.getUpdateTime());
                System.out.println("insert " + basicInfoEntity.getName() + " success");
            }
        }
    }

    private static List<TCaiexPlayerBasicInfoEntity> buildPlayerBasicInfos(List<TCaiexMatchPlayerEntity> playerEntities) {

        List<TCaiexPlayerBasicInfoEntity> basicInfoEntities = new ArrayList<>(playerEntities.size());

        for (TCaiexMatchPlayerEntity playerEntity : playerEntities) {
            TCaiexPlayerBasicInfoEntity entity = new TCaiexPlayerBasicInfoEntity();
            entity.setName(playerEntity.getName());
            entity.setUpdateTime(new Date());
            basicInfoEntities.add(entity);
        }
        return basicInfoEntities;
    }

    private static Long generateId(String tableName) {

        Long currentId;
        try {
            currentId = jdbcTemplate.queryForObject("SELECT table_id FROM " + T_CAIEX_TABLE_ID + " WHERE table_name = ?", new Object[]{tableName}, Long.class);
            if (currentId != null) {
                currentId += 1;
                jdbcTemplate.update("UPDATE " + T_CAIEX_TABLE_ID + " SET table_id = ? WHERE table_name = ?", new Object[]{currentId, tableName});
                System.out.println("update " + tableName + " id success");
                return currentId;
            }
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalStateException("尚无" + tableName + "的id信息");
        }
        return currentId;
    }

    private static void setMatchId(long matchId, TCaiexMatchEntity matchEntity, List<TCaiexMatchPlayerEntity> playerEntities) {

//        matchEntity.setId(matchId);
//        statisticEntity.setMatchId(matchId);
//        for (TCaiexMatchPlayerEntity playerEntity : playerEntities) {
//            playerEntity.setMatchId(matchId);
//        }
    }

    private static Map<Integer, TCaiexMatchPlayerEntity> buildMatchPlayersWithoutState(XSSFSheet sheet) {

        Map<Integer, TCaiexMatchPlayerEntity> result = new HashMap<>();
        RangeColumnRowIndex playerIndex = metadata.playerIndex();

        List<XSSFRow> playerRows = new ArrayList<>();
        int startRow = playerIndex.getStartRow();
        int endRow = playerIndex.getEndRow();
        for (int row = startRow; row <= endRow; row++) {//理论上只有三行
            playerRows.add(sheet.getRow(row));
        }
        for (int col = playerIndex.getStartColumn(); col <= playerIndex.getEndColumn(); col++) {
            TCaiexMatchPlayerEntity playerEntity = new TCaiexMatchPlayerEntity();
            playerEntity.setPosition(sheet.getRow(startRow).getCell(col).getStringCellValue());
            playerEntity.setNumber((int) sheet.getRow(startRow + 1).getCell(col).getNumericCellValue());
            playerEntity.setName(sheet.getRow(endRow).getCell(col).getStringCellValue());
            result.put(col, playerEntity);
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
        shootingAttempt.setTeam(1);
        shootingAttempt.setUpdateTime(new Date());
        statisticEntities.add(shootingAttempt);

        TCaiexMatchStatisticEntity opponentShootingAttempt = new TCaiexMatchStatisticEntity();
        opponentShootingAttempt.setItem(StatisticItem.OpponentShootingAttempt);
        opponentShootingAttempt.setMatchId(matchId);
        opponentShootingAttempt.setCount((int) row.getCell(metadata.opponentShootingAttempt()).getNumericCellValue());
        opponentShootingAttempt.setTeam(2);
        opponentShootingAttempt.setUpdateTime(new Date());
        statisticEntities.add(opponentShootingAttempt);

        TCaiexMatchStatisticEntity shootingOnTarget = new TCaiexMatchStatisticEntity();
        shootingOnTarget.setItem(StatisticItem.ShootingOnTarget);
        shootingOnTarget.setMatchId(matchId);
        shootingOnTarget.setCount((int) row.getCell(metadata.shootingOnTarget()).getNumericCellValue());
        shootingOnTarget.setTeam(1);
        shootingOnTarget.setUpdateTime(new Date());
        statisticEntities.add(shootingOnTarget);

        TCaiexMatchStatisticEntity opponentShootingOnTarget = new TCaiexMatchStatisticEntity();
        opponentShootingOnTarget.setItem(StatisticItem.OpponentShootingOnTarget);
        opponentShootingOnTarget.setMatchId(matchId);
        opponentShootingOnTarget.setCount((int) row.getCell(metadata.opponentShootingOnTarget()).getNumericCellValue());
        opponentShootingOnTarget.setTeam(2);
        opponentShootingOnTarget.setUpdateTime(new Date());
        statisticEntities.add(opponentShootingOnTarget);

        TCaiexMatchStatisticEntity possession = new TCaiexMatchStatisticEntity();
        possession.setItem(StatisticItem.Possession);
        possession.setMatchId(matchId);
        possession.setCount((int) row.getCell(metadata.possession()).getNumericCellValue());
        possession.setTeam(1);
        possession.setUpdateTime(new Date());
        statisticEntities.add(possession);

        TCaiexMatchStatisticEntity opponentPossession = new TCaiexMatchStatisticEntity();
        opponentPossession.setItem(StatisticItem.OpponentPossession);
        opponentPossession.setMatchId(matchId);
        opponentPossession.setCount((int) row.getCell(metadata.opponentPossession()).getNumericCellValue());
        opponentPossession.setTeam(2);
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
        if (homeOrAway.equals("H")) {
            matchEntity.setHomeTeamId(opponentTeamId);
            matchEntity.setAwayTeamId(currentTeamId);
        } else if (homeOrAway.equals("A")) {
            matchEntity.setHomeTeamId(currentTeamId);
            matchEntity.setAwayTeamId(opponentTeamId);
        } else {
            throw new IllegalStateException("主客队标志位既不是H也不是A");
        }
        matchEntity.setMatchDate(infoRow.getCell(metadata.matchDate()).getDateCellValue());
        matchEntity.setHomeScore((int) infoRow.getCell(metadata.homeScore()).getNumericCellValue());
        matchEntity.setAwayScore((int) infoRow.getCell(metadata.awayScore()).getNumericCellValue());
        matchEntity.setUpdateTime(new Date());
        return matchEntity;
    }

    private static Long persistMatchEntity(TCaiexMatchEntity matchEntity) {

        Long matchId;
        try {
            matchId = jdbcTemplate.queryForObject(
                    "SELECT id FROM " + T_CAIEX_MATCH + " WHERE league_id = ? and home_team_id = ? and away_team_id = ?",
                    new Object[]{matchEntity.getLeagueId(), matchEntity.getHomeTeamId(), matchEntity.getAwayTeamId()}, Long.class);
        } catch (EmptyResultDataAccessException e) {
            matchId = generateId(T_CAIEX_MATCH);
            matchEntity.setId(matchId);
            jdbcTemplate.update(
                    "INSERT INTO t_caiex_match (id, league_id, home_team_id, away_team_id, match_date, home_score, away_score, update_time) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                    new Object[]{matchEntity.getId(), matchEntity.getLeagueId(), matchEntity.getHomeTeamId(), matchEntity.getAwayTeamId(), matchEntity.getMatchDate(), matchEntity.getHomeScore(), matchEntity.getAwayScore(), matchEntity.getUpdateTime()});
        }
        return matchId;
    }

    private static class StatisticItem {

        public static String ShootingAttempt = "ShootingAttempt";

        public static String OpponentShootingAttempt = "OpponentShootingAttempt";

        public static String ShootingOnTarget = "ShootingOnTarget";

        public static String OpponentShootingOnTarget = "OpponentShootingOnTarget";

        public static String Possession = "Possession";

        public static String OpponentPossession = "OpponentPossession";
    }
}
