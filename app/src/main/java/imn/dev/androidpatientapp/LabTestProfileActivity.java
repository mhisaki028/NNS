package imn.dev.androidpatientapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class LabTestProfileActivity extends AppCompatActivity {

    TextView service_name, service_price, service_prep;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labtestprofile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_labtesthelp);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        service_name = (TextView) findViewById(R.id.service_name);
        service_price = (TextView) findViewById(R.id.service_price);
        service_prep = (TextView) findViewById(R.id.service_prep);

        String serviceName = getIntent().getStringExtra("serviceName");
        String servicePrice = getIntent().getStringExtra("servicePrice");

        service_name.setText(serviceName);
        service_price.setText(servicePrice);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
