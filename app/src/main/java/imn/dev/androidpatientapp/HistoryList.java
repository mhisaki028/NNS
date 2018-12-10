package imn.dev.androidpatientapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import imn.dev.androidpatientapp.Model.Bookings;
import imn.dev.androidpatientapp.Model.Reviews;

public class HistoryList extends ArrayAdapter<Bookings> {

    private Activity context;
    private List<Bookings> bookingsList;

    public HistoryList(@NonNull Activity context, List<Bookings> bookingsList) {
        super(context, R.layout.list_history, bookingsList);
        this.context = context;
        this.bookingsList = bookingsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_history, null, true);

        ImageView lab_image = (ImageView)listViewItem.findViewById(R.id.lab_image);
        TextView txtTime = (TextView)listViewItem.findViewById(R.id.txtTime);
        TextView lab_name = (TextView)listViewItem.findViewById(R.id.lab_name);
        TextView txtDate = (TextView)listViewItem.findViewById(R.id.txtDate);
        TextView service_name = (TextView) listViewItem.findViewById(R.id.service_name);



        Bookings bookings = bookingsList.get(position);

        txtTime.setText(bookings.getTime());
        lab_name.setText(bookings.getLab_name());
        service_name.setText(bookings.getService_name());
        txtDate.setText(bookings.getDate());

        Glide.with(getContext()).load(bookings.getLab_image()).into(lab_image);


        return listViewItem;
    }
}
