package com.engineering.marcacevedo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn1 = (Button)findViewById(R.id.New_Game_Button);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setActivityBackgroundColor();
    }

    btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            startActivity(new Intent(MainActivity.this, newActivity.class));
        }
    });

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setActivityBackgroundColor() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
    }








}
