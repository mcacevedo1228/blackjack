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

    private Deck deck;
    private ArrayList<Player> players;
    private int playerTurn;
    int cardCounter;


    /**
     * Constructor for Blackjack models.Game.
     */
    public Game()
    {
        // Creates new deck to be played with.
        deck = new Deck();

        //Creates list of players and adds players to list.
        players = new ArrayList<Player>();
        players.add(new Player("Dealer"));
        players.add(new Player("User"));

        // User goes first.
        playerTurn = 1;

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
                cardParse(card);
                player.dealCard(card);
            }
        }
    }

    // Gets card rank to be assigned for play.
    public void cardParse(Card card)
    {
        if (card.getRank() <=6 && card.getRank() >=2)
        {
            cardCounter+=1;
        }
        else if (card.getRank() >= 10 && card.getRank() <=14)
        {
            cardCounter-=1;
        }
        else {}
    }

    public void newGame()
    {
        for (Player player: players)
        {
            player.clearHand();
            dealCards();
            playerTurn = 1;
        }
    }

    public void hit()
    {
        Card card = deck.dealCard();
        getCurrPlayer().dealCard(card);
        cardParse(card);
    }

    public Player getCurrPlayer()
    {
        return players.get(playerTurn);
    }

    public int score(Player player)
    {
        int score = 0;
        int numAces = 0;
        int rank;
        for (Card card: player.getHand())
        {
            rank = card.getRank();
            if(rank <=10)
            {
                score+= rank;
            }
            else if (rank <= 13)
            {
                score+= 10;
            }
            else {
                score += 11;
                numAces++;
            }
            while (score > 21 && numAces > 0)
            {
                score-= 10;
                numAces--;
            }
        }
        return score;
    }

    public Player scoreHand()
    {
        Player winner = null;

        for (Player player: players)
        {
            player.setScore(score(player));
            if (player.getScore() <= 21)
            {
                winner = player;
            }
        }
        return winner;
    }


    public void nextPlayerTurn()
    {
        if (playerTurn == 1)
        {
            playerTurn = 0;
        }
        else
        {
            playerTurn = 1;
        }
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public Player getCurrentPlayer()
    {
        return players.get(playerTurn);
    }








}
