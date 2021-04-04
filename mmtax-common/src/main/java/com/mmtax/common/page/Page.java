package com.mmtax.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ljd on 2018/3/16.
 * 分页查询工具类
 */
public class Page<T> implements Serializable {

    protected int currentPage = 1; // 当前页
    protected int pageSize = 20; //每页显示记录数
    protected int startRecord = 1; //起始查询记录
    protected int totalPage = 0; //总页数
    protected int totalRecord = 0; //总记录数

    protected List<T> data;

    public Page(){}

    public Page(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        if(this.currentPage <= 0) {
            this.currentPage = 1;
        }
        if(this.pageSize <=0) {
            this.pageSize = 1;
        }
    }

    public Page(int totalcounts, List<T> data, int currentPage, int pageSize) {
        this.totalRecord = totalcounts;
        this.pageSize=pageSize;
        this.data=data;
        this.currentPage=currentPage;
    }

    public Page(int currentPage, int pageSize, int totalRecord) {
        this(currentPage, pageSize);
        this.totalRecord = totalRecord;
        if(this.totalRecord <=0) {
            this.totalRecord = 1;
        }
    }

    public int getCurrentPage() {
        if(currentPage <= 0) {
            return 1;
        }
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalRecord() {
        if(totalRecord < 0) {
            return 0;
        }
        return totalRecord;
    }
    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }
    public List<T> getData() {
        return data;
    }
    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPage() {
        if(totalRecord <= 0) {
            return 0;
        }
        if(pageSize<=0){
            pageSize = 10;
        }
        int size = totalRecord / pageSize;//总条数/每页显示的条数=总页数
        int mod = totalRecord % pageSize;//最后一页的条数
        if(mod != 0) {
            size++;
        }
        totalPage = size;
        return totalPage;
    }

    public int getStartRecord() {
        startRecord = (getCurrentPage() - 1) * pageSize;
        return startRecord;
    }

}
