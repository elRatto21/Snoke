package snakeMain;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;

//import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
* 
* Snoke Version 1.3
*
*/





public class Start extends JFrame {
	private int highscore;
	private int oldScore;
	private int newScore;
	private Canvas player = new Canvas();
	private Canvas food = new Canvas();
	private int xPlayer = 100;
	private int yPlayer = 100;
	private int playerHeight = 50;
	private int playerWidth = 50;
	private int xFood = 300;
	private int yFood = 300;
	public int oldX = xPlayer;
	public int oldY = yPlayer;
	private int canX;
	private int canY;
	private int tempX;
	private boolean start = false;
	private int tempY;
	private int tempTempX;
	private int oldHigh;
	private int scoreNow;
	private int tempTempY;
	private int foodCount = 0;
	private boolean done = false;
	private int vier = 1;
	private int gameO = 0;
	private int delay = 300;
	private Timer timer1 = new Timer(100, null);
	public static int dir; //Direction: 1 = North  2 = East  3 = South  4 = West
	private JLabel lGameover = new JLabel();
	Random rnd = new Random();
	private Canvas jMenue = new Canvas();
	private JLabel jHighscore = new JLabel();
	private JLabel jScore = new JLabel();
	private JLabel jEndscore = new JLabel();
	private JButton jStart = new JButton();
	private JButton jQuit = new JButton();
	private JButton jSettings = new JButton();
	private JButton jStats = new JButton();
	private JButton jHome = new JButton();
	private JLabel jSnoke = new JLabel();
	ArrayList <String> cords = new ArrayList <String>();
	ArrayList <Canvas> canvs = new ArrayList <Canvas>();
	Container cp = getContentPane();
	AudioInputStream audioIn = AudioSystem.getAudioInputStream(Start.class.getResource("music.wav"));
	Clip clip = AudioSystem.getClip();
	AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(Start.class.getResource("clown.wav"));
	Clip clip2 = AudioSystem.getClip();
	
	private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
	private static final String MOVE_UP = "move up";
	private static final String MOVE_DOWN = "move down";
	private static final String MOVE_LEFT = "move left";
	private static final String MOVE_RIGHT = "move right";
	

	/* Borders for Player:
  	x Max: 700
  	y Max: 650
  	x Min: 0
  	y Min: 0
	 */


