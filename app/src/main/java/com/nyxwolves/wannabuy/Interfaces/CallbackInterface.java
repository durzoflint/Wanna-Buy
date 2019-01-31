package com.nyxwolves.wannabuy.Interfaces;

import org.json.JSONObject;

public interface CallbackInterface {
    void setData(JSONObject data);
    void isSuccess(boolean isSuccess);
}
