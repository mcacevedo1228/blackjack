package com.engineering.marcacevedo.myapplication;

import android.view.View.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setActivityBackgroundColor();

        btn1 = (Button)findViewById(R.id.New_Game_Button);
        btn1.setOnClickListener(this);
    }


    public void setActivityBackgroundColor() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
    }


    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.New_Game_Button)
        {
            Intent intent = new Intent(MainActivity.this, GameActivity.class);
            startActivity(intent);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
    }


}
