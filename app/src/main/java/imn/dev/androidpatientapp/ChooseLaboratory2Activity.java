package imn.dev.androidpatientapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.Labs;


public class ChooseLaboratory2Activity extends AppCompatActivity {

    RelativeLayout rootLayout;

    DatabaseReference databaseLabs;

    ListView listViewLabs;
    List<Labs> labList;
    LaboratoriesList adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooselaboratory2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_chooselab);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        databaseLabs = FirebaseDatabase.getInstance().getReference("Laboratories");


        rootLayout = (RelativeLayout) findViewById(R.id.rootLayout);
        listViewLabs = (ListView) findViewById(R.id.listview_labs);
        labList = new ArrayList<>();





    }


    @Override
    protected void onStart() {
        super.onStart();

        databaseLabs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                labList.clear();

                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){
                    Labs labs = serviceSnapshot.getValue(Labs.class);

                    labList.add(labs);
                }

                adapter = new LaboratoriesList(ChooseLaboratory2Activity.this, labList);
                listViewLabs.setAdapter(adapter);

                listViewLabs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Labs labs = (Labs)parent.getItemAtPosition(position);
                        String labID = Integer.toString(labs.getLab_id());
                        String labImage = labs.getLab_image();
                        String labName = labs.getLab_name();
                        String labDesc = labs.getLab_desc();
                        String labLoc = labs.getLab_loc();
                        String labSched = labs.getLab_sched();
                        String labAbout = labs.getLab_about();
                        Intent intent = new Intent(ChooseLaboratory2Activity.this, ChooseLabTestActivity.class);
                        intent.putExtra("labID", labID);
                        intent.putExtra("labName", labName);
                        intent.putExtra("labDesc", labDesc);
                        intent.putExtra("labLoc", labLoc);
                        intent.putExtra("labSched", labSched);
                        intent.putExtra("labAbout", labAbout);
                        intent.putExtra("labImage", labImage);
                        startActivity(intent);
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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



