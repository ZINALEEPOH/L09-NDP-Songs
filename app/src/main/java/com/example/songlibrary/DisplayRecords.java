package com.example.songlibrary;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class DisplayRecords extends AppCompatActivity {

    ListView lvRecords;
    Button btnStars;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_records);
        btnStars=findViewById(R.id.btnStars);
        lvRecords=findViewById(R.id.listViewRecords);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);
        lvRecords.setAdapter(aa);

        DBHelper dbh = new DBHelper(DisplayRecords.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

//        btnStars.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DBHelper dbh = new DBHelper(DisplayRecords.this);
//                al.clear();
//                int stars =
//                if(stars != 0) {
//                    al.addAll(dbh.getAllSongs());
//                }
//                else{
//                    al.addAll(dbh.getAllSongs(stars));
//                }
//                aa.notifyDataSetChanged();
//            }
//
//        });

        lvRecords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song selectedSong = al.get(position);
                Intent i = new Intent(DisplayRecords.this, ManageSongs.class);
                i.putExtra("selectedSong", (Serializable) selectedSong);
                startActivity(i);

                DBHelper dbh = new DBHelper(DisplayRecords.this);
                al.clear();
                al.addAll(dbh.getAllSongs());
                aa.notifyDataSetChanged();
            }
        });
    }
}