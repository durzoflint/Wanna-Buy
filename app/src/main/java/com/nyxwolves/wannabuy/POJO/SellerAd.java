package com.nyxwolves.wannabuy.POJO;

public class SellerAd {

    public String area;
    public String propertyAddress;
    public String type;
    public String subType;
    public String doorNo;
    public String sellOrRent = "NOT_NEEDED";
    public String facing = "NOT_NEEDED";
    public String size;
    public String bhk = "NOT_NEEDED";
    public String isNew;
    public String floor;
    public String budget;
    public String buyOrRent;
    public String budgetNegotiable;
    public String cornerPlot;
    public String furnished  ="NOT_NEEDED";

    private static SellerAd instance = null;

    public static SellerAd getInstance(){
        if(instance == null){
            instance = new SellerAd();
        }
        return instance;
    }
}
