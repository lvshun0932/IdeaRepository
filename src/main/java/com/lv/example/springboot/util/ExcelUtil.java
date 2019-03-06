package com.lv.example.springboot.util;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

/***
 *@Title ${TODO}
 *@author shunlv
 *@Date 2018/12/7 2:49 PM
 */
public class ExcelUtil {


    private ExcelUtil() {
    }

    public static void export(ExcelParam excelParam, HttpServletResponse response) throws IOException {
        if (excelParam.widths == null) {
            excelParam.widths = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.widths[i] = excelParam.width;
            }
        }
        if (excelParam.ds_format == null) {
            excelParam.ds_format = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.ds_format[i] = 1;
            }
        }
        //创建一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        int rowCount = 0;
        int rowCount2 = 0;
        //创建一个sheet
        HSSFSheet sheet = wb.createSheet("用户表");
        //创建第二一个sheet
        HSSFSheet sheet2 = wb.createSheet("说明");
        if (excelParam.headers != null) {
            HSSFRow row = sheet.createRow(rowCount);
            HSSFRow row2 = sheet2.createRow(rowCount2);
            //表头样式
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short) 11);
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            for (int i = 0; i < excelParam.headers.length; i++) {
                sheet.setColumnWidth(i, excelParam.widths[i]);
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(excelParam.headers[i]);
                cell.setCellStyle(style);
            }

            for (int i = 0; i < 3; i++) {
                sheet2.setColumnWidth(i, excelParam.widths[0]);
                HSSFCell cell = row2.createCell(i);
                cell.setCellValue("admin");
                cell.setCellStyle(style);
            }
            rowCount++;
            rowCount2++;
        }
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //表格主体  解析list
        for (int i = 0; i < excelParam.data.size(); i++) {  //行数
            HSSFRow row = sheet.createRow(rowCount);
            for (int j = 0; j < excelParam.headers.length; j++) {  //列数
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(excelParam.data.get(i)[j]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        //表2
        for (int i = 1; i < 10; i++) {  //行数
            HSSFRow row = sheet2.createRow(rowCount2);
            for (int j = 0; j < 3; j++) {  //列数
                HSSFCell cell = row.createCell(j);
                cell.setCellValue("lisi");
                cell.setCellStyle(style);
            }
            rowCount2++;
        }
        //设置文件名
        String fileName = excelParam.name + ".xls";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Pragma", "No-cache");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * @Description: 创建单个工作簿 xlsx
     * @param excelParam	表格参数
     * @param response
     * @param remark	工作簿说明
     * @throws IOException
     * 2018年6月21日 上午11:12:11
     */
    public static void exportOneBookForXlsx(ExcelParam excelParam, HttpServletResponse response, String remark) throws IOException {
        if (excelParam.widths == null) {
            excelParam.widths = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.widths[i] = excelParam.width;
            }
        }
        if (excelParam.ds_format == null) {
            excelParam.ds_format = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.ds_format[i] = 1;
            }
        }
        //创建一个工作薄
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        int rowCount = 0;
        //创建一个sheet
        SXSSFSheet sheet = wb.createSheet(remark);
        if (excelParam.headers != null) {
            SXSSFRow row = sheet.createRow(rowCount);
            //表头样式
            CellStyle style = wb.createCellStyle();
            Font font = wb.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short) 11);
            style.setFont(font);
            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

            for (int i = 0; i < excelParam.headers.length; i++) {
                sheet.setColumnWidth(i, excelParam.widths[i]);
                SXSSFCell cell = row.createCell(i);
                cell.setCellValue(excelParam.headers[i]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //表格主体  解析list
        for (int i = 0; i < excelParam.data.size(); i++) {  //行数
            SXSSFRow row = sheet.createRow(rowCount);
            for (int j = 0; j < excelParam.headers.length; j++) {  //列数
                SXSSFCell cell = row.createCell(j);
                cell.setCellValue(excelParam.data.get(i)[j]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        //设置文件名
        String fileName = excelParam.name + ".xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Pragma", "No-cache");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    /**
     * @Description: 创建单个工作簿 xls
     * @param excelParam	表格参数
     * @param response
     * @param remark	工作簿说明
     * @throws IOException
     * 2018年6月21日 上午11:12:11
     */
    public static void exportOneBook(ExcelParam excelParam, HttpServletResponse response, String remark) throws IOException {
        if (excelParam.widths == null) {
            excelParam.widths = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.widths[i] = excelParam.width;
            }
        }
        if (excelParam.ds_format == null) {
            excelParam.ds_format = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.ds_format[i] = 1;
            }
        }
        //创建一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        int rowCount = 0;
        //创建一个sheet
        HSSFSheet sheet = wb.createSheet(remark);
        if (excelParam.headers != null) {
            HSSFRow row = sheet.createRow(rowCount);
            //表头样式
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short) 11);
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            for (int i = 0; i < excelParam.headers.length; i++) {
                sheet.setColumnWidth(i, excelParam.widths[i]);
                HSSFCell cell = row.createCell(i);
                cell.setCellValue(excelParam.headers[i]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //表格主体  解析list
        for (int i = 0; i < excelParam.data.size(); i++) {  //行数
            HSSFRow row = sheet.createRow(rowCount);
            for (int j = 0; j < excelParam.headers.length; j++) {  //列数
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(excelParam.data.get(i)[j]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        //设置文件名
        String fileName = excelParam.name + ".xlsx";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Pragma", "No-cache");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * @Description: 创建单个工作簿 xlsx 存到指定位置
     * @param excelParam	表格参数
     * @param remark	工作簿说明
     * @throws IOException
     * 2018年6月21日 上午11:12:11
     */
    public static void createOneBookForXlsx(ExcelParam excelParam, String remark,String FileDir) throws IOException {
        try {
            if (excelParam.widths == null) {
                excelParam.widths = new int[excelParam.headers.length];
                for (int i = 0; i < excelParam.headers.length; i++) {
                    excelParam.widths[i] = excelParam.width;
                }
            }
            if (excelParam.ds_format == null) {
                excelParam.ds_format = new int[excelParam.headers.length];
                for (int i = 0; i < excelParam.headers.length; i++) {
                    excelParam.ds_format[i] = 1;
                }
            }
            //创建一个工作薄
            SXSSFWorkbook wb = new SXSSFWorkbook(100);
            int rowCount = 0;
            //创建一个sheet
            SXSSFSheet sheet = wb.createSheet(remark);
            if (excelParam.headers != null) {
                SXSSFRow row = sheet.createRow(rowCount);
                //表头样式
                CellStyle style = wb.createCellStyle();
                Font font = wb.createFont();
                font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
                font.setFontHeightInPoints((short) 11);
                style.setFont(font);
                style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

                for (int i = 0; i < excelParam.headers.length; i++) {
                    sheet.setColumnWidth(i, excelParam.widths[i]);
                    SXSSFCell cell = row.createCell(i);
                    cell.setCellValue(excelParam.headers[i]);
                    cell.setCellStyle(style);
                }
                rowCount++;
            }
            CellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //表格主体  解析list
            for (int i = 0; i < excelParam.data.size(); i++) {  //行数
                SXSSFRow row = sheet.createRow(rowCount);
                for (int j = 0; j < excelParam.headers.length; j++) {  //列数
                    SXSSFCell cell = row.createCell(j);
                    cell.setCellValue(excelParam.data.get(i)[j]);
                    cell.setCellStyle(style);
                }
                rowCount++;
            }
            //设置文件名
            String fileName = excelParam.name + ".xlsx";

            //将Excel文件放到本地路径下
            File file = new File(FileDir + fileName);
            File fileParent = file.getParentFile();
            if(!fileParent.exists()){
                fileParent.mkdirs();
            }
            file.createNewFile();
            FileOutputStream os = new FileOutputStream(FileDir + fileName);
            wb.write(os);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    /**
     * @Description: 创建单个工作簿 xls
     * @param excelParam	表格参数
     * @param response
     * @param remark	工作簿说明  导出对账报表专用
     * @throws IOException
     * 2018年6月21日 上午11:12:11
     */
    public static void exportAcountBook(ExcelParam excelParam, HttpServletResponse response, String remark,String[]
    array) throws
    IOException {
        if (excelParam.widths == null) {
            excelParam.widths = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.widths[i] = excelParam.width;
            }
        }
        if (excelParam.ds_format == null) {
            excelParam.ds_format = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.ds_format[i] = 1;
            }
        }
        //创建一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        int rowCount = 0;
        //创建一个sheet
        HSSFSheet sheet = wb.createSheet(remark);
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,3));
        sheet.addMergedRegion(new CellRangeAddress(0,0,4,6));
        sheet.addMergedRegion(new CellRangeAddress(0,0,7,9));
        sheet.addMergedRegion(new CellRangeAddress(0,0,10,12));
        sheet.addMergedRegion(new CellRangeAddress(0,0,13,16));
        sheet.addMergedRegion(new CellRangeAddress(0,0,17,20));
        sheet.addMergedRegion(new CellRangeAddress(0,0,21,24));
        HSSFRow row1 = sheet.createRow(0);//第一行数据
        if (excelParam.headers != null) {
            HSSFRow row = sheet.createRow(rowCount);
            //表头样式
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short) 12);
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //设置边框
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            style.setWrapText(true);//设置自动换行
            int[] intArray = {0,1,4,7,10,13,17,21};
            int m =0;
            for (int i : intArray) {
                HSSFCell cell = row.createCell(i);
                sheet.setColumnWidth(i, excelParam.widths[i]);
                cell.setCellValue(array[m]);
                cell.setCellStyle(style);
                m++;
            }
            rowCount++;
            HSSFRow row2 = sheet.createRow(rowCount);
            for (int i = 0; i < excelParam.headers.length; i++) {
                if(i==0){
                    continue;
                }
                sheet.setColumnWidth(i, excelParam.widths[i]);
                HSSFCell cell = row2.createCell(i);
                cell.setCellValue(excelParam.headers[i]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //表格主体  解析list
        //设置边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        for (int i = 0; i < excelParam.data.size(); i++) {  //行数
            HSSFRow row = sheet.createRow(rowCount);
            for (int j = 0; j < excelParam.headers.length; j++) {  //列数
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(excelParam.data.get(i)[j]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        //设置文件名
        //String fileName = excelParam.name + ".xlsx";
        String fileName = excelParam.name + ".xls";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Pragma", "No-cache");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
    /**
     * @Description: 创建单个工作簿 xls
     * @param excelParam	表格参数
     * @param response
     * @param remark	工作簿说明  导出对账报表专用
     * @throws IOException
     * 2018年6月21日 上午11:12:11
     */
    public static void exportFranchiserBook(ExcelParam excelParam, HttpServletResponse response, String remark,
            String[] array) throws
    IOException {
        if (excelParam.widths == null) {
            excelParam.widths = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.widths[i] = excelParam.width;
            }
        }
        if (excelParam.ds_format == null) {
            excelParam.ds_format = new int[excelParam.headers.length];
            for (int i = 0; i < excelParam.headers.length; i++) {
                excelParam.ds_format[i] = 1;
            }
        }
        //创建一个工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        int rowCount = 0;
        //创建一个sheet
        HSSFSheet sheet = wb.createSheet(remark);
        sheet.addMergedRegion(new CellRangeAddress(0,1,0,0));
        sheet.addMergedRegion(new CellRangeAddress(0,1,1,1));
        sheet.addMergedRegion(new CellRangeAddress(0,1,2,2));
        sheet.addMergedRegion(new CellRangeAddress(0,0,3,5));
        sheet.addMergedRegion(new CellRangeAddress(0,1,6,6));
        sheet.addMergedRegion(new CellRangeAddress(0,1,7,7));
        sheet.addMergedRegion(new CellRangeAddress(0,0,8,10));
//		sheet.addMergedRegion(new CellRangeAddress(0,0,10,10));
        HSSFRow row1 = sheet.createRow(0);//第一行数据
        if (excelParam.headers != null) {
            HSSFRow row = sheet.createRow(rowCount);
            //表头样式
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setFontHeightInPoints((short) 12);
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            //设置边框
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
            style.setWrapText(true);//设置自动换行
            int[] intArray = {0,1,2,3,6,7,8,10};
            int m =0;
            for (int i : intArray) {
                HSSFCell cell = row.createCell(i);
                sheet.setColumnWidth(i, excelParam.widths[i]);
                cell.setCellValue(array[m]);
                cell.setCellStyle(style);
                m++;
            }
            rowCount++;
            HSSFRow row2 = sheet.createRow(rowCount);
            for (int i = 0; i < excelParam.headers.length; i++) {
                if(i==0){
                    continue;
                }
                sheet.setColumnWidth(i, excelParam.widths[i]);
                HSSFCell cell = row2.createCell(i);
                cell.setCellValue(excelParam.headers[i]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //表格主体  解析list
        //设置边框
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        for (int i = 0; i < excelParam.data.size(); i++) {  //行数
            HSSFRow row = sheet.createRow(rowCount);
            for (int j = 0; j < excelParam.headers.length; j++) {  //列数
                HSSFCell cell = row.createCell(j);
                cell.setCellValue(excelParam.data.get(i)[j]);
                cell.setCellStyle(style);
            }
            rowCount++;
        }
        //设置文件名
        //String fileName = excelParam.name + ".xlsx";
        String fileName = excelParam.name + ".xls";
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        response.setHeader("Pragma", "No-cache");
        OutputStream outputStream = response.getOutputStream();
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


}
