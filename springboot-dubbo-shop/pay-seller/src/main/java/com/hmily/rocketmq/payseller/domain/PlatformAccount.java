package com.hmily.rocketmq.payseller.domain;

import java.math.BigDecimal;
import java.util.Date;

public class PlatformAccount {
    private String accountId;

    private String accountNo;

    private Date dateTime;

    private BigDecimal currentBalance;

    private Integer version;

    private Date createTime;

    private Date updateTime;

    public PlatformAccount(String accountId, String accountNo, Date dateTime, BigDecimal currentBalance, Integer version, Date createTime, Date updateTime) {
        this.accountId = accountId;
        this.accountNo = accountNo;
        this.dateTime = dateTime;
        this.currentBalance = currentBalance;
        this.version = version;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public PlatformAccount() {
        super();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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