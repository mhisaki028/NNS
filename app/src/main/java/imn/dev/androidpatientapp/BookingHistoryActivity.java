package imn.dev.androidpatientapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.Bookings;
import imn.dev.androidpatientapp.Model.LabService;

public class BookingHistoryActivity extends AppCompatActivity {
    DatabaseReference databaseBookingReference;

    ListView listViewHistory;
    List<Bookings> bookingsList;
    RelativeLayout rootLayout;
    HistoryList adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookinghistory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_history);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        databaseBookingReference = FirebaseDatabase.getInstance().getReference("Bookings");
        listViewHistory = (ListView)findViewById(R.id.listview_history);
        bookingsList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseBookingReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookingsList.clear();

                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){
                    Bookings bookings = serviceSnapshot.getValue(Bookings.class);

                    bookingsList.add(bookings);
                }

                Collections.reverse(bookingsList);

                adapter = new HistoryList(BookingHistoryActivity.this, bookingsList);
                listViewHistory.setAdapter(adapter);

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
