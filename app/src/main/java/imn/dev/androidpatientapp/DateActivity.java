package imn.dev.androidpatientapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DateActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView mydate;
    Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_booking);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        calendarView = (CalendarView)findViewById(R.id.calendar_view);
        mydate = (TextView) findViewById(R.id.date);
        btnNext = (Button) findViewById(R.id.btnNext);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String monthname = (String) android.text.format.DateFormat.format("MMMM", new Date());
                String date = dayOfMonth + " - " + monthname + " - " + year;
                mydate.setText(date);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String labID = getIntent().getStringExtra("labID");
                String labImage = getIntent().getStringExtra("labImage");
                String labName = getIntent().getStringExtra("labName");
                String labDesc = getIntent().getStringExtra("labDesc");
                String labLoc = getIntent().getStringExtra("labLoc");
                String serviceName = getIntent().getStringExtra("serviceName");
                String servicePrice = getIntent().getStringExtra("servicePrice");
                String serviceDesc = getIntent().getStringExtra("serviceDesc");
                String bookDate = mydate.getText().toString();

                Intent intent = new Intent(DateActivity.this, TimeActivity.class);
                intent.putExtra("labID", labID);
                intent.putExtra("labImage", labImage);
                intent.putExtra("labName", labName);
                intent.putExtra("labDesc", labDesc);
                intent.putExtra("labLoc", labLoc);
                intent.putExtra("serviceName",serviceName);
                intent.putExtra("servicePrice",servicePrice);
                intent.putExtra("serviceDesc", serviceDesc);
                intent.putExtra("bookDate", bookDate);

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
