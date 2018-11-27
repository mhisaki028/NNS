package imn.dev.androidpatientapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Size;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.Patient;

public class SignUpActivity extends AppCompatActivity {

    private EditText edtEmail, edtPassword, edtName, edtPhone;
    private Button btnRegister, btndisabled;
    private CheckBox checkBox;
    private ProgressBar progressBar;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Patients");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_signup);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        /*if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }*/

        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btndisabled = (Button) findViewById(R.id.btndisabled);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtName = (EditText) findViewById(R.id.edtName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        checkBox = (CheckBox) findViewById(R.id.checkbox_signup);

        btndisabled.setEnabled(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    btnRegister.setVisibility(View.VISIBLE);
                    btndisabled.setVisibility(View.GONE);
                }
                else{
                    btndisabled.setVisibility(View.VISIBLE);
                    btnRegister.setVisibility(View.GONE);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                final String name = edtName.getText().toString().trim();
                final String phone = edtPhone.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    edtName.setBackgroundResource(R.drawable.edt_signin_error);
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                   edtEmail.setBackgroundResource(R.drawable.edt_signin_error);
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    edtPhone.setBackgroundResource(R.drawable.edt_signin_error);
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                   edtPassword.setBackgroundResource(R.drawable.edt_signin_error);
                    return;
                }

                if (password.length() < 6) {
                    edtPassword.setBackgroundResource(R.drawable.edt_signin_error);
                    return;
                }

                try{
                    InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
                catch (Exception e){

                }

                edtEmail.setBackgroundResource(R.drawable.edt_signup_bg);
                edtPassword.setBackgroundResource(R.drawable.edt_signup_bg);
                edtName.setBackgroundResource(R.drawable.edt_signup_bg);
                edtPhone.setBackgroundResource(R.drawable.edt_signup_bg);
                progressBar.setVisibility(View.VISIBLE);
                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                                Patient patient = new Patient();
                                patient.setEmail(edtEmail.getText().toString());
                                patient.setPassword(edtPassword.getText().toString());
                                patient.setName(edtName.getText().toString());
                                patient.setPhone(edtPhone.getText().toString());

                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(patient)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                FirebaseUser user = auth.getCurrentUser();

                                                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                                                        .setDisplayName(name).build();


                                                user.updateProfile(profileChangeRequest);

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });

                                progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Snackbar.make(rootLayout, "Authentication failed." + task.getException(),
                                            Snackbar.LENGTH_SHORT).show();
                                } else {
                                    finish();
                                    startActivity(new Intent(SignUpActivity.this, BaseActivity.class));
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                                }
                            }
                        });
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }


}
