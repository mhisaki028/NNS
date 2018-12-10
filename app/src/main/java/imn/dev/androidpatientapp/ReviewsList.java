package imn.dev.androidpatientapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import imn.dev.androidpatientapp.Model.Results;
import imn.dev.androidpatientapp.Model.Reviews;

public class ReviewsList extends ArrayAdapter<Reviews> {

    private Activity context;
    private List<Reviews> reviewsList;

    public ReviewsList(@NonNull Activity context, List<Reviews> reviewsList) {
        super(context, R.layout.list_reviews, reviewsList);
        this.context = context;
        this.reviewsList = reviewsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_reviews, null, true);

        TextView patient_name = (TextView)listViewItem.findViewById(R.id.patient_name);
        TextView txtBody = (TextView)listViewItem.findViewById(R.id.txtBody);
        TextView txtDay = (TextView) listViewItem.findViewById(R.id.txtDay);
        TextView txtTime = (TextView) listViewItem.findViewById(R.id.txtTime);
        TextView txtRating = (TextView) listViewItem.findViewById(R.id.txtRating);
        TextView lab_name = (TextView) listViewItem.findViewById(R.id.lab_name);
        TextView star_rate = (TextView) listViewItem.findViewById(R.id.star_rate);


        Reviews reviews = reviewsList.get(position);

        patient_name.setText(reviews.getPatient_name());
        txtBody.setText(reviews.getTxtBody());
        txtDay.setText(reviews.getTxtDay());
        txtTime.setText(reviews.getTxtTime());
        txtRating.setText(reviews.getRating());
        lab_name.setText(reviews.getLab());
        star_rate.setText(reviews.getStars());


        return listViewItem;
    }
}
