package com.engineering.marcacevedo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import models.Player;
import models.Card;
import models.Game;

public class GameActivity extends AppCompatActivity implements Runnable {
    Game game;
    ArrayList<Player> players;
    TextView tv;

    private Button hit;
    private Button stay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setActivityBackgroundColor();   // Sets background color

        game = new Game();  // Creates a new game for the user. A new game creates two players, deals them cards, and scores their hands
        final Player user = game.getPlayers().get(0);

        hit = (Button)findViewById(R.id.Hit);
        stay = (Button)findViewById(R.id.Stay);

        setScreenScore(game);   // Sets the initial score of cards dealt.

        hit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.hit(user);
                setScreenScore(game);

                // If current player busts, go to next player's turn.
                if (game.score(user) > 21)
                         {
                    game.nextPlayerTurn();
                }

                // If the current player is the Dealer, let him make his move.
                if (game.getCurrentPlayer().getName().equals("Dealer")
                        ) {
                            System.out.println("DEALER MOVE");
                            dealerMove();
                            System.out.println("DEALER STAY");
                            game.scoreHand();
                }

            }
        });

        //TODO: Finish stay executions & declare winner

        // Once user stays, an iteraiton of the dealerMove signals end of the game. Score hands now and declare winner.
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.nextPlayerTurn();
                if (game.getCurrentPlayer().getName().equals("Dealer"))
                {
                    dealerMove();
                    System.out.println("DEALER STAY");
                    System.out.println("The winner of the game is the " + game.scoreHand().getName());
                }

            }
        });


    }

    public void setScreenScore(Game game)
    {
        players = game.getPlayers();

        Player user = players.get(0);
        Player dealer = players.get(1);

        String user_score = Integer.toString(user.getScore());
        String dealer_score = Integer.toString(dealer.getScore());

        tv = findViewById(R.id.temp);
        tv.setText(user_score);

        tv = findViewById(R.id.temp2);
        tv.setText(dealer_score);

    }

    public void setActivityBackgroundColor() {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
    }

    @Override
    public void run() {

    }

    private void dealerMove()
    {
        Player dealer = game.getPlayers().get(1);
        while (game.score(dealer) < 16)
        {
            game.hit(dealer);
            setScreenScore(game);
        }

    }


    public void resetGame()
    {
        game.newGame();
    }

}
