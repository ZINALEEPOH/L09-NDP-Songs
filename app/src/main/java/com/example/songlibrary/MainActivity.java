package com.example.songlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.songlibrary.DBHelper;
import com.example.songlibrary.Song;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnI, btnSL;
    EditText etTitle, etSingers, etYear;
    RadioGroup rg;
    RadioButton rb;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnI=findViewById(R.id.btnInsert);
        btnSL=findViewById(R.id.btnShowList);
        etTitle=findViewById(R.id.editTextTitle);
        etSingers=findViewById(R.id.editTextSingers);
        etYear=findViewById(R.id.editTextYear);
        rg=findViewById(R.id.radioGroup);
        rb=findViewById(R.id.radioButton1);

        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);

        btnI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers=etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int selectedId=rg.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(selectedId);
                int stars=Integer.parseInt(rb.getText().toString());
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singers, year, stars);

                if (inserted_id != -1){
                    al.clear();
                    al.addAll(dbh.getAllSongs());
                    aa.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DisplayRecords.class));
            }
        });

    }
}