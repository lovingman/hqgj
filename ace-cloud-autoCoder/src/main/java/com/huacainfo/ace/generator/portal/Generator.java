package com.huacainfo.ace.generator.portal;

import com.huacainfo.ace.generator.base.CodeGenerator;

public class Generator {

    public static void main(String[] args) throws Exception {

        CodeGenerator codeGenerator = new CodeGenerator("portal", "分页测试", "ArvinZou");
//        Class<?> c = PageTest.class;
        Class<?> c = null;
        codeGenerator.init(c);
        codeGenerator.generatorJavaManager(true, true, true, c);


    }

}
