package com.huacainfo.ace.generator.hqgj;
import com.huacainfo.ace.hqgj.model.ServeBusinessAppend;


public class Generator {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator("hqgj", "创业服务资料其它附加信息", "豆文国");
        Class<?> c = ServeBusinessAppend.class;//null;
        codeGenerator.init(c);
        codeGenerator.generatorJavaManager(true, true, true, c);
    }

}
