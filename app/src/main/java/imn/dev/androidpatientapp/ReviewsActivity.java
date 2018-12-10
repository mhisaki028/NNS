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
import imn.dev.androidpatientapp.Model.LabService;
import imn.dev.androidpatientapp.Model.Reviews;

public class ReviewsActivity extends AppCompatActivity {

    DatabaseReference databaseReviewsReference;

    ListView listViewReviews;
    List<Reviews> reviewsList;
    RelativeLayout rootLayout;
    ReviewsList adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_reviews);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        databaseReviewsReference = FirebaseDatabase.getInstance().getReference("Reviews");
        listViewReviews = (ListView)findViewById(R.id.listview_reviews);
        reviewsList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReviewsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                reviewsList.clear();

                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){
                    Reviews reviews = serviceSnapshot.getValue(Reviews.class);

                    reviewsList.add(reviews);
                }

                Collections.reverse(reviewsList);

                adapter = new ReviewsList(ReviewsActivity.this, reviewsList);
                listViewReviews.setAdapter(adapter);

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
