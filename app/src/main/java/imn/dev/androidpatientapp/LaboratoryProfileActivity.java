package imn.dev.androidpatientapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LaboratoryProfileActivity extends AppCompatActivity {

    RelativeLayout btnLabInfo, btnReviews;
    TextView lab_name, lab_desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laboratoryprofile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_lab);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        btnLabInfo = (RelativeLayout) findViewById(R.id.btnLabInfo);
        btnReviews = (RelativeLayout) findViewById(R.id.btnReviews);
        lab_name = (TextView) findViewById(R.id.lab_name);
        lab_desc = (TextView) findViewById(R.id.lab_desc);

        final String labID = getIntent().getStringExtra("labID");
        final String labName = getIntent().getStringExtra("labName");
        final String labDesc = getIntent().getStringExtra("labDesc");
        final String labLoc = getIntent().getStringExtra("labLoc");
        final String labSched = getIntent().getStringExtra("labSched");
        final String labAbout = getIntent().getStringExtra("labAbout");

        lab_name.setText(labName);
        lab_desc.setText(labDesc);

        btnLabInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaboratoryProfileActivity.this, LaboratoryInfoActivity.class);

                intent.putExtra("labID", labID);
                intent.putExtra("labName", labName);
                intent.putExtra("labDesc", labDesc);
                intent.putExtra("labLoc", labLoc);
                intent.putExtra("labSched", labSched);
                intent.putExtra("labAbout", labAbout);

                startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        btnReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LaboratoryProfileActivity.this, ReviewsActivity.class);
                intent.putExtra("labID", labID);intent.putExtra("labName", labName);
                intent.putExtra("labDesc", labDesc);
                intent.putExtra("labLoc", labLoc);
                intent.putExtra("labSched", labSched);
                intent.putExtra("labAbout", labAbout);

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
