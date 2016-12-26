package com.zhu.entity.query.base;

/**
 * 分页
 */
public class Paged {
    private Integer page;
    private Integer pageSize = 10;
    private Integer offSet;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        setOffSet(page<=0?0:(page-1)*pageSize);
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }
}
