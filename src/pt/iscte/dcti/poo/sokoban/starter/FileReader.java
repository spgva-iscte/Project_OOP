package pt.iscte.dcti.poo.sokoban.starter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pt.iul.ista.poo.utils.Point2D;

public class FileReader {
	private static int WIDTH=10;

	public static List<GameObject> read(String fn) {
		List<GameObject> lista=new ArrayList<>();
		try {
			Scanner fs=new Scanner(new File(fn));
			int countY=0;
			while(fs.hasNextLine()) {
				String line=fs.nextLine();
				int countX=0;
				while(countX<WIDTH) {
					String[] s=line.split("");
					GameObject o=create(s[countX], new Point2D(countX,countY));
					lista.add(o);
					countX++;
				}
				countY++;
			}
			fs.close();
		}catch (FileNotFoundException e) {
			System.err.println("ficheiro nao encontrado");
		}
		return lista;
	}
	
	public static GameObject create(String s,Point2D position) {
		switch(s) {
		case "#":return new Wall(position); 
		case "C":return new Box(position);
		case "E":return new Player(position);
		case "X":return new Target(position);
		case "b":return new Battery(position);
		case "O":return new Hole(position);
		case "p":return new SmallStone(position);
		case "P":return new BigStone(position);
		case "g":return new Ice(position);
		case "%":return new BreakableWall(position);
		case "m":return new Hammer(position);
		case "t":return new Teleport(position);
		default: return new Chao(position);
		}
	}

}