	public Start() throws LineUnavailableException, IOException, UnsupportedAudioFileException { 
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		int frameWidth = 764; 
		int frameHeight = 737;
		setSize(frameWidth, frameHeight);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (d.width - getSize().width) / 2;
		int y = (d.height - getSize().height) / 2;
		setLocation(x, y);
		setTitle("Snake auf Wish bestellt");
		setResizable(false);
		cp.setLayout(null);
		
		/*this.addKeyListener(new KeyAdapter() { 
			public void keyPressed(KeyEvent evt) { 
				snake_KeyPressed(evt);
			}
		});*/
		
		jSnoke.setBounds(175, 50, 400, 100);
		jSnoke.setText("Snoke");
		jSnoke.setHorizontalAlignment(SwingConstants.CENTER);
		jSnoke.setOpaque(true);
		jSnoke.setBackground(Color.DARK_GRAY);
		jSnoke.setForeground(Color.WHITE);
		jSnoke.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 69));
		jSnoke.setVisible(true);
		cp.add(jSnoke);
		
		jStart.setBounds(225, 200, 300, 50);
		jStart.setBackground(Color.GRAY);
		jStart.setForeground(Color.GREEN);
		jStart.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		jStart.setText("START");
		jStart.setVisible(true);
		cp.add(jStart);
		jStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jStart_ActionPerformed(evt);
			}
		});
		
		jStats.setBounds(225, 300, 300, 50);
		jStats.setBackground(Color.GRAY);
		jStats.setForeground(Color.RED);
		jStats.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		jStats.setText("STATS");
		jStats.setVisible(true);
		cp.add(jStats);
		
		jSettings.setBounds(225, 400, 300, 50);
		jSettings.setBackground(Color.GRAY);
		jSettings.setForeground(Color.RED);
		jSettings.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		jSettings.setText("SETTINGS");
		jSettings.setVisible(true);
		cp.add(jSettings);
		
		jQuit.setBounds(225, 500, 300, 50);
		jQuit.setBackground(Color.GRAY);
		jQuit.setForeground(Color.WHITE);
		jQuit.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		jQuit.setText("QUIT");
		jQuit.setVisible(true);
		cp.add(jQuit);
		jQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jQuit_ActionPerformed(evt);
			}
		});
		
		//Menü
		jMenue.setBounds(0, 0, 764, 737);
		jMenue.setVisible(true);
		jMenue.setBackground(Color.DARK_GRAY);
		cp.add(jMenue);
		
		jHome.setBounds(250, 200, 250, 50);
		jHome.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		jHome.setText("Return to menu");
		jHome.setHorizontalAlignment(SwingConstants.CENTER);
		jHome.setBackground(Color.GRAY);
		jHome.setForeground(Color.white);
		jHome.setVisible(false);
		cp.add(jHome);
		jHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					jHome_ActionPerformed(evt);
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Highscore Anzeige
		jHighscore.setBounds(244, 100, 278, 725);
		jHighscore.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 30));
		jHighscore.setHorizontalAlignment(SwingConstants.CENTER);
		jHighscore.setVisible(false);
		cp.add(jHighscore);
		
		//keine Lust mehr zu kommentieren, find selbst raus was Sache ist kekw
		jEndscore.setBounds(244, 384, 278, 68);
		jEndscore.setText("");
		jEndscore.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 30));
		jEndscore.setHorizontalAlignment(SwingConstants.CENTER);
		jEndscore.setVisible(false);
		cp.add(jEndscore);
		lGameover.setBounds(0, 0, 764, 737);
		lGameover.setText("Game over!");
		lGameover.setHorizontalTextPosition(SwingConstants.LEFT);
		lGameover.setHorizontalAlignment(SwingConstants.CENTER);
		lGameover.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 50));
		lGameover.setVisible(false);
		lGameover.setEnabled(true);
		lGameover.setBackground(Color.RED);
		lGameover.setOpaque(true);
		cp.add(lGameover);
		player.setBounds(xPlayer, yPlayer, playerHeight, playerWidth);
		player.setBackground(new Color(0x404040));
		cp.add(player);
		food.setBounds(xFood, yFood, 50, 50);
		food.setBackground(Color.GREEN);
		cp.add(food);
		jScore.setBounds(632, 8, 110, 39);
		jScore.setText("Score: " + foodCount);
		jScore.setFont(new Font("Arial", Font.BOLD, 20));
		jScore.setOpaque(true);
		jScore.setBackground(null);
		cp.add(jScore);
		cp.setBackground(Color.LIGHT_GRAY);
		timer1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent evt) { 
				try {
					timer1_ActionPerformed(evt);
				} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		timer1.setRepeats(true);
		timer1.setInitialDelay(0);
		timer1.setDelay(delay);
		
		
		jScore.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
		jScore.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
		jScore.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
		jScore.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);
		
		jScore.getActionMap().put(MOVE_UP, new MoveAction(1, getDir()));
		jScore.getActionMap().put(MOVE_RIGHT, new MoveAction(2, getDir()));
		jScore.getActionMap().put(MOVE_DOWN, new MoveAction(3, getDir()));
		jScore.getActionMap().put(MOVE_LEFT, new MoveAction(4, getDir()));
		
		setVisible(true);
	}

	public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		new Start();
	}

	/*public void snake_KeyPressed(KeyEvent evt) {
		System.out.println("wdd");
		if(evt.getKeyCode() == 38) {
			if(dir != 3) {
				dir = 1;
			}
		} else if (evt.getKeyCode() == 40) {
			if(dir != 1) {
				dir = 3;
			}
		} else if (evt.getKeyCode() == 39) {
			if(dir != 4) {
				dir = 2; 
			}
		} else if (evt.getKeyCode() == 37) {
			if(dir != 2) {	
				dir = 4; 
			}
		}
	}*/

	public void timer1_ActionPerformed(ActionEvent evt) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if(vier == 1) {
			cp.setBackground(Color.YELLOW);
			vier++;
		} else {
			cp.setBackground(Color.BLUE);
			vier = 1;
		}
		switch(dir) {
		case 1:
			if(yPlayer-50 >= 0) {
				oldX = xPlayer;
				oldY = yPlayer;
				yPlayer = yPlayer - 50;
				player.setBounds(xPlayer, yPlayer, playerHeight, playerWidth);
			} else {
				//System.out.println("Dead North");
				gameOver();
			}
			break;
		case 2:
			if(xPlayer+50 <= 700) {
				oldX = xPlayer;
				oldY = yPlayer;
				xPlayer = xPlayer + 50;
				player.setBounds(xPlayer, yPlayer, playerHeight, playerWidth);
			} else {
				//System.out.println("Dead East");
				gameOver();
			}
			break;
		case 3:
			if(yPlayer+50 <= 650) {
				oldX = xPlayer;
				oldY = yPlayer;
				yPlayer = yPlayer + 50;
				player.setBounds(xPlayer, yPlayer, playerHeight, playerWidth);
			} else {
				//System.out.println("Dead South");
				gameOver();;
			}
			break;
		case 4:
			if(xPlayer-50 >= 0) {
				oldX = xPlayer;
				oldY = yPlayer;
				xPlayer = xPlayer - 50;
				player.setBounds(xPlayer, yPlayer, playerHeight, playerWidth);
			} else {
				//System.out.println("Dead West");
				gameOver();
			}
			break;
		}
		if(gameO == 0) {
			gameOver();
		}
		for(int p = 0; p < canvs.size(); p++) {
			if(p == 0) {
				tempX = canvs.get(p).getX();
				tempY = canvs.get(p).getY();
				canvs.get(p).setBounds(oldX, oldY, 50, 50);
				canvs.get(canvs.size()-1).setVisible(true);
			} else {
				tempTempX = tempX;
				tempTempY = tempY;
				tempX = canvs.get(p).getX();
				tempY = canvs.get(p).getY();
				canvs.get(p).setBounds(tempTempX, tempTempY, 50, 50);
				canvs.get(canvs.size()-1).setVisible(true);
			}
		}
		if(xPlayer == xFood && yPlayer == yFood) {
			if(delay-5 > 30) {
				delay = delay - 10;
			}
			timer1.setDelay(delay);
			food.setVisible(false);
			xFood = rnd.nextInt(14) * 50;
			yFood = rnd.nextInt(13) * 50;
			food.setBounds(xFood, yFood, 50, 50);
			food.setVisible(true);
			foodCount++;
			newScore = foodCount;
			jScore.setText("Score: " + foodCount);
			
			String valStr[] = new String[2];
			//System.out.println(cords.size());
			
			if(cords.size() == 0) {
				cords.add(oldX + " " + oldY);
				canvs.add(new Canvas());
				canvs.get(canvs.size()-1).setBounds(oldX, oldY, 50, 50);
				canvs.get(canvs.size()-1).setBackground(Color.GRAY);
				canvs.get(canvs.size()-1).setVisible(true);
				cp.add(canvs.get(canvs.size()-1));
				//System.out.println(cords);
				//System.out.println(canvs);
			} else {
				valStr = cords.get(cords.size()-1).split(" ");
				//System.out.println(valStr[0] + " " + valStr[1]);
				int lastX = Integer.parseInt(valStr[0]);
				int lastY = Integer.parseInt(valStr[1]);
				//System.out.println(lastX + " " + lastY);
				cords.add(lastX + " " + lastY);
				canvs.add(new Canvas());
				canvs.get(canvs.size()-1).setBounds(lastX, lastY, 50, 50);
				canvs.get(canvs.size()-1).setBackground(Color.GRAY);
				canvs.get(canvs.size()-1).setVisible(true);
				cp.add(canvs.get(canvs.size()-1));
				//System.out.println(cords);
				//System.out.println(canvs);
			}
		}
		for(int u = 0; u < canvs.size(); u++) {
			cords.set(u, canvs.get(u).getX() + " " + canvs.get(u).getY());
		}
		for(int h = 0; h < canvs.size(); h++) {
			if(player.getX() == canvs.get(h).getX() && player.getY() == canvs.get(h).getY()) {
				//System.out.println("Dead Crash");
				gameOver();
			}
		}
	}
	
	
	public void gameOver() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		if(gameO == 0) {
			if(clip.isOpen() == false) {
				clip.open(audioIn);
			}
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.setFramePosition(0);
			clip.start();
			gameO = 1;
		} else if(gameO == 1) {
			timer1.stop();
			Highscore();
			lGameover.setVisible(true);
			jHighscore.setText("Highscore: " + String.valueOf(highscore));
			jHighscore.setVisible(true);
			jEndscore.setText("Final score: " + foodCount);
			jEndscore.setVisible(true);
			jHome.setVisible(true);
			clip.stop();
			if(clip2.isOpen() == false) {
				clip2.open(audioIn2);
			}
			clip2.setFramePosition(0);
			clip2.start();
			gameO = 2;
			if(done == false) {
				done = true;
			}
		}
	}
	
	public void Highscore() throws NumberFormatException, IOException {
		File in = new File("inHighscore.txt");
		File out = new File("outHighscore.txt");
		if(in.createNewFile()) {
			Writer fwNew = new FileWriter(in);
			BufferedWriter bwNew = new BufferedWriter(fwNew);
			bwNew.write("0");
			bwNew.flush();
			bwNew.close();
			fwNew.close();
		}
		Writer fw = new FileWriter(out);
		Reader fr = new FileReader(in);
		BufferedWriter bw = new BufferedWriter(fw);
		BufferedReader br = new BufferedReader(fr);
		
		String line = br.readLine();
		
		oldScore = Integer.parseInt(line);
		highscore = oldScore;
		System.out.println(oldScore);
		
		if(newScore > highscore) {
			highscore = newScore;
		}
		bw.write(String.valueOf(highscore));
		bw.flush();
		System.out.println("Done");
		bw.close();
		br.close();
		fr.close();
		fw.close();
		
		Writer fwTransfer = new FileWriter(in);
		BufferedWriter bwTransfer = new BufferedWriter(fwTransfer);
		bwTransfer.write(String.valueOf(highscore));
		bwTransfer.flush();
		bwTransfer.close();
		fwTransfer.close();
	}
	
	public void jQuit_ActionPerformed(ActionEvent evt) {
		System.exit(EXIT_ON_CLOSE);
	}
	
	public void jStart_ActionPerformed(ActionEvent evt) {
		jStart.setVisible(false);
		jSettings.setVisible(false);
		jStats.setVisible(false);
		jQuit.setVisible(false);
		jSnoke.setVisible(false);
		jMenue.setVisible(false);
		jHome.setVisible(false);
		starteDiese();
	}
	
	public void jHome_ActionPerformed(ActionEvent evt) throws NumberFormatException, IOException {
		//System.out.println("yikes");
		
		lGameover.setVisible(false);
		jHighscore.setVisible(false);
		jEndscore.setVisible(false);
		jHome.setVisible(false);
		if(clip2.isRunning()) {
			clip2.stop();
		}
		foodCount = 0;
		delay = 300;
		timer1.setDelay(delay);
		vier = 1;
		gameO = 0;
		jScore.setText("Score: " + String.valueOf(foodCount));
		xPlayer = 100;
		yPlayer = 100;
		oldX = xPlayer;
		oldY = yPlayer;
		playerHeight = 50;
		playerWidth = 50;
		player.setBounds(xPlayer, yPlayer, playerHeight, playerWidth);
		xFood = 300;
		yFood = 300;
		food.setBounds(xFood, yFood, 50, 50);
		//System.out.println(canvs.size());
		for(int iy = 0; iy < canvs.size(); iy++) {
			canvs.get(iy).setVisible(false);
		}
		canvs.removeAll(canvs);
		cords.removeAll(cords);
		//System.out.println(canvs.size());
		jSnoke.setVisible(true);
		jHome.setVisible(true);
		jStart.setVisible(true);
		jStats.setVisible(true);
		jSettings.setVisible(true);
		jQuit.setVisible(true);
		jMenue.setVisible(true);
		//System.out.println(canvs);
	}
	
	public void menue() {
		
		//timer1.start();
	}
	
	public void starteDiese() {
		timer1.start();
		dir = 3;
	}
	
	public int getDir() {
		return this.dir;
	}
	
	private class MoveAction extends AbstractAction{
		int oldDir;
		int newDir;
		
		MoveAction(int newDir, int oldDir) {
			this.newDir = newDir;
			this.oldDir = oldDir;
		}
		
		public void actionPerformed(ActionEvent e) {
			//System.out.println(newDir + " " + this.oldDir);
			//System.out.println(Start.dir);
			if(newDir == 1 && Start.dir != 3) {
				Start.dir = 1;
			} else if(newDir == 2 && Start.dir != 4) {
				Start.dir = 2;
			} else if(newDir == 3 && Start.dir != 1) {
				Start.dir = 3;
			} else if(newDir == 4 && Start.dir != 2) {
				Start.dir = 4;
			}
			oldDir = newDir;
			//System.out.println("brukes");
		}
	}
}
