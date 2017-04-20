import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private ObjectHandler handler;
	protected static boolean p1CanAttack = true;
	protected static boolean p2CanAttack = true;
	
	private boolean[] keysPressed = new boolean[4];
	
	public KeyInput(ObjectHandler handler){
		this.handler = handler;
		
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(Game.state == State.Game){
			for(int i = 0; i < handler.objList.size(); i++){
				GameObject temp = handler.objList.get(i);
				
				if(temp.getID() == ID.Player){
					if(key == KeyEvent.VK_D){
						temp.setVelX(2);
						keysPressed[0] = true;
					}
					else if(key == KeyEvent.VK_A){
						temp.setVelX(-2);
						keysPressed[1] = true;
					}
					else if(key == KeyEvent.VK_E && p1CanAttack){
						Player.player1Attacking = true;
						//System.out.println("Player1 attacking.");
						p1CanAttack = false;
					}
				}
				else if(temp.getID() == ID.Player2){
					if(key == KeyEvent.VK_RIGHT){
						temp.setVelX(2);
						keysPressed[2] = true;
					}
					else if(key == KeyEvent.VK_LEFT){
						temp.setVelX(-2);
						keysPressed[3] = true;
					}
					else if(key == KeyEvent.VK_NUMPAD1 && p2CanAttack){
						Player.player2Attacking = true;
						//System.out.println("Player2 attacking.");
						p2CanAttack = false;
					}
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			if(Game.state == State.Game){
				Game.state = State.Pause;
			}
			else if(Game.state == State.Pause){
				Game.state = State.Game;
			}
		}
		
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.objList.size(); i++){
			GameObject temp = handler.objList.get(i);
			
			if(temp.getID() == ID.Player){
				if(key == KeyEvent.VK_D){
					keysPressed[0] = false;
				}
				else if(key == KeyEvent.VK_A){
					keysPressed[1] = false;
				}
				else if(key == KeyEvent.VK_E){
					p1CanAttack = true;
					Player.player1Attacking = false;
					//System.out.println("Player 1 stopped attacking.");
				}
			}
			else if(temp.getID() == ID.Player2){
				if(key == KeyEvent.VK_RIGHT){
					keysPressed[2] = true;
				}
				else if(key == KeyEvent.VK_LEFT){
					keysPressed[3] = true;
				}
				else if(key == KeyEvent.VK_NUMPAD1){
					p2CanAttack = true;
					Player.player2Attacking = false;
					//System.out.println("Player 1 stopped attacking.");
				}
			}
			
			if(!keysPressed[0] && !keysPressed[1]){
				temp.setVelX(0);
			}
			else if(!keysPressed[2] && !keysPressed[3]){
				temp.setVelX(0);
			}
		}
	}
}
