package com.huacainfo.ace.portal.model;

import com.huacainfo.ace.common.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 表名：dict
 * 注释：
 *
 * @author HuaCai008
 * @date 2019-05-16
 */
@ApiModel(description = "数据字典对象")
public class Dict extends BaseModel {
    /**
     * 字段名：dict.id
     * 注释：
     */
    @ApiModelProperty(value = "唯一主键")
    private Integer id;

    /**
     * 字段名：dict.categoryId
     * 注释：
     */
    @ApiModelProperty(value = "分类ID")
    private String categoryId;

    /**
     * 字段名：dict.code
     * 注释：
     */
    @ApiModelProperty(value = "字典编码")
    private String code;

    /**
     * 字段名：dict.pcode
     * 注释：拼音码
     */
    @ApiModelProperty(value = "字典-拼音码")
    private String pcode;

    /**
     * 字段名：dict.name
     * 注释：
     */
    @ApiModelProperty(value = "字典-名称")
    private String name;

    /**
     * 字段名：dict.spell
     * 注释：拼音码
     */
    @ApiModelProperty(value = "字典-拼音码")
    private String spell;

    /**
     * 字段名：dict.remark
     * 注释：
     */
    @ApiModelProperty(value = "字典-拼音码")
    private String remark;

    /**
     * 字段名：dict.createTime
     * 注释：
     */
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell == null ? null : spell.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
