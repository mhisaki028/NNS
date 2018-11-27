package imn.dev.androidpatientapp;

import android.content.Intent;
import android.content.pm.SigningInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SignInActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtPhone, edtCode;
    private Button btnSignUp, btnSignIn, btnForgotPassword, btnUsePhone, btnSend, btnSignInPhone, btnUseEmail;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    RelativeLayout rootLayout, relemail, relphone;

    String codeSent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();



        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        relemail = (RelativeLayout) findViewById(R.id.relemail);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnUsePhone = (Button) findViewById(R.id.btnUsePhone);
        btnForgotPassword = (Button) findViewById(R.id.btnForgotPassword);

        relphone = (RelativeLayout) findViewById(R.id.relphone);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtCode = (EditText) findViewById(R.id.edtCode);
        btnSend = (Button) findViewById(R.id.btnSend);
        btnSignInPhone = (Button) findViewById(R.id.btnSignInPhone);
        btnUseEmail = (Button) findViewById(R.id.btnUseEmail);

        btnUsePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relemail.setVisibility(View.GONE);
                relphone.setVisibility(View.VISIBLE);

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSend.setBackgroundResource(R.drawable.btnsignin_bg);
                sendVerificationCode();
            }
        });

        btnSignInPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = edtPhone.getText().toString();
                String code = edtCode.getText().toString();
                if(phone.isEmpty()){
                    edtPhone.setBackgroundResource(R.drawable.edt_signin_error);
                }

                if(code.isEmpty()){
                    edtCode.setBackgroundResource(R.drawable.edt_signin_error);
                }

                verifySignInCode();
            }
        });

        btnUseEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relemail.setVisibility(View.VISIBLE);
                relphone.setVisibility(View.GONE);
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SignInActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                final String password = edtPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    edtEmail.setBackgroundResource(R.drawable.edt_signin_error);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                   edtPassword.setBackgroundResource(R.drawable.edt_signin_error);
                    return;
                }

                try{
                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                catch (Exception e){

                }

                edtEmail.setBackgroundResource(R.drawable.edt_signin_bg);
                edtPassword.setBackgroundResource(R.drawable.edt_signin_bg);
                progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        Snackbar.make(rootLayout, "Password too short!", Snackbar.LENGTH_SHORT).show();
                                    } else {
                                        Snackbar.make(rootLayout, "Authentication failed", Snackbar.LENGTH_LONG).show();
                                    }
                                } else {
                                    finish();
                                    Intent intent = new Intent(SignInActivity.this, BaseActivity.class);
                                    startActivity(intent);
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                                }
                            }
                        });
            }
        });
    }

    private void verifySignInCode(){

        String code = edtCode.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
        progressBar.setVisibility(View.VISIBLE);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user = auth.getCurrentUser();
                            String phone = edtPhone.getText().toString();


                            Intent intent = new Intent(SignInActivity.this, BaseActivity.class);
                            intent.putExtra("phone", phone);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                        }
                        else {
                            if(task.getException() instanceof FirebaseAuthInvalidCredentialsException){

                            }
                        }
                    }
                });
    }

    private void sendVerificationCode(){

        String phone = edtPhone.getText().toString();

        if(phone.isEmpty()){
            edtPhone.setBackgroundResource(R.drawable.edt_signin_error);
            return;
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                this,
                mCallbacks
        );
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent = s;
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }


}

