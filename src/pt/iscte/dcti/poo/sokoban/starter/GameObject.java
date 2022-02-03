package pt.iscte.dcti.poo.sokoban.starter;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class GameObject implements ImageTile  {
	private Point2D position;
	private String name;
	private int layer;
	private boolean transposable;
	private boolean movable;
	
	public GameObject(Point2D position,String name,int layer,boolean transposable,boolean movable) {
		this.position=position;
		this.name=name;
		this.layer=layer;
		this.transposable=transposable;
		this.movable=movable;
	}
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D newPosition) {
		this.position=newPosition;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public int getLayer() {
		return layer;
	}
	
	public boolean getTransposable() {
		return transposable;
	}
	
	public void setTransposable(boolean b) {
		this.transposable=b;
	}
	
	public boolean getMovable() {
		return movable;
	}
	
	public void setMovable(boolean b) {
		this.movable=b;
	}

	public void move(Direction d) {
		for(GameObject o:SokobanGame.getInstance().getObject(getNewPosition(d))) 
			if(o instanceof ActiveObject)
				((ActiveObject)o).interact(this, d);
		if(SokobanGame.getInstance().validatePosition(getNewPosition(d)))
				setPosition(getNewPosition(d));
	}
	
	public Point2D getNewPosition(Direction d) {
		Point2D newPosition=getPosition().plus(d.asVector());
		return newPosition;
	}

}
