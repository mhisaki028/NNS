package imn.dev.androidpatientapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import imn.dev.androidpatientapp.Model.LabService;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class LabServiceActivity extends AppCompatActivity {

    EditText search_field;
    DatabaseReference databaseLabServices;

    ListView listViewServices;
    List<LabService> labServiceList;
    ArrayList<LabService> arrayList;
    RelativeLayout rootLayout;
    LabServiceList adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labservices);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_labservices);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        search_field = (EditText) findViewById(R.id.search_field);
        databaseLabServices = FirebaseDatabase.getInstance().getReference("Labservices");
        listViewServices = (ListView)findViewById(R.id.listview_services);
        labServiceList = new ArrayList<>();

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

        databaseLabServices.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                labServiceList.clear();

                for(DataSnapshot serviceSnapshot : dataSnapshot.getChildren()){
                    LabService labservice = serviceSnapshot.getValue(LabService.class);

                    labServiceList.add(labservice);
                }

                adapter = new LabServiceList(LabServiceActivity.this, labServiceList, arrayList);
                listViewServices.setAdapter(adapter);

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
