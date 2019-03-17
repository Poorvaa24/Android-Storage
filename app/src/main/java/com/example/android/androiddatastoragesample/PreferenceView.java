package com.example.android.androiddatastoragesample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PreferenceView extends AppCompatActivity {
    private EditText txt_name;
    private EditText txt_author;
    private EditText txt_desc;
    private Button btn_save;
    String name = " ";
    String author = " ";
    String desc = " ";


    static final String mypreference = "mypref";
    static final String Name = "nameKey";
    static final String Author = "authorKey";
    static final String Desc = "descKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference_view);

        txt_name = (EditText)findViewById(R.id.input_name);
        txt_author = (EditText)findViewById(R.id.input_author);
        txt_desc = (EditText)findViewById(R.id.input_desc);
        btn_save = (Button)findViewById(R.id.btn_save);

        String name = retrieve_name();
        if(TextUtils.isEmpty(name))
        {
            txt_name.setText("");
        }
        else
        {
            txt_name.setText(name);
        }

        String author = retrieve_author();
        if(TextUtils.isEmpty(author))
        {
            txt_author.setText("");
        }
        else
        {
            txt_author.setText(author);
        }

        String desc = retrieve_desc();
        if(TextUtils.isEmpty(desc))
        {
            txt_desc.setText("");
        }
        else
        {
            txt_desc.setText(desc);
        }


        btn_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                String input_name = txt_name.getText().toString();
                String input_author = txt_author.getText().toString();
                String input_desc = txt_desc.getText().toString();
                if(TextUtils.isEmpty(input_name))
                {
                    Toast t = Toast.makeText(PreferenceView.this, "Please enter name", Toast.LENGTH_SHORT);
                    t.show();
                }
                else if(TextUtils.isEmpty(input_author))
                {
                    Toast t = Toast.makeText(PreferenceView.this, "Please enter author", Toast.LENGTH_SHORT);
                    t.show();
                }
                else if(TextUtils.isEmpty(input_desc))
                {
                    Toast t = Toast.makeText(PreferenceView.this, "Please enter descirption", Toast.LENGTH_SHORT);
                    t.show();
                }
                else
                {
                    Save(input_name,input_author,input_desc);
                    Toast t = Toast.makeText(PreferenceView.this, "Added Successfully", Toast.LENGTH_SHORT);
                    t.show();



                }
            }
        });



    }

    public void Save(String input_name, String input_author, String input_desc)
    {
        SharedPreferences myShPref = this.getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = myShPref.edit();
        editor.putString(Name,input_name);
        editor.putString(Author,input_author);
        editor.putString(Desc,input_desc);
        editor.commit();
        Intent intent = new Intent();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy - hh:mm aa");
        String currentDateTime = dateFormat.format(new Date());

        //  intent.putExtra("counter",count);
        intent.putExtra("time_stamp",currentDateTime);
        intent.putExtra("type","Pref");
        setResult(RESULT_OK, intent);
        finish();
    }


    public String retrieve_name()
    {

        SharedPreferences myShPref = this.getPreferences(this.MODE_PRIVATE);
        if(myShPref.contains(Name))
        {
             name = myShPref.getString(Name," ");

        }
        return name;
    }

    public String retrieve_author()
    {

        SharedPreferences myShPref = this.getPreferences(this.MODE_PRIVATE);
        if(myShPref.contains(Author))
        {
            author = myShPref.getString(Author," ");

        }
        return author;
    }

    public String retrieve_desc()
    {

        SharedPreferences myShPref = this.getPreferences(this.MODE_PRIVATE);
        if(myShPref.contains(Desc))
        {
            desc = myShPref.getString(Desc," ");

        }
        return desc;
    }

    public void finishActivity(View v)
    {
        finish();

    }


}
