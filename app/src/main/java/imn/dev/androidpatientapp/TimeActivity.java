package imn.dev.androidpatientapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.Text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TimeActivity extends AppCompatActivity {

    Button btnNext;
    TextView time, meridian, one, two, three, eight, nine, ten, eleven;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_booking);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        time = (TextView) findViewById(R.id.time);
        meridian = (TextView) findViewById(R.id.meridian);
        eight = (TextView) findViewById(R.id.eight);
        nine = (TextView) findViewById(R.id.nine);
        ten = (TextView) findViewById(R.id.ten);
        eleven = (TextView) findViewById(R.id.eleven);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);


        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("8:00 - 9:00");
                meridian.setText("AM");
                eight.setBackgroundResource(R.drawable.select_time_bg);
                eight.setTextColor(Color.parseColor("#5FE5BC"));

                nine.setBackgroundResource(R.drawable.edt_signup_bg);
                nine.setTextColor(Color.parseColor("#3D3D3D"));

                ten.setBackgroundResource(R.drawable.edt_signup_bg);
                ten.setTextColor(Color.parseColor("#3D3D3D"));

                eleven.setBackgroundResource(R.drawable.edt_signup_bg);
                eleven.setTextColor(Color.parseColor("#3D3D3D"));

                one.setBackgroundResource(R.drawable.edt_signup_bg);
                one.setTextColor(Color.parseColor("#3D3D3D"));

                two.setBackgroundResource(R.drawable.edt_signup_bg);
                two.setTextColor(Color.parseColor("#3D3D3D"));

                three.setBackgroundResource(R.drawable.edt_signup_bg);
                three.setTextColor(Color.parseColor("#3D3D3D"));
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("9:00 - 10:00");
                meridian.setText("AM");
                eight.setBackgroundResource(R.drawable.edt_signup_bg);
                eight.setTextColor(Color.parseColor("#3D3D3D"));

                nine.setBackgroundResource(R.drawable.select_time_bg);
                nine.setTextColor(Color.parseColor("#5FE5BC"));

                ten.setBackgroundResource(R.drawable.edt_signup_bg);
                ten.setTextColor(Color.parseColor("#3D3D3D"));

                eleven.setBackgroundResource(R.drawable.edt_signup_bg);
                eleven.setTextColor(Color.parseColor("#3D3D3D"));

                one.setBackgroundResource(R.drawable.edt_signup_bg);
                one.setTextColor(Color.parseColor("#3D3D3D"));

                two.setBackgroundResource(R.drawable.edt_signup_bg);
                two.setTextColor(Color.parseColor("#3D3D3D"));

                three.setBackgroundResource(R.drawable.edt_signup_bg);
                three.setTextColor(Color.parseColor("#3D3D3D"));
            }
        });

        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("10:00 - 11:00");
                meridian.setText("AM");
                eight.setBackgroundResource(R.drawable.edt_signup_bg);
                eight.setTextColor(Color.parseColor("#3D3D3D"));

                nine.setBackgroundResource(R.drawable.edt_signup_bg);
                nine.setTextColor(Color.parseColor("#3D3D3D"));

                ten.setBackgroundResource(R.drawable.select_time_bg);
                ten.setTextColor(Color.parseColor("#5FE5BC"));

                eleven.setBackgroundResource(R.drawable.edt_signup_bg);
                eleven.setTextColor(Color.parseColor("#3D3D3D"));

                one.setBackgroundResource(R.drawable.edt_signup_bg);
                one.setTextColor(Color.parseColor("#3D3D3D"));

                two.setBackgroundResource(R.drawable.edt_signup_bg);
                two.setTextColor(Color.parseColor("#3D3D3D"));

                three.setBackgroundResource(R.drawable.edt_signup_bg);
                three.setTextColor(Color.parseColor("#3D3D3D"));
            }
        });

        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("11:00 - 12:00");
                meridian.setText("AM");
                eight.setBackgroundResource(R.drawable.edt_signup_bg);
                eight.setTextColor(Color.parseColor("#3D3D3D"));

                nine.setBackgroundResource(R.drawable.edt_signup_bg);
                nine.setTextColor(Color.parseColor("#3D3D3D"));

                ten.setBackgroundResource(R.drawable.edt_signup_bg);
                ten.setTextColor(Color.parseColor("#3D3D3D"));

                eleven.setBackgroundResource(R.drawable.select_time_bg);
                eleven.setTextColor(Color.parseColor("#5FE5BC"));

                one.setBackgroundResource(R.drawable.edt_signup_bg);
                one.setTextColor(Color.parseColor("#3D3D3D"));

                two.setBackgroundResource(R.drawable.edt_signup_bg);
                two.setTextColor(Color.parseColor("#3D3D3D"));

                three.setBackgroundResource(R.drawable.edt_signup_bg);
                three.setTextColor(Color.parseColor("#3D3D3D"));
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("1:00 - 2:00");
                meridian.setText("PM");
                eight.setBackgroundResource(R.drawable.edt_signup_bg);
                eight.setTextColor(Color.parseColor("#3D3D3D"));

                nine.setBackgroundResource(R.drawable.edt_signup_bg);
                nine.setTextColor(Color.parseColor("#3D3D3D"));

                ten.setBackgroundResource(R.drawable.edt_signup_bg);
                ten.setTextColor(Color.parseColor("#3D3D3D"));

                eleven.setBackgroundResource(R.drawable.edt_signup_bg);
                eleven.setTextColor(Color.parseColor("#3D3D3D"));

                one.setBackgroundResource(R.drawable.select_time_bg);
                one.setTextColor(Color.parseColor("#5FE5BC"));

                two.setBackgroundResource(R.drawable.edt_signup_bg);
                two.setTextColor(Color.parseColor("#3D3D3D"));

                three.setBackgroundResource(R.drawable.edt_signup_bg);
                three.setTextColor(Color.parseColor("#3D3D3D"));
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("2:00 - 3:00");
                meridian.setText("PM");
                eight.setBackgroundResource(R.drawable.edt_signup_bg);
                eight.setTextColor(Color.parseColor("#3D3D3D"));

                nine.setBackgroundResource(R.drawable.edt_signup_bg);
                nine.setTextColor(Color.parseColor("#3D3D3D"));

                ten.setBackgroundResource(R.drawable.edt_signup_bg);
                ten.setTextColor(Color.parseColor("#3D3D3D"));

                eleven.setBackgroundResource(R.drawable.edt_signup_bg);
                eleven.setTextColor(Color.parseColor("#3D3D3D"));

                one.setBackgroundResource(R.drawable.edt_signup_bg);
                one.setTextColor(Color.parseColor("#3D3D3D"));

                two.setBackgroundResource(R.drawable.select_time_bg);
                two.setTextColor(Color.parseColor("#5FE5BC"));

                three.setBackgroundResource(R.drawable.edt_signup_bg);
                three.setTextColor(Color.parseColor("#3D3D3D"));
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setText("3:00 - 4:00");
                meridian.setText("PM");
                eight.setBackgroundResource(R.drawable.edt_signup_bg);
                eight.setTextColor(Color.parseColor("#3D3D3D"));

                nine.setBackgroundResource(R.drawable.edt_signup_bg);
                nine.setTextColor(Color.parseColor("#3D3D3D"));

                ten.setBackgroundResource(R.drawable.edt_signup_bg);
                ten.setTextColor(Color.parseColor("#3D3D3D"));

                eleven.setBackgroundResource(R.drawable.edt_signup_bg);
                eleven.setTextColor(Color.parseColor("#3D3D3D"));

                one.setBackgroundResource(R.drawable.edt_signup_bg);
                one.setTextColor(Color.parseColor("#3D3D3D"));

                two.setBackgroundResource(R.drawable.edt_signup_bg);
                two.setTextColor(Color.parseColor("#3D3D3D"));

                three.setBackgroundResource(R.drawable.select_time_bg);
                three.setTextColor(Color.parseColor("#5FE5BC"));
            }
        });

        btnNext = (Button) findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String labID = getIntent().getStringExtra("labID");
                String labImage = getIntent().getStringExtra("labImage");
                String labName = getIntent().getStringExtra("labName");
                String labDesc = getIntent().getStringExtra("labDesc");
                String labLoc = getIntent().getStringExtra("labLoc");
                String labService = getIntent().getStringExtra("labService");
                String serviceName = getIntent().getStringExtra("serviceName");
                String servicePrice = getIntent().getStringExtra("servicePrice");
                String serviceDesc = getIntent().getStringExtra("serviceDesc");
                String bookDate = getIntent().getStringExtra("bookDate");
                String mytime = time.getText().toString();
                String mer = meridian.getText().toString();
                String bookTime = mytime + " " + mer;

                Intent intent = new Intent(TimeActivity.this, PatientDetailsActivity.class);

                intent.putExtra("labID", labID);
                intent.putExtra("labImage", labImage);
                intent.putExtra("labName", labName);
                intent.putExtra("labDesc", labDesc);
                intent.putExtra("labLoc", labLoc);
                intent.putExtra("labService", labService);
                intent.putExtra("serviceName", serviceName);
                intent.putExtra("servicePrice", servicePrice);
                intent.putExtra("serviceDesc", serviceDesc);
                intent.putExtra("bookDate", bookDate);
                intent.putExtra("bookTime", bookTime);

                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

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
