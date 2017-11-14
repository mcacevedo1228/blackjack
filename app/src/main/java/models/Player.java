package models;

import java.util.ArrayList;

/**
 * Created by marcacevedo on 11/13/17.
 */

public class Player {
    // Fields to be used in class.
    private String name;
    private ArrayList<Card> hand;
    private int score;

    /**
     * Getter for user score.
     * @return Score of user.
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for user score.
     * @param value of user's new score.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Constructor for Player class.
     */
    public Player(String name)
    {
        // Sets name and creates a hand of cards for said user.
        this.name = name;
        hand = new ArrayList<Card>();
    }

    /**
     * Clears the users hand after an iteration of play.
     */
    public void clearHand()
    {
        hand.clear();
    }

    /**
     * Deals a hand to the user.
     * @param Card to be added to user's hand.
     */
    public void dealCard(Card card)
    {
        hand.add(card);
    }

    /**
     * Getter method to retrieve user's hand.
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * Getter method to retrieve user's name.
     */
    public String getName()
    {
        return name;
    }




}
