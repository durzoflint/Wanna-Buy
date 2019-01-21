package com.nyxwolves.wannabuy.POJO;

public class SellerAd {

    public String adsLocation = "no";
    public String adsPropertyAddress = "no";
    public String adsPropertyType = "no";
    public String adsSubType = "no";
    public String adsDoorNo = "no";
    public String adsSellOrRent = "no";
    public String adsFacing = "no";
    public String adsBhk = "no";
    public String adsNoOfApartments = "no";
    public String adsTotalFloors = "no";
    public String adsAge = "no";
    public String adsIsNew = "no";
    public String adsFloor = "no";
    public String adsBudget = "NO_SET";
    public String adsBudgetNegotiable = "Yes";
    public String adsFurnished  ="no";
    public String adsCovCarParking = "no";
    public String adsUnCovParking = "no";
    public String adsCovCarParkingNum = "NO";
    public String adsUnCovCarParkingNum = "NO";
    public String adsLandArea = "no";
    public String adsLandAreaUnit = "Sq. Ft";
    public String adsBuiltUpArea = "no";
    public String adsMaintance = "no";
    public String adsCdmaApproved = "no";
    public String adsDtcpApproved = "no";
    public String adsCorpApproved = "no";
    public String adsPanchApproved = "no";
    public String adsCommApproved = "no";
    public String adsIndusApproved = "no";
    public String adsReraApproved = "no";
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
    public String adsRoi = "no";
    public String adsRoiIncrementPeriod = "no";
    public String adsPetsAllowed = "no";
    public String adsRoadWidth = "no";
    public String adsAdvanceDeposit = "no";
    public String noOfRooms = "no";
    public String personPerRoom = "no";
    public String withFood = "no";
    public String pgRentPerMonth = "no";
    public String guestHousePrice = "no";
    public String boysHostelPrice = "no";
    public String girlsHostelPrice = "no";
    public String workingMenHostelPrice = "no";
    public String workingWomenHostelPrice = "no";

    private static SellerAd instance = null;

    public static SellerAd getInstance(){
        if(instance == null){
            instance = new SellerAd();
        }
        return instance;
    }
}
