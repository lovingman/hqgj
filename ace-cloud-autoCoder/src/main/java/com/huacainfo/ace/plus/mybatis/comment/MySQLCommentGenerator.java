package com.huacainfo.ace.plus.mybatis.comment;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName MySQLCommentGenerator
 * @Description 自定义注释生成器
 * @Author Arvin Zou
 * @Date 2019/4/30 11:42
 */
public class MySQLCommentGenerator extends EmptyCommentGenerator {
    private Properties properties;

    public MySQLCommentGenerator() {
        properties = new Properties();
    }

    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();
        File configFile = new File(MySQLCommentGenerator.class.getClassLoader().getResource("generatorConfig.xml").getFile());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        // 获取自定义的 properties
        this.properties.putAll(properties);
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd");
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        // 获取表注释
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        String remarks = introspectedTable.getRemarks();
        Properties prop = System.getProperties();

        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * 表名：" + tableName);
        topLevelClass.addJavaDocLine(" * 注释：" + remarks);
        topLevelClass.addJavaDocLine(" *");
        topLevelClass.addJavaDocLine(" * @author " + prop.getProperty("user.name"));
        topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        // 获取列注释
        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();
        String columnName = introspectedColumn.getActualColumnName();
        String remarks = introspectedColumn.getRemarks();

        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * 字段名：" + tableName + "." + columnName);
        field.addJavaDocLine(" * 注释：" + remarks);
        field.addJavaDocLine(" */");
    }
}
