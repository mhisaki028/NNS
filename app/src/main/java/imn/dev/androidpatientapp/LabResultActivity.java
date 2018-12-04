package imn.dev.androidpatientapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.LabService;
import imn.dev.androidpatientapp.Model.Patient;
import imn.dev.androidpatientapp.Model.Results;
import imn.dev.androidpatientapp.Model.Reviews;
import retrofit2.http.Url;

public class LabResultActivity extends AppCompatActivity {

    DatabaseReference databaseResultsReference;

    ListView listViewResults;
    List<Results> resultsList;
    RelativeLayout rootLayout;
    ResultsList adapter;

    FirebaseAuth auth;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;

    EditText btnlike, btndislike, reviewcontent;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labresult);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_results);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Reviews");

        final FirebaseUser user = auth.getCurrentUser();

        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
        View popUpView = inflater.inflate(R.layout.popup_addreview, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                LabResultActivity.this);

        alertDialogBuilder.setView(popUpView);

        reviewcontent = (EditText)popUpView.findViewById(R.id.reviewcontent);
        final String patient_name = user.getDisplayName();
        btnlike = (EditText)popUpView.findViewById(R.id.btnlike);
        btndislike = (EditText)popUpView.findViewById(R.id.btndislike);

        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnlike.setBackgroundResource(R.drawable.edt_selected);
                btnlike.setHintTextColor(Color.parseColor("#27A9A2"));
                btndislike.setBackgroundResource(R.drawable.edt_signup_bg);
                btndislike.setHintTextColor(Color.parseColor("#9A9A9A"));

            }
        });

        btndislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnlike.setBackgroundResource(R.drawable.edt_signup_bg);
                btnlike.setHintTextColor(Color.parseColor("#9A9A9A"));
                btndislike.setBackgroundResource(R.drawable.edt_selected);
                btndislike.setHintTextColor(Color.parseColor("#27A9A2"));

            }
        });
        String rate = "";
        if(btnlike.isSelected() == true){
            rate = "Like";

        }
        else{
            rate = "Dislike";
        }
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);

        String monthname = (String) android.text.format.DateFormat.format("MMMM", new Date());
        final String date = day + " " + monthname;

        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8:00"));
        Date currentLocalTIme = cal.getTime();
        DateFormat mydate = new SimpleDateFormat("KK:mm");
        mydate.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        final String time = mydate.format(currentLocalTIme);


        final String finalRate = rate;
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("Post", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Reviews reviews = new Reviews();
                        reviews.setPatient_name(patient_name);
                        reviews.setTxtBody(reviewcontent.getText().toString());
                        reviews.setTxtDay(date);
                        reviews.setTxtTime(time);
                        reviews.setRating(finalRate);

                       DatabaseReference newRef = reference.push();
                       newRef.setValue(reviews);

                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();

        databaseResultsReference = FirebaseDatabase.getInstance().getReference("Results");
        listViewResults = (ListView)findViewById(R.id.listview_results);
        resultsList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseResultsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                resultsList.clear();

                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){
                    Results results = serviceSnapshot.getValue(Results.class);

                    resultsList.add(results);
                }

                adapter = new ResultsList(LabResultActivity.this, resultsList);
                listViewResults.setAdapter(adapter);

                listViewResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Results results = (Results) parent.getItemAtPosition(position);
                        String url = results.getUrl();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);

                    }
                });

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
