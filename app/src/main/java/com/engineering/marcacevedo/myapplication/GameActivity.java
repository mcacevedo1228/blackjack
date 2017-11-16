package com.engineering.marcacevedo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import models.Player;
import models.Card;
import models.Game;

public class GameActivity extends AppCompatActivity {

    // Class Variables
    Game game;
    ArrayList<Player> players;
    TextView tv;
    private Button hit, stay, main_men, play_again;
    Player user;

    Player winner;

    private View.OnClickListener hitClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hitButtonClicked();
        }
    };

    private View.OnClickListener stayClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            stayButtonClicked();
        }
    };

    private View.OnClickListener mainMenClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            mainMenButtonClicked();
        }
    };

    private View.OnClickListener playAgainClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            playAgainButtonClicked();
        }
    };

    private void playAgainButtonClicked() {
        resetGame();
    }

    private void mainMenButtonClicked() {
        Intent intent = new Intent(GameActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void stayButtonClicked() {
        game.nextPlayerTurn();

        if (game.getCurrentPlayer().getName().equals("Dealer"))
        {
            dealerMove();
            winner = game.scoreHand();
            displayWinner(winner);
        }
    }

    private void hitButtonClicked() {
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
            // Very rudimentary way of determining whether to hit or not.
            dealerMove();

            // Scores hand to determine winner.
            game.scoreHand();
            winner = game.scoreHand();
            displayWinner(winner);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setActivityBackgroundColor();   // Sets background color

        game = new Game();  // Creates a new game for the user. A new game creates two players, deals them cards, and scores their hands
        user = game.getPlayers().get(0);

        // Buttons for game-play
        hit = (Button)findViewById(R.id.Hit);
        stay = (Button)findViewById(R.id.Stay);

        // Buttons for post-game decisions
        main_men = (Button)findViewById(R.id.Main_Menu);
        play_again = (Button)findViewById(R.id.Play_Again);

        setScreenScore(game);   // Sets the initial score of cards dealt.

        // Click Listeners for each button.
        hit.setOnClickListener(hitClickListener);
        stay.setOnClickListener(stayClickListener);
        main_men.setOnClickListener(mainMenClickListener);
        play_again.setOnClickListener(playAgainClickListener);
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

    /**
     * Rudimentary form for the system to determine whether it should hit or stay based on current score.
     * This can be greatly improved, however...Time restraints.. Of course...
     */
    private void dealerMove()
    {
        Player dealer = game.getPlayers().get(1);
        while (game.score(dealer) < 16)
        {
            game.hit(dealer);
            setScreenScore(game);
        }

    }

    /**
     * Displays winner of game and the options to play again or go to the main menu.
     * tv.setVisibility(View.VISIBILITY) is used to make the previously invisible buttons usable.
     * @param winner - winner of the game.
     */
    public void displayWinner(Player winner)
    {
        tv = findViewById(R.id.Winner);
        tv.setText("The " + winner.getName() + " has the winning hand!");
        tv.setVisibility(View.VISIBLE);

        tv = findViewById(R.id.Main_Menu);
        tv.setVisibility(View.VISIBLE);

        tv = findViewById(R.id.Play_Again);
        tv.setVisibility(View.VISIBLE);



    }


    /**
     * Resets game so another hand may be played.
     * Gets intent and finishes it so it can start anew.
     */
    public void resetGame()
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
