package com.aryanonline.Model;


public class My_order_detail_model {

    String sale_item_id;
    String sale_id;
    String product_id;
    String product_name;
    String qty;
    String unit;
    String unit_value;
    String price;
    String qty_in_kg;
    String product_image;
    String tv_del_ch;
    public String getTv_del_ch() {
        return tv_del_ch;
    }

    public void setTv_del_ch(String tv_del_ch) {
        this.tv_del_ch = tv_del_ch;
    }



    public String getSale_item_id(){
        return sale_item_id;
    }

    public String getSale_id(){
        return sale_id;
    }

    public String getProduct_id(){
        return product_id;
    }

    public String getProduct_name(){
        return product_name;
    }

    public String getQty(){
        return qty;
    }

    public String getUnit(){
        return unit;
    }

    public String getUnit_value(){
        return unit_value;
    }

    public String getPrice(){
        return price;
    }

    public String getQty_in_kg(){
        return qty_in_kg;
    }

    public String getProduct_image(){
        return product_image;
    }

}
