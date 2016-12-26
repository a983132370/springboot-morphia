package com.zhu.entity.query.base;

import com.zhu.entity.query.base.Paged;

import java.util.List;

/**
 * base的查询条件
 */
public class Condition {
    //是否使用降序, 默认升序
    private boolean isOrderDesc;

    //分页条件
    private Paged paged;

    //支持按创建时间排序
    private boolean needCreatedTimeOrder;

    //字段列表接收开关, true:接收指定字段列表， false:不接收指定字段列表
    private boolean isFieldsIncluded;

    //接收字段列表，注意： 字段名称与Entity属性一致
    private String[] retrievedFields;

    private Integer status;

    //排序条件 add by luyu
    private List<String> orderList;

    public boolean isOrderDesc() {
        return isOrderDesc;
    }

    public void setIsOrderDesc(boolean isOrderDesc) {
        this.isOrderDesc = isOrderDesc;
    }

    public Paged getPaged() {
        return paged;
    }

    public void setPaged(Paged paged) {
        this.paged = paged;
    }


    public boolean isNeedCreatedTimeOrder() {
        return needCreatedTimeOrder;
    }

    public void setNeedCreatedTimeOrder(boolean needCreatedTimeOrder) {
        this.needCreatedTimeOrder = needCreatedTimeOrder;
    }

    public boolean isFieldsIncluded() {
        return isFieldsIncluded;
    }

    public void setIsFieldsIncluded(boolean isFieldsIncluded) {
        this.isFieldsIncluded = isFieldsIncluded;
    }


    public String[] getRetrievedFields() {
        return retrievedFields;
    }

    public void setRetrievedFields(String[] retrievedFields) {
        this.retrievedFields = retrievedFields;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public List<String> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<String> orderList) {
        this.orderList = orderList;
    }
}
