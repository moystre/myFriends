package com.easv.myfriends.myfriends;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailsActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText addressEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText birthDayEditText;
    private EditText websiteEditText;
    private Button setHomeButton;
    private Button showOnMapButton;
    private Button callButton;
    private Button messageButton;
    private Button saveFriendButton;
    private Button deleteFriendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    public void setHome(View v) {}

    public void showOnMap(View v){}

    public void call(View v){}

    public void message(View v){}

    public void saveFriend(View v){}

    public void deleteFriend(View v){}
}
