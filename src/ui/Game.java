package ui;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import world.HexMap;

public class Game {
	
	public static void main(String[] args) {
		
		HexMap worldMap = new HexMap(40,30);
		
		
		
	    JFrame frame = new JFrame();
	    frame.setTitle("Hex game");
	    frame.setSize(1000, 800);
	    frame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	    
	    Container contentPane = frame.getContentPane();
	    contentPane.add(new DrawMap(worldMap));
	 
	    frame.setVisible(true);
	  }

}
