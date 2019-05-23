package com.ariasilver.mehdi.bookestan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private BookModel book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        inCommingIntent();
    }


    private void inCommingIntent() {

        if(getIntent().hasExtra("item")) {
            book = (BookModel) getIntent().getSerializableExtra("item");
            initialValues();
        }
    }

    private void initialValues() {
        TextView txt_title          = findViewById(R.id.txt_title);
        TextView txt_description    = findViewById(R.id.txt_description);
        TextView txt_phone          = findViewById(R.id.txt_phone);
        TextView txt_location       = findViewById(R.id.txt_location);
        TextView txt_price          = findViewById(R.id.txt_price);
        TextView txt_created_at     = findViewById(R.id.txt_created_at);
        ImageView img_thubmnail = findViewById(R.id.img_thumbnail);


        txt_title.setText(book.getTitle());
        txt_description.setText(book.getDescription());
        txt_phone.setText(" شماره تماس :" + book.getMobile());
        txt_location.setText("محل آگهی : " + book.getUniversity());

        String price;
        if(book.getPrice().equals("0")) {
            price = "قیمت توافقی";
        }else {
            price = "قیمت  :" + book.getPrice() + " تومان ";
        }
        txt_price.setText(price);
        txt_created_at.setText("تاریخ ایجاد :" + book.getCreateDate());

        Picasso.get().load(book.getImage()).placeholder(R.mipmap.no_picture).into(img_thubmnail);



        Button btn_call = findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + book.getMobile()));
                startActivity(intent);
            }
        });
    }
}
