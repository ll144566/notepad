package com.example.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends BaseActivity implements View.OnClickListener{

    private Button bt_save;
    private EditText et_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        bt_save=findViewById(R.id.bt_save);
        et_content=findViewById(R.id.et_content);
        bt_save.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_save:
                Intent result_intent=new Intent();
                result_intent.putExtra("content",et_content.getText().toString());
                result_intent.putExtra("time",getTime());
                setResult(0,result_intent);
                finish();
                break;
        }
    }

    private String getTime(){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }
}