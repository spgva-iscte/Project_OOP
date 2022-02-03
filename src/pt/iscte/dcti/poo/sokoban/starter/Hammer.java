package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Hammer extends GameObject implements ActiveObject {

	public Hammer(Point2D position) {
		super(position, "Martelo", 1, true,false);
	}

	@Override
	public void interact(GameObject o,Direction d) {
		if(o instanceof Player) {
			SokobanGame.getInstance().getPlayer().setHammer(true);
			ImageMatrixGUI.getInstance().removeImage(this);
			SokobanGame.getInstance().removeObject(this);
		}
	}

}
