package com.example.domi.fightlapyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.domi.fightlapyapp.*;
import com.example.domi.fightlapyapp.zawodniczkaActivities.WyborZawodniczkiActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import android.view.View;

public class FacebookActivity extends AppCompatActivity {

    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        textView=(TextView) findViewById(R.id.textView);
        callbackManager=CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                textView.setText(getString(R.string.udane_logowanie));
                Log.i("*** Logowanie ", "ok***");
                logowanieOk();
            }

            @Override
            public void onCancel() {
                textView.setText(getString(R.string.logowanie_anulowane));
                Log.i("*** Logowanie ", "anulowane***");

            }

            @Override
            public void onError(FacebookException error) {
                textView.setText(getString(R.string.nieudane_logowanie));
                Log.i("*** Logowanie ", "nieudane***");

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,requestCode,data);
    }

    public void logowanieOk(){
        Intent intent = new Intent(this, AfterLoggingActivity.class);
        startActivity(intent);
    }

}
