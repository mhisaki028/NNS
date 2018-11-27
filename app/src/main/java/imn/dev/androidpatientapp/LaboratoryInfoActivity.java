package imn.dev.androidpatientapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LaboratoryInfoActivity extends AppCompatActivity {

    TextView lab_name, lab_desc, lab_about, lab_loc, lab_sched;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratoryinfo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_labinfo);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        lab_name = (TextView) findViewById(R.id.lab_name);
        lab_desc = (TextView) findViewById(R.id.lab_desc);
        lab_about = (TextView) findViewById(R.id.lab_about);
        lab_sched = (TextView) findViewById(R.id.lab_sched);
        lab_loc = (TextView) findViewById(R.id.lab_loc);

        String labName = getIntent().getStringExtra("labName");
        String labDesc = getIntent().getStringExtra("labDesc");
        String labAbout = getIntent().getStringExtra("labAbout");
        String labLoc = getIntent().getStringExtra("labLoc");
        String labSched = getIntent().getStringExtra("labSched");

        lab_name.setText(labName);
        lab_desc.setText(labDesc);
        lab_about.setText(labAbout);
        lab_loc.setText(labLoc);
        lab_sched.setText(labSched);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
