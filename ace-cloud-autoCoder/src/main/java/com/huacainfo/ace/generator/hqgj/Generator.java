package com.huacainfo.ace.generator.hqgj;



public class Generator {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator("hqgj", "企业管理", "何双");
        Class<?> c = null;//BaseCompany.class;
        codeGenerator.init(c);
        codeGenerator.generatorJavaManager(true, true, true, c);
    }

}
