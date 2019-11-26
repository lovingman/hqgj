package com.huacainfo.ace.generator.zcpa;

import com.huacainfo.ace.zcpa.model.RelationSocialVolunteer;

public class Generator {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator("zcpa", "志愿者与社会组织关联", "豆文国");
        Class<?> c =RelationSocialVolunteer.class;//ActProject.class;
        codeGenerator.init(c);
        codeGenerator.generatorJavaManager(true, true, true, c);
    }

}
