package imn.dev.androidpatientapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import imn.dev.androidpatientapp.Model.LabService;

public class LabServiceList extends ArrayAdapter<LabService> {

    private Activity context;
    private List<LabService> labserviceList;

    public LabServiceList(@NonNull Activity context, List<LabService> labserviceList) {
        super(context, R.layout.list_labservices, labserviceList);
        this.context = context;
        this.labserviceList = labserviceList;
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
        service_price.setText(Integer.toString(labservice.getServicePrice()));

        return listViewItem;
    }
}
