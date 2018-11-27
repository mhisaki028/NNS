package imn.dev.androidpatientapp;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import imn.dev.androidpatientapp.Model.Patient;

public class ProfileFragment extends Fragment {
    RelativeLayout btnUserSetting, btnResult, btnHistory;
    ImageView btnLogout, img2, img1;
    TextView full_name, email_address, txtemail, txtsphone, txtsemail;
    ProgressBar progressBar;

    FirebaseAuth auth;
    DatabaseReference databaseReference;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, null);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_profile);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Patients");


        full_name = (TextView) view.findViewById(R.id.full_name);
        email_address = (TextView) view.findViewById(R.id.email_address);


        txtsphone = (TextView) view.findViewById(R.id.txtsphone);
        txtsemail = (TextView) view.findViewById(R.id.txtsemail);

        img2 = (ImageView) view.findViewById(R.id.img2);
        img1 = (ImageView) view.findViewById(R.id.img1);
        txtemail = (TextView) view.findViewById(R.id.txtemail);



        btnUserSetting = (RelativeLayout) view.findViewById(R.id.btnUserSetting);
        btnResult = (RelativeLayout) view.findViewById(R.id.btnResult);
        btnHistory = (RelativeLayout) view.findViewById(R.id.btnHistory);
        btnLogout = (ImageView) view.findViewById(R.id.btnLogout);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        loadUserInformation();



        btnUserSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), UserSettingActivity.class);
                startActivity(intent);
                ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), LabResultActivity.class);
                startActivity(intent);
                ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), BookingHistoryActivity.class);
                startActivity(intent);
                ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                FirebaseAuth.getInstance().signOut();
                ((AppCompatActivity)getActivity()).finish();
                startActivity(new Intent(view.getContext(), SignInActivity.class));

            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(auth.getCurrentUser() == null){
            ((AppCompatActivity)getActivity()).finish();
            startActivity(new Intent(getContext(), SignInActivity.class));
        }
    }

    private void loadUserInformation(){

        FirebaseUser user = auth.getCurrentUser();
        String phone = ((AppCompatActivity)getActivity()).getIntent().getStringExtra("phone");

        if(user != null){
            if(user.getDisplayName() !=null){
                full_name.setText(user.getDisplayName());
            }
            else{
                full_name.setText(phone);
            }

            if(user.getEmail() !=null){
                email_address.setText(user.getEmail());
                txtsemail.setVisibility(View.VISIBLE);
            }else{
                txtsemail.setVisibility(View.GONE);
                txtsphone.setVisibility(View.VISIBLE);
                btnUserSetting.setVisibility(View.GONE);
                img1.setVisibility(View.GONE);
                txtemail.setVisibility(View.GONE);
                email_address.setVisibility(View.GONE);

            }



        }




    }



}
