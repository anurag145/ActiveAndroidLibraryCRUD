package com.example.anurag.activeandroidlibrarycrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private AppCompatEditText country;
private AppCompatEditText city;
private AppCompatButton button;
public static String TOKEN = "Country";
public static String TOKEN_1="City";

private ArrayList<Country> list ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         country=(AppCompatEditText)findViewById(R.id.view);
        city=(AppCompatEditText)findViewById(R.id.view2);
        button=(AppCompatButton)findViewById(R.id.button);
        button.setOnClickListener(this);
          list=new ArrayList<>();
    }

    public void Savefileorshow()
    {
        String c1=country.getText().toString().trim();
        String c2=city.getText().toString().trim();
        if(c1.equalsIgnoreCase(""))
        {
            Toast.makeText(getApplicationContext(),"Country Cannot be empty",Toast.LENGTH_LONG).show();
        }else
        {
            if(c2.equalsIgnoreCase(""))
            {
                Country ob = new Country();
                ob.name=c1;

                //Switching activity and passing extra data to show cities belonging to the country in the database

                if(list.contains(ob)) {
                    Intent intent = new Intent(this, Main2Activity.class);
                    intent.putExtra(TOKEN,c1);

                    List<City> cityList=get(ob);
                    ArrayList<String> namelist= new ArrayList<>();
                    for(int i =0;i<cityList.size() ;i++)
                    {
                        String name = cityList.get(i).name;
                        namelist.add(name);
                    }
                    intent.putExtra(TOKEN_1,namelist);

                    startActivity(intent);

                }
            }else
            {

                    Country ob = new Country();
                     ob.name=c1;

                      if(!list.contains(ob))
                      {   ob.save();
                          list.add(ob);

                      }
                     City ob1= new City();
                     ob1.name=c2;
                     ob1.country=ob;
                     ob1.save();
                     Toast.makeText(getApplicationContext(),"Value accepted",Toast.LENGTH_LONG).show();

            }
        }

    }
    public static  List<City> get(Country ob)
    {
        return  new Select().from(City.class).where("Country=?",ob.getId()).orderBy("Name ASC").execute();
    }

    @Override
    public void onClick(View v) {
      Savefileorshow();
    }
}
