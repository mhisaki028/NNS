package imn.dev.androidpatientapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {

    public ThirdFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_third, null);

        Button btnavail = (Button) view.findViewById(R.id.btnavail);
        btnavail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = "Package 3";
                String serviceDesc = "FBS, BUN, Creatinine, Uric Acid, Cholesterol, Triglycerides, HLD, LDL, SGPT, Total Protein/Albumin";
                String servicePrice = "1450";
                String labID = "1";
                String labName = "LC Diagnostic Center";
                String labImage = "https://firebasestorage.googleapis.com/v0/b/androidpatientapp.appspot.com/o/lab_pics%2Flc.png?alt=media&token=a7a838ef-7b73-4481-880d-65f279225bd2";

                Intent intent = new Intent(getActivity(), DateActivity.class);
                intent.putExtra("serviceName", serviceName);
                intent.putExtra("serviceDesc", serviceDesc);
                intent.putExtra("servicePrice", servicePrice);
                intent.putExtra("labID", labID);
                intent.putExtra("labName", labName);
                intent.putExtra("labImage", labImage);

                startActivity(intent);
                ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });


        return view;
    }
}
