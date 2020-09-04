package com.metroapps.honeybee.model;

public class Cart
{
    String itemName;
    String iqty, iprice;

    public Cart(String itemName, String iqty, String iprice)
    {
        this.itemName = itemName;
        this.iqty = iqty;
        this.iprice = iprice;
    }

    public Cart()
    {

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getIqty() {
        return iqty;
    }

    public void setIqty(String iqty) {
        this.iqty = iqty;
    }

    public String getIprice() {
        return iprice;
    }

    public void setIprice(String iprice) {
        this.iprice = iprice;
    }
}
