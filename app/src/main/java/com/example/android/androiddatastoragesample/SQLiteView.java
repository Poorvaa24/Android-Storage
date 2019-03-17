package com.example.android.androiddatastoragesample;

import android.content.Intent;
import android.database.Cursor;
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

public class SQLiteView extends AppCompatActivity {

    SqliteHelper mSqliteHelper;
    private Button btn_save;
    private EditText txt_msg;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_view);

        btn_save = (Button)findViewById(R.id.btn_save);
        txt_msg = (EditText)findViewById(R.id.input_msg);
        mSqliteHelper = new SqliteHelper(this);

        String mes = getSavedMessage();
        if(TextUtils.isEmpty(mes))
        {
            txt_msg.setText("");
        }
        else
        {
            txt_msg.setText(mes);
        }



        btn_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String input_message = txt_msg.getText().toString();

                if(TextUtils.isEmpty(input_message))
                {
                    Toast t = Toast.makeText(SQLiteView.this, "Please add some message", Toast.LENGTH_SHORT);
                    t.show();
                }
                else
                {

                    addMessage(input_message);
                    txt_msg.setText("");
                    Intent intent = new Intent();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy - hh:mm aa");
                    String currentDateTime = dateFormat.format(new Date());

                    //  intent.putExtra("counter",count);
                    intent.putExtra("time_stamp",currentDateTime);
                    intent.putExtra("type","SQLite");
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
            }
        );

    }
    public void addMessage(String message)
    {
        boolean insertMessage = mSqliteHelper.saveBlog(message);

        if(insertMessage)
        {
            Toast t = Toast.makeText(SQLiteView.this, "Successfully Added", Toast.LENGTH_SHORT);
            t.show();
        }
        else
        {
            Toast t = Toast.makeText(SQLiteView.this, "Something went wrong!", Toast.LENGTH_SHORT);
            t.show();
        }
    }


    public String getSavedMessage()
    {

        Cursor cursor = mSqliteHelper.getMessage();
        cursor.moveToLast();

        String blog_msg = cursor.getString(1);
        return blog_msg;

    }


    public void finishActivity(View v)
    {
        finish();

    }

}
