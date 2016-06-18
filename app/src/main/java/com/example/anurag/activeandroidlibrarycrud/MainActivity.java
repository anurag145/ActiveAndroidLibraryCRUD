package com.example.anurag.activeandroidlibrarycrud;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editText;
    private  EditText editText2;
    private  EditText editText3;
    private Button button;
    private Button button2;
    private Button button3;
    private Button button4;
    String c1,c2,c3;
    List<Country> list1;
    List<City> list;
    public static String TOKEN_3 = "Duplicate";
public static String TOKEN = "Country";
public static String TOKEN_1="City";
    public static String TOKEN_2="Prime";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        button=(Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
      button4=(Button)findViewById(R.id.button4);
       button2.setOnClickListener(this);
        button.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);


    }

    public void delete()
    {
           if(c1.equalsIgnoreCase(""))
           {
               Toast.makeText(getApplicationContext(),"Specify a country or a city ",Toast.LENGTH_LONG).show();
           }else
           {
               if(c2.equalsIgnoreCase("")&&(!list1.isEmpty()))
               {
                   new Delete().from(City.class).where("Country=?",c1).execute();
                   new Delete().from(Country.class).where("name=?",c1).execute();
               }else
                   if(!list1.isEmpty()) {
                       ArrayList<String> namelist = new ArrayList<>();
                       for (int i = 0; i < list.size(); i++) {
                           String name = list.get(i).name;
                           namelist.add(name);

                       }
                       if(namelist.contains(c2))
                       new Delete().from(City.class).where("name=?", c2).execute();
                       else
                       Toast.makeText(getApplicationContext(),"city not in database",Toast.LENGTH_LONG).show();
                   }  else
                       Toast.makeText(getApplicationContext(),"country not in database",Toast.LENGTH_LONG).show();
           }

    }

    public void update()
    {
        if (!list1.isEmpty()&&(!c2.equalsIgnoreCase(""))) {
            Intent intent = new Intent(this, Main3Activity.class);
            boolean flag=false;

            ArrayList<String> namelist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (c2.equalsIgnoreCase(list.get(i).name))
                    flag=true;
                    String name = list.get(i).name;
                    namelist.add(name);


            }
            if(flag) {
                intent.putExtra(TOKEN, list1.get(0).name);
                intent.putExtra(TOKEN_3, namelist);

                intent.putExtra(TOKEN_1, c2);
                intent.putExtra(TOKEN_2, list1.get(0).pm);

                startActivity(intent);

            }else
                Toast.makeText(getApplicationContext(),"City not in db",Toast.LENGTH_LONG).show();
        }else {
            if(list1.isEmpty())
            Toast.makeText(getApplicationContext(),"Country not in db try submitting it",Toast.LENGTH_LONG).show();
            else
             Toast.makeText(getApplicationContext(),"Enter City as well",Toast.LENGTH_LONG).show();

        }

    }

    public void Savefileorshow()
    {

        if(c1.equalsIgnoreCase("")||c2.equalsIgnoreCase("")) {

         Toast.makeText(getApplicationContext(),"Country and City are mandatory",Toast.LENGTH_LONG).show();
        }
         else {

            boolean flag = true;

                if (list1.size() == 0&&(!c3.equalsIgnoreCase(""))) {

                    Country ob = new Country();
                    ob.name = c1;
                    ob.pm = c3;
                    ob.save();


                }else
                if(list1.size()==0)
                flag=false;


                if(flag) {
                    for (int i = 0; i < list.size(); i++) {
                        if (c2.equalsIgnoreCase(list.get(i).name)) {
                            flag = false;
                            break;
                        }
                    }
                    if (!flag) {
                        Toast.makeText(getApplicationContext(), "Already in db", Toast.LENGTH_LONG).show();
                    } else {
                        City ob1 = new City();
                        ob1.name = c2;
                        ob1.country = c1;
                        ob1.save();
                        Toast.makeText(getApplicationContext(), "Value Accepted", Toast.LENGTH_LONG).show();
                    }
                }else
                    Toast.makeText(getApplicationContext(),"Country not in db , give head of state as well",Toast.LENGTH_LONG).show();

        }
        }

    static  List<City> get(String ob)
    {
        return  new Select().from(City.class).where("Country=?",ob).orderBy("Name ASC").execute();
    }
public static List<Country> gets(String s)
{
    return  new Select().from(Country.class).where("name=?",s).execute();
}
    public void Display() {
        if (!list1.isEmpty()) {
            Intent intent = new Intent(this, Main2Activity.class);

            intent.putExtra(TOKEN, list1.get(0).name);


            ArrayList<String> namelist = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                String name = list.get(i).name;
                namelist.add(name);

            }
            intent.putExtra(TOKEN_1, namelist);
          intent.putExtra(TOKEN_2,list1.get(0).pm);

            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(),"Country not in db try submitting it",Toast.LENGTH_LONG).show();


        }
    }

    @Override
    public void onClick(View v) {
        c1=editText.getText().toString().trim();

        c2=editText2.getText().toString().trim();
        c3=editText3.getText().toString().trim();
        list1 = gets(c1);
        list=get(c1);

        switch (v.getId())
        {
            case R.id.button :
                 Savefileorshow();
                 break;
            case R.id.button2:
                 Display();
                break;
            case R.id.button3 :
                 delete();
                break;
            case R.id.button4:
                update();
                break;
            default:break;
        }

    }
}
