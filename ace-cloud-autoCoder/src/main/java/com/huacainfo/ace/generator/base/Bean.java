/**
 * @Title: Bean.java
 * @Package org.platform.snail.utils
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:19:12
 * @version V1.0
 */

package com.huacainfo.ace.generator.base;

/**
 * @ClassName: Bean
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月14日 下午8:19:12
 *
 */

public class Bean {

	/** bean 名称 */
	private String name;
	/** bean 首字母小写名称 */
	private String lowerName;

	private String validate;

	private String columName;
	private String columModel;
	private String view;
	private String tableChineseName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLowerName() {
		return lowerName;
	}

	public void setLowerName(String lowerName) {
		this.lowerName = lowerName;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}

	public String getColumName() {
		return columName;
	}

	public void setColumName(String columName) {
		this.columName = columName;
	}

	public String getColumModel() {
		return columModel;
	}

	public void setColumModel(String columModel) {
		this.columModel = columModel;
	}

	public String getTableChineseName() {
		return tableChineseName;
	}

	public void setTableChineseName(String tableChineseName) {
		this.tableChineseName = tableChineseName;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}
}