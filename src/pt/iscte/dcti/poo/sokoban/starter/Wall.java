package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.utils.Point2D;

public class Wall extends GameObject {
	
	public Wall(Point2D position) {
		super(position,"Parede",2,false,false);
	}
}