package com.example.freshfoodapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.freshfoodapp.Models.User;

public class SharedPrefManager {
    private  static final  String SHARED_PREF_NAME =  "volleyregisterlogin";
    private  static final  String KEY_NAME =  "keyname";
    private  static final  String KEY_EMAIL =  "keyemail";
//    private  static final  String KEY_ACCOUNT =  "keyaccount";
    private  static final  String KEY_ID =  "keyid";

    private  static final  String KEY_AVATAR =  "keyavatar";
    private  static SharedPrefManager mInstance;
    private static Context ctx;

    public SharedPrefManager(Context context) {
        ctx=context;
    }

    public  static synchronized  SharedPrefManager getInstance(Context context){
        if(mInstance==null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin(User user){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(KEY_ID,user.getId());
        editor.putString(KEY_NAME,user.getName());
        editor.putString(KEY_EMAIL,user.getEmail());
//        editor.putInt(KEY_ACCOUNT,user.getAccount());
        if(/*user.getAvatar().toString().isEmpty() || */user.getAvatar()==null)
            user.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSODfgXVmuk7-PD1wdcMoU4IvvUZhlu7fiU1w&usqp=CAU");
        editor.putString(KEY_AVATAR,user.getAvatar());
        editor.apply();
    }

    public void userSignUp(User user){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt(KEY_ID,user.getId());
        editor.putString(KEY_NAME,user.getName());
        editor.putString(KEY_EMAIL,user.getEmail());
//        editor.putInt(KEY_ACCOUNT,user.getAccount());
        user.setAvatar("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSODfgXVmuk7-PD1wdcMoU4IvvUZhlu7fiU1w&usqp=CAU");
        editor.putString(KEY_AVATAR,user.getAvatar());
        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME,null)!=null;
    }

    public  User getUser(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return  new User(
                sharedPreferences.getInt(KEY_ID,-1),
//        sharedPreferences.getInt(KEY_ACCOUNT,-1),
        sharedPreferences.getString(KEY_NAME,null),
        sharedPreferences.getString(KEY_EMAIL,null),
        sharedPreferences.getString(KEY_AVATAR,null)
        );
    }

    public void  logout(){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
    }


}
