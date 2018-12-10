package imn.dev.androidpatientapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

import androidx.appcompat.app.AppCompatActivity;



public class BookingFinishActivity extends AppCompatActivity {

    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingfinish);

        btnFinish = (Button) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labID = getIntent().getStringExtra("labID");
                String labName = getIntent().getStringExtra("labName");
                String labDesc = getIntent().getStringExtra("labDesc");
                String labLoc = getIntent().getStringExtra("labLoc");
                String serviceName = getIntent().getStringExtra("serviceName");
                String servicePrice = getIntent().getStringExtra("servicePrice");
                String serviceDesc = getIntent().getStringExtra("serviceDesc");
                String bookDate = getIntent().getStringExtra("bookDate");
                String bookTime = getIntent().getStringExtra("bookTime");
                String patientName = getIntent().getStringExtra("patientName");
                String address = getIntent().getStringExtra("address");

                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                Double servprice = Double.parseDouble(servicePrice);
                Double total_fee;
                total_fee = (100 + 50) + servprice;
                final String sp = String.valueOf(decimalFormat.format(servprice));
                final String total = String.valueOf(decimalFormat.format(total_fee));


                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
                View popUpView = inflater.inflate(R.layout.popup_details, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        BookingFinishActivity.this);

                alertDialogBuilder.setView(popUpView);

                TextView service_name = (TextView)popUpView.findViewById(R.id.service_name);
                TextView service_price = (TextView)popUpView.findViewById(R.id.service_price);
                TextView service_desc = (TextView)popUpView.findViewById(R.id.service_desc);
                TextView myaddress = (TextView)popUpView.findViewById(R.id.patient_address);
                TextView lab_name = (TextView)popUpView.findViewById(R.id.lab_name);
                TextView date = (TextView)popUpView.findViewById(R.id.bookDate);
                TextView time = (TextView)popUpView.findViewById(R.id.bookTime);
                TextView service_price2 = (TextView)popUpView.findViewById(R.id.service_price2);
                TextView totalfee = (TextView)popUpView.findViewById(R.id.totalfee);

                service_name.setText(serviceName);
                service_price.setText("P "+ sp);
                service_desc.setText(serviceDesc);
                myaddress.setText(address);
                lab_name.setText(labName);
                date.setText(bookDate);
                time.setText(bookTime);
                service_price2.setText("P "+ sp);
                totalfee.setText("P " + total);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                startActivity(new Intent(BookingFinishActivity.this, BaseActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.show();
            }
        });



    }





}

