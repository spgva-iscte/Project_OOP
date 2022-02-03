package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class BreakableWall extends GameObject implements ActiveObject{

	public BreakableWall(Point2D position) {
		super(position, "Parede_Partida", 2, false,false);
	}

	@Override
	public void interact(GameObject o,Direction d) {
		if(o instanceof Player)
			if(SokobanGame.getInstance().getPlayer().getHammer()) {
				setTransposable(true);
				ImageMatrixGUI.getInstance().removeImage(this);
				SokobanGame.getInstance().removeObject(this);
			}
	}

}
