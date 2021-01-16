/** 
 * CS 231
 * Jiahao Ye
 * Project 2
 */

import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Card {
    private int cardvalue;

    public enum Suit {
    Spades, Hearts, Clubs, Diamonds
    }

    private Suit suit;
    private Boolean visible;
    private BufferedImage image;
    private static BufferedImage backimage;

    public Card(){
    }

    public Card (int v, Suit s, boolean vis) {
        this.cardvalue = v;
        this.suit = s;
        this.visible = vis;
        loadImage();
    }

    private void loadImage() {
        try {
            image = ImageIO.read( new File( "PlayingCards/png/" + cardvalue + "_of_" + SuitString(suit)  + ".png" ));
        } catch( IOException e ) {
            System.out.println("Unable to find image file");
            return;
        }
        
    }

    private void loadBkgImage() {
        try {
            backimage = ImageIO.read( new File( "PlayingCards/back.png") );
        } catch( IOException e ) {
            System.out.println("Unable to find image file");
            return;
        }

    }

    public Suit getSuit() {
        return suit;
    }

    public boolean getVisible() {
        return visible;
    }

    public void setVisible (boolean vis) {
        this.visible = vis;
    }

    public static String SuitString( Suit s ) {
        return "" + s;
    }

    public String toString() {
        return cardvalue + " of " + SuitString(suit) ;
    }

    public void draw (Graphics g, int x0, int y0, int drawWidth) {
        if (visible == true) {
            loadImage();
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            g.drawImage( image, x0, y0, drawWidth, (height * drawWidth)/width, null );
        }
        if (visible == false) {
            loadBkgImage();
            int width = backimage.getWidth(null);
            int height = backimage.getHeight(null);
            g.drawImage( backimage, x0, y0, drawWidth, (height * drawWidth)/width, null );
        }
    }

    public Card(int v) {
        if (v > 13) {
            System.out.println("invalid cardvalue");
        }
        else {
            cardvalue = v;
        }
    }

    public int getValue() {
        return cardvalue;
    }

}