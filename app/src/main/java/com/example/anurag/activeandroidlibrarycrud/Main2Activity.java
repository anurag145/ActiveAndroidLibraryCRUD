package com.example.anurag.activeandroidlibrarycrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    public static String TOKEN = "Country";
    public static String TOKEN_1="City";
    public static String TOKEN_2="Prime";
    private ArrayAdapter adapter;
    private TextView textView;
    private TextView textView2;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extra = getIntent().getExtras();
        textView=(TextView)findViewById(R.id.textView4);
        textView2=(TextView)findViewById(R.id.textView5) ;
        listView=(ListView)findViewById(R.id.listView);
        if(extra!=null)
        {
              textView.setText(extra.getString(TOKEN));
              textView2.setText(extra.getString(TOKEN_2));
            ArrayList<String> city = extra.getStringArrayList(TOKEN_1);


               adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, city);
                 listView.setAdapter(adapter);


             }

    }
}
