/** 
 * CS 231
 * Jiahao Ye
 * Project 2
 */

import java.util.ArrayList;
import java.util.Random; 
import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class Deck {
    private ArrayList<Card> cards;

    //initialize the Deck
    public Deck() {
        cards = new ArrayList<Card>();
        build();
        shuffle(); 
    }

    /** Create 52 cards in 4 different suits
     */
    public void build() {
        for (int x =0; x < 4; x++){
            for(int y = 1; y < 14; y++){
                Card card;
                card = new Card();
                    if (x == 0) {
                        card = new Card (y, Card.Suit.Spades, true);
                    }
                    if (x == 1) {
                        card = new Card (y, Card.Suit.Hearts, true);
                    }
                    if (x == 2) {
                        card = new Card (y, Card.Suit.Clubs, true);
                    }
                    if (x == 3) {
                        card = new Card (y, Card.Suit.Diamonds, true);
                    }
                    cards.add(card);
                }
        }
    }

    public int size() {
        return cards.size();
    }

    public Card deal() {
        if (cards.size() > 0) {
            return cards.remove(0); 
        }
        else {
            return null;
        }
    }

    public Card pick(int i) {
        return cards.remove(0);
    }

    public void shuffle() {
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis()); 
        ArrayList<Card> cards2 = new ArrayList<Card>(); 
        
        while(cards.size() > 0) {
            cards2.add(cards.remove(rand.nextInt(cards.size()))); 
        }

        while(cards2.size() > 0) {
            cards.add(cards2.remove(rand.nextInt(cards2.size()))); 
        } 
    }

    /** let each line show 3 card values
     */
    public String toString() {
        String content = "";
        for (int i = 0; i < cards.size(); i++) {
            if (i == 0) { 
                content = content + " " + cards.get(i).toString();
            }
            else if (i % 3 == 0) {
                content = content + " " + cards.get(i).toString() + "\t";
            }
            else{
                content = content + " " + cards.get(i).toString();
            } 
        }
        return content;
    }

    /** Draw the deck with cards in 3D style 
     */
    public void draw(Graphics g, int x0, int y0, int drawWidth) {
        // if there are more than 1 card on the deck...
        if (cards.size() >= 2) {
            Card card = new Card();
            card.setVisible(false);
            // Draw 3D style cards
            card.draw(g, x0, y0, drawWidth);
            card.draw(g, x0+5, y0+5, drawWidth);
            card.draw(g, x0+10, y0+10, drawWidth);
        }
        // if there are only one card on the deck...
        if (cards.size() == 1) {
            // Draw only one card
            Card card = new Card();
            card.draw(g, x0, y0, drawWidth);
        }
    }

}