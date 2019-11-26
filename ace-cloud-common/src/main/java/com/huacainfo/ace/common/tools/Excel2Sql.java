package com.huacainfo.ace.common.tools;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxiaoke on 2017/6/14.
 */
public class Excel2Sql {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Excel2Sql.class);

    private static final String SRC_FILE_PATH = "/Users/chenxiaoke/Documents/data.xls";
    private static final String OUT_FILE_PATH = "/Users/chenxiaoke/Documents/sql.sql";

    public static void main(String[] args) throws Exception {
        //logger.info("start excel2sql ");
        File file = new File(SRC_FILE_PATH);
        InputStream is = new java.io.FileInputStream(file);
        WorkbookSettings workbookSettings = new WorkbookSettings();
        workbookSettings.setEncoding("GBK"); //解决中文乱码，或GBK
        Workbook workbook = Workbook.getWorkbook(is, workbookSettings);
        String[] sheetNames = workbook.getSheetNames();
        Map<String, List<String>> tables = new HashMap<String, List<String>>();
        StringBuffer sql = new StringBuffer("");
        String remark = "";
        String name = "";
        String pk = "";
        for (String sheetName : sheetNames) {
            // logger.info("reading sheet by name --> {} " ,sheetName);
            Sheet sheet = workbook.getSheet(sheetName);
            int cols = sheet.getColumns();
            int rows = sheet.getRows();
            //logger.info("max cols ->{}",cols);
            //logger.info("max rows ->{}",rows);
            for (int r = 0; r < rows; r++) {
                List<String> row = new ArrayList<String>();
                for (int c = 0; c < cols; c++) {
                    Cell cell = sheet.getCell(c, r);
                    String content = cell.getContents();
                    if (content != null && content.equals("实体名")) {
                        String cc = sheet.getCell(c + 1, r).getContents();
                        name = cc.substring(cc.indexOf("(") + 1, cc.indexOf(")"));
                        remark = cc.substring(0, cc.indexOf("("));
                        sql.append("--  Table structure for `" + name + "`\n\r");
                        //logger.info("tableName ->{} remark ->{}",name,remark);
                        sql.append("DROP TABLE IF EXISTS `" + name + "`;\n\r");
                        sql.append("CREATE TABLE `" + name + "` (\n\r");
                    }
                    row.add(content);

                }
                //logger.info("row ->{}",row);
                if (row.get(0) != null && !row.get(0).equals("实体名") && !row.get(0).equals("属性") && !row.get(0).equals("")) {
                    if (row.get(5) != null && row.get(5).equals("YES")) {
                        pk = row.get(1);
                    }
                    sql.append("`");
                    sql.append(row.get(1));
                    sql.append("`");
                    sql.append(" ");
                    sql.append(row.get(2));
                    if (!row.get(2).equals("timestamp")) {
                        if (row.get(3) != null && row.get(3).equals("N")) {
                            sql.append(" NOT NULL");
                        } else {
                            sql.append(" DEFAULT NULL");
                        }
                    } else {
                        sql.append(" NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP");
                    }

                    sql.append(" ");
                    sql.append("COMMENT");
                    sql.append(" ");
                    sql.append("'");
                    sql.append(row.get(0));
                    sql.append("'");
                    sql.append(",");
                    sql.append("\n\r");
                }
                if (row.get(0) == null || row.get(0).equals("")) {
                    sql.append("PRIMARY KEY (`");
                    sql.append(pk);
                    sql.append("`");
                    sql.append(")");
                    sql.append("\n\r");
                    sql.append(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='" + remark + "';");
                    sql.append("\n\r");
                }


            }
        }
        sql.append("PRIMARY KEY (`");
        sql.append(pk);
        sql.append("`");
        sql.append(")");
        sql.append("\n\r");
        sql.append(" ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='" + remark + "';");
        sql.append("\n\r");
        System.out.println(sql.toString());

        File fileOut = new File(OUT_FILE_PATH);
        java.io.FileOutputStream out = new java.io.FileOutputStream(fileOut);
        out.write(sql.toString().getBytes());
        out.flush();
        out.close();

    }
}
