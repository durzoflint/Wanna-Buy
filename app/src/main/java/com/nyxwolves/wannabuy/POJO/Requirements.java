package com.nyxwolves.wannabuy.POJO;

public class Requirements {

    public String locationOne = "DEFAULT";
    public String locationTwo = "DEFAULT";
    public String locationThree = "DEFAULT";
    public String locationFour = "DEFAULT";
    public String locationFive = "DEFAULT";
    public String type;
    public String area;
    public String buyorRent;
    public String facing;
    public String size;
    public String bhk;
    public String isNew;
    public String budget;
    public String subType;
    public String floor="1";
    public String furnished  ="UNFURNISHED";

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
}
