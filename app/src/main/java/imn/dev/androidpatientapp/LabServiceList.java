package imn.dev.androidpatientapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import imn.dev.androidpatientapp.Model.LabService;

public class LabServiceList extends ArrayAdapter<LabService> {

    private Activity context;
    private List<LabService> labserviceList;
    private ArrayList<LabService> arrayList = null;

    public LabServiceList(@NonNull Activity context, List<LabService> labserviceList, ArrayList<LabService> arrayList) {
        super(context, R.layout.list_labservices, labserviceList);
        this.context = context;
        this.labserviceList = labserviceList;
        this.arrayList = new ArrayList<LabService>();
        this.arrayList.addAll(labserviceList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_labservices, null, true);

        TextView service_name = (TextView)listViewItem.findViewById(R.id.service_name);
        TextView service_price = (TextView)listViewItem.findViewById(R.id.service_price);


        LabService labservice = labserviceList.get(position);

        service_name.setText(labservice.getServiceName());


        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        int sp = labservice.getServicePrice();
        String servp = String.valueOf(decimalFormat.format(sp));
        service_price.setText("P " + servp);

        return listViewItem;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        labserviceList.clear();
        if(charText.length() == 0){
            labserviceList.addAll(arrayList);
        }
        else{
            for(LabService wp: arrayList){
               if(wp.getServiceName().toLowerCase(Locale.getDefault()).contains(charText)){
                   labserviceList.add(wp);
               }
            }
        }
        notifyDataSetChanged();
    }

}
