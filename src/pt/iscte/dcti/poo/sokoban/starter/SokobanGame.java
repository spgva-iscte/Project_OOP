package pt.iscte.dcti.poo.sokoban.starter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class SokobanGame implements Observer {	
	private Player player; 
	private List<GameObject> board;
	private int level;
	private static SokobanGame instance;
	private static int WIDTH=10;
	private static int HEIGHT=10;
	private Score score;

	private SokobanGame(){
		this.level=0;
		this.board=FileReader.read("levels//level" +level+ ".txt");
		this.score=new Score(0);
		createLevel();
		ImageMatrixGUI.getInstance().setName("SokobanGame");
	}
	
	public static SokobanGame getInstance() {
		 if(instance == null) 
			 instance =new SokobanGame();
		 return instance;
		 }
	
	public Player getPlayer() {
		return player;
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getHeight() {
		return HEIGHT;
	}
	
	public void removeObject(GameObject o) {
		board.remove(o);
	}

	public Score getScore() {
		return score;
	}
	
	public void setScore() {
		this.score=new Score(this.player.getEnergy()*10-this.player.getMoves());
	}
	
	public boolean validatePosition(Point2D position) {
		boolean b=true;
		for(GameObject o:SokobanGame.getInstance().getObject(position))
			if(o.getTransposable()==false)
				b=false;
		if(position.getX()<0 || position.getX()>=WIDTH || position.getY()<0 
				|| position.getY()>=HEIGHT)
			b=false;
		return b;
			
	}
	
	public void createLevel() {
		for(int x=0;x<WIDTH;x++)
			for(int y=0;y<HEIGHT;y++)
				ImageMatrixGUI.getInstance().addImage(new Chao(new Point2D(x,y)));
		int n=0;
		for(int i=0;i<board.size();i++) {
			ImageMatrixGUI.getInstance().addImage(board.get(i));
			if(board.get(i) instanceof Player)
				this.player=(Player)board.get(i);
			if(board.get(i) instanceof Teleport)
				n++;
		}
		if(n%2!=0)
			throw new IllegalArgumentException();
		if(this.player==null)
			throw new IllegalArgumentException();
	}
	
	public void restart() {
		for(int i=0;i<board.size();i++) 
			ImageMatrixGUI.getInstance().removeImage(board.get(i));
		this.board=FileReader.read("levels//level"+level+".txt");
		createLevel();
		ImageMatrixGUI.getInstance().update();
	}
	
	public void changeLevel() {
		score.write(level);
		score=new Score(0);
		level++;
		for(int i=0;i<board.size();i++) 
			ImageMatrixGUI.getInstance().removeImage(board.get(i));
		this.board=FileReader.read("levels//level"+level+".txt");
		createLevel();
			ImageMatrixGUI.getInstance().update();
	}
	
	public void gameOver() {
		ImageMatrixGUI.getInstance().setStatusMessage("GAME OVER");
		ImageMatrixGUI.getInstance().dispose();
	}

	public boolean levelComplete() {
		List<GameObject> lista=new ArrayList<>();
		List<GameObject> lista1=new ArrayList<>();	
		for(GameObject o:board)
			if(o instanceof Target)
				lista.add(o);
		for(GameObject obj:lista)
			if(((Target)obj).isActive())
				lista1.add(obj);
		return lista.size()==lista1.size();
	}
	
	public List<GameObject> getObject(Point2D position){
		List<GameObject> lista=new ArrayList<>();
		for(int i=0;i<board.size();i++)
			if(board.get(i).getPosition().equals(position))
				lista.add(board.get(i));
		if(lista.isEmpty())
			lista.add(new Chao(position));
		return lista;
	}

	@Override
	public void update(Observed arg0) {
		int lastKeyPressed = ((ImageMatrixGUI)arg0).keyPressed();
		ImageMatrixGUI.getInstance().setStatusMessage("Level:"+String.valueOf(level)+" Moves:"+
		String.valueOf(player.getMoves())+" Energy:"+String.valueOf(player.getEnergy()));
		if (lastKeyPressed==KeyEvent.VK_UP || lastKeyPressed==KeyEvent.VK_DOWN || 
			lastKeyPressed==KeyEvent.VK_LEFT || lastKeyPressed==KeyEvent.VK_RIGHT) {
			Direction d=Direction.directionFor(lastKeyPressed);
			player.move(d);
		}
		if(lastKeyPressed==KeyEvent.VK_R)
			restart();
		if(levelComplete()) {
			setScore();
			changeLevel();
			ImageMatrixGUI.getInstance().setStatusMessage("Level Completed");  
		}
	}
	
}