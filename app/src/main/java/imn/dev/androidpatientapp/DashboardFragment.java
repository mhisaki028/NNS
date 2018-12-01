package imn.dev.androidpatientapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DashboardFragment extends Fragment {

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "Clinical and Diagnostic Laboratories", "Medical Laboratory Tests", "Laboratory Test Packages",
            "Laboratory Test Preparations"
    };


    int[] listviewImage = new int[]{
            R.drawable.icon_book, R.drawable.icon_labservices, R.drawable.icon_labtestlist, R.drawable.icon_labtesthelp

    };

    String[] listviewShortDescription = new String[]{
            "Know Your Laboratory", "List of Available Laboratory Tests", "Package Deals",
            "Know How to Prepare for Lab Tests"
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_dashboard, null);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_dashboard);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);




        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 4; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) view.findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);

        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String phone = ((AppCompatActivity)getActivity()).getIntent().getStringExtra("phone");
                if (position == 0) {
                    Intent myIntent = new Intent(view.getContext(), ChooseLaboratoryActivity.class);
                    myIntent.putExtra("phone", phone);
                    startActivityForResult(myIntent, 0);
                    ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }

                if (position == 1) {
                    Intent myIntent = new Intent(view.getContext(), LabServiceActivity.class);
                    startActivityForResult(myIntent, 0);
                    ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }

                if (position == 2) {
                    Intent myIntent = new Intent(view.getContext(), PackagesActivity.class);
                    startActivityForResult(myIntent, 0);
                    ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }

                if (position == 3) {
                    Intent myIntent = new Intent(view.getContext(), LabTestHelpActivity.class);
                    startActivityForResult(myIntent, 0);
                    ((AppCompatActivity)getActivity()).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                }


            }
        });

        Button btnBook = view.findViewById(R.id.btnBook);
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), ChooseLaboratory2Activity.class);
                startActivity(intent);


            }
        });



        return view;


        }



    }