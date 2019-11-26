package com.huacainfo.ace.generator.base;

public class ColumsInfo implements java.io.Serializable {
	private static final long serialVersionUID = 6388496672390508697L;
	private String columName;
	private String remarks;
	private String typeName;
	private String isNullAble;
	private String len;
	public String getColumName() {
		return columName;
	}
	public void setColumName(String columName) {
		this.columName = columName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getIsNullAble() {
		return isNullAble;
	}
	public void setIsNullAble(String isNullAble) {
		this.isNullAble = isNullAble;
	}

	public String getLen() {
		return len;
	}

	public void setLen(String len) {
		this.len = len;
	}

	@Override
	public String toString() {
		return "ColumsInfo{" +
				"columName='" + columName + '\'' +
				", remarks='" + remarks + '\'' +
				", typeName='" + typeName + '\'' +
				", isNullAble='" + isNullAble + '\'' +
				", len='" + len + '\'' +
				'}';
	}
}
