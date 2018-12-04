package imn.dev.androidpatientapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import imn.dev.androidpatientapp.Model.LabService;
import imn.dev.androidpatientapp.Model.Results;

public class ResultsList extends ArrayAdapter<Results> {

    private Activity context;
    private List<Results> resultsList;


    public ResultsList(@NonNull Activity context, List<Results> resultsList) {
        super(context, R.layout.list_results, resultsList);
        this.context = context;
        this.resultsList = resultsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_results, null, true);

        TextView result_name = (TextView)listViewItem.findViewById(R.id.result_name);
        TextView url = (TextView)listViewItem.findViewById(R.id.url);
        TextView uploaded_at = (TextView) listViewItem.findViewById(R.id.uploaded_at);
        TextView uploaded_by = (TextView) listViewItem.findViewById(R.id.uploaded_by);


        Results results = resultsList.get(position);

        result_name.setText(results.getResult_name());
        url.setText(results.getUrl());
        uploaded_at.setText(results.getUploaded_at());
        uploaded_by.setText(results.getUploaded_by());


        return listViewItem;
    }
}
