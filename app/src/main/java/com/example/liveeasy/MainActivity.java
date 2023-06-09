package com.example.liveeasy;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
     int lang;
//    protected static FirebaseAuth mAuth;
//    protected static PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
//    protected static String otpverify;
//    protected static String mobilenum;
//    protected  static EditText mobile;
//    protected static CountryCodePicker countryCodePicker;
//    protected static PhoneAuthProvider.ForceResendingToken forceResendingToken;
//    private Button continueb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Spinner spinner = findViewById(R.id.spinner2);
        Button next = findViewById(R.id.button3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.languages, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lang = i;
                Log.i(TAG, "onItemSelected: "+i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lang == 0){
                    Intent intent = new Intent(MainActivity.this,start.class);
                    startActivity(intent);
                    return;
                }else{
                    Intent intent = new Intent(MainActivity.this,startHindi.class);
                    Log.i(TAG, "onClick: "+"idhr hi hu");
                    startActivity(intent);
                    return;
                }
            }
        });

    }
}