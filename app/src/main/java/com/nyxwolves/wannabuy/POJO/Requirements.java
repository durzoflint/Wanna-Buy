package com.nyxwolves.wannabuy.POJO;

public class Requirements {

    public String reqName = "NO_NAME";
    public String state="DEFAULT";
    public String city = "DEFAULT";
    public String locationOne = "DEFAULT";
    public String locationTwo = "DEFAULT";
    public String locationThree = "DEFAULT";
    public String locationFour = "DEFAULT";
    public String locationFive = "DEFAULT";
    public String type = "DEFAULT";
    public String area = "DEFAULT";
    public String minAge = "DEFAULT";
    public String maxAge = "DEFAULT";
    public String buyorRent = "DEFAULT";
    public String facingEast = "DEFAULT";
    public String facingNorth = "DEFAULT";
    public String facingSouth = "DEFAULT";
    public String facingWest = "DEFAULT";
    public String minSize = "DEFAULT";
    public String maxSize = "DEFAULT";
    public String cdmaApproved = "DEFAULT";
    public String dtcpApproved = "DEFAULT";
    public String corporationApproved = "DEFAULT";
    public String panchayatApproved = "DEFAULT";
    public String commercialApproved = "DEFAULT";
    public String industrialApproved = "DEFAULT";
    public String bhk = "DEFAULT";
    public String approval;
    public String budget;
    public String facing;
    public String isNew = "DEFAULT";
    public String isResale = "DEFAULT";
    public String minbudget = "DEFAULT";
    public String maxBudget = "DEFAULT";
    public String subType = "DEFAULT";
    public String floor = "DEFAULT";
    public String furnished  ="DEFAULT";
    public String pgRentType = "DEFAULT";
    public String gym = "NO";
    public String powerBackup = "NO";
    public String securityGuard = "NO";
    public String lift = "NO";
    public String swimmingPool = "NO";
    public String cafetria = "NO";
    public String garden = "NO";
    public String water = "NO";
    public String playArea = "NO";
    public String metroWater ="NO";
    public String drainageConnection = "NO";
    public String roadWidth = "DEFAULT";
    public String isCovparking = "NO";
    public String isUnCovParking = "NO";
    public String noOfCovParking = "DEFAULT";
    public String noOfUnCovParking = "DEFAULT";

    private static Requirements instance;

    public static Requirements getInstance(){
        if(instance == null){
            instance = new Requirements();
        }
        return instance;
    }

    public boolean checkLocation(){
        if(!locationFive.equals("NONE") || !locationFour.equals("NONE") || !locationOne.equals("NONE") || !locationThree.equals("NONE") || !locationTwo.equals("NONE")){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkFacing(){
        if(!facingEast.equals("DEFAULT") || !facingNorth.equals("DEFAULT") || !facingSouth.equals("DEFAULT") || !facingWest.equals("DEFAULT")){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkApproval(){
        if(!dtcpApproved.equals("DEFAULT") || !cdmaApproved.equals("DEFAULT") || !commercialApproved.equals("DEFAULT") || !industrialApproved.equals("DEFAULT") || !panchayatApproved.equals("DEFAULT") || !corporationApproved.equals("DEFAULT")){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkNewOrResale(){
        if(!isNew.equals("DEFAULT") || !isResale.equals("DEFAULT")){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkCovParking(){
        if(!isCovparking.equals("DEFAULT") && Integer.parseInt(noOfCovParking) !=0){
            return true;
        }else{
            return false;
        }
    }

    public boolean checkUnCovParking(){
        if(!isUnCovParking.equals("DEFAULT") && Integer.parseInt(noOfUnCovParking) !=0){
            return true;
        }else{
            return false;
        }
    }
}
