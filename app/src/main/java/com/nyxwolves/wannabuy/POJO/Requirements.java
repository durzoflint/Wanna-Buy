package com.nyxwolves.wannabuy.POJO;

public class Requirements {

    public String reqName = "NOT_SET";
    public String state="NOT_SET";
    public String city = "NOT_SET";
    public String locationOne = "NOT_SET";
    public String locationTwo = "NOT_SET";
    public String locationThree = "NOT_SET";
    public String locationFour = "NOT_SET";
    public String locationFive = "NOT_SET";
    public String type = "NOT_SET";
    public String area = "NOT_SET";
    public String minAge = "NOT_SET";
    public String maxAge = "NOT_SET";
    public String buyorRent = "NOT_SET";
    public String facingEast = "NOT_SET";
    public String facingNorth = "NOT_SET";
    public String facingSouth = "NOT_SET";
    public String facingWest = "NOT_SET";
    public String minSizeLand = "NOT_SET";
    public String maxSizeLand = "NOT_SET";
    public String minSizeBuilding = "NOT_SET";
    public String maxSizeBuilding = "NOT_SET";
    public String cdmaApproved = "NOT_SET";
    public String dtcpApproved = "NOT_SET";
    public String corporationApproved = "NOT_SET";
    public String panchayatApproved = "NOT_SET";
    public String commercialApproved = "NOT_SET";
    public String industrialApproved = "NOT_SET";
    public String reraApproved = "NOT_SET";
    public String bhk = "NOT_SET";
    public String isNew = "NOT_SET";
    public String isResale = "NOT_SET";
    public String minBudget = "NOT_SET";
    public String maxBudget = "NOT_SET";
    public String minBudgetUnit = "NOT_SET";
    public String maxBudgetUnit = "NOT_SET";
    public String pgRentType = "NOT_SET";
    public String gym = "NO";
    public String powerBackup = "NO";
    public String securityGuard = "NO";
    public String lift = "NO";
    public String swimmingPool = "NO";
    public String cafetria = "NO";
    public String garden = "NO";
    public String subType = "NOT_SET";
    public String floor = "NOT_SET";
    public String furnished  ="NOT_SET";
    public String water = "NO";
    public String playArea = "NO";
    public String metroWater ="NO";
    public String drainageConnection = "NO";
    public String roadWidth = "NOT_SET";
    public String isCovparking = "NO";
    public String isUnCovParking = "NO";
    public String noOfCovParking = "0";
    public String noOfUnCovParking = "0";
    public String isRentalIncome = "No";
    public String roi = "DEFAULT";
    public String petsAllowed = "NOT_SET";
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

    public void clearState(){
         reqName = "NOT_SET";
         state="NOT_SET";
         city = "NOT_SET";
         locationOne = "NOT_SET";
         locationTwo = "NOT_SET";
         locationThree = "NOT_SET";
         locationFour = "NOT_SET";
         locationFive = "NOT_SET";
         type = "NOT_SET";
         area = "NOT_SET";
         minAge = "NOT_SET";
         maxAge = "NOT_SET";
         buyorRent = "NOT_SET";
         facingEast = "NOT_SET";
         facingNorth = "NOT_SET";
         facingSouth = "NOT_SET";
         facingWest = "NOT_SET";
         minSizeLand = "NOT_SET";
         maxSizeLand = "NOT_SET";
         minSizeBuilding = "NOT_SET";
         maxSizeBuilding = "NOT_SET";
         cdmaApproved = "NOT_SET";
         dtcpApproved = "NOT_SET";
         corporationApproved = "NOT_SET";
         panchayatApproved = "NOT_SET";
         commercialApproved = "NOT_SET";
         industrialApproved = "NOT_SET";
         reraApproved = "NOT_SET";
         bhk = "NOT_SET";
         isNew = "NOT_SET";
         isResale = "NOT_SET";
         minBudget = "NOT_SET";
         pgRentType = "NOT_SET";
         gym = "NO";
         powerBackup = "NO";
         securityGuard = "NO";
         lift = "NO";
         swimmingPool = "NO";
         cafetria = "NO";
         garden = "NO";
         maxBudget = "NOT_SET";
         subType = "NOT_SET";
         floor = "NOT_SET";
         furnished  ="NOT_SET";
         water = "NO";
         playArea = "NO";
         metroWater ="NO";
         drainageConnection = "NO";
         roadWidth = "NOT_SET";
         isCovparking = "NO";
         isUnCovParking = "NO";
         noOfCovParking = "0";
         noOfUnCovParking = "0";
         isRentalIncome = "No";
         roi = "NO_SET";
    }
}
