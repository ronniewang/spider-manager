package com.spider.poi;

import com.spider.db.entity.caiex.TCaiexMatchEntity;
import com.spider.db.entity.caiex.TCaiexMatchPlayerEntity;
import com.spider.db.entity.caiex.TCaiexMatchStatisticEntity;
import com.spider.poi.metadata.DefaultPlayerSheetMetadata;
import com.spider.poi.metadata.PlayerSheetMetadata;
import com.spider.poi.metadata.RangeColumnRowIndex;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ronnie on 2016/4/26.
 *
 * @author ronnie
 */
public class PoiUtil {

    public static void main(String[] args) throws IOException {

        long start = System.currentTimeMillis();
        PlayerSheetMetadata metadata = new DefaultPlayerSheetMetadata();
        FileInputStream fileInputStream = new FileInputStream("C:\\workspace\\spider-manager\\CSL_playersheet2016.xlsm");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("广州恒大");
        XSSFRow row = sheet.getRow(26);
        long end = System.currentTimeMillis();
        XSSFCell cell = row.getCell(36);
        System.out.println(cell);
        Map<Integer, TCaiexMatchPlayerEntity> playerEntitiesWithoutState = buildMatchPlayersWithoutState(sheet, metadata);
        System.out.println(cell.getCellComment().getString());
        TCaiexMatchEntity matchEntity = buildMatchInfo(row, metadata);
        TCaiexMatchStatisticEntity statisticEntity = buildMatchStatistic(row, metadata);
        List<TCaiexMatchPlayerEntity> playerEntities = buildMatchPlayers(row, playerEntitiesWithoutState);
        long matchId = generateMatchId();
        setMatchId(matchId, matchEntity, statisticEntity, playerEntities);
        System.out.println((end - start) / 1000 + "s");
    }

    private static long generateMatchId() {

        //import
        return 0;
    }

    private static void setMatchId(long matchId, TCaiexMatchEntity matchEntity, TCaiexMatchStatisticEntity statisticEntity, List<TCaiexMatchPlayerEntity> playerEntities) {

        matchEntity.setId(matchId);
        statisticEntity.setMatchId(matchId);
        for (TCaiexMatchPlayerEntity playerEntity : playerEntities) {
            playerEntity.setMatchId(matchId);
        }
    }

    private static Map<Integer, TCaiexMatchPlayerEntity> buildMatchPlayersWithoutState(XSSFSheet sheet, PlayerSheetMetadata metadata) {

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

    private static TCaiexMatchStatisticEntity buildMatchStatistic(XSSFRow row, PlayerSheetMetadata metadata) {

        //import
        TCaiexMatchStatisticEntity statisticEntity = new TCaiexMatchStatisticEntity();
        return null;
    }

    private static TCaiexMatchEntity buildMatchInfo(XSSFRow row, PlayerSheetMetadata metadata) {

        //import
        return null;
    }
}
