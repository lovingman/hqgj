package com.huacainfo.ace.hqgj.vo;

import com.huacainfo.ace.hqgj.model.BasicAnnex;
import com.huacainfo.ace.hqgj.model.LawPolicy;

import java.util.List;


public class LawPolicyVo extends LawPolicy {
    private List<BasicAnnex> basicAnnexes ;
private static final long serialVersionUID = 1L;

    @Override
    public List<BasicAnnex> getBasicAnnexes() {
        return basicAnnexes;
    }

    @Override
    public void setBasicAnnexes(List<BasicAnnex> basicAnnexes) {
        this.basicAnnexes = basicAnnexes;
    }
}
