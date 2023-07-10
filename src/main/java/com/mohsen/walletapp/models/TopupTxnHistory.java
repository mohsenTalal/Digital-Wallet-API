package com.mohsen.walletapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "topup_txn_history")
public class TopupTxnHistory implements Serializable {
    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(unique = true, name = "tran_ref")
    private String tranRef;
    @Column(name = "tran_type")
    private String tranType;
    @Column(name = "cart_id")
    private String cartId;
    @Column(name = "cart_description")
    private String cartDescription;
    @Column(name = "cart_currency")
    private String cartCurrency;
    @Column(name = "cart_amount")
    private Double cartAmount;
    @Column(name = "tran_currency")
    private String tranCurrency;
    @Column(name = "tran_total")
    private String tranTotal;
    @Column(name = "call_back")
    private String callback;
    @Column(name = "return_url")
    private String returnUrl;
    @Column(name = "redirect_url")
    private String redirect_url;

    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "profile_id")
    private float profileId;
    @Column(name = "merchant_id")
    private float merchantId;
    @Column(name = "ipn_trace")
    private String ipnTrace;
    @Column(name = "hold")
    private boolean hold;
    @Column(name = "hold_time")
    private String hold_time;
    @JsonIgnore()
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topup_customerdetails_id", referencedColumnName = "id")
    private TopupCustomerdetails topupCustomerDetails;

    public TopupTxnHistory() {
    }


    public Long getId() {
        return id;
    }

    public String getTranRef() {
        return tranRef;
    }

    public String getTranType() {
        return tranType;
    }

    public String getCartId() {
        return cartId;
    }

    public String getCartDescription() {
        return cartDescription;
    }

    public String getCartCurrency() {
        return cartCurrency;
    }

    public Double getCartAmount() {
        return cartAmount;
    }

    public String getTranCurrency() {
        return tranCurrency;
    }

    public String getTranTotal() {
        return tranTotal;
    }

    public String getCallback() {
        return callback;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getRedirect_url() {
        return redirect_url;
    }

    public String getServiceId() {
        return serviceId;
    }

    public float getProfileId() {
        return profileId;
    }

    public float getMerchantId() {
        return merchantId;
    }

    public String getIpnTrace() {
        return ipnTrace;
    }

    public boolean isHold() {
        return hold;
    }

    public String getHold_time() {
        return hold_time;
    }

    public TopupCustomerdetails getTopupCustomerDetails() {
        return topupCustomerDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTranRef(String tranRef) {
        this.tranRef = tranRef;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public void setCartDescription(String cartDescription) {
        this.cartDescription = cartDescription;
    }

    public void setCartCurrency(String cartCurrency) {
        this.cartCurrency = cartCurrency;
    }

    public void setCartAmount(Double cartAmount) {
        this.cartAmount = cartAmount;
    }

    public void setTranCurrency(String tranCurrency) {
        this.tranCurrency = tranCurrency;
    }

    public void setTranTotal(String tranTotal) {
        this.tranTotal = tranTotal;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public void setProfileId(float profileId) {
        this.profileId = profileId;
    }

    public void setMerchantId(float merchantId) {
        this.merchantId = merchantId;
    }

    public void setIpnTrace(String ipnTrace) {
        this.ipnTrace = ipnTrace;
    }

    public void setHold(boolean hold) {
        this.hold = hold;
    }

    public void setHold_time(String hold_time) {
        this.hold_time = hold_time;
    }

    public void setTopupCustomerDetails(TopupCustomerdetails topupCustomerDetails) {
        this.topupCustomerDetails = topupCustomerDetails;
    }

    @Override
    public String toString() {
        return "TopupTxnHistory{" +
                "id=" + id +
                ", tranRef='" + tranRef + '\'' +
                ", tranType='" + tranType + '\'' +
                ", cartId='" + cartId + '\'' +
                ", cartDescription='" + cartDescription + '\'' +
                ", cartCurrency='" + cartCurrency + '\'' +
                ", cartAmount=" + cartAmount +
                ", tranCurrency='" + tranCurrency + '\'' +
                ", tranTotal='" + tranTotal + '\'' +
                ", callback='" + callback + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", redirect_url='" + redirect_url + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", profileId=" + profileId +
                ", merchantId=" + merchantId +
                ", ipnTrace='" + ipnTrace + '\'' +
                ", hold=" + hold +
                ", hold_time='" + hold_time + '\'' +
                ", topupCustomerDetails=" + topupCustomerDetails +
                '}';
    }
}
