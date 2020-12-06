package starter;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	
	private GLabel topScores = new GLabel("HIGHEST SCORES",514,270);
	private GLabel currentScore = new GLabel("CURRENT SCORE",515,140);
	private GLabel scoreLabel = new GLabel("",605,200);
	private GLabel gameOver = new GLabel("G  A  M  E  O  V  E  R",420,80);
	private GButton playAgain = new GButton("Play Again", LEFT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
	
	public GameOverPane(MainApplication app,int score) {
		super();
		program = app;
		this.score = score;
		mainMenu = new GButton("Main Menu", RIGHT_BOTTOM, BOTTOM, REG_BUTTON_WIDTH, REG_BUTTON_HEIGHT);
		mainMenu.setFillColor(BUTTON_COLOR);
		topScores.setFont(new Font("Ariel", Font.PLAIN,30));
		topScores.setColor(Color.BLUE);
		currentScore.setFont(new Font("Ariel", Font.PLAIN,30));
		currentScore.setColor(Color.BLUE);
		scoreLabel.setFont(new Font("Ariel", Font.PLAIN,35));
		scoreLabel.setLabel(Integer.toString(score));
		gameOver.setFont(new Font("Ariel", Font.PLAIN,45));
		gameOver.setColor(Color.black);
		playAgain.setFillColor(BUTTON_COLOR);
	}
	private void writeScore() {// writing of score to a text file.
		 try {
			 	File scoreFile = new File("../media/score.txt");
			 	FileWriter fileWrite = new FileWriter(scoreFile,true);
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWrite);
	           
	            System.out.println(Integer.toString(score));
	            bufferedWriter.flush();
	            bufferedWriter.newLine();
	            bufferedWriter.write(Integer.toString(score)); 
	            bufferedWriter.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
	private void readScore() {// reading of score from a text file.
		try {
            FileReader reader = new FileReader("../media/score.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                scores.add(Integer.parseInt(line));
            }
            Collections.sort(scores,Collections.reverseOrder());//Sorting scores with highest score being at the top.
            scoresDisplaying();
            reader.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	private void scoresDisplaying() {
		 int i = 0;
		 while(i<5 && i < scores.size()) {
       	  GLabel label = new GLabel(scores.get(i).toString(),605,320+(i*50));
       	  label.setFont(new Font("Ariel", Font.PLAIN,25));
       	  program.add(label);
       		
       		i++;
       }
	}
	@Override
	public void showContents() {
		program.add(mainMenu);
		writeScore();
		readScore();
		program.add(topScores);
		program.add(currentScore);
		program.add(scoreLabel);
		program.add(gameOver);
		program.add(playAgain);
	}

	@Override
	public void hideContents() {
		program.removeAll();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == mainMenu) {
			program.playSound("newbutton.wav",false);
			program.switchToMenu();
		}
		if(obj == playAgain) {
			program.playSound("newbutton.wav",false);
			program.switchToCharacterSelection();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		
		boolean buttonHover = false;
		if(!buttonHover) {
			notHovered(mainMenu);
			notHovered(playAgain);
		}
		
		if(obj == mainMenu) {
			hover(mainMenu);
		}
		else if(obj == playAgain) {
			hover(playAgain);
		}
		buttonHover = true;
	}
}
