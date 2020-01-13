package com.huacainfo.ace.hqgj.plugins.wechat.pojo;

import java.util.List;

/**
 * Created by ArvinZou on 2018/6/11.
 *
 * @author ArvinZou
 */
public class UserList {
    // 一次性拉取OPENID的最大个数
    public static final int MAX_GET_COUNT = 10000;

    /**
     * 关注该公众账号的总用户数
     */
    private int total;
    /**
     * 拉取的OPENID个数，最大值为10000
     */
    private int count;
    /**
     * 列表数据，OPENID的列表
     */
    private UserOpenIds data;
    /**
     * 拉取列表的最后一个用户的OPENID
     */
    private String next_openid;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UserOpenIds getData() {
        return data;
    }

    public void setData(UserOpenIds data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    public class UserOpenIds {
        private List<String> openid;

        public List<String> getOpenid() {
            return openid;
        }

        public void setOpenid(List<String> openid) {
            this.openid = openid;
        }
    }
}
