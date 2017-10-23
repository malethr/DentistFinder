package com.epicodus.dentistfinder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BetterDoctorService {

    public static void findDentists(String location, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BETTER_DOCTOR_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BETTER_DOCTOR_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Dentist> processResults(Response response) {
        ArrayList<Dentist> dentists = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject betterDoctorJSON = new JSONObject(jsonData);
            JSONArray mainDentistsJSON = betterDoctorJSON.getJSONArray("data");
            for (int i = 0; i < mainDentistsJSON.length(); i++) {
                JSONObject dentistJSON = mainDentistsJSON.getJSONObject(i);
                JSONObject practiceJSON = dentistJSON.getJSONObject("practices");
                String name = practiceJSON.getString("name");
                String website = practiceJSON.getString("website");
                JSONObject addressJSON = practiceJSON.getJSONObject("visit_address");
                String street = addressJSON.getString("street");
                String city = addressJSON.getString("city");
                String state = addressJSON.getString("state");
                String zip = addressJSON.getString("zip");
                JSONObject phoneJson = practiceJSON.getJSONObject("phones");
                String phone = phoneJson.getString("phone");
                JSONObject profileJson = dentistJSON.getJSONObject("profile");
                String imageUrl = profileJson.getString("image_url");
                Dentist dentist = new Dentist(name, website, imageUrl, phone, street, city, state, zip);
                dentists.add(dentist);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return dentists;
    }
}
