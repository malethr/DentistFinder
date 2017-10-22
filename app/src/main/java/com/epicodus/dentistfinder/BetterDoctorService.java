package com.epicodus.dentistfinder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class BetterDoctorService {

    public static void findDentists(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();
        String url = Constants.BETTER_DOCTOR_URL;

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }
}
