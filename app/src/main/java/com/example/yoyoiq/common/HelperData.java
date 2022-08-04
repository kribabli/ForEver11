package com.example.yoyoiq.common;

import androidx.lifecycle.MutableLiveData;

import com.example.yoyoiq.CreateTeamActivity;
import com.example.yoyoiq.Model.AllSelectedPlayer;

import java.util.ArrayList;
import java.util.List;

public class HelperData {
    public static MutableLiveData<Integer> playerCounter = new MutableLiveData<>(0);
    public static String type_selected = "Cricket";
    public static MutableLiveData<Integer> conty1 = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> conty2 = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> wk = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> bat = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> ar = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> bowl = new MutableLiveData<>(0);
    public static MutableLiveData<Double> creditCounter = new MutableLiveData<>(0.0);
    public static MutableLiveData<List<AllSelectedPlayer>> allSelectedPlayer = new MutableLiveData<>();

    public static String selected = "wk";
    public static ArrayList<AllSelectedPlayer> myTeamList = new ArrayList<>();
    public static String team1NameShort = "";
    public static String team2NameShort = "";
    public static boolean teamEdt = false;
    public static int limit = 11;
    public static int selectedTeamNo;
    public static String vcap = "";
    public static String cap = "";
    public static String addedPlayerIds;

    public static void newTeamMaking() {
        myTeamList.clear();
        selected = "wk";
        wk.setValue(0);
        playerCounter.setValue(0);
        bat.setValue(0);
        ar.setValue(0);
        bowl.setValue(0);
        conty1.setValue(0);
        conty2.setValue(0);
        creditCounter.setValue(100.0);
        CreateTeamActivity.addedPlayerIds = "";
    }
}
