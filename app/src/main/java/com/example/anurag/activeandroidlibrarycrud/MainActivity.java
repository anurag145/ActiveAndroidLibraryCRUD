package com.example.anurag.activeandroidlibrarycrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
private AppCompatEditText country;
private AppCompatEditText city;
private AppCompatButton button;

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
                //Display Function to show cities belonging to the country in the database
            }else
            {
                 if(list.isEmpty())
                 {

                     Country ob=new Country();
                     ob.name=c1;
                     ob.save();
                     list.add(ob);
                     City ob1=new City();
                     ob1.name=c2;
                     ob1.country=ob;
                     ob1.save();
                     Toast.makeText(getApplicationContext(),"Value accepted",Toast.LENGTH_LONG).show();

                 }else
                 {   Country ob = new Country();
                     ob.name=c1;
                     ob.save();
                      if(!list.contains(ob))
                      {
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

    }

    @Override
    public void onClick(View v) {
      Savefileorshow();
    }
}
