package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Ice extends GameObject implements ActiveObject{

	public Ice(Point2D position) {
		super(position, "Gelo", 1, true,false);
	}

	@Override
	public void interact(GameObject o,Direction d) {
		for(GameObject i:SokobanGame.getInstance().getObject(getNewPosition(d))) 
			if(i instanceof ActiveObject)
				((ActiveObject)i).interact(o, d);
		if(SokobanGame.getInstance().validatePosition(o.getNewPosition(d)))
			o.setPosition(o.getNewPosition(d));
	}

}
