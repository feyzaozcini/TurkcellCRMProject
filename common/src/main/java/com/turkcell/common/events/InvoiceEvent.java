package com.turkcell.common.events;

import java.util.List;

public class InvoiceEvent {
    private int customerId;
    private int accountId;
    private int serviceAddress;
    private List<Integer> productIds;
    private float totalAmount;
    public InvoiceEvent(int customerId, int accountId, int serviceAddress, List<Integer> productIds, float totalAmount) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.serviceAddress = serviceAddress;
        this.productIds = productIds;
        this.totalAmount = totalAmount;
    }

    public InvoiceEvent(){

    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(int serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }



}
