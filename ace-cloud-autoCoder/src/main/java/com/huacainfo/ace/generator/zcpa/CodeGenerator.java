/**
 * @Title: BeanUtils.java
 * @Package org.platform.snail.utils
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:15:42
 * @version V1.0
 */

package com.huacainfo.ace.generator.zcpa;

/**
 * @ClassName: BeanUtils
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:15:42
 */

/**
 *
 */

import com.huacainfo.ace.common.tools.CommonUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CodeGenerator {
    private static Bean bean = new Bean();
    private static Annotation annotation = new Annotation();
    Logger logger = (Logger) LoggerFactory.getLogger(CodeGenerator.class);
    private String appPath;
    private String cfg;
    private String name;
    private String author;


    public CodeGenerator(String cfg, String name, String author) {
        this.cfg = cfg;
        this.name = name;
        this.author = author;
        this.appPath = System.getProperty("user.dir");

        if (this.getProperty("local").equals("true")) {
            String userDir = System.getProperty("user.dir");
            logger.info(userDir);
            this.appPath = userDir + this.getProperty("appPath");


        }
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    private static String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 创建bean的Dao<br>
     *
     * @param c
     * @throws Exception
     */

    /**
     * 初始化bean和注解
     *
     * @param c
     */
    public void init(Class c) {
        if (c != null) {
            String cName = c.getName();
            bean.setName(getLastChar(cName));
            bean.setTableChineseName(name);
            bean.setLowerName(getLowercaseChar(getLastChar(cName)));
            annotation.setAuthorName(this.author);
            annotation.setAuthorMail("249134995@qq.com");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            annotation.setDate(simpleDateFormat.format(new Date()));
            annotation.setVersion(simpleDateFormat.format(new Date()));
        }
    }

    /**
     * 创建bean的Dao的实现<br>
     *
     * @param c
     * @throws Exception
     */
    public void generatorJavaController(Class c) throws Exception {
        String cName = c.getName();
        String path = appPath + "/src/main/java/" + this.getProperty("package.controller") + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + getLastChar(cName) + "Controller.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(generatorCode(this.getProperty("java.controller.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    public void generatorJavaVO(Class c) throws Exception {
        String cName = c.getName();
        String path = appPath + "/src/main/java/" + this.getProperty("package.vo") + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Vo.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);

        fw.write(generatorCode(this.getProperty("java.vo.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    public void generatorJavaQVO(Class c) throws Exception {
        String cName = c.getName();
        String path = appPath + "/src/main/java/" + this.getProperty("package.vo") + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "QVo.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);


        fw.write(generatorCode(this.getProperty("java.qvo.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    public void generatorJavaDAO(Class c) throws Exception {
        String cName = c.getName();
        String path = appPath + "/src/main/java/" + this.getProperty("package.dao") + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Dao.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);


        fw.write(generatorCode(this.getProperty("java.dao.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    /**
     * 创建bean的Service<br>
     *
     * @param c
     * @throws Exception
     */
    public void generatorJavaService(Class c) throws Exception {
        String cName = c.getName();
        String path = appPath + "/src/main/java/" + this.getProperty("package.service") + "/";
        File filePath = new File(path);
        createFilePath(filePath);

        String fileName = path + getLastChar(cName) + "Service.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);


        fw.write(generatorCode(this.getProperty("java.service.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    /**
     * 创建bean的Service的实现<br>
     *
     * @param c
     * @throws Exception
     */
    public void generatorJavaServiceImpl(Class c) throws Exception {
        StringBuffer validate = new StringBuffer();
        String cName = c.getName();
        String path = appPath + "/src/main/java/" + this.getProperty("package.service.impl") + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + getLastChar(cName) + "ServiceImpl.java";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        //logger.info(CommonUtils.propertyToField(c.getSimpleName()));
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        for (ColumsInfo o : list) {
            if (o.getIsNullAble().equals("NO") && (!o.getColumName().equals("createUserId")) && (!o.getColumName().equals("createUserName")) && (!o.getColumName().equals("createDate"))) {
                validate.append("if (CommonUtils.isBlank(o.get"
                        + CommonUtils.firstToUpper(CommonUtils.getJavaName(o
                        .getColumName())) + "())) {\r");
                validate.append("return new ResponseDTO(ResultCode.FAIL, \""
                        + o.getRemarks() + "不能为空！\");\r");
                validate.append("}\r");
            }

        }
        bean.setValidate(validate.toString());
        fw.write(generatorCode(this.getProperty("java.serviceimpl.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    public void generatorJavaJqgrid(Class c) throws Exception {
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        String path = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "index.jsp";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(generatorCode(this.getProperty("jsp.jqgrid.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    public void generatorJsConfig(Class c) throws Exception {
        String path = appPath + "/src/main/webapp/content/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "config.js";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(generatorCode(this.getProperty("js.config.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    public void generatorJsView(Class c) throws Exception {
        String path = appPath + "/src/main/webapp/content/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "view.js";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(generatorCode(this.getProperty("js.view.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    public void generatorJsModel(Class c) throws Exception {
        String path = appPath + "/src/main/webapp/content/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "model.js";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        StringBuffer _colNames = new StringBuffer();
        StringBuffer _colModel = new StringBuffer();
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
            _colModel.append("name : '"
                    + CommonUtils.getJavaName(o.getColumName()) + "',\r");
            boolean status = o.getColumName().equals("lastModifyUserName") || o.getColumName().equals("lastModifyDate") || o.getColumName().equals("createUserId") || o.getColumName().equals("lastModifyUserId") || o.getColumName().equals("createUserName") || o.getColumName().equals("createDate");
            if (status) {
                _colModel.append("hidden : true,\r");
                _colModel.append("editable : false,\r");
                _colModel.append("width : 100\r");
            } else {
                _colModel.append("editable : true,\r");
                _colModel.append("width : 100,\r");
            }
            if (o.getTypeName().equals("date") || o.getTypeName().equals("datetime")) {
                if (!status) {
                    _colModel.append("edittype : \"datebox\",\r");
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

            boolean combox = false;
            if (o.getTypeName().equals("varchar") && o.getLen() != null) {
                if (o.getLen().equals("20")) {
                    _colModel.append("edittype : \"select\",\r");
                    _colModel.append("renderer : function(value) {\r");
                    _colModel.append("return rsd(value, \"01\");\r");
                    _colModel.append("},\r");
                    _colModel.append("editoptions : {\r");
                    _colModel.append("value : odparse(\"01\")\r");
                    _colModel.append("},\r");
                    combox = true;
                }
            }
            if (o.getTypeName().equals("varchar") && o.getLen() != null) {
                if (o.getLen().equals("12")) {
                    _colModel.append("edittype : \"combotree\",\r");
                    _colModel.append("dataoptions : {\r");
                    _colModel.append("url : portalPath + '/system/selectProvinceTreeList.do',\r");
                    _colModel.append("required : false\r");
                    _colModel.append("},\r");
                    _colModel.append("renderer : function(value, cur) {\r");
                    _colModel.append("return $.jgrid.getAccessor(cur, 'areaName');\r");
                    _colModel.append("},\r");
                    combox = true;
                }
            }
            if (o.getTypeName().equals("varchar") && o.getLen() != null) {
                if (o.getLen().equals("16")) {
                    _colModel.append("edittype : \"combotree\",\r");
                    _colModel.append("dataoptions : {\r");
                    _colModel.append("url : portalPath + '/system/selectDepartmentTreeList.do',\r");
                    _colModel.append("required : false\r");
                    _colModel.append("},\r");
                    _colModel.append("renderer : function(value, cur) {\r");
                    _colModel.append("return $.jgrid.getAccessor(cur, 'areaName');\r");
                    _colModel.append("},\r");
                    combox = true;
                }
            }
            if (o.getColumName().equals("status")) {
                _colModel.append("edittype : \"checkbox\",\r");
                _colModel.append("editoptions : {\r");
                _colModel.append("value : \"1:0\"\r");
                _colModel.append("},\r");
                _colModel.append("cellattr : function(rowId, val, rawObject, cm, rdata) {\r");
                _colModel.append("if (rawObject.status == '0') {\r");
                _colModel.append("return \"style='color:red;'\";\r");
                _colModel.append("}\r");
                _colModel.append("if (rawObject.status == '1') {\r");
                _colModel.append("return \"style='color:#FF9224;'\";\r");
                _colModel.append("}\r");
                _colModel.append("if (rawObject.status == '2') {\r");
                _colModel.append("return \"style='color:green;'\";\r");
                _colModel.append("}\r");
                _colModel.append("if (rawObject.status == '3') {\r");
                _colModel.append("return \"style='color:#F9F900;'\";\r");
                _colModel.append("}\r");
                _colModel.append("if (rawObject.status == '4') {\r");
                _colModel.append("return \"style='color:#FF9224;'\";\r");
                _colModel.append("}\r");
                _colModel.append("},\r");
                _colModel.append("unformat : aceSwitch,\r");
                _colModel.append("renderer : function(value) {\r");
                _colModel.append("var rst = \"\";\r");
                _colModel.append("switch (value) {\r");
                _colModel.append("case '1' :\r");
                _colModel.append("rst = \"ON\";\r");
                _colModel.append("break;\r");
                _colModel.append("case '0' :\r");
                _colModel.append("rst = \"OFF\";\r");
                _colModel.append("break;\r");
                _colModel.append("default :\r");
                _colModel.append("rst = \"N/A\";\r");
                _colModel.append("}\r");
                _colModel.append("return rst;\r");
                _colModel.append("},\r");
                combox = true;
            }
            if (!status && (!combox)) {
                _colModel.append("editoptions : {\r");
                _colModel.append("size : \"20\",\r");
                _colModel.append("maxlength : \"50\"\r");
                _colModel.append("},");
            }


            if (o.getIsNullAble().equals("NO") && (!status)) {
                _colModel.append("\rformoptions : {\r");
                _colModel.append("elmprefix : \"\",\r");
                _colModel
                        .append("elmsuffix : \"<span style='color:red;\'>*</span>\"\r");
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
        bean.setColumModel(_colModel.toString());
        bean.setColumName(_colNames.toString());
        fw.write(generatorCode(this.getProperty("js.model.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    public void generatorJsController(Class c) throws Exception {
        String path = appPath + "/src/main/webapp/content/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePath = new File(path);
        createFilePath(filePath);
        String fileName = path + "controller.js";
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        fw.write(generatorCode(this.getProperty("js.controller.vm"), bean, annotation));
        fw.flush();
        fw.close();
        logs(fileName);
    }

    private String lowerCase(String str) {
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1)
                .toLowerCase());
    }

    /**
     * 根据模板生成代码
     *
     * @param fileVMPath 模板路径
     * @param bean       目标bean
     * @param annotation 注释
     * @return
     * @throws Exception
     */
    private String generatorCode(String fileVMPath, Bean bean, Annotation annotation)
            throws Exception {
        //fileVMPath=this.serverws+"/ace-generator/"+fileVMPath;
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(fileVMPath);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("bean", bean);
        velocityContext.put("annotation", annotation);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    private String generatorCode(String fileVMPath, Bean bean, Annotation annotation, List<ColumsInfo> list)
            throws Exception {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("input.encoding", "UTF-8");
        velocityEngine.setProperty("output.encoding", "UTF-8");
        velocityEngine.setProperty("resource.loader", "class");
        velocityEngine.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(fileVMPath);
        VelocityContext velocityContext = new VelocityContext();
        velocityContext.put("bean", bean);
        velocityContext.put("annotation", annotation);
        velocityContext.put("list", list);
        StringWriter stringWriter = new StringWriter();
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }

    /**
     * 创建文件
     *
     * @param file
     */
    private void createFilePath(File file) {
        if (!file.exists()) {
            logger.info("create dir " + file.getAbsolutePath() + " " + file.mkdirs());
        } else {
            logger.info("already exist dir " + file.getAbsolutePath());
        }
    }

    /**
     * 获取路径的最后面字符串<br>
     * 如：<br>
     * <code>str = "com.b510.zcpa.bean.User"</code><br>
     * <code> return "User";<code>
     *
     * @param str
     * @return
     */
    private String getLastChar(String str) {
        if ((str != null) && (str.length() > 0)) {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1))) {
                return str.substring(dot + 1);
            }
        }
        return str;
    }

    /**
     * 把第一个字母变为小写<br>
     * 如：<br>
     * <code>str = "UserDao";</code><br>
     * <code>return "userDao";</code>
     *
     * @param str
     * @return
     */
    private String getLowercaseChar(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 显示信息
     *
     * @param
     */
    private void logs(String log) {
        logger.info("generator " + log + " ok");
    }

    private String getProperty(String key) {
        ResourceBundle resource = ResourceBundle.getBundle(this.cfg);
        return resource.getString(key);
    }


    public void generatorIndexDir(Class c) throws Exception {
        /**创建根目录**/
        String pathRoot = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePathRoot = new File(pathRoot);
        createFilePath(filePathRoot);


        /**创建JSP**/
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        List<ColumsInfo> list = DBHelpInfo.getTableInfo(tableName);
        String pathJsp = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/";
        File filePathJsp = new File(pathJsp);
        createFilePath(filePathJsp);
        String fileNameJsp = pathJsp + "index.jsp";
        File fileJsp = new File(fileNameJsp);
        FileWriter fwJsp = new FileWriter(fileJsp);
        fwJsp.write(generatorCode(this.getProperty("jsp.index.vm"), bean, annotation, list));
        fwJsp.flush();
        fwJsp.close();
        logs(fileNameJsp);

        /**创建CSS目录**/
        String pathCss = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/css/";
        File filePathCss = new File(pathCss);
        createFilePath(filePathCss);
        String fileNameCss = pathCss + "style.css";
        File fileCss = new File(fileNameCss);
        FileWriter fwCss = new FileWriter(fileCss);
        fwCss.write("");
        fwCss.flush();
        fwCss.close();
        logs(fileNameCss);

        /**创建IMG目录**/
        String pathImg = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/img/";
        File filePathImg = new File(pathImg);
        createFilePath(filePathImg);


        /**创建JS目录**/
        String pathJs = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/js/";
        File filePathJs = new File(pathJs);
        createFilePath(filePathJs);
        String fileNameJs = pathJs + "act.js";
        File fileJs = new File(fileNameJs);
        FileWriter fwJs = new FileWriter(fileJs);
        fwJs.write(generatorCode(this.getProperty("js.index.vm"), bean, annotation, list));
        fwJs.flush();
        fwJs.close();
        logs(fileNameJs);

    }

    public void generatorAddDir(Class c) throws Exception {
        /**创建根目录**/
        String pathRoot = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/add/";
        File filePathRoot = new File(pathRoot);
        createFilePath(filePathRoot);


        /**创建JSP**/
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        List<ColumsInfo> items = DBHelpInfo.getTableInfo(tableName);
        List<ColumsInfo> list = new ArrayList<ColumsInfo>();
        for (ColumsInfo o : items) {
            if (o.getColumName().equals("id") || o.getColumName().equals("status") || o.getColumName().equals("createUserId") || o.getColumName().equals("createUserName") || o.getColumName().equals("lastModifyUserId") || o.getColumName().equals("lastModifyUserName") || o.getColumName().equals("lastModifyDate") || o.getColumName().equals("createDate")) {

            } else {
                list.add(o);
            }
        }
        String pathJsp = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/add/";
        File filePathJsp = new File(pathJsp);
        createFilePath(filePathJsp);
        String fileNameJsp = pathJsp + "index.jsp";
        File fileJsp = new File(fileNameJsp);
        FileWriter fwJsp = new FileWriter(fileJsp);
        fwJsp.write(generatorCode(this.getProperty("jsp.add.vm"), bean, annotation, list));
        fwJsp.flush();
        fwJsp.close();
        logs(fileNameJsp);

        /**创建CSS目录**/
        String pathCss = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/add/css/";
        File filePathCss = new File(pathCss);
        createFilePath(filePathCss);
        String fileNameCss = pathCss + "style.css";
        File fileCss = new File(fileNameCss);
        FileWriter fwCss = new FileWriter(fileCss);
        fwCss.write("");
        fwCss.flush();
        fwCss.close();
        logs(fileNameCss);

        /**创建IMG目录**/
        String pathImg = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/add/img/";
        File filePathImg = new File(pathImg);
        createFilePath(filePathImg);

        List<ColumsInfo> list1 = new ArrayList<ColumsInfo>();
        for (ColumsInfo o : items) {
            if (o.getColumName().equals("id") || o.getColumName().equals("status") || o.getColumName().equals("createUserId") || o.getColumName().equals("createUserName") || o.getColumName().equals("lastModifyUserId") || o.getColumName().equals("lastModifyUserName") || o.getColumName().equals("lastModifyDate") || o.getColumName().equals("createDate")) {

            } else {
                if (o.getIsNullAble().equals("NO") && (o.getTypeName().equals("varchar") || o.getTypeName().equals("longtext"))) {
                    list1.add(o);
                }
            }
        }
        /**创建JS目录**/
        String pathJs = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/add/js/";
        File filePathJs = new File(pathJs);
        createFilePath(filePathJs);
        String fileNameJs = pathJs + "act.js";
        File fileJs = new File(fileNameJs);
        FileWriter fwJs = new FileWriter(fileJs);
        fwJs.write(generatorCode(this.getProperty("js.add.vm"), bean, annotation, list1));
        fwJs.flush();
        fwJs.close();
        logs(fileNameJs);

    }

    public void generatorEditDir(Class c) throws Exception {
        /**创建根目录**/
        String pathRoot = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/edit/";
        File filePathRoot = new File(pathRoot);
        createFilePath(filePathRoot);


        /**创建JSP**/
        String tableName = CommonUtils.propertyToField(c.getSimpleName());
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1, tableName.length());
        }
        List<ColumsInfo> items = DBHelpInfo.getTableInfo(tableName);
        List<ColumsInfo> list = new ArrayList<ColumsInfo>();
        for (ColumsInfo o : items) {
            if (o.getColumName().equals("id") || o.getColumName().equals("status") || o.getColumName().equals("createUserId") || o.getColumName().equals("createUserName") || o.getColumName().equals("lastModifyUserId") || o.getColumName().equals("lastModifyUserName") || o.getColumName().equals("lastModifyDate") || o.getColumName().equals("createDate")) {

            } else {
                list.add(o);
            }
        }
        String pathJsp = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/edit/";
        File filePathJsp = new File(pathJsp);
        createFilePath(filePathJsp);
        String fileNameJsp = pathJsp + "index.jsp";
        File fileJsp = new File(fileNameJsp);
        FileWriter fwJsp = new FileWriter(fileJsp);
        fwJsp.write(generatorCode(this.getProperty("jsp.edit.vm"), bean, annotation, list));
        fwJsp.flush();
        fwJsp.close();
        logs(fileNameJsp);

        /**创建CSS目录**/
        String pathCss = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/edit/css/";
        File filePathCss = new File(pathCss);
        createFilePath(filePathCss);
        String fileNameCss = pathCss + "style.css";
        File fileCss = new File(fileNameCss);
        FileWriter fwCss = new FileWriter(fileCss);
        fwCss.write("");
        fwCss.flush();
        fwCss.close();
        logs(fileNameCss);

        /**创建IMG目录**/
        String pathImg = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/edit/img/";
        File filePathImg = new File(pathImg);
        createFilePath(filePathImg);

        List<ColumsInfo> list1 = new ArrayList<ColumsInfo>();
        for (ColumsInfo o : items) {
            if (o.getColumName().equals("id") || o.getColumName().equals("status") || o.getColumName().equals("createUserId") || o.getColumName().equals("createUserName") || o.getColumName().equals("lastModifyUserId") || o.getColumName().equals("lastModifyUserName") || o.getColumName().equals("lastModifyDate") || o.getColumName().equals("createDate")) {

            } else {
                if (o.getIsNullAble().equals("NO") && (o.getTypeName().equals("varchar") || o.getTypeName().equals("longtext"))) {
                    list1.add(o);
                }
            }
        }
        /**创建JS目录**/
        String pathJs = appPath + "/src/main/webapp/dynamic/service/" + lowerCase(c.getSimpleName()) + "/edit/js/";
        File filePathJs = new File(pathJs);
        createFilePath(filePathJs);
        String fileNameJs = pathJs + "act.js";
        File fileJs = new File(fileNameJs);
        FileWriter fwJs = new FileWriter(fileJs);
        fwJs.write(generatorCode(this.getProperty("js.edit.vm"), bean, annotation, list1));
        fwJs.flush();
        fwJs.close();
        logs(fileNameJs);
    }

    /**
     * web形式-适用于新互联网架构
     *
     * @param c
     * @throws Exception
     */
    public void generatorWebClient1(Class c) throws Exception {
        this.generatorIndexDir(c);
        this.generatorAddDir(c);
        this.generatorEditDir(c);
    }

    /**
     * web形式-适用于多表格，多数据的表单结构
     *
     * @param c
     * @throws Exception
     */
    public void generatorWebClient2(Class c) throws Exception {
        this.generatorJavaJqgrid(c);
        this.generatorJsModel(c);
        this.generatorJsView(c);
        this.generatorJsConfig(c);
        this.generatorJsController(c);
    }

    /**
     * java后端代码生成器
     * @param controllerEnable controller生成开关
     * @param daoEnable dao层 生成开关
     * @param voEnable Vo&QVo 生成开关
     * @param c model.class
     * @throws Exception
     */
    public void generatorJavaManager(boolean controllerEnable,
                                     boolean daoEnable,
                                     boolean voEnable,
                                     Class c) throws Exception {
        if (controllerEnable) {
            this.generatorJavaController(c);
        }
        if (daoEnable) {
            this.generatorJavaDAO(c);
        }
        if (voEnable) {
            this.generatorJavaQVO(c);
            this.generatorJavaVO(c);
        }

        this.generatorJavaService(c);
        this.generatorJavaServiceImpl(c);
    }
}
