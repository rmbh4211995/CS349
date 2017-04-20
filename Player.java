import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Player extends GameObject{
	
	ObjectHandler handler;
	protected static boolean player1Attacking = false;
	protected static boolean player2Attacking = false;
	private int basicDamage = 5;
	
	public Player(int x, int y, ID id, ObjectHandler handler) {
		super(x, y, id);
		this.handler = handler;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		
		if(id == ID.Player && Game.twoPlayerMode){
			x = Game.bound((int)x, 30, Game.width - getPlayerImg().getWidth(null) * 2 - 15);
		}
		else if(id == ID.Player2 && Game.twoPlayerMode){
			x = Game.bound((int)x, getPlayerImg().getWidth(null) + 15, Game.width - getPlayerImg().getWidth(null) - 30);
		}
		
		attackCollision();
		movementCollision();
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		
		g.drawImage(getPlayerImg(), (int)x, (int)y, null);
		g.setColor(Color.GREEN);
		g2.draw(getAttackBounds());
		g.setColor(Color.RED);
		g2.draw(getMovementBounds());
			
		if (Game.twoPlayerMode){
			g.drawImage(getPlayerImg(), (int)x, (int)y, null);
			g.setColor(Color.GREEN);
			g2.draw(getAttackBounds());
			g.setColor(Color.RED);
			g2.draw(getMovementBounds());
		}
		
		
		
	}

	public Image getPlayerImg(){
		ImageIcon player1 = new ImageIcon("C:/Users/Jochs01/workspace/BoxingGame/src/idle animation 1.png");
		ImageIcon player2 = new ImageIcon("C:/Users/Jochs01/workspace/BoxingGame/src/idle_animation_1_gold.png");
		try{
			if(id == ID.Player){
				return player1.getImage();
			}
			else{
				return player2.getImage();
			}
		}catch (Exception e){
			System.out.println("Player image not found.");
		}
		return null;
	}

	public void attackCollision(){
		for(int i = 0; i < handler.objList.size(); i++){
			GameObject temp = handler.objList.get(i);
			
			if(temp.getID() == ID.AI){
				if(getAttackBounds().intersects(temp.getAttackBounds())){
					//Unimplemented
				}
			}
			else{
				if(getAttackBounds().intersects(temp.getAttackBounds()) && id != temp.getID()){
					if(!player1Attacking && player2Attacking){
						HUD.updateHealth(ID.Player, basicDamage);
						Player.player2Attacking = false;
						//System.out.println("Player 2 stopped attacking.");
					}
					else if(player1Attacking && !player2Attacking){
						HUD.updateHealth(ID.Player2, basicDamage);
						Player.player1Attacking = false;
						//System.out.println("Player 1 stopped attacking.");
					}
					else if(player1Attacking && player2Attacking){
						HUD.updateHealth(ID.Player, basicDamage);
						HUD.updateHealth(ID.Player2, basicDamage);
						Player.player1Attacking = false;
						Player.player2Attacking = false;
					}
				}
			}
		}
	}
	
	public void movementCollision(){
		for(int i = 0; i < handler.objList.size(); i++){
			GameObject temp = handler.objList.get(i);
			
			if(temp.getID() == ID.AI){
				if(getMovementBounds().intersects(temp.getMovementBounds())){
					//Unimplemented
				}
			}
			else{
				if(getMovementBounds().intersects(temp.getMovementBounds())){
					if(getVelX() >= 0 && temp.getVelX() <= 0){
						x -= velX;
						temp.x -= temp.velX;
					}

				}
			}
		}
	}

	//Set up Collision box for player
	public Rectangle getAttackBounds() {
		// TODO Auto-generated method stub
		if(id == ID.Player){
			return new Rectangle((int)x + 20, (int)y, 38, 60);
		}
		else{
			return new Rectangle((int)x + 5, (int)y, 38, 60);
		}
	}
	
	public Rectangle getMovementBounds(){
		
		if(id == ID.Player){
			return new Rectangle((int)x + 25, (int)y, 30, 60);
		}
		else{
			return new Rectangle((int)x + 10, (int)y, 30, 60);
		}
	}

	

}
