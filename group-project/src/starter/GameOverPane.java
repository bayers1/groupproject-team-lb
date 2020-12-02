package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;

public class GameOverPane extends GraphicsPane {
	private MainApplication program;
	
	public static final int MENU_BUTTON_HEIGHT = 60;
	public static final int MENU_BUTTON_WIDTH = 140;
	public static final int START_POS = 180;
	
	private GButton mainMenu;
	private int score;
	private ArrayList<Integer> scores = new ArrayList<Integer>();
	
	String text;
	
	
	public GameOverPane(MainApplication app,int score) {
		super();
		program = app;
		this.score = score;
		mainMenu = new GButton("Main Menu", RIGHT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		mainMenu.setFillColor(BUTTON_COLOR);
		//readScore();
		
	}
	private void writeScore() {// writing of score to a text file.called in gameover().
		 try {
	           // FileOutputStream outputStream = new FileOutputStream("../media/score.txt");
	            //OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			 	File scoreFile = new File("../media/score.txt");
			 	FileWriter fileWrite = new FileWriter(scoreFile,true);
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
	           
	         //   scores.add(score);
	            System.out.println(Integer.toString(score));
	            bufferedWriter.flush();
	            bufferedWriter.newLine();
	            bufferedWriter.write(Integer.toString(score));
	            //bufferedWriter.append(Integer.toString(score));
	            
	          //  bufferedWriter.newLine();
	             
	            bufferedWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	private void readScore() {// reading of score from a text file.//called when collision.
		try {
            FileReader reader = new FileReader("../media/score.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                scores.add(Integer.parseInt(line));
              
                
            }
            Collections.sort(scores,Collections.reverseOrder());
            while(i<5 && i < scores.size()) {
            	  GLabel label = new GLabel(scores.get(i).toString(),500,40+(i*40));
            	  program.add(label);
            		
            		i++;
            }
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	@Override
	public void showContents() {
		program.add(mainMenu);
		writeScore();
		readScore();
		
		/*
		for(GLabel score: last5scores) {
		program.add(score);
		}
		*/
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == mainMenu) {
			program.switchToMenu();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		boolean buttonHover = false;
		if(!buttonHover) {
			notHovered(mainMenu);
		}
		
		if(obj == mainMenu) {
			hover(mainMenu);
		}
		
		buttonHover = true;
		
	}
}
