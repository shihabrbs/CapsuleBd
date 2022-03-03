package com.rightbrain.officeproject.MyPreferance;

import android.content.Context;
import android.content.SharedPreferences;

public class MysharedPreferance {


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor ;
    private static MysharedPreferance mysharedPreferance=null;

    private MysharedPreferance(Context context)
    {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }


  public static synchronized MysharedPreferance getPreferences(Context context)
  {

      if(mysharedPreferance==null) {
          mysharedPreferance = new MysharedPreferance(context);
      }

      return mysharedPreferance;
  }


    public void setName(String user)
    {
        editor.putString("login",user);
        editor.apply();
    }

    public String getName()
    {
        return sharedPreferences.getString("login","none");
    }


    public void setViewType(String viewType)
    {
        editor.putString("viewtype",viewType);
        editor.apply();
    }

    public String getViewType()
    {
       return sharedPreferences.getString("viewtype","grid");
       // return sharedPreferences.getString("viewtype","list");
    }


    public  void setAppName(String appName)
    {
        editor.putString("phone",appName);
        editor.apply();

    }


    public String getAppName(){
        return  sharedPreferences.getString("appName","FamilyMart");
    }




    public  void setPhone(String phone)
    {
        editor.putString("phone",phone);
        editor.apply();

    }


    public String getMobile(){
        return  sharedPreferences.getString("mobile","none");
    }

    public  void setMobile(String mobile)
    {
        editor.putString("mobile",mobile);
        editor.apply();

    }

    public String getAnotherPhone(){
        return  sharedPreferences.getString("anotherphone","");
    }

    public  void setAnotherPhone(String anotherphone)
    {
        editor.putString("anotherphone",anotherphone);
        editor.apply();

    }


    public String getUserId(){
        return  sharedPreferences.getString("userid","");
    }

    public  void setUserId(String userid)
    {
        editor.putString("userid",userid);
        editor.apply();

    }



    public String getPhone(){
        return  sharedPreferences.getString("phone","");
    }


    public  void setAddress(String address)
    {
        editor.putString("address",address);
        editor.apply();

    }

    public String getAddress(){return  sharedPreferences.getString("address","");}


    public  void setEmail(String email)
    {
        editor.putString("email",email);
        editor.apply();

    }

    public String getemail(){return  sharedPreferences.getString("email","");}








}
