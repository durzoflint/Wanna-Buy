package com.nyxwolves.wannabuy.POJO;

public class SellerAd {

    public String adsLocation = "NOT_SET";
    public String adsPropertyAddress = "NOT_SET";
    public String adsPropertyType = "NOT_SET";
    public String adsSubType = "NOT_SET";
    public String adsDoorNo = "NOT_SET";
    public String adsSellOrRent = "NOT_SET";
    public String adsFacing = "NOT_SET";
    public String adsBhk = "NOT_SET";
    public String adsIsNew = "NOT_SET";
    public String adsFloor = "NOT_SET";
    public String adsBudget = "NO_SET";
    public String adsBuyOrRent = "NOT_SET";
    public String adsBudgetNegotiable = "Yes";
    public String adsFurnished  ="NOT_SET";
    public String adsCovCarParking = "NOT_SET";
    public String adsUnCovParking = "NOT_SET";
    public String adsCovCarParkingNum = "NOT SET";
    public String adsUnCovCarParkingNum = "NOT SET";
    public String adsLandArea = "NOT_SET";
    public String adsLandAreaUnit = "Sq. Ft";
    public String adsBuiltUpArea = "NOT_SET";
    public String adsGym = "NO";
    public String adsPowerBackup = "NO";
    public String adsSecurityGuard = "NO";
    public String adsLift = "NO";
    public String adsSwimmingPool = "NO";
    public String adsCafetria = "NO";
    public String adsGarden = "NO";
    public String adsWater = "NO";
    public String adsPlayArea = "NO";
    public String adsMetroWater ="NO";
    public String adsDrainageConnection = "NO";
    public String adsLeaseStartDate = "NOT_SET";
    public String adsRentalIncrement = "NOT_SET";
    public String adsRoi = "NOT_SET";
    public String adsRoiIncrementPeriod = "NOT_SET";

    private static SellerAd instance = null;

    public static SellerAd getInstance(){
        if(instance == null){
            instance = new SellerAd();
        }
        return instance;
    }
}
