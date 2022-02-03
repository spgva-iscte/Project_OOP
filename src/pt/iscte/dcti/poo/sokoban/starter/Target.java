package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Target extends GameObject implements ActiveObject {
	private boolean active;
	
	public Target(Point2D position) {
		super(position,"Alvo",1,true,false);
		this.active=false;
	}

	public boolean isActive() {
		return active;
	}
	
	@Override
	public void interact(GameObject o,Direction d) {
		if(o instanceof Box) 
			this.active=true;
		else
			this.active=false;
	}
}