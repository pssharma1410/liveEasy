package com.example.liveeasy;

import static android.content.ContentValues.TAG;

import static com.example.liveeasy.start.mCallbacks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class otp extends AppCompatActivity {

    Button button;
    TextView resendV;
    TextView numb;
    String codever;
    String otpAuto;
    private static final int REQ_USER_CONSENT = 200;
    SmsBroadcastReceiver smsBroadcastReceiver;
    EditText in1;
    EditText in2;
    EditText in3;
    EditText in4;
    EditText in5;
    EditText in6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        ImageView img = findViewById(R.id.imageView6);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(otp.this,start.class);
                startActivity(intent);
                finish();
            }
        });
        in1 = findViewById(R.id.editTextTextPersonName1);
        in2 = findViewById(R.id.editTextTextPersonName2);
        in3 = findViewById(R.id.editTextTextPersonName3);
        in4 = findViewById(R.id.editTextTextPersonName4);
        in5 = findViewById(R.id.editTextTextPersonName5);
        in6 = findViewById(R.id.editTextTextPersonName6);
        button = findViewById(R.id.button);
        resendV = findViewById(R.id.textView4);
        numb = findViewById(R.id.textView5);

        numb.setText("OTP has been sent to "+start.mobilenum.substring(3));
        in1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    in2.requestFocus();
                }else{
                    //nothing
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        in2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    in3.requestFocus();
                }else{
                    in1.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        in3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    in4.requestFocus();
                }
                else{
                    in2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        in4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    in5.requestFocus();
                }else{
                    in3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        in5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
                    in6.requestFocus();
                }else{
                    in4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        in6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()){
//                    in2.requestFocus();
                }else{
                    in5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                codever = in1.getText().toString() + in2.getText().toString() + in3.getText().toString() + in4.getText().toString() + in5.getText().toString() + in6.getText().toString();
                if(codever.equals("")){
                    Toast.makeText(otp.this, "Enter an OTP", Toast.LENGTH_SHORT).show();
                }else if(codever.length() != 6){
                    Toast.makeText(otp.this, "Enter complete OTP", Toast.LENGTH_SHORT).show();
                }else{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(start.otpverify, codever);
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
        resendV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendVerificationCode(start.mobilenum,start.forceResendingToken);
                Toast.makeText(otp.this, "OTP SENT !", Toast.LENGTH_SHORT).show();
            }
        });
        try{
            startSmsUserConsent();
        }catch(Exception e){
            Toast.makeText(this, "OTP counld'nt be auto verified", Toast.LENGTH_SHORT).show();
        }
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        start.mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i(TAG, "signInWithCredential:success");
                            Intent intent = new Intent(otp.this,user.class);
                            startActivity(intent);
                            // Update UI
                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(otp.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(otp.this, "Enter an OTP", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                0,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }
    private void startSmsUserConsent() {
        SmsRetrieverClient client = SmsRetriever.getClient(this);
        //We can add sender phone number or leave it blank
        // I'm adding null here
        client.startSmsUserConsent(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_USER_CONSENT) {
            if ((resultCode == RESULT_OK) && (data != null)) {
                //That gives all message to us.
                // We need to get the code from inside with regex
                String message = data.getStringExtra(SmsRetriever.EXTRA_SMS_MESSAGE);
//                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                Log.i(TAG, "onActivityResult: "+message);
                getOtpFromMessage(message);
                putInFieldsAndVerify(otpAuto);

            }
        }
    }
    private void getOtpFromMessage(String message) {
//         This will match any 6 digit number in the message
        Pattern pattern = Pattern.compile("(|^)\\d{6}");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find()) {

            otpAuto = matcher.group(0);
        }

    }
    private void registerBroadcastReceiver() {
        smsBroadcastReceiver = new SmsBroadcastReceiver();
        smsBroadcastReceiver.smsBroadcastReceiverListener =
                new SmsBroadcastReceiver.SmsBroadcastReceiverListener() {
                    @Override
                    public void onSuccess(Intent intent) {
                        startActivityForResult(intent, REQ_USER_CONSENT);
                    }
                    @Override
                    public void onFailure() {
                    }
                };
        IntentFilter intentFilter = new IntentFilter(SmsRetriever.SMS_RETRIEVED_ACTION);
        registerReceiver(smsBroadcastReceiver, intentFilter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        registerBroadcastReceiver();
    }
    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(smsBroadcastReceiver);
    }
    protected void putInFieldsAndVerify(String num){
        in1.setText(num.charAt(0)+"");
        in2.setText(num.charAt(1)+"");
        in3.setText(num.charAt(2)+"");
        in4.setText(num.charAt(3)+"");
        in5.setText(num.charAt(4)+"");
        in6.setText(num.charAt(5)+"");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(start.otpverify, num);
        signInWithPhoneAuthCredential(credential);
    }
}