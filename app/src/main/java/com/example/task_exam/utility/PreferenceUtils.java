package com.example.task_exam.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.task_exam.model.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PreferenceUtils {

    public static final String PREFS_NAME = "Test";
    public static final String MAIN = "Main";
    public static final String USER = "User";
    public static final String USER_EMAIL = "User_Email";
    public static final String USER_MOBILE = "User_Mobile";

    public static void saveUserData(Context context, String email, String mobile) {
        SharedPreferences.Editor editor = context.getSharedPreferences(USER, Context.MODE_PRIVATE).edit();
        editor.putString(USER_EMAIL, email);
        editor.putString(USER_MOBILE, mobile);
        editor.apply();

    }
    public static Map<String, String> getUserData(Context context) { //other paramaters are only usefull if you want provide default values
        SharedPreferences sharedPrefs = context.getSharedPreferences(USER, Context.MODE_PRIVATE);
        Map<String, String> userDetails = new HashMap<String,String>(); //create the map with String->String

        String email = sharedPrefs.getString(USER_EMAIL, ""); //get the value
        userDetails.put(USER_EMAIL, email); //put it in the map
        String mobile = sharedPrefs.getString(USER_MOBILE, "");
        userDetails.put(USER_MOBILE, mobile);
        return userDetails; //return the map
    }

    public static void saveUsers(Context context, ArrayList<Item> users) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(users);

        editor.putString(MAIN, jsonFavorites);

        editor.commit();
    }

    public static ArrayList<Item> getUsers(Context context) {
        SharedPreferences settings;
        settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String users = settings.getString(MAIN,null);

        Gson gson = new Gson();

        ArrayList<Item> usersList= (ArrayList<Item>) gson.fromJson(users,
                new TypeToken<ArrayList<Item>>() {
                }.getType());

        return usersList;
    }
}
