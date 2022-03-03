package com.rightbrain.officeproject.MyPreferance;

import android.content.Context;
import android.content.SharedPreferences;

public class MysharedPreferanceSetup {

    private SharedPreferences sharedPreferencesSetups;
    private SharedPreferences.Editor editor ;
    private static MysharedPreferanceSetup sharedPreferencesSetup=null;

    private MysharedPreferanceSetup(Context context)
    {
        sharedPreferencesSetups = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
        editor = sharedPreferencesSetups.edit();
        editor.apply();
    }


    public static synchronized MysharedPreferanceSetup getPreferences(Context context)
    {

        if(sharedPreferencesSetup==null) {
            sharedPreferencesSetup = new MysharedPreferanceSetup(context);
        }

        return sharedPreferencesSetup;
    }

    public void setUniqueCode(String uniqueCode)
    {
        editor.putString("uniqueCode",uniqueCode);
        editor.apply();
    }

    public String getUniqueCode()
    {
        return sharedPreferencesSetups.getString("uniqueCode","UBE49VBB");
    }


    public void setShopName(String shopName)
    {
        editor.putString("shopName",shopName);
        editor.apply();
    }

    public String getShopName()
    {
        return sharedPreferencesSetups.getString("shopName","FamilyMart");
    }

    public void setSplashScreen(String splashScreen)
    {
        editor.putString("splashScreen",splashScreen);
        editor.apply();
    }

    public String getSplashScreen()
    {
        return sharedPreferencesSetups.getString("splashScreen","none");
    }

    public void setCurrency(String currency)
    {
        editor.putString("currency",currency);
        editor.apply();
    }

    public String getCurrency()
    {
        return sharedPreferencesSetups.getString("currency","");
    }

    public void setShippingCharge(String shippingCharge)
    {
        editor.putString("shippingCharge",shippingCharge);
        editor.apply();
    }

    public String getShippingCharge()
    {
        return sharedPreferencesSetups.getString("shippingCharge","0");
    }


    public void setLogo(String logo)
    {
        editor.putString("logo",logo);
        editor.apply();
    }

    public String getLogo()
    {
        return sharedPreferencesSetups.getString("logo","");
    }

    public void setCartProcess(String cartProcess)
    {
        editor.putString("cartProcess",cartProcess);
        editor.apply();
    }

    public String getCartProcess()
    {
        return sharedPreferencesSetups.getString("cartProcess","inline");
    }


}
