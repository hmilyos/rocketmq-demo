package com.hmily.rocketmq.logistics.domain;

import java.util.Date;

public class Packaged {
    private String packageId;

    private String orderId;

    private String supplierId;

    private String addressId;

    private String remark;

    private String packageStatus;

    private Date createTime;

    private Date updateTime;

    public Packaged(String packageId, String orderId, String supplierId, String addressId, String remark, String packageStatus, Date createTime, Date updateTime) {
        this.packageId = packageId;
        this.orderId = orderId;
        this.supplierId = supplierId;
        this.addressId = addressId;
        this.remark = remark;
        this.packageStatus = packageStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Packaged() {
        super();
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId == null ? null : packageId.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId == null ? null : supplierId.trim();
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId == null ? null : addressId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus == null ? null : packageStatus.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}