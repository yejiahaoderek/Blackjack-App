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

public class Hand {
    ArrayList<Card> cardsinhand;
    
    //initialize the Hand
    public Hand() {
        cardsinhand = new ArrayList<Card>();
        this.cardsinhand.clear();
    }
    
    public void reset() {
        cardsinhand.clear();
    }

    public void add(Card card) {
        cardsinhand.add(card);
    }

    public int size() {
        return cardsinhand.size();
    }

    public Card getCard(int i) {
        return cardsinhand.get(i);
    }

    /** Three rules applied here
     * 1. J/Q/K (11/12/13) valued as 10
     * 2. see if the card is Ace (see if the value is 1)
     *    if so, make it valuing 11 while adding to sum (under the upper boundary: 11 + 10(maximum sum) = 21)
     *    or leave it valuing 1 while adding to sum (exceed the upper boundary: sum already >= 1).
     * 3. other values without change
     */ 
    public int getTotalValue() {
        int sum = 0;
        for (int i = 0; i < cardsinhand.size(); i++) {
            // for J/Q/K
            if (cardsinhand.get(i).getValue() > 10) {
                sum = sum + 10;
            }
            // Ace condition 1 (valued as 11)
            else if ( (cardsinhand.get(i).getValue() == 1) && (sum <= 10)) {
                sum = sum + 11;
            }
            // Ace condition 2 (valued as 1)
            else if ( (cardsinhand.get(i).getValue() == 1) && (sum > 10)) {
                sum = sum + 1;
            }
            // normal condition
            else if ( (cardsinhand.get(i).getValue() != 1) && (cardsinhand.get(i).getValue() <= 10) ) {
                sum = sum + cardsinhand.get(i).getValue();
            }
        }
        return sum;
    }

    /** let each line show 3 card values
    */
    public String toString() {
        String content = "";
        for (int i = 0; i < cardsinhand.size(); i++) {
            if (i == 0) { 
                content = content + " " + cardsinhand.get(i).toString();
            }
            else if (i % 3 == 0) {
                content = content + " " + cardsinhand.get(i).toString() + "\t";
            }
            else{
                content = content + " " + cardsinhand.get(i).toString();
            } 
        }
        return content;
    }

    /** Draw the cards in hand
     */
    public void draw(Graphics g, int x0, int y0, int drawWidth) {
        int x = x0;
        int y = y0;
        for (int i = 0; i < cardsinhand.size(); i++) {
            cardsinhand.get(i).draw(g, x, y, drawWidth);
            // in 3D style
            x = x + 20;
            y = y + 10;
        }
    }

}