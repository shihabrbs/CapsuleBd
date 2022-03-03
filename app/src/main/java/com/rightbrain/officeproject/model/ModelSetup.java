package com.rightbrain.officeproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelSetup {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("license")
    @Expose
    private Integer license;
    @SerializedName("activeKey")
    @Expose
    private String activeKey;
    @SerializedName("setupId")
    @Expose
    private Integer setupId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("locationName")
    @Expose
    private String locationName;
    @SerializedName("vatRegNo")
    @Expose
    private String vatRegNo;
    @SerializedName("vatPercentage")
    @Expose
    private String vatPercentage;
    @SerializedName("productColumn")
    @Expose
    private Integer productColumn;
    @SerializedName("productFeatureColumn")
    @Expose
    private Integer productFeatureColumn;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("preOrder")
    @Expose
    private Boolean preOrder;
    @SerializedName("cartProcess")
    @Expose
    private String cartProcess;
    @SerializedName("shippingCharge")
    @Expose
    private Integer shippingCharge;
    @SerializedName("cashOnDelivery")
    @Expose
    private Boolean cashOnDelivery;
    @SerializedName("pickupLocation")
    @Expose
    private Object pickupLocation;
    @SerializedName("vatEnable")
    @Expose
    private String vatEnable;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("backgroundImage")
    @Expose
    private String backgroundImage;
    @SerializedName("productMode")
    @Expose
    private String productMode;
    @SerializedName("relatedProductMode")
    @Expose
    private String relatedProductMode;

    @SerializedName("uniqueCode")
    @Expose
    private String uniqueCode;

    @SerializedName("locationId")
    @Expose
    private Integer locationId;

    @SerializedName("main_app")
    @Expose
    private Integer mainApp;
    @SerializedName("main_app_name")
    @Expose
    private String mainAppName;
    @SerializedName("appsManual")
    @Expose
    private Object appsManual;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLicense() {
        return license;
    }

    public void setLicense(Integer license) {
        this.license = license;
    }

    public String getActiveKey() {
        return activeKey;
    }

    public void setActiveKey(String activeKey) {
        this.activeKey = activeKey;
    }

    public Integer getSetupId() {
        return setupId;
    }

    public void setSetupId(Integer setupId) {
        this.setupId = setupId;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getMainApp() {
        return mainApp;
    }

    public void setMainApp(Integer mainApp) {
        this.mainApp = mainApp;
    }

    public String getMainAppName() {
        return mainAppName;
    }

    public void setMainAppName(String mainAppName) {
        this.mainAppName = mainAppName;
    }

    public Object getAppsManual() {
        return appsManual;
    }

    public void setAppsManual(Object appsManual) {
        this.appsManual = appsManual;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getVatRegNo() {
        return vatRegNo;
    }

    public void setVatRegNo(String vatRegNo) {
        this.vatRegNo = vatRegNo;
    }

    public String getVatPercentage() {
        return vatPercentage;
    }

    public void setVatPercentage(String vatPercentage) {
        this.vatPercentage = vatPercentage;
    }

    public Integer getProductColumn() {
        return productColumn;
    }

    public void setProductColumn(Integer productColumn) {
        this.productColumn = productColumn;
    }

    public Integer getProductFeatureColumn() {
        return productFeatureColumn;
    }

    public void setProductFeatureColumn(Integer productFeatureColumn) {
        this.productFeatureColumn = productFeatureColumn;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getPreOrder() {
        return preOrder;
    }

    public void setPreOrder(Boolean preOrder) {
        this.preOrder = preOrder;
    }

    public String getCartProcess() {
        return cartProcess;
    }

    public void setCartProcess(String cartProcess) {
        this.cartProcess = cartProcess;
    }

    public Integer getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(Integer shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    public Boolean getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(Boolean cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public Object getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(Object pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public String getVatEnable() {
        return vatEnable;
    }

    public void setVatEnable(String vatEnable) {
        this.vatEnable = vatEnable;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getProductMode() {
        return productMode;
    }

    public void setProductMode(String productMode) {
        this.productMode = productMode;
    }

    public String getRelatedProductMode() {
        return relatedProductMode;
    }

    public void setRelatedProductMode(String relatedProductMode) {
        this.relatedProductMode = relatedProductMode;
    }
}
