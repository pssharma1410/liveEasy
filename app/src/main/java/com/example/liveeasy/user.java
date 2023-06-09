package com.example.liveeasy;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;

public class user extends AppCompatActivity {
    boolean ship = false;
    boolean trans = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
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
                Toast.makeText(user.this, "NOT AVAILABLE", Toast.LENGTH_SHORT).show();
            }
        });
    }

}