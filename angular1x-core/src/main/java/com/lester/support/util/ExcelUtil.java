package com.lester.support.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lesterhsu on 2017/6/16.
 */
public class ExcelUtil {

    private static final Logger log = Logger.getLogger(ExcelUtil.class);

    public static File bildExcell(Map<String, List<Map<String, String>>> data, String filePath) throws Exception {
        return bildExcell(data, filePath, null, DateUtil.toString(DateUtil.FORMAT_DATETIME_2, new Date()));
    }

    public static File bildExcell(Map<String, List<Map<String, String>>> data, String filePath, String sPassword) throws Exception {
        return bildExcell(data, filePath, sPassword, DateUtil.toString(DateUtil.FORMAT_DATE_YYYYMMDD, new Date()));
    }

    public static File bildExcell(Map<String, List<Map<String, String>>> data, String filePath, String sPassword, String fileName) throws Exception {
        // 資料格式 {sheetName , {List<Map<Col, data>>}}
        HSSFWorkbook workbook = new HSSFWorkbook();//建立一個Excel活頁簿

        for (String sheetName : data.keySet()) {
            buildSheet(data.get(sheetName), workbook, sheetName);
        }


        // 檔名
        //String f = DateUtil.toString(DateUtil.yyyyMMdd, new Date());
        fileName += ".xls";

        // 路徑
        //String filePath = System.getProperty("catalina.base");
        //String filePath = env.getProperty("filePath");
        File file = new File(filePath + fileName);
        log.info("暫存檔路徑：" + file.getAbsolutePath());

        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        FileOutputStream fOut = new FileOutputStream(file);
        workbook.write(fOut);
        fOut.flush();
        fOut.close();
        if (StringUtils.isNotBlank(sPassword)) {
            POIFSFileSystem fs = new POIFSFileSystem();
            EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile);
            // EncryptionInfo info = new EncryptionInfo(EncryptionMode.agile, CipherAlgorithm.aes192, HashAlgorithm.sha384, -1, -1, null);

            Encryptor enc = info.getEncryptor();
            enc.confirmPassword(sPassword);

            OPCPackage opc = OPCPackage.open(new File(file.toString()), PackageAccess.READ_WRITE);
            OutputStream os = enc.getDataStream(fs);
            opc.save(os);
            opc.close();

            FileOutputStream fos = new FileOutputStream(file.toString());
            fs.writeFilesystem(fos);
            fos.close();
        }
        return file;
    }

    private static void buildSheet(List<Map<String, String>> sheetData, HSSFWorkbook workbook, String sheetName) throws Exception {

        HSSFSheet sheet = workbook.createSheet(sheetName); //在活頁簿中建立一個Sheet

        sheet.autoSizeColumn(0);

        if (sheetData.size() == 0) {
            HSSFRow rowCount = sheet.createRow(0);
            HSSFCell cell = rowCount.createCell(0);
            cell.setCellValue("查無資料");
        } else {
            // 設定表頭
            HSSFRow rowHead = sheet.createRow(0);
            int i = 0;
            for (String head : sheetData.get(0).keySet()) {
                HSSFCell cell = rowHead.createCell(i);
                cell.setCellValue(head);
                // 設定表頭樣式
                setHedStyle(workbook, cell);
                i++;
            }

            // 設定內容
            int rowIdx = 1;
            for (Map<String, String> data : sheetData) {
                HSSFRow rowCount = sheet.createRow(rowIdx);
                int cellIdx = 0;
                for (String head : data.keySet()) {
                    HSSFCell cell = rowCount.createCell(cellIdx);
                    cell.setCellValue(data.get(head));
                    cellIdx++;
                }
                sheet.autoSizeColumn(rowIdx);
                rowIdx++;
            }
        }

    }

    /*private static void buildSheet(List<Map<String, String>> sheetData, XSSFWorkbook workbook, String sheetName) throws Exception {

        XSSFSheet sheet = workbook.createSheet(sheetName); //在活頁簿中建立一個Sheet

        sheet.autoSizeColumn(0);

        if (sheetData.size() == 0) {
            XSSFRow rowCount = sheet.createRow(0);
            XSSFCell cell = rowCount.createCell(0);
            cell.setCellValue("查無資料");
        } else {
            // 設定表頭
            XSSFRow rowHead = sheet.createRow(0);
            int i = 0;
            for (String head: sheetData.get(0).keySet()) {
                XSSFCell cell = rowHead.createCell(i);
                cell.setCellValue(head);
                // 設定表頭樣式
                setHedStyle(workbook, cell);
                i++;
            }

            // 設定內容
            int rowIdx = 1;
            for (Map<String, String> data : sheetData) {
                XSSFRow rowCount = sheet.createRow(rowIdx);
                int cellIdx = 0;
                for (String head : data.keySet()) {
                    XSSFCell cell = rowCount.createCell(cellIdx);
                    cell.setCellValue(data.get(head));
                    cellIdx++;
                }
                sheet.autoSizeColumn(rowIdx);
                rowIdx++;
            }
        }

    }*/

    private static void setHedStyle(Workbook workbook, Cell cell) {
        // 設定儲存格格式
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.YELLOW.index);//填滿顏色
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);//水平置中
        style.setVerticalAlignment(VerticalAlignment.CENTER);//垂直置中
        // 設定字型
        Font font = workbook.createFont();
        //font.setColor(HSSFColor.BLACK.index);//顏色
        //font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setBold(true);//粗體
        style.setFont(font);
        // 設定框線
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setWrapText(true);//自動換行

        cell.setCellStyle(style);//將格式套用格式到指定的cell中
    }

}
