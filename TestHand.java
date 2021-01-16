/*
  Bruce A. Maxwell
  Fall 2018
  CS 231 Project 2
  Test function for hand
*/

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;



public class TestHand {
    JFrame win;
    JLabel status;
    JLabel value;
    JButton dealButton;
    Deck deck;
    Hand hand;

    public TestHand() {
	deck = new Deck();
	deck.shuffle();

	hand = new Hand();

    	// GUI
	buildGUI();
    }

    public void buildGUI() {
	// create the main window and set its size
	win = new JFrame();

	// set the default close operation
	this.win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	// create panels for drawing stuff
	DrawPanel dp = new DrawPanel( 500, 300 );
	status = new JLabel("Hand Size: " + hand.size(), JLabel.CENTER );
	value = new JLabel( "Hand Value: " + hand.getTotalValue(), JLabel.CENTER );
	dealButton = new JButton( "Deal" );
	dealButton.addActionListener( new DealActionListener() );

	BorderLayout layout = new BorderLayout();
	layout.setHgap(20);
	this.win.setLayout( layout );
	this.win.add( dp, BorderLayout.NORTH );
	this.win.add( status, BorderLayout.LINE_START );
	this.win.add( value, BorderLayout.CENTER );
	this.win.add( dealButton, BorderLayout.LINE_END );
	this.win.pack();

	this.win.setVisible( true );
    }

    private class DrawPanel extends JPanel {
	    
	public DrawPanel( int width, int height) {
	    super();
	    this.setPreferredSize( new Dimension(width, height) );
	    this.setBackground( new Color( 0.1f, 0.5f, 0.13f ) );
	}

	public void paintComponent(Graphics g)  {
	    super.paintComponent(g);

	    // draw the deck
	    deck.draw( g, 30, 10, 100 );
	    hand.draw( g, 200, 10, 100 );
	}
    }

    private class DealActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    if( hand.size() < 10 )
		hand.add( deck.deal() );
	    else
		dealButton.setEnabled( false );
		
	    status.setText( "Hand Size: " + hand.size() );
	    value.setText( "Hand Value: " + hand.getTotalValue() );
	    win.repaint();
	}
    }

    
    public static void main( String[] argv ) throws InterruptedException {
	TestHand app = new TestHand();
    }
}