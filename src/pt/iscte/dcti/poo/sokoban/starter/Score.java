package pt.iscte.dcti.poo.sokoban.starter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Score {
	private static List<Score> highScore;
	private static int TOP=10;
	private int score;
	
	public Score(int score) {
		this.score=score;
	}
	
	public int getScore() {
		return score;
	}
	
	public List<Score> read(String fn){
		List<Score> lista=new ArrayList<>();
			try {
				Scanner fs = new Scanner(new File(fn));
				if(fs.hasNext())
					fs.nextLine();
				while(fs.hasNextLine()) {
					Scanner ls=new Scanner(fs.nextLine());
					ls.next();
					Score score=new Score(Integer.parseInt(ls.next()));
					lista.add(score);
					ls.close();
				}
				fs.close();
			} catch (FileNotFoundException e) {
				System.err.println("ficheiro nao encontrado");
			}
		return lista;
	}
	
	
	
	@Override
	public String toString() {
		return  " "+this.score;
	}

	public void write(int level) {
		highScore=read("HighScore"+level+".txt");
		int position=1;
		if(highScore.size()>=TOP) {
			if(SokobanGame.getInstance().getScore().getScore()>highScore.get(TOP-1).getScore()) {
				highScore.remove(highScore.get(TOP-1));
				highScore.add(SokobanGame.getInstance().getScore());
			}
		}else
			highScore.add(SokobanGame.getInstance().getScore());
		highScore.sort((Score s1,Score s2)->s2.getScore()-s1.getScore());
		File f=new File("HighScore"+level+".txt");
		try {
			PrintWriter pw=new PrintWriter(f);
			pw.println(TOP+" Pontuacoes Mais Altas do nivel "+level);
			for(Score i:highScore) {
				pw.println(position+": "+i.toString());
				position++;
			}
			pw.close();
		}catch(FileNotFoundException e) {
			System.err.println("Erro na abertura do ficheiro para escrita");
		}
	}

}
