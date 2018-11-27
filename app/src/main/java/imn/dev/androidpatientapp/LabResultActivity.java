package imn.dev.androidpatientapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.LabService;
import imn.dev.androidpatientapp.Model.Results;
import retrofit2.http.Url;

public class LabResultActivity extends AppCompatActivity {

    DatabaseReference databaseResultsReference;

    ListView listViewResults;
    List<Results> resultsList;
    RelativeLayout rootLayout;
    ResultsList adapter;



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
