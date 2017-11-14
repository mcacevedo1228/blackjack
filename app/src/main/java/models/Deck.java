package models;

/**
 * Created by marcacevedo on 11/13/17.
 */

public class Deck {

    private Card[] cards;
    private int numCards;

    /**
     * Constructor method for Deck class.
     */
    public Deck()
    {
        cards = new Card[52];
        fillDeck();
        shuffleDeck();
    }


    /**
     * Fills array of Cards with all cards.
     */
    private void fillDeck()
    {
        int index = 0;
        for (int i = 2; i <= 14; i++)
        {
            for (int j = 1; j <= 4; j++)
            {
                cards[index] = new Card(i, j);
                index++;
            }
            numCards = 52;
        }
    }

    /**
     * Shuffles the deck for the game.
     */
    public void shuffleDeck()
    {
        // Field for a random card to be assigned too.
        int randomCard;

        // Fill every index in the array with a new value.
        for (int i = 0; i < numCards - 1; i++)
        {
            // Generates Pseudo-Randomly Generated Number in the accepted range.
            randomCard = (int) ((numCards-i)*Math.random()+i);

            //Assigns and replaces indeces with newly generated values.
            Card tmp = cards[i];
            cards[i] = cards[randomCard];
            cards[randomCard] = tmp;
        }
    }


    /**
     * Deals card out to user.
     * @return Card that is dealt to user.
     */
    public Card dealCard()
    {
        // Reset the deck if there are no cards lefts in the deck.
        if (numCards == 0)
        {
            fillDeck();
            shuffleDeck();
        }
        // Else, keep dealing cards in deck.
        numCards--;
        return cards[numCards];
    }


    /**
     * Getter method for retrieving the number of cards left in deck.
     * @return Number of cards left in deck.
     */
    public int getNumCards() {
        return numCards;
    }

}
