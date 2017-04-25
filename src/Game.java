import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;

import javax.swing.ImageIcon;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = -1442798787354930462L;
	
	protected static final int width = 500, height = 300;
	
	protected static boolean twoPlayerMode = false;
	protected static boolean gameBackgroundRendered = false;
	
	private Thread thread;
	private boolean isRunning = false;
	
	private ObjectHandler handler;
	private HUD hud;
	private Menu menu;
	private PauseMenu pauseMenu;
	private MusicPlayer music;
	
	//private ImageIcon backGD = new ImageIcon("res/JMvUBackground.png");
	private ImageIcon backGD = new ImageIcon(this.getClass().getResource("JMvUBackground.png"));
	private ImageIcon ringBack = new ImageIcon(this.getClass().getResource("ring.png"));
	private ImageIcon ringFront = new ImageIcon(this.getClass().getResource("ring_front.png"));
	
	public static State state = State.Menu;
	
	public Game(){
		handler = new ObjectHandler();
		menu = new Menu(handler);
		pauseMenu = new PauseMenu(handler);
		music = new MusicPlayer();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		this.addMouseMotionListener(menu);
		this.addMouseListener(pauseMenu);
		this.addMouseMotionListener(pauseMenu);
		
		new Display(width, height, "JMvU", this);	
		
		hud = new HUD(handler);
		music.play();
	}

	public static int bound(int var, int min, int max){
		if(var >= max){
			return var = max;
		}
		else if(var <= min){
			return var = min;
		}
		else{
			return var;
		}
	}

	@Override
	public void run() {
		long previous = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning){
			long current = System.nanoTime();
			delta += (current - previous) / ns;
			previous = current;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(isRunning){
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	
	private void tick(){
		handler.tick();
		if(state == State.Game){
			hud.tick();
		}
		else if(state == State.Menu){
			menu.tick();
		}

	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(backGD.getImage(), 0, 0, null);
		
		if(state == State.Game){
			hud.render(g);
			g.drawImage(ringBack.getImage(), Game.width / 2 - ringBack.getIconWidth() / 2, Game.height / 2 - 75, null);
			handler.render(g);
			g.drawImage(ringFront.getImage(), Game.width / 2 - ringFront.getIconWidth() / 2 - 10, Game.height / 2 - 87, null);

		}
		else if(state == State.Menu || state == State.Credits || state == State.Help){		
			menu.render(g);
		}
		else if(state == State.Pause){
			hud.render(g);
			
			handler.render(g);
			pauseMenu.render(g);
		}

		g.dispose();
		bs.show();
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			isRunning = false;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		new Game();
	}
}
