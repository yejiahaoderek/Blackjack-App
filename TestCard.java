/*
  Bruce A. Maxwell
  Fall 2018
  CS 231 Project 2
  Test function for cards
*/

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;



public class TestCard {
    JFrame win;
    JLabel status;
    int curCard;
    ArrayList<Card> deck;

    public TestCard() {
	deck = new ArrayList<Card>();
	for(int i=1;i<=13;i++) {
	    deck.add( new Card(i, Card.Suit.Spades, true ) );
	    deck.add( new Card(i, Card.Suit.Hearts, true ) );
	    deck.add( new Card(i, Card.Suit.Clubs, true ) );
	    deck.add( new Card(i, Card.Suit.Diamonds, true ) );
	}

	curCard = 0;

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
	status = new JLabel(deck.get(curCard).toString(), JLabel.CENTER );
	JButton next = new JButton( "Next" );
	next.addActionListener( new NextActionListener() );
	JButton flip = new JButton( "Flip" );
	flip.addActionListener( new FlipActionListener() );

	BorderLayout layout = new BorderLayout();
	layout.setHgap(20);
	this.win.setLayout( layout );
	this.win.add( dp, BorderLayout.NORTH );
	this.win.add( status, BorderLayout.LINE_START );
	this.win.add( next, BorderLayout.CENTER );
	this.win.add( flip, BorderLayout.LINE_END );
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

	    // draw the current card
	    deck.get(curCard).draw( g, 100, 10, 100 );
	}
    }

    private class NextActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    curCard = (curCard + 1) % deck.size();
	    status.setText( deck.get(curCard).toString());
	    win.repaint();
	}
    }

    private class FlipActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    deck.get(curCard).setVisible( !deck.get(curCard).getVisible() );
	    win.repaint();
	}
    } 

    
    public static void main( String[] argv ) throws InterruptedException {
	TestCard app = new TestCard();
    }
}