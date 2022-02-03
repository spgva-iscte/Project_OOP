package pt.iscte.dcti.poo.sokoban.starter;
import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.utils.Direction;
//import java.util.List;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Teleport extends GameObject implements ActiveObject {
	private Teleport exit;
	
	public Teleport(Point2D position) {
		super(position, "Portal_Azul", 1, true,false);
		exit=null;
	}
	
	public void setExit(Teleport t) {
		this.exit=t;
	}
	
	public void pair() {
		List<GameObject> lista=new ArrayList<>();
		for(int x=0;x<SokobanGame.getInstance().getWidth();x++)
			for(int y=0;y<SokobanGame.getInstance().getHeight();y++)
				for(GameObject o:SokobanGame.getInstance().getObject(new Point2D(x,y)))
					if(o instanceof Teleport)
						lista.add(o);
		for(GameObject o:lista)
			if(o.getPosition()!=this.getPosition())
				setExit((Teleport)o);
	}

	@Override
	public void interact(GameObject o,Direction d) {
		this.pair();
		if(SokobanGame.getInstance().validatePosition(exit.getPosition()))
			o.setPosition(this.exit.getPosition().plus(new Vector2D(-d.asVector().getX(),-d.asVector().getY())));
	}

}
