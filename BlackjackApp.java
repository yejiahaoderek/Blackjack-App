/** 
 * CS 231
 * Jiahao Ye
 * Project 2
 */

import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.imageio.ImageIO;

public class BlackjackApp {
    Blackjack game = new Blackjack();
    JFrame win;
    JButton Deal;
    JButton Hit;
    JButton Hold;
    JLabel playerstatus;
    JLabel dealerstatus;
    // store the score(ration) in form of player : dealer
    JLabel score;
    JLabel outcome;
    JPanel drawbox;
    // count the win time of each role
    int playerwin = 0;
    int dealerwin = 0;

    public BlackjackApp() {

        createGUI();
    }

    /** 1. create objects; 
        2. draw and add panel/buttons/labels 
        3. attach different ActionListenr to different buttons
    */
    private void createGUI() {
        // create all of the necessary GUI elements here
        win = new JFrame();
        Deal = new JButton("Deal");
        Hit = new JButton("Hit");
        Hold = new JButton("Hold");
        playerstatus = new JLabel("Player Status:", JLabel.CENTER);
        dealerstatus = new JLabel("Dealer Status:", JLabel.CENTER);
        outcome = new JLabel("Outcome:", JLabel.CENTER);
        score = new JLabel("Score P/D:  0 : 0", JLabel.CENTER);
        drawbox = new DrawPanel(700, 700);
        this.win.add(drawbox);
        this.drawbox.add(Deal);
        this.drawbox.add(Hit);
        this.drawbox.add(Hold);
        Deal.setEnabled(true);
        Hit.setEnabled(false);
        Hold.setEnabled(false);
        this.win.add(playerstatus);
        this.win.add(dealerstatus);
        this.win.add(outcome);
        this.drawbox.add(score);
        Deal.addActionListener (new DealActionListener());
        Hit.addActionListener (new HitActionListener());
        Hold.addActionListener (new HoldActionListener());
        this.win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.win.setLayout( new FlowLayout() );
        this.win.setSize(600, 800);
        this.win.setVisible(true);
        
      }


     /** class extends JComponents, used to draw panel
     */
    private class DrawPanel extends JPanel {
    
        public DrawPanel( int width, int height) {
            super();
            this.setPreferredSize( new Dimension(width, height) );
            this.setBackground( new Color( 0.1f, 0.5f, 0.13f ) );
        }

        public void paintComponent(Graphics g)  {
        
            super.paintComponent(g);
            game.draw(g, 0, 0, 125);
        }
    }

    /** create private classes that extend ActionListener (for buttons)
    */

    // Deal button action
    private class DealActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           
            // set up the buttons
            Hit.setEnabled(true);
            Hold.setEnabled(true);
            Deal.setEnabled(false);

            // reset the game; clear the label
            game.reset(true);
            outcome.setText("Outcome: ");

            // start the game
            game.deal();
            playerstatus.setText("Player Status: " + game.playerHandValue());

            // set dealerstatus unavailable to the player
            dealerstatus.setText("");

            // judge the game after deal, as Ace might be valued as 11,
            // resulting in 21 with only two cards

            // if player's total card value is 21 (not equal to dealer's) ==> player wins
            if ( (game.playerHandValue() == 21) && (game.dealerHandValue() != 21) ) {
                outcome.setText("Player wins!");
                Hit.setEnabled(false);
                Hold.setEnabled(false);
                Deal.setEnabled(true);
                // update the player's win count
                playerwin++;
                game.setVisible();
                dealerstatus.setText("Dealer Status: " + game.dealerHandValue());
            }

            // if dealer's total card value is 21 (not equal to player's) ==> dealer wins
            if ( (game.playerHandValue() != 21) && (game.dealerHandValue() == 21) ) {
                outcome.setText("Dealer wins!");
                Hit.setEnabled(false);
                Hold.setEnabled(false);
                Deal.setEnabled(true);
                // update the dealer's win count
                dealerwin++;
                game.setVisible();
                dealerstatus.setText("Dealer Status: " + game.dealerHandValue());
            }

            // if both dealer and player got an Ace and an J/Q/K, resulting in 11+10=21, ==> tie
            if ( (game.playerHandValue() == 21) && (game.dealerHandValue() == 21) ) {
                outcome.setText("Tie!");
                game.setVisible();
                dealerstatus.setText("Dealer Status: " + game.dealerHandValue());
            }

            // update the score
            score.setText("Score P/L: " + playerwin + " : " + dealerwin);
            win.repaint();
        }
    }
    
    // Hit button action
    private class HitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            game.hitPlayer();

            // judge the current status

            // player busts; game ends; buttons reset;
            if (game.playerHandValue() > 21) {
                Hit.setEnabled(false);
                Hold.setEnabled(false);
                Deal.setEnabled(true);
                playerstatus.setText("Player Status: " + game.playerHandValue());
                dealerstatus.setText("Dealer Status: " + game.dealerHandValue());
                outcome.setText("Outcome: Dealer wins!");
                dealerwin++;
                game.setVisible();
                score.setText("Score P/L: " + playerwin + " : " + dealerwin);
            }

            // player stays; update player's total value
            if (game.playerHandValue() <= 21) {
                playerstatus.setText("Player Status: " + game.playerHandValue());
            }
            win.repaint();
        }
    }

    // Hold button action
    private class HoldActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // game is about to end; reset the buttons
            Hit.setEnabled(false);
            Hold.setEnabled(false);
            Deal.setEnabled(true);
            //dealer's turn
            game.dealerTurn();
            // judge the outcome and update the outcome label
            outcome.setText("Outcome: " + game.toString());
            dealerstatus.setText("Dealer Status: " + game.dealerHandValue());
            // keep tracking player's and dealer's win count
            if (game.toString() == "Dealer wins!") {
                dealerwin++;
            }
            if (game.toString() == "Player wins!") {
                playerwin++;
            }
            //update the score
            score.setText("Score P/L: " + playerwin + " : " + dealerwin);    
            win.repaint();
        }
    }

    // main function that starts everything off
    public static void main( String[] argv ) {

    BlackjackApp app = new BlackjackApp();
    
    }

}