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

public class NinthFragment extends Fragment {

    public NinthFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_ninth, null);

        Button btnavail = (Button) view.findViewById(R.id.btnavail);
        btnavail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String serviceName = "Complete Sports Clearance Package";
                String serviceDesc = "Complete Blood Count, Fasting Blood Sugar, Creatinine, Total Cholesterol, HDL, Triglyceride, Routine Urinalysis";
                String servicePrice = "995";

                Intent intent = new Intent(getActivity(), ChooseLaboratory3Activity.class);
                intent.putExtra("serviceName", serviceName);
                intent.putExtra("serviceDesc", serviceDesc);
                intent.putExtra("servicePrice", servicePrice);


                startActivity(intent);
                ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });


        return view;
    }
}
