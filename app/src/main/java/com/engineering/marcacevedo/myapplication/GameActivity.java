package com.engineering.marcacevedo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import models.Player;
import models.Card;
import models.Game;

public class GameActivity extends AppCompatActivity implements Runnable {
    Game game;

    private Button hit;
    private Button stay;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


    }


}
