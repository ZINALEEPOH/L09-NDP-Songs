package com.example.songlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ManageSongs extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rg;
    RadioButton rb;
    Button btnUpd, btnDel, btnCnc;
    Song selectedSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_songs);

        etID=findViewById(R.id.editTextID);
        etTitle=findViewById(R.id.editTextTitle);
        etSingers=findViewById(R.id.editTextSingers);
        etYear=findViewById(R.id.editTextYear);
        rg=findViewById(R.id.radioGroup);
        btnUpd=findViewById(R.id.btnUpdate);
        btnDel=findViewById(R.id.btnDelete);
        btnCnc=findViewById(R.id.btnCancel);

        Intent i=getIntent();
        if (i != null) {
            selectedSong = (Song) i.getSerializableExtra("selectedSong");
            if (selectedSong != null) {
//                etID.setText(selectedSong.getId());
//                etID.setEnabled(false);
                etTitle.setText(selectedSong.getTitle());
                etSingers.setText(selectedSong.getSingers());
                etYear.setText(String.valueOf(selectedSong.getYear()));

                int stars = selectedSong.getStars();
                switch (stars) {
                    case 1:
                        rb = findViewById(R.id.radioButton1);
                        break;
                    case 2:
                        rb = findViewById(R.id.radioButton2);
                        break;
                    case 3:
                        rb = findViewById(R.id.radioButton3);
                        break;
                    case 4:
                        rb = findViewById(R.id.radioButton4);
                        break;
                    case 5:
                        rb = findViewById(R.id.radioButton5);
                        break;
                }

                if (rb != null) {
                    rb.setChecked(true);
                }
            }
        }

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ManageSongs.this);
                selectedSong.setId(Integer.parseInt(etID.getText().toString()));
                dbh.updateSong(selectedSong);
                dbh.close();
                finish();
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ManageSongs.this);
                dbh.deleteSong(selectedSong.getId());
                finish();
            }
        });

        btnCnc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}