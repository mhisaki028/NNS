package imn.dev.androidpatientapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AppointmentDetailsActivity extends AppCompatActivity {

    TextView lab_name, lab_desc, lab_loc, mydate, mytime, patient_name, patient_address, patient_phone, service_price, service_name, totalfee;
    Button btnDone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentdetails);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        lab_name = (TextView) findViewById(R.id.lab_name);
        lab_desc = (TextView) findViewById(R.id.lab_desc);
        lab_loc = (TextView) findViewById(R.id.lab_loc);
        mydate = (TextView) findViewById(R.id.mydate);
        mytime = (TextView) findViewById(R.id.mytime);
        patient_name = (TextView) findViewById(R.id.patient_name);
        patient_address = (TextView) findViewById(R.id.patient_address);
        patient_phone = (TextView) findViewById(R.id.patient_phone);
        service_name = (TextView) findViewById(R.id.service_name);
        service_price = (TextView) findViewById(R.id.service_price);
        totalfee = (TextView) findViewById(R.id.totalfee);
        btnDone = (Button) findViewById(R.id.btnDone);


        String labID = getIntent().getStringExtra("labID");
        String labName = getIntent().getStringExtra("labName");
        String labDesc = getIntent().getStringExtra("labDesc");
        String labLoc = getIntent().getStringExtra("labLoc");
        String serviceName = getIntent().getStringExtra("serviceName");
        String servicePrice = getIntent().getStringExtra("servicePrice");
        String bookDate = getIntent().getStringExtra("bookDate");
        String bookTime = getIntent().getStringExtra("bookTime");
        String patientName = getIntent().getStringExtra("patientName");
        String phone = getIntent().getStringExtra("phone");
        String address = getIntent().getStringExtra("address");

        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        Double servprice = Double.parseDouble(servicePrice);

        Double total_fee = (100 + 50) + servprice;

        String sp = String.valueOf(decimalFormat.format(servprice));
        String total = String.valueOf(decimalFormat.format(total_fee));

        lab_name.setText(labName);
        lab_desc.setText(labDesc);
        lab_loc.setText(labLoc);
        service_name.setText(serviceName);
        service_price.setText(sp);
        mydate.setText(bookDate);
        mytime.setText(bookTime);
        patient_name.setText(patientName);
        patient_address.setText(address);
        patient_phone.setText(phone);
        totalfee.setText(total);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(AppointmentDetailsActivity.this, BaseActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
