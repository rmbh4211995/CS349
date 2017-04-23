import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	ObjectHandler handler;
	private static float p1Health = 100;
	private static float p2Health = 100;
	
	private float grnValBar1 = 255;
	private float grnValBar2 = 255;
	
	private static int p1Score = 0;
	private static int p2Score = 0;
	private int level = 1;
	
	private Color jmuGold = new Color(194, 161, 77);
	
	
	public HUD(ObjectHandler handler){
		this.handler = handler;
	}
	
	
	public void tick(){
		p1Health = Game.bound((int)p1Health, 0, 100);
		p2Health = Game.bound((int)p2Health, 0, 100);
		
		grnValBar1 = Game.bound((int)grnValBar1, 0, 255);
		grnValBar2 = Game.bound((int)grnValBar2, 0, 255);
		
		grnValBar1 = p1Health * 2;
		grnValBar2 = p2Health * 2;
	}
	
	public void render(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(10, 15, 150, 20);
		g.setColor(new Color(75, (int)grnValBar1, 0));
		g.fillRect(10, 15, (int)(p1Health * 1.5), 20);
		g.setColor(Color.white);
		g.drawRect(10, 15, 150, 20);
		
		if(Game.twoPlayerMode){
			g.setColor(Color.DARK_GRAY);
			g.fillRect(335, 15, 150, 20);
			g.setColor(new Color(75, (int)grnValBar2, 0));
			g.fillRect(335, 15, (int)(p2Health * 1.5), 20);
			g.setColor(Color.white);
			g.drawRect(335, 15, 150, 20);
		}
		
		g.setColor(jmuGold);
		g.setFont(new Font("Fipps", Font.PLAIN, 24));
		g.drawString("" + p1Score, 190, 40);
		g.drawString("" + p2Score, 280, 40);
		
		g.setFont(new Font("Fipps", Font.PLAIN, 16));
		g.drawString("vs", 230, 35);
	}
	
	public static void updateHealth(ID id, int damage, ObjectHandler h){
		ObjectHandler tempHandle = h;
		MusicPlayer player = new MusicPlayer();
	  if(id == ID.Player){
			p1Health -= damage;
			//System.out.println("Player1 took damage.");
			if(p1Health == 0){
				//System.out.println("Player 1 has died.");
			  player.playWin();
				p2Score += 1;
				h.remAll();
				h.addObject(new Player(Game.width / 6, Game.height / 6 + 130, ID.Player, h));
        h.addObject(new Player(Game.width / 2, Game.height / 6 + 130, ID.Player2, h));
				p1Health = 100;
				p2Health = 100;
			}
		}
		else if(id == ID.Player2){
			p2Health -= damage;
			//System.out.println("Player2 took damage.");
			if(p2Health == 0){
				//System.out.println("Player 2 has died.");
			  player.playWin();
				p1Score += 1;
				h.remAll();
        h.addObject(new Player(Game.width / 6, Game.height / 6 + 130, ID.Player, h));
        h.addObject(new Player(Game.width / 2, Game.height / 6 + 130, ID.Player2, h));
				p1Health = 100;
				p2Health = 100;
			}
		}
	}

}
