package com.example.guardian;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ProfileSettings extends AppCompatActivity {

    private ListView settingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        settingList=findViewById(R.id.settingList);
        ArrayList<String> settings = new ArrayList<>();
        settings.add("Social Media platform Monitor");
        settings.add("Block Apps");
        settings.add("Screen Time Management");

        //adapter is used to fetch data for the list view

        ArrayAdapter<String> settingsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                settings
        );
        settingList.setAdapter(settingsAdapter);

        //Inorder to make the items in the viewList clickable
        settingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}