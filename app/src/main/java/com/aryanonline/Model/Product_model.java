package com.aryanonline.Model;


import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product_model implements Serializable {

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
    private String date;
    private String offersPersent;
    private String offersWarranty;
    private String stock;
    private String title;
    private String parent;
    //+++++++++++++++++++++++++++++++++++++++clothers+++++++++++++++++++++++++
    private String s_clolor;

    private String s_size;
    private String cloth_color;
    private String cloth_size;
    private String replacement_policy;
    private String cod;
    //+++++++++++++++++++++++++++++++++++++++++++for+++++++++++++++++++
    public Product_model(String productId, String productName, String productDescription, String productImage, String categoryId, String inStock, String price, String unitValue, String unit, String increament, String mrp, String todayDeals, String offersCat, String dealsDescription, String offersCatDesc, String emi, String warranty, String productOfferImage, String pOfferDescription, String topProductStatus, String date, String offersPersent, String offersWarranty, String stock, String title, String parent,String s_clolor,String s_size,String cloth_color,String cloth_size,
                         String replacement_policy,String cod) {
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.categoryId = categoryId;
        this.inStock = inStock;
        Log.e("in stock" , "stocksssssssssssss"+inStock);
        //+++++++++++++++++++++++++++++++++++++++CLother suff new 828
        this.s_clolor = s_clolor;
        this.s_size = s_size;
        this.cloth_color = cloth_color;
        this.cloth_size = cloth_size;
        this.replacement_policy = replacement_policy;
        this.cod = cod;
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++end
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
        this.date = date;
        this.offersPersent = offersPersent;
        this.offersWarranty = offersWarranty;
        this.stock = stock;
        this.title = title;
        this.parent = parent;
    }

    public String getpOfferDescription() {
        return pOfferDescription;
    }

    public void setpOfferDescription(String pOfferDescription) {
        this.pOfferDescription = pOfferDescription;
    }

    public String getS_clolor() {
        return s_clolor;
    }

    public void setS_clolor(String s_clolor) {
        this.s_clolor = s_clolor;
    }

    public String getS_size() {
        return s_size;
    }

    public void setS_size(String s_size) {
        this.s_size = s_size;
    }

    public String getCloth_color() {
        return cloth_color;
    }

    public void setCloth_color(String cloth_color) {
        this.cloth_color = cloth_color;
    }

    public String getCloth_size() {
        return cloth_size;
    }

    public void setCloth_size(String cloth_size) {
        this.cloth_size = cloth_size;
    }

    public String getReplacement_policy() {
        return replacement_policy;
    }

    public void setReplacement_policy(String replacement_policy) {
        this.replacement_policy = replacement_policy;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOffersPersent() {
        return offersPersent;
    }

    public void setOffersPersent(String offersPersent) {
        this.offersPersent = offersPersent;
    }

    public String getOffersWarranty() {
        return offersWarranty;
    }

    public void setOffersWarranty(String offersWarranty) {
        this.offersWarranty = offersWarranty;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
  /*  String product_id;
    String product_name;
    String product_description;
    String product_image;
    String category_id;
    String in_stock;
    String price;
    String unit_value;
    String unit;
    String increament;
    String title;
    String Mrp;
    String today_deals;
    String offers_cat;
    String deals_description;
    String offers_cat_desc;
    String emi;
    String warranty;
    String product_offer_image;
    String p_offer_description;
    String top_product_status;
    String stock;


    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public String getProduct_image() {
        return product_image;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getIn_stock() {
        return in_stock;
    }

    public String getPrice() {
        return price;
    }

    public String getUnit_value() {
        return unit_value;
    }

    public String getUnit() {
        return unit;
    }

    public String getP_offer_description() {
        return p_offer_description;
    }

    public String getStock() {
        return stock;
    }

    public String getTop_product_status() {
        return top_product_status;
    }

    public String getProduct_offer_image() {
        return product_offer_image;
    }

    public String getEmi() {
        return emi;
    }

    public String getWarranty() {
        return warranty;
    }

    public String getOffers_cat_desc() {
        return offers_cat_desc;
    }

    public String getDeals_description() {
        return deals_description;
    }

    public String getOffers_cat() {
        return offers_cat;
    }

    public String getIncreament() {
        return increament;
    }

    public String getTitle() {
        return title;
    }

    public String getMrp() {
        return Mrp;
    }

    public String getToday_deals() {
        return today_deals;
    }*/
}
