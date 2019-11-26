/**
 * @Title: QueueCmccHisVo.java
 * @Package com.huacainfo.ace.portal.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.QueueCmccHis;

/**
 * @author chenxiaoke
 * @ClassName: QueueCmccHisVo
 * @Description: TODO
 * @date 2014年12月24日 下午3:44:43
 */
public class QueueCmccHisQVo extends QueueCmccHis {
    private static final long serialVersionUID = 1L;
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
