package com.soumo.videoapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Adapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.soumo.videoapplication.R;
import com.soumo.videoapplication.model.Song;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String URL_JSON = "http://wap.gaanbajna.net/app/allcontent.php?fbclid=IwAR1_UCe-z7q5X0Tim8do0zKvZALRWV24x2J5Reaf0hDadZ1WpvWjhrkC5QU";
    private JsonArrayRequest ArrayRequest ;
    private RequestQueue requestQueue ;
    private List<Song> listSong = new ArrayList<>();
    private RecyclerView recyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerViewId);
        jsoncall();
    }

    public void jsoncall() {


        ArrayRequest = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;


                for (int i = 0 ; i<response.length();i++) {

                    //Toast.makeText(getApplicationContext(),String.valueOf(i),Toast.LENGTH_SHORT).show();

                    try {

                        jsonObject = response.getJSONObject(i);
                        Song song = new Song();

                        song.setId(jsonObject.getString("srl"));
                        song.setTitle(jsonObject.getString("title"));
                        song.setSubcategory(jsonObject.getString("subcategory"));
                        song.setImage_url(jsonObject.getString("preview"));
                        song.setVideo_url(jsonObject.getString("contentpath"));

                        //Toast.makeText(MainActivity.this,anime.toString(),Toast.LENGTH_SHORT).show();
                        listSong.add(song);
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setRvadapter(listSong);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(ArrayRequest);
    }

    private void setRvadapter(List<Song> listSong) {

        RecyclerView.Adapter adapter = new com.soumo.videoapplication.adapter.Adapter(this,listSong);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }


}
