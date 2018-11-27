package imn.dev.androidpatientapp;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar progressBar;
    TextView textView;
    private float from;
    private float to;


    public ProgressBarAnimation(Context context, ProgressBar progressBar, TextView textView, float from, float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int)value);
        textView.setText((int)value+" %");

        if(value == to){
            ((AppCompatActivity)context).finish();

            final String labID = ((AppCompatActivity)context).getIntent().getStringExtra("labID");
            final String labName = ((AppCompatActivity)context).getIntent().getStringExtra("labName");
            final String labDesc = ((AppCompatActivity)context).getIntent().getStringExtra("labDesc");
            final String labLoc = ((AppCompatActivity)context).getIntent().getStringExtra("labLoc");
            final String serviceName = ((AppCompatActivity)context).getIntent().getStringExtra("serviceName");
            final String servicePrice = ((AppCompatActivity)context).getIntent().getStringExtra("servicePrice");
            final String bookDate = ((AppCompatActivity)context).getIntent().getStringExtra("bookDate");
            final String bookTime = ((AppCompatActivity)context).getIntent().getStringExtra("bookTime");
            final String patientName = ((AppCompatActivity)context).getIntent().getStringExtra("patientName");
            final String phone = ((AppCompatActivity)context).getIntent().getStringExtra("phone");
            final String address = ((AppCompatActivity)context).getIntent().getStringExtra("address");
            final String totalfee = ((AppCompatActivity)context).getIntent().getStringExtra("totalfee");


            Intent intent = new Intent(context, BookingFinishActivity.class);
            intent.putExtra("labID", labID);
            intent.putExtra("labName", labName);
            intent.putExtra("labDesc", labDesc);
            intent.putExtra("labLoc", labLoc);
            intent.putExtra("serviceName",serviceName);
            intent.putExtra("servicePrice",servicePrice);
            intent.putExtra("bookDate", bookDate);
            intent.putExtra("bookTime", bookTime);
            intent.putExtra("patientName", patientName);
            intent.putExtra("phone", phone);
            intent.putExtra("address", address);
            intent.putExtra("totalfee", totalfee);

            context.startActivity(intent);
            ((AppCompatActivity)context).overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

    }
}
