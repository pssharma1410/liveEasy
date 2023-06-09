package com.example.liveeasy;

//import static android.content.ContentValues.TAG;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Spinner;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthOptions;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.hbb20.CountryCodePicker;
//
//import java.util.concurrent.TimeUnit;
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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import org.intellij.lang.annotations.Language;

import java.util.concurrent.TimeUnit;

public class start extends AppCompatActivity {
    protected static FirebaseAuth mAuth;
    protected static PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    protected static String otpverify;
    protected static String mobilenum;
    protected  static EditText mobile;
    protected static CountryCodePicker countryCodePicker;
    protected static PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private Button continueb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ImageView img = findViewById(R.id.imageView6);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(start.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        mobile = findViewById(R.id.editTextPhone);
        mAuth = FirebaseAuth.getInstance();
        continueb = findViewById(R.id.button2);
        countryCodePicker = findViewById(R.id.countryCodePicker);
        continueb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countryCode = "+"+countryCodePicker.getSelectedCountryCode();
                mobilenum = countryCode+mobile.getText().toString();
                Log.i(mobilenum, "onCreate: ");
                if(mobilenum.length() != 13){
                    Toast.makeText(start.this, "Mobile number should be of 10 digits", Toast.LENGTH_SHORT).show();
                    return;
                }
                otpSend();
            }
        });
    }
    public void otpSend(){

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                Log.i(TAG, "onVerificationCompleted: ");
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.i(TAG, "onVerificationFailed: "+ e.toString());

                Toast.makeText(start.this, "OTP couldn't be sent", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                otpverify = verificationId;
                forceResendingToken = token;
                Intent intent = new Intent(start.this,otp.class);
                startActivity(intent);
                Log.i(TAG, "onCodeSent:" + verificationId);

            }
        };
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(mobilenum)       // Phone number to verify
                        .setTimeout(0L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(start.this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.i(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Log.i(TAG, "end");
                            }
                        }
                    }
                });
    }
}