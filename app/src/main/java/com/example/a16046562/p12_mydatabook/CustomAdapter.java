package com.example.a16046562.p12_mydatabook;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomAdapter extends ArrayAdapter<String> {
    Context context;
    String[] items;
    int resource;
    ImageView ivpic;
    TextView tvTitle;
    public CustomAdapter(Context context, int resource, String[] items) {
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.resource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        String item = items[position];

        tvTitle = (TextView) rowView.findViewById(R.id.tvtitle);
        ivpic = (ImageView) rowView.findViewById(R.id.imageViewPicture);

        tvTitle.setText("" + item);

        //Check position to set images
        if (position == 0) {
            ivpic.setImageResource(android.R.drawable.ic_dialog_info);
        } else if (position == 1) {
            ivpic.setImageResource(android.R.drawable.ic_menu_edit);
        } else if (position == 2) {
            ivpic.setImageResource(android.R.drawable.ic_menu_my_calendar);
        } else {
            ivpic.setImageResource(android.R.drawable.btn_star_big_on);
        }

        return rowView;
    }
}
