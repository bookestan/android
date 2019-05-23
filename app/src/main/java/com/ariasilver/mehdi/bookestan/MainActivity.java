package com.ariasilver.mehdi.bookestan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView bookRecycler;
    private ArrayList<BookModel> books;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        books = new ArrayList<BookModel>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        requestToServer();
    }

    private void requestToServer() {
        String url = "http://10.0.2.2:8000/api/books";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {


                    for (int i = 0; i <= response.length() - 1; i++) {
                        JSONObject advertObject = response.getJSONObject(i);

                        BookModel bookModel = new BookModel();
                        bookModel.setId(advertObject.getInt("id"));
                        bookModel.setTitle(advertObject.getString("title"));
                        bookModel.setDescription(advertObject.getString("description"));
                        bookModel.setMobile(advertObject.getString("mobile"));

                        bookModel.setPrice(advertObject.getString("price"));
                        bookModel.setImage(advertObject.getString("image"));
                        bookModel.setCreateDate(advertObject.getString("create_date"));
                        bookModel.setUniversity(advertObject.getString("university"));

                        books.add(bookModel);

                    }
                    initialRecyclerView();
                }catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("tookestan", "tookestan get error");
                }

                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                    Log.i("tookestan", "tookestan get volley error");
            }
        });
        Volley.newRequestQueue(this).add(jsonArrayRequest);
    }


    private void initialRecyclerView(){

        bookRecycler = findViewById(R.id.recycler_list);
        bookRecycler.setLayoutManager(new LinearLayoutManager(this));

        BookRecyclerAdapter bookRecyclerAdapter = new BookRecyclerAdapter(books, this);

        bookRecycler.setAdapter(bookRecyclerAdapter);
    }
}
