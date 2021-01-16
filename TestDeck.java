/*
  Bruce A. Maxwell
  Fall 2018
  CS 231 Project 2
  Test function for deck
*/

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;



public class TestDeck {
    JFrame win;
    JLabel status;
    Deck deck;
    Card removed;

    public TestDeck() {
	deck = new Deck();
	deck.shuffle();
	removed = null;

    	// GUI
	buildGUI();
    }

    public void buildGUI() {
	// create the main window and set its size
	win = new JFrame();

	// set the default close operation
	this.win.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	// create panels for drawing stuff
	DrawPanel dp = new DrawPanel( 300, 300 );
	status = new JLabel("" + deck.size(), JLabel.CENTER );
	JButton deal = new JButton( "Deal" );
	deal.addActionListener( new DealActionListener() );

	BorderLayout layout = new BorderLayout();
	layout.setHgap(20);
	this.win.setLayout( layout );
	this.win.add( dp, BorderLayout.NORTH );
	this.win.add( status, BorderLayout.LINE_START );
	this.win.add( deal, BorderLayout.LINE_END );
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
	    if( removed != null ) {
		removed.draw( g, 170, 10, 100 );
	    }
	    
	}
    }

    private class DealActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    removed = deck.deal();
	    status.setText( "" + deck.size() );
	    win.repaint();
	}
    }

    
    public static void main( String[] argv ) throws InterruptedException {
	TestDeck app = new TestDeck();
    }
}