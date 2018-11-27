package imn.dev.androidpatientapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class AppInfoFragment extends Fragment {

//////HELLO

    String[] listviewTitle = new String[]{
            "Appointments", "Reminders", "Lab Test Results",
            "Settings", "Logout",
    };


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_appinfo, null);




        return view;
    }

}
