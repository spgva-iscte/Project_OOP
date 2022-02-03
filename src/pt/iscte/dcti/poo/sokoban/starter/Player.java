package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Player extends GameObject {
	private int moves;
	private int energy;
	private boolean hammer;
	
	public Player(Point2D position){
		super(position,"Empilhadora_U",2,false,true);
		this.moves=0;
		this.energy=100;
		this.hammer=false;
	}

	public void setName(Direction d) {
		setName("Empilhadora_"+d.name().charAt(0));
	}

	public int getEnergy() {
		return energy;
	}
	
	public void setEnergy(int n) {
		this.energy+=n;
	}
	
	public int getMoves() {
		return moves;
	}
	
	public boolean getHammer() {
		return hammer;
	}
	
	public void setHammer(boolean b) {
		this.hammer=b;
	}

	@Override
	public void move(Direction d) {
		if(energy!=0) {
			this.setName(d);
			for(GameObject o:SokobanGame.getInstance().getObject(getNewPosition(d))) 
				if(o.getMovable()) 
					o.move(d);
			for(GameObject o:SokobanGame.getInstance().getObject(getNewPosition(d))) {
				if(o instanceof ActiveObject)
					((ActiveObject)o).interact(this, d);
			}
			if(SokobanGame.getInstance().validatePosition(getNewPosition(d))) {
				setPosition(getNewPosition(d));
				moves++;
				energy--;
			}
			ImageMatrixGUI.getInstance().update();
		}else
			SokobanGame.getInstance().gameOver();
	}

}
