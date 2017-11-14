package models;

import java.util.ArrayList;

import models.Card;
import models.Deck;
import models.Player;

/**
 * Created by marcacevedo on 11/13/17.
 */

public class Game
{

    // Class variables
    private Deck deck;
    private ArrayList<Player> players;
    private int playerTurn;



    /**
     * Constructor for Blackjack models.Game.
     */
    public Game()
    {
        // Creates new deck to be played with.
        deck = new Deck();

        //Creates list of players and adds players to list.
        players = new ArrayList<Player>();
        players.add(new Player("User"));
        players.add(new Player("Dealer"));


        newGame();
    }


    /**
     * Deals cards to each player.
     */
    public void dealCards()
    {
        // For loop used for elegance. Always two players.
        for (Player player: players)
        {
            for (int i = 0; i < 2; i++)
            {
                // Deals card from deck, gets card rank and issues card to player.
                Card card = deck.dealCard();
                player.dealCard(card);
                player.setScore(score(player));     // TODO: MAY NOT WORK
            }

            System.out.println(score(player));
        }
    }

    /**
     * Creates a new game(hand) to be played.
     * Cycles through players in arrayList and clears hands followed by dealing.
     * User starts.
     */
    public void newGame()
    {
        for (Player player: players)
        {
            player.clearHand();
        }
        dealCards();
        this.playerTurn = 0;
    }

    /**
     * Player hit. Takes a card from the deck and deals it to the player requesting hit.
     * Sets score after hit.
     * @param player - Player requesting hit. Will add card to his/her hand.
     */
    public void hit(Player player)
    {
        Card card = deck.dealCard();
        getCurrPlayer().dealCard(card);
        player.setScore(score(player));

        // TODO: DELETE CONSOLE LINE
        System.out.println("New Score is:" + score(player));
    }

    /**
     * Getter method for the current player moving.
     * @return Current player
     */
    public Player getCurrPlayer()
    {
        return players.get(playerTurn);
    }

    /**
     * Scores the hand of the player passed in.
     * @param player Player's hand to be scored.
     * @return int value of player's score.
     */
    public int score(Player player)
    {
        int score = 0;
        int numAces = 0;
        int rank;

        for (Card card: player.getHand())
        {
            rank = card.getRank();

            if (rank < 2)
            {
                // TODO: DELETE CONSOLE LINE
                System.out.println("Error");
            }
            // If 2-10 rank gets added to score
            else if(rank <=10)
            {
                score+= rank;
            }
            // Else, + 10
            else if (rank <= 13)
            {
                score+= 10;
            }
            // Ace
            else {
                score += 11;
                numAces++;
            }
            // If brought over 21 by Ace, soft scoring occurs and ace becomes +1
            while (score > 21 && numAces > 0)
            {
                score-= 10;
                numAces--;
            }
        }
        return score;
    }


    /**
     * Scores each hand to determine a winner.
     * @return Player that won the hand.
     */
    public Player scoreHand()
    {
        int maxScore = 0;
        Player winner = null;

        // Cycle through, take score and determine winner based on condition.
        for (Player player: players)
        {
            player.setScore(score(player));

            // If score is <= 21 and greater than the held max score, they win.
            if (player.getScore() <= 21 && player.getScore() > maxScore)
            {
                maxScore = player.getScore();
                winner = player;
            }
            // If score is <= 21 and even to dealer score, house wins.
            else if (player.getScore() <= 21 && player.getScore() == maxScore)
            {
                winner = players.get(1);
            }

        }
        return winner;
    }


    /**
     * Changes turns from user to dealer.
     */
    public void nextPlayerTurn()
    {
        if (playerTurn == 0)
        {
            playerTurn = 1;
        }
        // Added just in case.
        if (playerTurn == 1)
        {
            playerTurn = 0;
        }

    }

    /**
     * Retrieves an arrayList of players in the game. (Should only ever be user and dealer)
     * @return List of players in game.
     */
    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    /**
     * Getter method to determine who's turn it is.
     */
    public Player getCurrentPlayer()
    {
        return players.get(playerTurn);
    }








}
