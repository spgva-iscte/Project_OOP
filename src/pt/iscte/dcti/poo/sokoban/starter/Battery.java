package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Battery extends GameObject implements ActiveObject{
	private static int POWER=15;
	
	public Battery(Point2D position) {
		super(position,"Bateria",1,true,false);
	}

	@Override
	public void interact(GameObject o,Direction d) {
		if(o instanceof Player) {
			SokobanGame.getInstance().getPlayer().setEnergy(POWER);
			ImageMatrixGUI.getInstance().removeImage(this);
			SokobanGame.getInstance().removeObject(this);
		}
	}

}
