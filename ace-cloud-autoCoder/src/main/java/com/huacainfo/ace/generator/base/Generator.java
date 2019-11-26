package com.huacainfo.ace.generator.base;

public class Generator {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator("zcpa", "项目资料", "王恩");
        Class<?> c = null;//ActProject.class;
        codeGenerator.init(c);
        codeGenerator.generatorJavaManager(true, true, true, c);
    }

}
