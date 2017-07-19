package com.example.domi.fightlapyapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Created by Domi on 2017-07-17.
 */

public class CustomOnItemSelectedListener implements OnItemSelectedListener{
    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
        Toast.makeText(parent.getContext(),
                "Wybrana pozycja : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
