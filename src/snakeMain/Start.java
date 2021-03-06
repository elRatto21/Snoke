package snakeMain;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;

//import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.util.Random;
import java.io.*;

import javax.imageio.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
* 
* Snoke Version 0.3
*
*/

public class Start extends JFrame {
	
	private int highscore;
	private int oldScore;
	private int newScore;
	
	private JPanel player = new JPanel();
	private JPanel food = new JPanel();
	
	private int xPlayer = 100;
	private int yPlayer = 100;
	private int playerHeight = 50;
	private int playerWidth = 50;
	private int xFood = 300;
	private int yFood = 300;
	public int oldX = xPlayer;
	public int oldY = yPlayer;
	private int tempX;
	private int tempY;
	private int tempTempX;
	private int tempTempY;
	private int volume = 10;
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
	private JPanel jSetSettings = new JPanel();
	private JSlider jSetDB = new JSlider();
	private JLabel jSetDBText = new JLabel();
	private JButton jSetHome = new JButton();
	private JLabel jSetSetText = new JLabel();
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

		jSetDBText.setBounds(325, 169, 300, 75);
		jSetDBText.setText("VOLUME");
		jSetDBText.setForeground(Color.WHITE);
		jSetDBText.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 18));
		jSetDBText.setVisible(false);
		cp.add(jSetDBText);
		
		jSetSetText.setBounds(250, 69, 300, 75);
		jSetSetText.setText("SETTINGS");
		jSetSetText.setForeground(Color.WHITE);
		jSetSetText.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 40));
		jSetSetText.setVisible(false);
		cp.add(jSetSetText);
		
		jSetDB.setBounds(225, 200, 300, 100);
		jSetDB.setVisible(false);
		jSetDB.setMinorTickSpacing(1);
	    jSetDB.setMajorTickSpacing(5);
	    jSetDB.setPaintTicks(true);
	    jSetDB.setPaintLabels(true);
	    jSetDB.setMaximum(10);
	    jSetDB.setMinimum(0);
	    jSetDB.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
	    jSetDB.setBackground(new Color(0x404040));
	    jSetDB.setForeground(Color.WHITE);
	    jSetDB.setSnapToTicks(true);
	    jSetDB.setOpaque(false);
		cp.add(jSetDB);
		
		jSetHome.setBounds(225, 500, 300, 75);
		jSetHome.setBackground(Color.GRAY);
		jSetHome.setForeground(Color.WHITE);
		jSetHome.setText("RETURN TO HOME");
		jSetHome.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		jSetHome.setVisible(false);
		cp.add(jSetHome);
		jSetHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					jSetHome_ActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		jSetSettings.setBounds(0, 0, 764, 737);
		jSetSettings.setBackground(Color.DARK_GRAY);
		jSetSettings.setVisible(false);
		cp.add(jSetSettings);
		
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
				try {
					jStart_ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		jStats.setBounds(225, 300, 300, 50);
		jStats.setBackground(Color.GRAY);
		jStats.setForeground(Color.WHITE);
		jStats.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		jStats.setText("STATS");
		jStats.setVisible(true);
		cp.add(jStats);
		
		jSettings.setBounds(225, 400, 300, 50);
		jSettings.setBackground(Color.GRAY);
		jSettings.setForeground(Color.WHITE);
		jSettings.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		jSettings.setText("SETTINGS");
		jSettings.setVisible(true);
		cp.add(jSettings);
		jSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				try {
					jSettings_ActionPerformed(evt);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		
		jQuit.setBounds(225, 500, 300, 50);
		jQuit.setBackground(Color.GRAY);
		jQuit.setForeground(Color.RED);
		jQuit.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 18));
		jQuit.setText("QUIT");
		jQuit.setVisible(true);
		cp.add(jQuit);
		jQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jQuit_ActionPerformed(evt);
			}
		});
		
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
		
		jHighscore.setBounds(244, 100, 278, 725);
		jHighscore.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 30));
		jHighscore.setHorizontalAlignment(SwingConstants.CENTER);
		jHighscore.setVisible(false);
		cp.add(jHighscore);
		
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
		
		player.setBackground(null);
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
			
			if(cords.size() == 0) {
				cords.add(oldX + " " + oldY);
				canvs.add(new Canvas());
				canvs.get(canvs.size()-1).setBounds(oldX, oldY, 50, 50);
				canvs.get(canvs.size()-1).setBackground(Color.GRAY);
				canvs.get(canvs.size()-1).setVisible(true);
				cp.add(canvs.get(canvs.size()-1));
			} else {
				valStr = cords.get(cords.size()-1).split(" ");
				int lastX = Integer.parseInt(valStr[0]);
				int lastY = Integer.parseInt(valStr[1]);
				cords.add(lastX + " " + lastY);
				canvs.add(new Canvas());
				canvs.get(canvs.size()-1).setBounds(lastX, lastY, 50, 50);
				canvs.get(canvs.size()-1).setBackground(Color.GRAY);
				canvs.get(canvs.size()-1).setVisible(true);
				cp.add(canvs.get(canvs.size()-1));
			}
		}
		for(int u = 0; u < canvs.size(); u++) {
			cords.set(u, canvs.get(u).getX() + " " + canvs.get(u).getY());
		}
		for(int h = 0; h < canvs.size(); h++) {
			if(player.getX() == canvs.get(h).getX() && player.getY() == canvs.get(h).getY()) {
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
			clipVolume(1);
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
			clipVolume(2);
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
	
	public void jStart_ActionPerformed(ActionEvent evt) throws IOException {
		jStart.setVisible(false);
		jSettings.setVisible(false);
		jStats.setVisible(false);
		jQuit.setVisible(false);
		jSnoke.setVisible(false);
		jMenue.setVisible(false);
		jHome.setVisible(false);
		readVolume();
		addSkin();
		starteDiese();
	}
	
	public void jSettings_ActionPerformed(ActionEvent evt) throws IOException {
		jStart.setVisible(false);
		jSettings.setVisible(false);
		jStats.setVisible(false);
		jQuit.setVisible(false);
		jSnoke.setVisible(false);
		jMenue.setVisible(false);
		jHome.setVisible(false);
		settings();
	}
	
	public void jSetHome_ActionPerformed(ActionEvent evt) throws IOException {
		jSetSettings.setVisible(false);
		jSetDB.setVisible(false);
		jSetDBText.setVisible(false);
		jSetHome.setVisible(false);
		jSetSetText.setVisible(false);
		volume = jSetDB.getValue();
		writeVolume();
		jStart.setVisible(true);
		jSettings.setVisible(true);
		jStats.setVisible(true);
		jQuit.setVisible(true);
		jSnoke.setVisible(true);
		jMenue.setVisible(true);
		jHome.setVisible(true);
	}
	
	public void jHome_ActionPerformed(ActionEvent evt) throws NumberFormatException, IOException {
		
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
		for(int iy = 0; iy < canvs.size(); iy++) {
			canvs.get(iy).setVisible(false);
		}
		canvs.removeAll(canvs);
		cords.removeAll(cords);
		jSnoke.setVisible(true);
		jHome.setVisible(true);
		jStart.setVisible(true);
		jStats.setVisible(true);
		jSettings.setVisible(true);
		jQuit.setVisible(true);
		jMenue.setVisible(true);
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
	
	public void settings() throws IOException {
		
		readVolume();
		jSetDB.setValue(volume);
		jSetSettings.setVisible(true);
		jSetDB.setVisible(true);
		jSetHome.setVisible(true);
		jSetSetText.setVisible(true);
		jSetDBText.setVisible(true);
		
	}
	
	public void clipVolume(int clipNr) {
		
		
		if(clipNr == 1) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			switch(volume) {
			case 0:
				gainControl.setValue(-80f);
				break;
			case 1:
				gainControl.setValue(-63f);
				break;
			case 2:
				gainControl.setValue(-56f);
				break;
			case 3:
				gainControl.setValue(-49f);
				break;
			case 4:
				gainControl.setValue(-42f);
				break;
			case 5:
				gainControl.setValue(-35f);
				break;
			case 6:
				gainControl.setValue(-28f);
				break;
			case 7:
				gainControl.setValue(-21f);
				break;
			case 8:
				gainControl.setValue(-14f);
				break;
			case 9:
				gainControl.setValue(-7f);
				break;
			case 10:
				gainControl.setValue(0);
				break;
			}
		} else {
			FloatControl gainControl2 = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
			switch(volume) {
			case 0:
				gainControl2.setValue(-80f);
				break;
			case 1:
				gainControl2.setValue(-63f);
				break;
			case 2:
				gainControl2.setValue(-56f);
				break;
			case 3:
				gainControl2.setValue(-49f);
				break;
			case 4:
				gainControl2.setValue(-42f);
				break;
			case 5:
				gainControl2.setValue(-35f);
				break;
			case 6:
				gainControl2.setValue(-28f);
				break;
			case 7:
				gainControl2.setValue(-21f);
				break;
			case 8:
				gainControl2.setValue(-14f);
				break;
			case 9:
				gainControl2.setValue(-7f);
				break;
			case 10:
				gainControl2.setValue(0);
				break;
			}
			
		}
		
	}
	
	public void writeVolume() throws IOException {
		File vol = new File("vol.txt");
		if(vol.createNewFile()) {
			Writer fwNew = new FileWriter(vol);
			BufferedWriter bwNew = new BufferedWriter(fwNew);
			bwNew.write("10");
			bwNew.flush();
			bwNew.close();
			fwNew.close();
		}
		Writer fw = new FileWriter(vol);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(String.valueOf(volume));
		bw.flush();
		bw.close();
		fw.close();
	}
	
	public void readVolume() throws IOException {
		File vol = new File("vol.txt");
		if(vol.createNewFile()) {
			System.out.println("yikes");
			Writer fwNew = new FileWriter(vol);
			BufferedWriter bwNew = new BufferedWriter(fwNew);
			bwNew.write("10");
			bwNew.flush();
			bwNew.close();
			fwNew.close();
		}
		Reader fr = new FileReader(vol);
		BufferedReader br = new BufferedReader(fr);
		
		String volStr = br.readLine();
		volume = Integer.parseInt(volStr);
	}
	
	public void addSkin() {
		ImageIcon playerSkin = new ImageIcon(Start.class.getResource("playerSkin.jpg"));
		System.out.println(playerSkin.getIconHeight());
		JLabel skinLabel = new JLabel();
		player.setBounds(xPlayer, yPlayer, playerHeight, playerWidth);
		
		System.out.println(skinLabel.getHeight());
		skinLabel.setIcon(playerSkin);
		player.add(skinLabel);
		skinLabel.setSize(50, 50);
		System.out.println(player.getHeight());
	}
	
	private class MoveAction extends AbstractAction{
		int oldDir;
		int newDir;
		
		MoveAction(int newDir, int oldDir) {
			this.newDir = newDir;
			this.oldDir = oldDir;
		}
		
		public void actionPerformed(ActionEvent e) {
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
		}
	}
}
