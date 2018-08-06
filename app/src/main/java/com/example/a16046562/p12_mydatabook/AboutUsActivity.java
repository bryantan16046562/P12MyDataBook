package com.example.a16046562.p12_mydatabook;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
public class AboutUsActivity extends AppCompatActivity {
    ActionBar ab;
    ImageView ivpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ab = getSupportActionBar();
        ab.setTitle("About Us");
        ab.setDisplayHomeAsUpEnabled(true);

        ivpic = (ImageView) findViewById(R.id.imageViewPicture);
        String imgURL = "https://az29734.vo.msecnd.net/clients/688/RP%20Logo-RGB%20tagline.png";
        Picasso.with(this)
                .load(imgURL)
                .placeholder(R.drawable.ajax_loader)
                .error(R.drawable.error)
                .into(ivpic);
    }
}
