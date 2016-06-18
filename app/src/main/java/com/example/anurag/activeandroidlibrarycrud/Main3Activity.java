package com.example.anurag.activeandroidlibrarycrud;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;


import com.activeandroid.query.Update;

import java.util.ArrayList;


public class Main3Activity extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private Button button;
    public static String TOKEN = "Country";
    public static String TOKEN_1="City";
    public static String TOKEN_2="Prime";
    public static String TOKEN_3 = "Duplicate";
ArrayList<String> name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        final Bundle extra = getIntent().getExtras();
        editText=(EditText) findViewById(R.id.editText5);
        editText2=(EditText) findViewById(R.id.editText6) ;
        editText3=(EditText) findViewById(R.id.editText7) ;
        if(extra!=null)
        {   button=(Button)findViewById(R.id.button5);
            editText.setText(extra.getString(TOKEN));
            editText2.setText(extra.getString(TOKEN_2));
            editText3.setText(extra.getString(TOKEN_1));
          name=extra.getStringArrayList(TOKEN_3);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  final String s=  editText.getText().toString().trim();
                    if(!s.equalsIgnoreCase(extra.getString(TOKEN)))
                    {
                        new Update(City.class).set("Country=?",s).where("Country=?",extra.getString(TOKEN)).execute();
                        new Update(Country.class).set("name=?",s).where("name=?",extra.getString(TOKEN)).execute();

                    }
                    String s1=editText2.getText().toString().trim();
                    if(!s1.equalsIgnoreCase(extra.getString(TOKEN_2)))
                    {
                        new Update(Country.class).set("pm=?",s1).where("name=?",s).execute();

                    }
                    String s2=editText3.getText().toString().trim();
                    if(!s2.equalsIgnoreCase(extra.getString(TOKEN_1))) {
                        boolean flag = true;
                        for (int i = 0; i < name.size(); i++) {
                            if (s2.equalsIgnoreCase(name.get(i))){
                                flag = false;
                                break;
                            }
                        }
                         if(flag) {

                             new Update(City.class).set("NAME=?", s2).where("NAME=?", extra.getString(TOKEN_1)).execute();
                         }
                    }

                }
            });

        }
    }
}
