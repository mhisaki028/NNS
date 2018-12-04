package imn.dev.androidpatientapp;

import android.content.Intent;
import android.content.pm.SigningInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class UserSettingActivity extends AppCompatActivity {
    EditText edtNewName, edtNewEmail, edtNewPassword;
    ImageView btnSave;
    ProgressBar progressBar;

    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener authStateListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        edtNewName = (EditText) findViewById(R.id.edtNewName);
        edtNewEmail = (EditText) findViewById(R.id.edtNewEmail);
        edtNewPassword = (EditText)findViewById(R.id.edtNewPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        final FirebaseUser user = auth.getCurrentUser();

        authStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user == null){
                    finish();
                    startActivity(new Intent(UserSettingActivity.this, SignInActivity.class));
                }
            }
        };

        edtNewName.setText(user.getDisplayName());
        edtNewEmail.setText(user.getEmail());
        btnSave = (ImageView) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newemail = edtNewEmail.getText().toString().trim();
                String newpassword = edtNewPassword.getText().toString().trim();
                String newname = edtNewName.getText().toString().trim();

                if(TextUtils.isEmpty(newemail)){
                    edtNewEmail.setBackgroundResource(R.drawable.edt_signin_error);
                    edtNewEmail.setHint("Enter email");
                    edtNewEmail.setHintTextColor(Color.parseColor("#D03E2F"));
                    return;

                }
                if(TextUtils.isEmpty(newpassword)){
                    edtNewEmail.setBackgroundResource(R.drawable.edt_signin_error);
                    edtNewEmail.setHint("Enter password");
                    edtNewEmail.setHintTextColor(Color.parseColor("#D03E2F"));
                    return;

                }

                if(TextUtils.isEmpty(newname)){
                    edtNewEmail.setBackgroundResource(R.drawable.edt_signin_error);
                    edtNewEmail.setHint("Enter name");
                    edtNewEmail.setHintTextColor(Color.parseColor("#D03E2F"));
                    return;
                }


                progressBar.setVisibility(View.VISIBLE);

                    user.updateEmail(newemail)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(UserSettingActivity.this, "Email Address Updated!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                    else{
                                        Toast.makeText(UserSettingActivity.this, "Failed to update email!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });

                    user.updatePassword(newpassword)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(UserSettingActivity.this, "Password Updated!", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);

                                    }
                                    else{
                                        Toast.makeText(UserSettingActivity.this, "Failed to update password!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });

                UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                        .setDisplayName(newname)
                        .build();
                user.updateProfile(profileChangeRequest);

                signOut();





            }
        });


    }

    public void signOut(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(UserSettingActivity.this, SignInActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }

}
