package com.example.yoyoiq.common;

import static android.util.Log.wtf;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.yoyoiq.CreateTeamActivity;
import com.example.yoyoiq.InSideContestActivityFragments.myAllTeamRequest;
import com.example.yoyoiq.KYC.KycAddedPostResponse;
import com.example.yoyoiq.Model.AllSelectedPlayer;
import com.example.yoyoiq.Retrofit.ApiClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HelperData {
    public static MutableLiveData<Integer> playerCounter = new MutableLiveData<>(0);
    public static String type_selected = "Cricket";
    public static MutableLiveData<Integer> conty1 = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> TeamCount = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> conty2 = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> wk = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> bat = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> ar = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> bowl = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> myTeam = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> myContest = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> Selectedcap = new MutableLiveData<>(0);
    public static MutableLiveData<Integer> selectedVcap = new MutableLiveData<>(0);
    public static MutableLiveData<Double> creditCounter = new MutableLiveData<>(0.0);
    public static MutableLiveData<List<AllSelectedPlayer>> allSelectedPlayer = new MutableLiveData<>();

    public static MutableLiveData<String> refreashLive = new MutableLiveData<>();
    public static MutableLiveData<String> selectedPlayer = new MutableLiveData<>();
    public static MutableLiveData<String> getCall_For_Refrashers = new MutableLiveData<>();

    public static String selected = "wk";
    public static ArrayList<AllSelectedPlayer> myTeamList = new ArrayList<>();
    public static ArrayList<myAllTeamRequest> myCountyPlayer = new ArrayList<>();
    public static String team1NameShort = "";
    public static String team2NameShort = "";
    public static boolean teamEdt = false;
    public static int limit = 11;
    public static boolean lineUp = false;
    public static int selectedTeamNo;
    public static boolean vcap = false;
    public static boolean cap = false;
    public static String addedPlayerIds;
    public static String matchId;
    public static String contestId;
    public static String UserId = "";
    public static String UserName = "";
    public static String Usermobile = "";
    public static String UserEmail = "";
    public static  String logoUrlTeamA="";
    public static  String logoUrlTeamB="";
    public static  String MatchStartTime="";
    public static  String MatchEndTime="";


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
        Selectedcap.setValue(0);
        selectedVcap.setValue(0);
        creditCounter.setValue(100.0);
        vcap = false;
        cap = false;
        CreateTeamActivity.addedPlayerIds = "";
    }

    public static void uploadFile(Context context,String user_Id, String fullName, String accountNo, String ifsc, String bankName,  String aadhar, String pan, String pan_img_path){
        MultipartBody.Part fileToUpload1 = null;

        File myFile1 = new File(pan_img_path);

        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), myFile1);
        MultipartBody.Part  fileToUpload2 = MultipartBody.Part.createFormData("pan_img", myFile1.getName(), requestBody1);
        Log.d("Amit","1 "+user_Id);
        Log.d("Amit","2 "+fullName);
        Log.d("Amit","3 "+accountNo);
        Log.d("Amit","4 "+pan_img_path);


        RequestBody user_id = RequestBody.create(MediaType.parse("multipart/form-data"), user_Id);
        RequestBody full_name = RequestBody.create(MediaType.parse("multipart/form-data"), fullName);
        RequestBody account_no = RequestBody.create(MediaType.parse("multipart/form-data"), accountNo);
        RequestBody ifsc_code = RequestBody.create(MediaType.parse("multipart/form-data"), ifsc);
        RequestBody bank_name = RequestBody.create(MediaType.parse("multipart/form-data"), bankName);
        RequestBody aadhar_no = RequestBody.create(MediaType.parse("multipart/form-data"), aadhar);
        RequestBody pancard_no = RequestBody.create(MediaType.parse("multipart/form-data"), pan);

        Call<KycAddedPostResponse> call=ApiClient.getInstance().getApi().sendKycDetailsOnServer(user_id,full_name,account_no,
                ifsc_code,bank_name,aadhar_no,pancard_no,fileToUpload2);

        call.enqueue(new Callback<KycAddedPostResponse>() {
            @Override
            public void onResponse(Call<KycAddedPostResponse> call, Response<KycAddedPostResponse> response) {
                KycAddedPostResponse kycAddedPostResponse= response.body();
                Log.d("Amit","Value Check Here "+response);
                if(response.isSuccessful()){
                    if(kycAddedPostResponse!=null){

                    }
                    else{

                    }

                }


            }

            @Override
            public void onFailure(Call<KycAddedPostResponse> call, Throwable t) {
                Log.d("Hulk","Value check "+""+t);

            }
        });



    }
}
