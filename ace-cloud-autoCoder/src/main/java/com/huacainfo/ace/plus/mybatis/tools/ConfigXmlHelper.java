package com.huacainfo.ace.plus.mybatis.tools;

import com.huacainfo.ace.plus.mybatis.TableNameHelper;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;

/**
 * @ClassName ConfigXmlHelper
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/5/16 19:55
 */
public class ConfigXmlHelper {

    public static void modifyTableName(String path, String tableName) throws DocumentException, IOException {

        String beanName = TableNameHelper.lineToHump(tableName);
        String modelName = TableNameHelper.toUpperCaseFirstOne(beanName);
        String daoName = modelName + "Dao";

        // 需要导入jar包:dom4j
        // 创建SAXReader对象
        SAXReader sr = new SAXReader();
        // 关联xml
        Document document = sr.read(path);
        // 获取根元素
        Element root = document.getRootElement();
        // 获取 context 元素
        Element context = root.element("context");
        // 获取 table 元素
        Element table = context.element("table");

        // 修改 table 元素的 tableName 属性
        Attribute ELTableName = table.attribute("tableName");
        ELTableName.setText(tableName);
        // 修改 table 元素的 domainObjectName 属性
        Attribute elDomainObjectName = table.attribute("domainObjectName");
        elDomainObjectName.setText(modelName);
        // 修改 table 元素的 mapperName 属性
        Attribute ELMapperName = table.attribute("mapperName");
        ELMapperName.setText(daoName);


        //调用下面的静态方法完成xml的写出
        saveDocument(document, new File(path));
    }


    /**
     * 下面的为固定代码---------可以完成java对XML的写,改等操作
     *
     * @param document Document
     * @param xmlFile  xmlFile
     * @throws IOException
     */
    private static void saveDocument(Document document, File xmlFile) throws IOException {
        // 创建输出流
        Writer osWrite = new OutputStreamWriter(new FileOutputStream(xmlFile));
        // 获取输出的指定格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置编码 ，确保解析的xml为UTF-8格式
        format.setEncoding("UTF-8");
        // XMLWriter
        XMLWriter writer = new XMLWriter(osWrite, format);
        // 指定输出文件以及格式
        // 把document写入xmlFile指定的文件(可以为被解析的文件或者新创建的文件)
        writer.write(document);
        writer.flush();
        writer.close();
    }
}
