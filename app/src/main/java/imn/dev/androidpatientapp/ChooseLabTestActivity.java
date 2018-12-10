package imn.dev.androidpatientapp;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.LabService;


public class ChooseLabTestActivity extends AppCompatActivity {
    EditText search_field;
    RelativeLayout rootLayout;
    EditText btnSearch;
    DatabaseReference databaseServices;
    ArrayList<LabService> arrayList;
    ListView listViewServices;
    List<LabService> servicesList;

    LabServiceList adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooselabtest);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_chooselabtest);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        databaseServices = FirebaseDatabase.getInstance().getReference("Labservices");


        search_field = (EditText) findViewById(R.id.search_field);
        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        listViewServices = (ListView) findViewById(R.id.listview_services);
        servicesList = new ArrayList<>();

        search_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = search_field.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }
        });



    }




    @Override
    protected void onStart() {
        super.onStart();

        databaseServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                servicesList.clear();

                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){
                    LabService labService = serviceSnapshot.getValue(LabService.class);

                    servicesList.add(labService);
                }

                adapter = new LabServiceList(ChooseLabTestActivity.this, servicesList, arrayList);
                listViewServices.setAdapter(adapter);

                listViewServices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        LabService labService = (LabService) parent.getItemAtPosition(position);
                        String labID = getIntent().getStringExtra("labID");
                        String labName = getIntent().getStringExtra("labName");
                        String labDesc = getIntent().getStringExtra("labDesc");
                        String labLoc = getIntent().getStringExtra("labLoc");
                        String labImage = getIntent().getStringExtra("labImage");

                        String serviceName = labService.getServiceName();
                        String servicePrice = Integer.toString(labService.getServicePrice());


                        Intent intent = new Intent(ChooseLabTestActivity.this, DateActivity.class);

                        intent.putExtra("labID", labID);
                        intent.putExtra("labImage", labImage);
                        intent.putExtra("labName", labName);
                        intent.putExtra("labDesc", labDesc);
                        intent.putExtra("labLoc", labLoc);
                        intent.putExtra("serviceName",serviceName);
                        intent.putExtra("servicePrice",servicePrice);
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



