package com.example.songlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    Button btnI, btnSL;
    EditText etTitle, etSingers, etYear;
    RadioGroup rg;
    RadioButton rb;

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

        btnI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                int selectedId=rg.getCheckedRadioButtonId();
                rb=(RadioButton)findViewById(selectedId);
                db.insertTask(etTitle.getText().toString(), etSingers.getText().toString(), Integer.parseInt(etYear.getText().toString()), Integer.parseInt(rb.getText().toString()));
            }
        });

        btnSL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, displayRecords.class));
            }
        });
    }
}