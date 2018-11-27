package imn.dev.androidpatientapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;



public class BookingFinishActivity extends AppCompatActivity {

    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingfinish);

        btnFinish = (Button) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                String street = getIntent().getStringExtra("street");
                String province = getIntent().getStringExtra("province");
                String city = getIntent().getStringExtra("city");
                String barangay = getIntent().getStringExtra("barangay");

                Intent intent = new Intent(BookingFinishActivity.this, AppointmentDetailsActivity.class);
                intent.putExtra("labID", labID);
                intent.putExtra("labName", labName);
                intent.putExtra("labDesc", labDesc);
                intent.putExtra("labLoc", labLoc);
                intent.putExtra("serviceName", serviceName);
                intent.putExtra("servicePrice", servicePrice);
                intent.putExtra("bookDate", bookDate);
                intent.putExtra("bookTime", bookTime);
                intent.putExtra("patientName", patientName);
                intent.putExtra("phone", phone);
                intent.putExtra("street", street);
                intent.putExtra("province", province);
                intent.putExtra("city", city);
                intent.putExtra("barangay", barangay);

                finish();
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });



    }





}

