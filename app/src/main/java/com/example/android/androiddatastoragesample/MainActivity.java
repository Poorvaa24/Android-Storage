package com.example.android.androiddatastoragesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SqliteHelper mySqliteHelper;
    private Button btn_sql;
    private Button btn_pref;
    private TextView txt_display;
    int sql_count = 0;
    int pref_count = 0;
    String timestamp = " ";
    String type = " ";
    String output = " ";


    private final int SQL_ACTIVITY_REQUEST_CODE = 1;
    private final int PREF_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_sql = (Button)findViewById(R.id.btn_sql);
        btn_sql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SQLiteView.class);
                startActivityForResult(i,SQL_ACTIVITY_REQUEST_CODE);
            }
        });

        btn_pref = (Button)findViewById(R.id.btn_pref);
        btn_pref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PreferenceView.class);
                startActivityForResult(i,PREF_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

         txt_display = (TextView)findViewById(R.id.txt_display);
         txt_display.setMovementMethod(new ScrollingMovementMethod());
         String old_output = txt_display.getText().toString();
         String new_output = display_newoutput();
         if(!TextUtils.isEmpty(new_output)) {
             txt_display.setText(old_output + "\n" + new_output + "\n");
         }
        type = " ";


    }

    public String display_newoutput()
    {
        if(type.equals("SQLite"))
        {
            sql_count = sql_count + 1;
            output = "SQLite "+sql_count+", "+timestamp;

        }
        else if(type.equals("Pref"))
        {
            pref_count = pref_count + 1;
            output = "Shared Preferences "+pref_count+", "+timestamp;
        }
        else
        {
            output = " ";
        }
        return output;
    }


   public void getLogs(String logs)
   {

       mySqliteHelper.saveLogs(logs);
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SQL_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK)
            {
                timestamp = data.getStringExtra("time_stamp");
                type = data.getStringExtra("type");
            }
        }

        if (requestCode == PREF_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK)
            {
                timestamp = data.getStringExtra("time_stamp");
            }
        }
    }
    public void finishActivity(View v)
    {
        this.finish();

    }



}
