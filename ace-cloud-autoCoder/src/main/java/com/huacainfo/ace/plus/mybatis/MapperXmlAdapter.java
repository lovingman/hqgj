package com.huacainfo.ace.plus.mybatis;

import com.huacainfo.ace.plus.mybatis.tools.ConfigXmlHelper;
import org.dom4j.DocumentException;
import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;

import java.io.IOException;
import java.util.List;

/**
 * @ClassName MapperXmlAdapter
 * @Description mybatis自定义生成工具
 * @Author Arvin Zou
 * @Date 2019/4/27 10:39
 */
public class MapperXmlAdapter extends PluginAdapter {


    public static void generate(String configName, String tableName) throws IOException, DocumentException {

        String config = MapperXmlAdapter.class.getClassLoader().getResource(configName).getFile();
        ConfigXmlHelper.modifyTableName(config, tableName);

        String[] arg = {"-configfile", config, "-overwrite"};
        ShellRunner.main(arg);
    }

    /**
     * 生成dao
     */
    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    /**
     * 生成实体中每个属性
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    /**
     * 生成实体
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 生成mapping
     */
    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        return super.sqlMapGenerated(sqlMap, introspectedTable);
    }

    /**
     * 生成mapping 添加自定义sql
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        // 数据库表名
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        //所有表字段
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();

        //父类框架MapperXml
        XmlElement parentElement = document.getRootElement();
        //添加-VoResultMap
        XmlElement VoResultMap = voResultMap(introspectedTable);
        //添加-Vo_Column_List
        XmlElement Vo_Column_List = VoColumnList(introspectedTable);
        //添加-From_Statement
        XmlElement From_Statement = fromStatement(tableName);
        //添加-Where_Condition
        XmlElement Where_Condition = Where_Condition(introspectedTable);
        //添加-selectVoByPrimaryKey
        XmlElement selectVoByPrimaryKey = selectVoByPrimaryKey(tableName, introspectedTable);
        //添加-findList
        XmlElement findList = findList();
        //添加-findCount
        XmlElement findCount = findCount();
        //添加-isExist
        XmlElement isExist = isExist(tableName, introspectedTable);
        //添加-deleteByIds
        XmlElement deleteByIds = deleteByIds(tableName, introspectedTable);


        //添加语句块
        parentElement.addElement(VoResultMap);
        parentElement.addElement(Vo_Column_List);
        parentElement.addElement(From_Statement);
        parentElement.addElement(Where_Condition);
        parentElement.addElement(selectVoByPrimaryKey);
        parentElement.addElement(findList);
        parentElement.addElement(findCount);
        parentElement.addElement(isExist);
        parentElement.addElement(deleteByIds);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private XmlElement deleteByIds(String tableName, IntrospectedTable introspectedTable) {
        XmlElement select = new XmlElement("delete");
        select.addAttribute(new Attribute("id", "deleteByIds"));

        select.addElement(new TextElement(
                " delete from " + tableName + "\n" +
                        " where id in\n" +
                        " <foreach collection=\"ids\" item=\"id\" open=\"(\" separator=\",\" close=\")\">\n" +
                        "    #{id}\n" +
                        " </foreach>"));

        return select;
    }

    private XmlElement isExist(String tableName, IntrospectedTable introspectedTable) {
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "isExist"));
        select.addAttribute(new Attribute("resultType", "int"));
        select.addAttribute(new Attribute("parameterType", introspectedTable.getBaseRecordType()));

        select.addElement(new TextElement("        select count(1)\n" +
                "        from " + tableName + "\n" +
                "        where id = #{id,jdbcType=VARCHAR}"));

        return select;
    }

    private XmlElement findCount() {
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "findCount"));
        select.addAttribute(new Attribute("resultType", "int"));

        select.addElement(new TextElement("SELECT COUNT(*)\n" +
                "<include refid=\"From_Statement\"/>\n" +
                "<include refid=\"Where_Condition\"/>"));

        return select;
    }

    private XmlElement fromStatement(String tableName) {

        XmlElement sql = new XmlElement("sql");
        sql.addAttribute(new Attribute("id", "From_Statement"));
        sql.addElement(new TextElement("FROM " + tableName + " t"));

        return sql;
    }

    private XmlElement findList() {
        XmlElement findList = new XmlElement("select");
        findList.addAttribute(new Attribute("id", "findList"));
        findList.addAttribute(new Attribute("parameterType", "java.lang.String"));
        findList.addAttribute(new Attribute("resultMap", "VoResultMap"));

        findList.addElement(new TextElement("        select\n" +
                "        <include refid=\"Vo_Column_List\"/>\n" +
                "        <include refid=\"From_Statement\"/>\n" +
                "        <include refid=\"Where_Condition\"/>\n" +
                "        <choose>\n" +
                "            <when test=\'orderBy!=null and orderBy!=\"\"\'>\n" +
                "                ORDER BY ${orderBy}\n" +
                "            </when>\n" +
                "            <otherwise>\n" +
                "                ORDER BY t.createDate desc\n" +
                "            </otherwise>\n" +
                "        </choose>\n"));

        return findList;
    }

    private XmlElement selectVoByPrimaryKey(String tableName, IntrospectedTable introspectedTable) {
        XmlElement select = new XmlElement("select");
        select.addAttribute(new Attribute("id", "selectVoByPrimaryKey"));
        select.addAttribute(new Attribute("parameterType", "java.lang.String"));
        select.addAttribute(new Attribute("resultMap", "VoResultMap"));

        select.addElement(new TextElement("  select\n" +
                "        <include refid=\"Vo_Column_List\"/>\n" +
                "        <include refid=\"From_Statement\"/>\n" +
                "        where t.id = #{id,jdbcType=VARCHAR}"));

        return select;
    }

    private XmlElement VoColumnList(IntrospectedTable introspectedTable) {
        XmlElement sql = new XmlElement("sql");
        sql.addAttribute(new Attribute("id", "Vo_Column_List"));
        //表中所有字段
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        StringBuilder text = new StringBuilder();
        for (IntrospectedColumn column : columns) {
            text.append("t.").append(column.getActualColumnName()).append(", ");
        }
        //
        String str = text.toString();
        str = str.substring(0, str.lastIndexOf(","));
        TextElement textItem = new TextElement(str);
        sql.addElement(textItem);

        return sql;
    }

    private XmlElement voResultMap(IntrospectedTable introspectedTable) {
        XmlElement voResultMap = new XmlElement("resultMap");
        voResultMap.addAttribute(new Attribute("id", "VoResultMap"));
        voResultMap.addAttribute(new Attribute("extends", "BaseResultMap"));
        //"com.huacainfo.ace.glink.model.ApiDemo"
        String modelPath = introspectedTable.getBaseRecordType();
        String voPath = modelPath.replace("model", "vo");

        voResultMap.addAttribute(new Attribute("type", voPath + "Vo"));

        //空
        voResultMap.addElement(new TextElement(" "));


        return voResultMap;
    }

    private XmlElement Where_Condition(IntrospectedTable introspectedTable) {
        XmlElement Where_Condition = new XmlElement("sql");
        Where_Condition.addAttribute(new Attribute("id", "Where_Condition"));
        //遍历
        XmlElement where = new XmlElement("where");
        where.addElement(new TextElement("1 = 1"));
        StringBuilder sb = new StringBuilder();
        for (IntrospectedColumn introspectedColumn : introspectedTable.getNonPrimaryKeyColumns()) {
            //$NON-NLS-1$
            XmlElement isNotNullElement = new XmlElement("if");
            //test
            sb.setLength(0);
            sb.append("condition.").append(introspectedColumn.getJavaProperty()).append(" != null")
                    .append(" and condition.").append(introspectedColumn.getJavaProperty()).append(" != ''");
            isNotNullElement.addAttribute(new Attribute("test", sb.toString()));
            where.addElement(isNotNullElement);
            //and
            sb.setLength(0);
            sb.append(" and t.").append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
            sb.append(" = ").append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
            isNotNullElement.addElement(new TextElement(sb.toString()));
        }

        //
        Where_Condition.addElement(where);
        return Where_Condition;
    }

    @Override
    public boolean validate(List<String> list) {
//        return false;
        return true;
    }
}
