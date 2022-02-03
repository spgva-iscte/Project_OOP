package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Hole extends GameObject implements ActiveObject{

	public Hole(Point2D position) {
		super(position,"Buraco",1,true,false);
	}

	@Override
	public void interact(GameObject o,Direction d) {
		if(o instanceof BigStone) {
			ImageMatrixGUI.getInstance().removeImage(this);
			SokobanGame.getInstance().removeObject(this);;
			 o.setMovable(false);
		}else {
			if(o instanceof Player) {
				SokobanGame.getInstance().gameOver();
			}	
			ImageMatrixGUI.getInstance().removeImage(o);
			SokobanGame.getInstance().removeObject(o);
		}		
	}

}
