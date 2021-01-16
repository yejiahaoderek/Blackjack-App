/** 
 * CS 231
 * Jiahao Ye
 * Project 2
 */

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Blackjack {
    private Deck gamedeck = new Deck();
    private Hand player = new Hand();
    private Hand dealer = new Hand();

    // initialize
    public Blackjack() {
        reset(true);
    }

    public int decksize() {
        return gamedeck.size();
    }

    public int playerHandValue() {
        return player.getTotalValue();
    }

    public int dealerHandValue() {
        return dealer.getTotalValue();
    }

    public void hitPlayer() {
        player.add(gamedeck.deal());
    }

    /** arrange positions for Player's hand, Deck, and Dealer's hand
     */
    public void draw( Graphics g, int x0, int y0, int drawWidth ) {
        // gamedeck in the middle
        gamedeck.draw(g, x0 + 275, y0 + 275, drawWidth);
        // player's hand in the left-upper corner
        player.draw(g, x0 + 75, y0 + 50, drawWidth);
        // dealer's hand in the right-down corner
        dealer.draw(g, x0 + 475, y0 + 475, drawWidth);
    }

    public void reset(boolean newDeck) {
        if (newDeck = true) {
            player.reset();
            dealer.reset();
            gamedeck.shuffle();
            // to prevent the deck is out of enough cards to play the game
            if (gamedeck.size() < 10) {
                gamedeck.build();
                gamedeck.shuffle();
            }
        }
    }

    /** make sure hands are initialized before deal, as deal is the first step of each game
    */
    public void deal() {
        player.reset();
        dealer.reset();
        player.add(gamedeck.deal());
        player.add(gamedeck.deal());
        dealer.add(gamedeck.deal());
        dealer.add(gamedeck.deal());
        dealer.getCard(1).setVisible(false);
    }

    public String toString() {
        String result;
        if (player.getTotalValue() > 21) {
            result = "Dealer wins!";
        }
        else if (dealer.getTotalValue() > 21) {
            result = "Player wins!";
        }
        else if (player.getTotalValue() == dealer.getTotalValue()) {
            result = "Tie!!";
        }
        else if (player.getTotalValue() > dealer.getTotalValue()) {
            result = "Player wins!";
        }
        else {
            result = "Dealer wins!";
        }

        return result;
    }
  
    public boolean playerTurn() {
        // if the deck has less than 26 cards, shuffle the rest cards
        if (gamedeck.size() < 26) {
            gamedeck.shuffle();
        }

        while (player.getTotalValue() < 16) {
            player.add(gamedeck.deal());
        }
        // check if the player loses at the current time
        if (player.getTotalValue() > 21) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean dealerTurn() {
        for (int i = 0; i < dealer.size(); i++) {
            dealer.cardsinhand.get(i).setVisible(true);
        }

        if (gamedeck.size() < 26) {
            gamedeck.shuffle();
        }

        while(dealer.getTotalValue() < 17) {
            dealer.add(gamedeck.deal());
        }
        // check if the dealer loses at this time point
        if (dealer.getTotalValue() > 21) {
            return false;
        }
        else {
            return true;
        }
    }
    public static void main(String[] args) {
        Blackjack newgame = new Blackjack();
        // play the game 3 times
        for (int i = 0; i < 3; i++) {
            newgame.game(true);
        }
    }

    public int game(boolean verbose) {
        // player wins: return 1
        // dealer wins: return -1
        // tie: return 0
        reset(true);
        deal();
        // record the initial hands
        int initialplayerhand = player.getTotalValue();
        int initialdealerhand = dealer.getTotalValue();
        int result = 0;
        // judge the game
        if (playerTurn() == false) {
            result = -1;
        }
        else {
            playerTurn();
            if (playerTurn() == false) {
                result = -1;
            }
            else {
                dealerTurn();
            if (dealerTurn() == false) {
                  result = 1;
            }
            else if (dealer.getTotalValue() == player.getTotalValue()) {
                 result = 0;
            }
            else if (dealer.getTotalValue() < player.getTotalValue()) {
                 result = 1;
            }
            else {
                 result = -1;
            }
            }
        }

        if (verbose == true) {
                System.out.println("The inital hands of player and dealer are: " + initialplayerhand + " and " + initialdealerhand + ".");
                System.out.println(toString() + "\n");
        }

        return result;
    } 
    
    public void setVisible() {
        for (int i = 0; i < dealer.size(); i++) {
            dealer.cardsinhand.get(i).setVisible(true);
        }
    }
}
                          
