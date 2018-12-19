package com.nyxwolves.wannabuy.POJO;

public class Requirements {

    public String area;
    public String type;
    public String buyorRent = "NOT_NEEDED";
    public String facing = "NOT_NEEDED";
    public String size;
    public String bhk = "NOT_NEEDED";
    public String isNew;
    public String budget;
    public String floor="1";
    public String  furnished  ="UNFURNISHED";

    private static Requirements instance;

    public static Requirements getInstance(){
        if(instance == null){
            instance = new Requirements();
        }
        return instance;
    }
}
