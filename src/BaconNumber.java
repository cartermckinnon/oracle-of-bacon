import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;


public class BaconNumber {

	public static void main(String[] args) {
		
		Graph aGraph = new Graph( "data" );
		aGraph.baconNumbers();
		
		Graph someGraph = new Graph( "data" );
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( new FileOutputStream( "Pairs.txt" ));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		someGraph = new Graph("data");
		writer.println( "1:  " + someGraph.breadthFirstSearch( "Hathaway, Anne (I)", "Bacon, Kevin") +"\n" );
		someGraph = new Graph("data");
		writer.println( "2:  " + someGraph.breadthFirstSearch( "Swinton, Tilda", "Lin, Feng Sheng" ) +"\n");
		someGraph = new Graph("data");
		writer.println("3:  " +  someGraph.breadthFirstSearch( "Brosnan, Pierce", "Connery, Sean") +"\n");
		someGraph = new Graph("data");
		writer.println("4:  " +  someGraph.breadthFirstSearch( "Aniston, Jennifer", "Pitt, Brad") +"\n");
		someGraph = new Graph("data");
		writer.println( "5:  " + someGraph.breadthFirstSearch( "DeGeneres, Ellen", "Curry, Tim (I)") +"\n");
		someGraph = new Graph("data");
		writer.println( "6:  " + someGraph.breadthFirstSearch( "Dench, Judi", "Rivera, Danny (III)") +"\n");
		someGraph = new Graph("data");
		writer.println( "7:  " + someGraph.breadthFirstSearch( "Rooney, Mickey (I)", "Allen, Woody") +"\n");
		someGraph = new Graph("data");
		writer.println( "8:  " + someGraph.breadthFirstSearch( "Stewart, Jon", "Reagan, Ronald (I)") +"\n");
		someGraph = new Graph("data");
		writer.println( "9:  " + someGraph.breadthFirstSearch( "Sandler, Adam (I)", "Blanchett, Cate") +"\n");
		someGraph = new Graph("data");
		writer.println( "10:  " + someGraph.breadthFirstSearch( "Jolie, Angelina", "Pitt, Brad") +"\n");
		System.out.println("Pairs.txt was generated");

		
		writer.flush();
		writer.close();

	}

}
