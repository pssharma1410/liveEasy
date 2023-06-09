package com.example.liveeasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class userHindi extends AppCompatActivity {
    boolean ship = false;
    boolean trans = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_hindi);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        LinearLayout lay = (LinearLayout) findViewById(R.id.lay);
        LinearLayout lay2 = (LinearLayout) findViewById(R.id.lay2);
        lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(trans){
                    trans = false;
                    lay2.setBackgroundResource(R.drawable.border);
                }
                if(ship){
                    lay.setBackgroundResource(R.drawable.border);
                    ship = false;
                    return;
                }
                lay.setBackgroundResource(R.drawable.big_border);
                ship = true;
            }
        });
        lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ship){
                    ship = false;
                    lay.setBackgroundResource(R.drawable.border);
                }
                if(trans){
                    lay2.setBackgroundResource(R.drawable.border);
                    trans = false;
                    return;
                }
                lay2.setBackgroundResource(R.drawable.big_border);
                trans = true;
            }
        });
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(userHindi.this, "NOT AVAILABLE", Toast.LENGTH_SHORT).show();
            }
        });
    }
}