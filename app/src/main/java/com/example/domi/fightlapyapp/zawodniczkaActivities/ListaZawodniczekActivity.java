package com.example.domi.fightlapyapp.zawodniczkaActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.domi.fightlapyapp.R;

import java.util.ArrayList;

import databaseHandler.DatabaseHandler;
import zawodniczka.*;

/**
 * Created by Dominika Saide on 2017-11-05.
 */

public class ListaZawodniczekActivity extends AppCompatActivity {

    private ListView listaZawodniczekLV;
    Intent i;
    private String[] listaZawodniczek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_zawodniczek);

        i= new Intent(getApplicationContext(), WybranaZawodniczkaZListyActivity.class);

        listaZawodniczekLV = (ListView) findViewById(R.id.listazawodniczek_list_view);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<Zawodniczka> zawodniczkiList = db.getWszystkieZawodniczki();
        db.close();

        ZawodniczkaObliczenia zawodniczkaObliczenia = new ZawodniczkaObliczenia();
        if(!zawodniczkiList.isEmpty()) {
            listaZawodniczek = zawodniczkaObliczenia.getListaZawodniczek(zawodniczkiList);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaZawodniczek);
            this.listaZawodniczekLV.setAdapter(adapter);
        }

        this.listaZawodniczekLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = ((TextView) view).getText().toString();
                i.putExtra("imie i nazwisko", product);
                startActivity(i);
            }
        });
    }
}