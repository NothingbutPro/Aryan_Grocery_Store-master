package com.aryanonline.Model;

import java.io.Serializable;

public class TopModel implements Serializable {

    private String productId;
    private String productName;
    private String productDescription;
    private String productImage;
    private String categoryId;
    private String inStock;
    private String price;
    private String unitValue;
    private String unit;
    private String increament;
    private String mrp;
    private String todayDeals;
    private String offersCat;
    private String dealsDescription;
    private String offersCatDesc;
    private String emi;
    private String warranty;
    private String productOfferImage;
    private String pOfferDescription;
    private String topProductStatus;



    private String free_mrp;



    private String free_product_name;



    private String offers_persent;

    public TopModel(String productId, String productName, String productDescription, String productImage, String categoryId, String inStock, String price, String unitValue, String unit, String increament, String mrp, String todayDeals, String offersCat, String dealsDescription, String offersCatDesc, String emi, String warranty, String productOfferImage, String pOfferDescription, String topProductStatus,String free_product_name
    ,String offers_persent,String free_mrp) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.categoryId = categoryId;
        this.inStock = inStock;
        this.price = price;
        this.unitValue = unitValue;
        this.unit = unit;
        this.increament = increament;
        this.mrp = mrp;
        this.todayDeals = todayDeals;
        this.offersCat = offersCat;
        this.dealsDescription = dealsDescription;
        this.offersCatDesc = offersCatDesc;
        this.emi = emi;
        this.warranty = warranty;
        this.productOfferImage = productOfferImage;
        this.pOfferDescription = pOfferDescription;
        this.topProductStatus = topProductStatus;
        this.offers_persent = offers_persent;
        this.free_mrp = free_mrp;
        this.free_product_name = free_product_name;
    }
    public String getFree_mrp() {
        return free_mrp;
    }
    public String getFree_product_name() {
        return free_product_name;
    }

    public void setFree_product_name(String free_product_name) {
        this.free_product_name = free_product_name;
    }
    public void setFree_mrp(String free_mrp) {
        this.free_mrp = free_mrp;
    }
    public String getpOfferDescription() {
        return pOfferDescription;
    }

    public void setpOfferDescription(String pOfferDescription) {
        this.pOfferDescription = pOfferDescription;
    }

    public String getOffers_persent() {
        return offers_persent;
    }

    public void setOffers_persent(String offers_persent) {
        this.offers_persent = offers_persent;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(String unitValue) {
        this.unitValue = unitValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIncreament() {
        return increament;
    }

    public void setIncreament(String increament) {
        this.increament = increament;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getTodayDeals() {
        return todayDeals;
    }

    public void setTodayDeals(String todayDeals) {
        this.todayDeals = todayDeals;
    }

    public String getOffersCat() {
        return offersCat;
    }

    public void setOffersCat(String offersCat) {
        this.offersCat = offersCat;
    }

    public String getDealsDescription() {
        return dealsDescription;
    }

    public void setDealsDescription(String dealsDescription) {
        this.dealsDescription = dealsDescription;
    }

    public String getOffersCatDesc() {
        return offersCatDesc;
    }

    public void setOffersCatDesc(String offersCatDesc) {
        this.offersCatDesc = offersCatDesc;
    }

    public String getEmi() {
        return emi;
    }

    public void setEmi(String emi) {
        this.emi = emi;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getProductOfferImage() {
        return productOfferImage;
    }

    public void setProductOfferImage(String productOfferImage) {
        this.productOfferImage = productOfferImage;
    }

    public String getPOfferDescription() {
        return pOfferDescription;
    }

    public void setPOfferDescription(String pOfferDescription) {
        this.pOfferDescription = pOfferDescription;
    }

    public String getTopProductStatus() {
        return topProductStatus;
    }

    public void setTopProductStatus(String topProductStatus) {
        this.topProductStatus = topProductStatus;
    }
}
