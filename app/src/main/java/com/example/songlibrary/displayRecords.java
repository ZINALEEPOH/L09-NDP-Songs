package com.example.songlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class displayRecords extends AppCompatActivity {

    ListView lvRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);

        lvRecords=findViewById(R.id.listViewRecords);
        ArrayAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Song.songs);
        lvRecords.setAdapter(adapter);

        DBHelper db = new DBHelper(displayRecords.this);
        db.getRecords().clear();
        db.getRecords();
        db.close();

    }
}