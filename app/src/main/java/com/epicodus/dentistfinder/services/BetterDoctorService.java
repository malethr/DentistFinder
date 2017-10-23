package com.epicodus.dentistfinder.services;

import com.epicodus.dentistfinder.Constants;
import com.epicodus.dentistfinder.models.Dentist;

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
                String name = dentistJSON.getJSONArray("practices").getJSONObject(0).getString("name");
                String website;
                try {
                    website = dentistJSON.getJSONArray("practices").getJSONObject(0).getString("website");
                } catch (JSONException e) {
                    website = "";
                }
                ArrayList<String> phone = new ArrayList<>();
                JSONArray phoneJSON = dentistJSON.getJSONArray("practices").getJSONObject(0).getJSONArray("phones");
                for (int j = 0; j < phoneJSON.length(); j++) {
                    String type = phoneJSON.getJSONObject(j).getString("type");
                    if(type == "landline"){
                        String jphone = phoneJSON.getJSONObject(j).getString("phone");
                        phone.add(jphone);
                    }
                }
                String city = dentistJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("city");
                String zip = dentistJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("zip");
                String state = dentistJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("state");
                String street = dentistJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street");

                String imageUrl = dentistJSON.getJSONObject("profile").getString("image_url");
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
