package imn.dev.androidpatientapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.Bookings;

public class PatientDetailsActivity extends AppCompatActivity {

    EditText edtPatientName, edtPhone, edtStreet;
    Spinner spinnerBarangay, spinnerProvince, spinnerCity;
    Button btnContinue, btnuser, btnother;


    FirebaseAuth auth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    RequestQueue requestQueue;
    String insertUrl = "http://192.168.22.7/androidconn/insertBooking.php";
    String showUrl = "http://192.168.22.9/androidconn/showBooking.php";

    String province[] = {"Quezon"};
    String city[] = {"Lucena"};
    String barangay[] = {"Barangay 1 (Pob)", "Barangay 2 (Pob)", "Barangay 3 (Pob)", "Barangay 4 (Pob)", "Barangay 5 (Pob)",
            "Barangay 6 (Pob)", "Barangay 7 (Pob)", "Barangay 8 (Pob)", "Barangay 9 (Pob)", "Barangay 10 (Pob)", "Barra",
            "Bocohan", "Cotta", "Dalahican", "Domoit", "Gulang-Gulang", "Ibabang Dupay", "Ibabang Iyam", "Ibabang Talim",
            "Ilayang Dupay", "Ilayang Iyam", "Ilayang Talim", "Isabang", "MarketView", "Mayao Castillo", "Mayao Crossing",
            "Mayao Kanluran", "Mayao Parada", "Mayao Silangan", "Ransoham", "Talinas", "Talao-talao",};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patientdetails);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_patientdetails);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Bookings");

        requestQueue = Volley.newRequestQueue(getApplicationContext());


        edtPatientName = (EditText) findViewById(R.id.edtPatientName);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtStreet = (EditText) findViewById(R.id.edtStreet);

        spinnerBarangay = (Spinner) findViewById(R.id.spinnerBarangay);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        spinnerProvince = (Spinner) findViewById(R.id.spinnerProvince);


        btnuser = (Button) findViewById(R.id.btnuser);
        btnother = (Button) findViewById(R.id.btnother);

        FirebaseUser user = auth.getCurrentUser();
        final String phone = getIntent().getStringExtra("phone");
        if(user.getDisplayName() !=null){
            edtPatientName.setText(user.getDisplayName());
            edtPatientName.setEnabled(false);
            edtPatientName.setBackgroundResource(R.drawable.card_bg);
            btnuser.setTextColor(Color.parseColor("#5FE5BC"));
        }
        else{
            edtPatientName.setText("");
            edtPatientName.setEnabled(true);
            edtPhone.setEnabled(false);
            edtPhone.setText(phone);
        }


        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = auth.getCurrentUser();
                if(user.getDisplayName() !=null){
                    edtPatientName.setText(user.getDisplayName());
                    edtPatientName.setEnabled(false);
                    edtPatientName.setBackgroundResource(R.drawable.card_bg);
                    btnuser.setTextColor(Color.parseColor("#5FE5BC"));
                    btnother.setTextColor(Color.parseColor("#616161"));
                }
                else{
                    edtPatientName.setText("");
                    edtPatientName.setEnabled(true);
                    edtPhone.setEnabled(false);
                    edtPhone.setText(phone);
                }

            }
        });

        btnother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtPatientName.setText("");
                edtPatientName.setEnabled(true);
                btnuser.setTextColor(Color.parseColor("#616161"));
                btnother.setTextColor(Color.parseColor("#5FE5BC"));
            }
        });

        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View popUpView = inflater.inflate(R.layout.popup_book, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        PatientDetailsActivity.this);

                alertDialogBuilder.setView(popUpView);

                final String labID = getIntent().getStringExtra("labID");
                final String labName = getIntent().getStringExtra("labName");
                final String labDesc = getIntent().getStringExtra("labDesc");
                final String labLoc = getIntent().getStringExtra("labLoc");
                final String serviceName = getIntent().getStringExtra("serviceName");
                final String servicePrice = getIntent().getStringExtra("servicePrice");
                final String bookDate = getIntent().getStringExtra("bookDate");
                final String bookTime = getIntent().getStringExtra("bookTime");
                final String patientName = edtPatientName.getText().toString();
                final String street = edtStreet.getText().toString();
                final String phone = edtPhone.getText().toString();
                final String barangay = "Mayao Crossing"; //spinnerBarangay.getSelectedItem().toString();
                final String city = "Lucena City"; //spinnerCity.getSelectedItem().toString();
                final String province = "Quezon";//spinnerProvince.getSelectedItem().toString();
                final String address = street + " " + barangay + " " + city + ", " + province;
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                Double servprice = Double.parseDouble(servicePrice);

                Double total_fee;
                total_fee = (100 + 50) + servprice;

                final String total = String.valueOf(decimalFormat.format(total_fee));

                final TextView lab_name = (TextView) popUpView.findViewById(R.id.lab_name);
                lab_name.setText(labName);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Book", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Bookings bookings = new Bookings();
                                bookings.setPatient_name(patientName);
                                bookings.setLab_name(labName);
                                bookings.setService_name(serviceName);

                                databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(bookings)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                            }
                                        });

                                //Book
                                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> parameters = new HashMap<String, String>();

                                        parameters.put("patient_name", patientName);
                                        parameters.put("patient_address", address);
                                        parameters.put("lab", labID);
                                        parameters.put("service", serviceName);
                                        parameters.put("totalfee", total);

                                        return parameters;
                                    }
                                };

                                requestQueue.add(request);

                                Intent intent = new Intent(PatientDetailsActivity.this, LoadingScreenActivity.class);
                                intent.putExtra("labID", labID);
                                intent.putExtra("labName", labName);
                                intent.putExtra("labDesc", labDesc);
                                intent.putExtra("labLoc", labLoc);
                                intent.putExtra("serviceName",serviceName);
                                intent.putExtra("servicePrice",servicePrice);
                                intent.putExtra("bookDate", bookDate);
                                intent.putExtra("bookTime", bookTime);
                                intent.putExtra("patientName", patientName);
                                intent.putExtra("phone", phone);
                                intent.putExtra("address", address);
                                intent.putExtra("totalfee", total);

                                startActivity(intent);
                                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();


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
