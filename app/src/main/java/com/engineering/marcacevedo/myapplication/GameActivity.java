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

    // Class Variables
    Game game;
    ArrayList<Player> players;
    TextView tv;
    private Button hit;
    private Button stay;
    Player winner;

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

        // Click listener with Overridden onClick method to execute desired functions.
        hit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                // Hit user and update the score.
                game.hit(user);
                setScreenScore(game);

                // If current player busts, go to next player's turn.
                if (game.score(user) > 21)
                {
                    game.nextPlayerTurn();
                }

                // If the current player is the Dealer, let him make his move. Upon completion, scoring takes place.
                if (game.getCurrentPlayer().getName().equals("Dealer"))
                {
                    //TODO: DELETE CONSOLE LINES
                    System.out.println("DEALER MOVE");
                    // Very rudimentary way of determining whether to hit or not.
                    dealerMove();
                    System.out.println("Dealer has finished, game is over! ");
                    // Scores hand to determine winner.
                    winner = game.scoreHand();
                }

            }
        });


        // Click listener with Overridden onClick method to execute desired functions.
        stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                // Signifies next player's turn.
                game.nextPlayerTurn();
                if (game.getCurrentPlayer().getName().equals("Dealer"))
                {
                    dealerMove();
                    winner = game.scoreHand();
                    System.out.println("DEALER STAY");
                    System.out.println("The winner of the game is the " + winner.getName());
                }
            }
        });

    }


    /**
     * Updates the score for the users to view.
     */
    public void setScreenScore(Game game)
    {
        // Gets players and assigns them accordingly.
        players = game.getPlayers();
        Player user = players.get(0);
        Player dealer = players.get(1);

        // Parsing scores into usable output for Activity Screen.
        // Text View tv.setText takes in char[] so conversion into string or char[] is needed.
        String user_score = Integer.toString(user.getScore());
        String dealer_score = Integer.toString(dealer.getScore());

        // Assign each score to correct place in Game Activity.
        tv = findViewById(R.id.temp);
        tv.setText(user_score);
        tv = findViewById(R.id.temp2);
        tv.setText(dealer_score);

    }

    /**
     * Sets the background of the Game Activity.
     * Can also be done using 3 param w/ RGB values.
     */
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
