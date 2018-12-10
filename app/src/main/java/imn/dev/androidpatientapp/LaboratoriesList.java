package imn.dev.androidpatientapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import imn.dev.androidpatientapp.Model.LabService;
import imn.dev.androidpatientapp.Model.Labs;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LaboratoriesList extends ArrayAdapter<Labs> {

    private Activity context;
    private List<Labs> labList;

    public LaboratoriesList(@NonNull Activity context, List<Labs> labList) {
        super(context, R.layout.list_labs, labList);
        this.context = context;
        this.labList = labList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_labs, null, true);

        ImageView lab_image = (ImageView) listViewItem.findViewById(R.id.lab_image);
        TextView lab_id = (TextView)listViewItem.findViewById(R.id.lab_id);
        TextView lab_name = (TextView)listViewItem.findViewById(R.id.lab_name);
        TextView lab_desc = (TextView)listViewItem.findViewById(R.id.lab_desc);
        TextView lab_loc = (TextView)listViewItem.findViewById(R.id.lab_loc);
        TextView lab_sched = (TextView)listViewItem.findViewById(R.id.lab_sched);
        TextView lab_about = (TextView)listViewItem.findViewById(R.id.lab_about);

        Labs labs = labList.get(position);

        Glide.with(getContext()).load(labs.getLab_image()).into(lab_image);


        lab_id.setText(Integer.toString(labs.getLab_id()));
        lab_name.setText(labs.getLab_name());
        lab_desc.setText(labs.getLab_desc());
        lab_loc.setText(labs.getLab_loc());
        lab_sched.setText(labs.getLab_sched());
        lab_about.setText(labs.getLab_about());

        return listViewItem;
    }


}
