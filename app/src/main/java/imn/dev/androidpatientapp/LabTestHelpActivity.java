package imn.dev.androidpatientapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.LabService;

public class LabTestHelpActivity extends AppCompatActivity {
    EditText search_field, btnSearch;
    DatabaseReference databaseLabServices;

    ListView listViewServices;
    List<LabService> labServiceList;
    LabServiceList adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtesthelp);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_labtesthelp);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        search_field = (EditText) findViewById(R.id.search_field);
        btnSearch = (EditText) findViewById(R.id.btnSearch);
        databaseLabServices = FirebaseDatabase.getInstance().getReference("Labservices");
        listViewServices = (ListView)findViewById(R.id.listview_labtesthelp);
        labServiceList = new ArrayList<>();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchtext = search_field.getText().toString();
                labTestSearch(searchtext);
            }
        });


    }

    private void labTestSearch(String searchtext) {
        if (TextUtils.isEmpty(search_field.getText())) {
            databaseLabServices.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    //dismissing the progress dialog

                    //iterating through all the values in database
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        LabService service = postSnapshot.getValue(LabService.class);
                        labServiceList.add(service);
                    }
                    //creating adapter
                    adapter = new LabServiceList(LabTestHelpActivity.this, labServiceList);

                    //adding adapter to recyclerview

                    listViewServices.setAdapter(adapter);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }else {
            final Query queryRef = databaseLabServices.orderByChild("service_name").equalTo(searchtext);

            queryRef.addValueEventListener(new ValueEventListener() {
                //   adding an event listener to fetch values

                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    //dismissing the progress dialog

                    //iterating through all the values in database
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        LabService service = postSnapshot.getValue(LabService.class);
                        labServiceList.add(service);
                    }
                    //creating adapter
                    adapter = new LabServiceList(LabTestHelpActivity.this, labServiceList);

                    //adding adapter to recyclerview
                    if (snapshot.getValue() != null) {

                        listViewServices.setAdapter(adapter);
                    } else {
                        labServiceList.clear();
                        listViewServices.setAdapter(null);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseLabServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                labServiceList.clear();

                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){
                    LabService labservice = serviceSnapshot.getValue(LabService.class);

                    labServiceList.add(labservice);
                }

                adapter = new LabServiceList(LabTestHelpActivity.this, labServiceList);
                listViewServices.setAdapter(adapter);

                listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        LabService labService = (LabService) parent.getItemAtPosition(position);
                        String serviceName = labService.getServiceName();
                        String servicePrice = Integer.toString(labService.getServicePrice());


                        Intent intent = new Intent(LabTestHelpActivity.this, LabTestProfileActivity.class);
                        intent.putExtra("serviceName", serviceName);
                        intent.putExtra("servicePrice", servicePrice);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

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
