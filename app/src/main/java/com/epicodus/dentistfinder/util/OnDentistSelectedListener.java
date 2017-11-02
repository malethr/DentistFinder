package com.epicodus.dentistfinder.util;

import com.epicodus.dentistfinder.models.Dentist;

import java.util.ArrayList;

public interface OnDentistSelectedListener {
    public void onDentistSelected(Integer position, ArrayList<Dentist> dentists, String source);
}
