package com.huacainfo.ace.generator.base;

import com.huacainfo.ace.common.tools.CommonUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class DBHelpInfo {

    /**
     * 这里是Oracle连接方法 private static final String driver =
     * "oracle.jdbc.driver.OracleDriver"; private static final String url =
     * "jdbc:oracle:thin:@localhost:1521:orcl"; private static final String uid
     * = "system"; private static final String pwd = "sys"; 这里是SQL Server连接方法
     * private static final String url =
     * "jdbc:sqlserver://localhost:1433;DateBaseName=数据库名"; private static final
     * String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver" private
     * static final String uid = "sa"; private static final String pwd = "sa";
     * <p>
     * <p>
     * 这里是MySQL连接方法
     */
    private static String driver = "";//"com.mysql.jdbc.Driver";
    //    private static String pwd = "huacai$2016";
//    private static String user = "root";
    private static String url = "";
    //        "jdbc:mysql://192.168.2.253/live" + "?user=" + user + "&password=" + pwd
//            + "&useUnicode=true&characterEncoding=UTF-8";
    private static Connection getConnection = null;

    static {
        ResourceBundle resource = ResourceBundle.getBundle("jdbc");
        driver = resource.getString("dao.db.driverClassName");
        url = resource.getString("dao.db.url");
    }

    public DBHelpInfo() {
    }

    public static void main(String[] args) {
        DBHelpInfo.getModelJs();
    }

    public static List<ColumsInfo> getTableInfo(String tableName) {


        List<ColumsInfo> list = new ArrayList<ColumsInfo>();
        getConnection = getConnections();
        try {
            DatabaseMetaData dbmd = getConnection.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                if (resultSet.getString("TABLE_NAME").equals(tableName)) {
                    ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
                    ResultSetMetaData rsmd = rs.getMetaData();
                    while (rs.next()) {
                        ColumsInfo o = new ColumsInfo();
                        o.setColumName(rs.getString("COLUMN_NAME"));
                        o.setRemarks(rs.getString("REMARKS"));
                        o.setTypeName(rs.getString("TYPE_NAME"));
                        o.setIsNullAble(rs.getString("IS_NULLABLE"));
                        o.setLen(rs.getString(7));
                        if (o.getLen() != null) {
                            if (o.getLen().equals("null")) {
                                o.setLen(null);
                            }
                        }
                        list.add(o);

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Connection getConnections() {
        try {
            Class.forName(driver).newInstance();

            getConnection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getConnection;
    }

    public static String getSchema() throws Exception {
        String schema;
        schema = getConnection.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();
    }

    public static void getModelJs() {
        List<ColumsInfo> list = DBHelpInfo.getTableInfo("personage");
        StringBuffer _colNames = new StringBuffer();
        StringBuffer _colModel = new StringBuffer("");
        _colNames.append("[");

        int i = 1;
        for (ColumsInfo o : list) {
            _colNames.append("'");
            _colNames.append(o.getRemarks());
            _colNames.append("'");
            if (i != list.size()) {
                _colNames.append(",");
            }
            _colModel.append("{\r");

            _colModel.append("name : '" + CommonUtils.getJavaName(o.getColumName()) + "',\r");
            System.out.println(_colModel.toString());
            System.out.println(i);

            boolean status = o.getColumName().equals("lastModifyUserName") || o.getColumName().equals("lastModifyDate") || o.getColumName().equals("createUserId") || o.getColumName().equals("lastModifyUserId") || o.getColumName().equals("createUserName") || o.getColumName().equals("createDate");
            if (status) {
                _colModel.append("hidden : true,\r");
                _colModel.append("editable : false,\r");
                _colModel.append("width : 100\r");
            } else {
                _colModel.append("editable : true,\r");
                _colModel.append("width : 100,\r");
            }
            //System.out.println(_colModel.toString());
            /*if(o.getTypeName().equals("date")||o.getTypeName().equals("datetime")){
                if(!status) {
					_colModel.append("edittype : \"datebox\",");
					_colModel.append("dataoptions : {\r");
					_colModel.append("    formatter : function(date) {\r");
					_colModel.append("var y = date.getFullYear();\r");
					_colModel.append("var m = date.getMonth() + 1;\r");
					_colModel.append("var d = date.getDate();\r");
					_colModel.append("return y + '-' + (m < 10 ? ('0' + m) : m) + '-'\r");
					_colModel.append(" + (d < 10 ? ('0' + d) : d);\r");
					_colModel.append("},\r");
					_colModel.append("parser : function(s) {\r");
					_colModel.append("if (!s)\r");
					_colModel.append("return new Date();\r");
					_colModel.append("var ss = (s.split('-'));\r");
					_colModel.append("var y = parseInt(ss[0], 10);\r");
					_colModel.append("var m = parseInt(ss[1], 10);\r");
					_colModel.append("var d = parseInt(ss[2], 10);\r");
					_colModel.append("if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {\r");
					_colModel.append("return new Date(y, m - 1, d);\r");
					_colModel.append("} else {\r");
					_colModel.append("return new Date();\r");
					_colModel.append("}\r");
					_colModel.append("}\r");
					_colModel.append("},\r");
				}
			}

			boolean combox=false;
			if(o.getTypeName().equals("varchar")&&o.getLen()!=null){
				if(o.getLen().equals("20")){
					_colModel.append("edittype : \"select\",");
					_colModel.append("renderer : function(value) {\r");
					_colModel.append("return rsd(value, \"01\");\r");
					_colModel.append("},\r");
					_colModel.append("editoptions : {\r");
					_colModel.append("value : odparse(\"01\")\r");
					_colModel.append("},\r");
					combox=true;
				}
			}
			if(o.getTypeName().equals("varchar")&&o.getLen()!=null){
				if(o.getLen().equals("12")){
					_colModel.append("edittype : \"combotree\",");
					_colModel.append("dataoptions : {\r");
					_colModel.append("url : portalPath + '/system/selectProvinceTreeList.do',\r");
					_colModel.append("required : false\r");
					_colModel.append("},\r");
					_colModel.append("renderer : function(value, cur) {\r");
					_colModel.append("return $.jgrid.getAccessor(cur, 'areaName');\r");
					_colModel.append("},\r");
					combox=true;
				}
			}
			if(o.getTypeName().equals("varchar")&&o.getLen()!=null){
				if(o.getLen().equals("16")){
					_colModel.append("edittype : \"combotree\",");
					_colModel.append("dataoptions : {\r");
					_colModel.append("url : portalPath + '/system/selectDepartmentTreeList.do',\r");
					_colModel.append("required : false\r");
					_colModel.append("},\r");
					_colModel.append("renderer : function(value, cur) {\r");
					_colModel.append("return $.jgrid.getAccessor(cur, 'areaName');\r");
					_colModel.append("},\r");
					combox=true;
				}
			}*/
            if (!status) {
                _colModel.append("editoptions : {\r");
                _colModel.append("size : \"20\",\r");
                _colModel.append("maxlength : \"50\"\r");
                _colModel.append("}");
            }


            if (o.getIsNullAble().equals("NO") && (!status)) {
                _colModel.append(",\rformoptions : {\r");
                _colModel.append("elmprefix : \"\",\r");
                _colModel
                        .append("elmsuffix : \"<span style='color:red;font-size:16px;font-weight:800\'>*</span>\"\r");
                _colModel.append("},");
                _colModel.append("editrules : {\r");
                _colModel.append("required : true\r");
                _colModel.append("}\r");
            }
            _colModel.append("}\r");
            if (i != list.size()) {
                _colModel.append(",");
            }
            i++;

        }
        _colNames.append(",'操作'");
        _colNames.append("]");
        System.out.println(_colNames.toString());
        System.out.println(_colModel.toString());
    }

    public static String getPreviewJs(String tableName) {
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        StringBuffer html = new StringBuffer();
        html.append("<h5 class=\"header-title\">基本信息</h5>\n");
        html.append("<div  class=\"row\"  style=\"padding:10px\">\n");
        html.append("\r\n");

        int i = 1;
        java.util.Map<String, String> p = new HashMap<String, String>();
        p.put("createUserId", "createUserId");
        p.put("createUserName", "createUserName");
        p.put("createDate", "createDate");
        p.put("lastModifyUserId", "lastModifyUserId");
        p.put("lastModifyUserName", "lastModifyUserName");
        p.put("lastModifyDate", "lastModifyDate");
        for (ColumsInfo o : list) {
            if (!p.containsValue(o.getColumName())) {
                html.append("<div class=\"labelItem\">\r\n");
                html.append("<span class=\"labelItemHeader\">").append(o.getRemarks()).append("</span>");
                html.append("\r\n").append("<br>").append("\r\n");
                html.append("<span name=\"").append(CommonUtils.getJavaName(o.getColumName())).append("\">");
                html.append("\\${").append(CommonUtils.getJavaName(o.getColumName())).append("}");
                html.append("</span>\n");
                html.append("</div>").append("\r\n");
            }
            i++;
        }
        html.append("</div>");
        html.append("\r\n");
        html.append("<h5 class=\"header-title\">操作信息</h5>");
        html.append("<div  class=\"row\"  style=\"padding:10px\">");
        html.append("\r\n");
        for (ColumsInfo o : list) {
            if (p.containsValue(o.getColumName())) {
                html.append("<div class=\"labelItem\">\n");
                html.append("<span class=\"labelItemHeader\">").append(o.getRemarks()).append("</span>");
                html.append("\r\n").append("<br>").append("\r\n");

                html.append("<span name=\"").append(CommonUtils.getJavaName(o.getColumName())).append("\">");
                html.append("\\${").append(CommonUtils.getJavaName(o.getColumName())).append("}");
                html.append("</span>").append("\r\n");
                html.append("</div>").append("\r\n");
            }
            i++;
        }
        html.append("</div>").append("\r\n");

        System.out.println(html.toString());
        return html.toString();
    }

    public static String getPreviewJs2(String tableName) {
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        StringBuffer html = new StringBuffer();

        html.append("<div class=\"profile-user-info profile-user-info-striped  profile-bg \">");
        html.append("\r\n");
        html.append("<div class=\"profile-group-title\">基本信息</div>");
        html.append("\r\n");
        html.append("<div class=\"profile-info-row\">");
        html.append("\r\n");
        int i = 1;
        for (ColumsInfo o : list) {
            html.append("<div class=\"profile-info-name\">");
            html.append("\r\n");
            html.append(o.getRemarks());
            html.append("</div>");
            html.append("\r\n");
            html.append("<div class=\"profile-info-value\">");
            html.append("\r\n");
            html.append("<span class=\"editable editable-click\" id=\"");
            html.append(CommonUtils.getJavaName(o.getColumName()));
            html.append("\">");
            html.append("\r\n");
            html.append("</span>");
            html.append("\r\n");
            html.append("</div>");
            html.append("\r\n");
            if (i % 3 == 0) {
                html.append("</div>");
                html.append("\r\n");
                html.append("<div class=\"profile-info-row\">");
                html.append("\r\n");
            }
            i++;
        }
        html.append("</div>");
        html.append("\r\n");
        html.append("</div>");
        html.append("\r\n");

        System.out.println(html.toString());
        return html.toString();
    }

}
