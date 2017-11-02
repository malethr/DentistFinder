package com.epicodus.dentistfinder;

public class Constants {
    public static final String BETTER_DOCTOR_QUERY_PARAMETER = "query";
    public static final String BETTER_DOCTOR_URL = "https://api.betterdoctor.com/2016-03-01/doctors?specialty_uid=dentist&location=wa-vancouver&sort=best-match-asc&skip=0&limit=50&user_key=9e18d0234fa6476718599583032121ca";
    public static final String PREFERENCES_LOCATION_KEY = "location";
    public static final String FIREBASE_CHILD_SEARCHED_LOCATION = "searchedLocation";
    public static final String FIREBASE_CHILD_DENTISTS = "dentists";
    public static final String FIREBASE_QUERY_INDEX = "index";
    public static final String EXTRA_KEY_POSITION = "position";
    public static final String EXTRA_KEY_DENTISTS = "dentists";
    public static final String KEY_SOURCE = "source";
    public static final String SOURCE_SAVED = "saved";
    public static final String SOURCE_FIND = "find";
}
