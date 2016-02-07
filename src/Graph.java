import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/** a CLASS defining a Graph of information that can provide
 * Breadth First Search (BFS) when loading from a file formatted
 * such as "cast-mpaa.txt". The number of edges in the Graph can
 * be retrieved with edges(). 
 * 
 * @author Carter McKinnon
 * created 04.28.2015
 */

public class Graph {

	private HashMap< String, HashSet<GraphNode>> theMap;
	private Scanner theScanner = null;
	private double edges;
	
	public Graph( String aFileName ){
		theMap = new HashMap< String, HashSet<GraphNode>>();
		edges = 0;
		loadDataFromFile( aFileName );
	}
	
	public void loadDataFromFile( String aFileName ){
		
		/** loads data to the graph from the specified file.
		 * ( file must be formatted like cast-mpaa.txt in order
		 * for Breadth First Search to function. )
		 * @param aFileName
		 */
		
		ArrayList<String> allLines = new ArrayList<String>();
		try {
			theScanner = new Scanner( new FileInputStream( aFileName ));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while( theScanner.hasNextLine() ){
			allLines.add( theScanner.nextLine() );	
		}
	
		for( String aString : allLines ){
			String[] data = aString.split("/");
			GraphNode theMovie = new GraphNode( data[0] );
			for( int i = 1; i < data.length; i++ ){
				HashSet<GraphNode> actorValue = new HashSet<GraphNode>();
				actorValue.add( theMovie );
				if( ! theMap.containsKey( data[i] )){
					theMap.put( data[i], actorValue );
					edges += ( data.length - 1 );
				}
				else{
					HashSet<GraphNode> orig = theMap.get( data[i]);
					theMap.remove( data[i] );
					orig.add( theMovie );
					theMap.put( data[i], orig );
				}
				edges += (( data.length * ( data.length - 1 ) ));
			}
			HashSet<GraphNode> movieValue = new HashSet<GraphNode>();
			for( int i = 1; i < data.length; i++ ){
				GraphNode anActor = new GraphNode( data[i] );
				movieValue.add( anActor );
			}
			theMap.put( data[0], movieValue );
		}
	}
	
	public double edges(){

		/** returns the number of edges in the graph.
		 * @return double
		 */
		
		return edges;
	}
	
	public boolean exists(String v){
		
		/** returns true is v is a vertex in the Graph
		 * @return boolean
		 */
		
		return theMap.containsKey( v );
	}
	
	public boolean exists(String v, String w){
		
		/** returns true if an edge exists between v and w
		 * @param v, w
		 * @return boolean
		 */
		
		for( GraphNode someNode : theMap.get( v ) ){
			for( GraphNode aNode : theMap.get( someNode.getValue() ) ){
				if( aNode.getValue().equalsIgnoreCase( w ) ){
					return true;
				}
			}
		}
		return false;
		
	}
	
	public Iterable<String> breadthFirstSearch(String source, String dest){
		
		/** returns the path (if one exists) between source and dest
		 * @param source, dest
		 * @return Iterable<String>
		 */
	
		ArrayList<String> result = new ArrayList<String>();
		Queue<GraphNode> toDo = new LinkedList<GraphNode>();
		if( ! exists( source ) ){
			ArrayList<String> nullResult = new ArrayList<String>();
			nullResult.add( "That source does not exist." );
			return nullResult;
		}
		else if( ! exists( dest ) ){
			ArrayList<String> nullResult = new ArrayList<String>();
			nullResult.add( "That destination doesn't exist." );
			return nullResult;
		}
		
		Queue<GraphNode> a = new LinkedList<GraphNode>();
		GraphNode sourcePreNode = new GraphNode( source );
		for( GraphNode aNode : theMap.get( source )){
			aNode.setPre( sourcePreNode );
			for( GraphNode someNode : theMap.get( aNode.getValue() ) ){
				if( !someNode.getValue().equals( source )){
					someNode.setPre( aNode );
					a.add( someNode );
				}
			}
			aNode.setVisited( true );
			toDo.addAll( a );
			a.clear();
		}
		
		while( ! toDo.isEmpty() ){
			GraphNode currentActor = toDo.remove();
			if( currentActor.getValue().equalsIgnoreCase( dest ) ){
				GraphNode pre = currentActor.getPre();
				result.add( currentActor.getValue() );
				while( !pre.getValue().equals( source )){
					result.add( pre.getValue() );
					pre = pre.getPre();
				}
				result.add( source );
				ArrayList<String> finalresult = new ArrayList<String>();
				for( int i = 0; i < result.size(); i++ ){
					if( !(( i%2 ) == 0) ){
						finalresult.add( "<-----[" + result.get(i) + "]-----" );
					}
					else{
						finalresult.add( result.get(i) );
					}
				}
				return finalresult;
			}
			if( !currentActor.visited() ){
				ArrayList<GraphNode> b = new ArrayList<GraphNode>();
				for( GraphNode aNode : theMap.get( currentActor.getValue() ) ){
					if( aNode.visited() == false ){
						b.clear();
						aNode.setPre( currentActor );
						for( GraphNode bNode : theMap.get( aNode.getValue() ) ){
							if( ( bNode.visited() == false )){
								bNode.setPre( aNode );
								b.add( bNode );
							}
						}
						aNode.setVisited( true );
						toDo.addAll( b );
					}
				}
				currentActor.setVisited( true );
			}
		}
		ArrayList<String> nullResult = new ArrayList<String>();
		nullResult.add( "There was no connection found." );
		return nullResult;
	}
	
	public void baconNumbers(){
		
		/** creates a file, BaconNumbers.txt, that lists the "bacon numbers", or number of edges,
		 * between "Bacon, Kevin" and all other actor names in the Graph. 
		 * Then prints the average number of edges. 
		 */
		
		Queue<ArrayList<String>> baconNumbers = new LinkedList<ArrayList<String>>();
		Queue<GraphNode> toDo = new LinkedList<GraphNode>();
		String source = "Bacon, Kevin";
		Queue<GraphNode> a = new LinkedList<GraphNode>();
		GraphNode sourcePreNode = new GraphNode( source );
		ArrayList<String> numberX = new ArrayList<String>();
		HashMap<String, Boolean> visitations = new HashMap<String, Boolean>();

		for( GraphNode aNode : theMap.get( source )){
			aNode.setPre( sourcePreNode );
			for( GraphNode someNode : theMap.get( aNode.getValue() ) ){
				numberX.add( someNode.getValue() );
				if( !someNode.getValue().equals( source )){
					a.add( someNode );
				}
			}
			aNode.setVisited( true );
			visitations.put( aNode.getValue(), true);
			toDo.addAll( a );
			a.clear();
		}
		ArrayList<String> numberOne = new ArrayList<String>();
		numberOne.addAll( numberX );
		baconNumbers.add( numberOne );
		numberX.clear();
		GraphNode bReak = new GraphNode( "BREAK" );
		toDo.add( bReak );
		while( ! toDo.isEmpty() ){
			// System.out.println( toDo );
			GraphNode currentActor = toDo.remove();
			if( currentActor.getValue().equals( "BREAK" ) ){
				if(! toDo.isEmpty() ){
					ArrayList<String> copy = new ArrayList<String>();
					copy.addAll( numberX );
					baconNumbers.add( copy );
					numberX.clear();
					toDo.add( bReak );
					
				}
				if( toDo.isEmpty() ){
					ArrayList<String> copy = new ArrayList<String>();
					copy.addAll( numberX );
					baconNumbers.add( copy );
					break;
				}
			}
			else if(( ! currentActor.visited() ) && (! currentActor.getValue().equals("Bacon, Kevin"))){
				ArrayList<GraphNode> b = new ArrayList<GraphNode>();
				for( GraphNode aNode : theMap.get( currentActor.getValue() ) ){
					if( aNode.visited() == false ){
						b.clear();
						aNode.setPre( currentActor );
						for( GraphNode bNode : theMap.get( aNode.getValue() ) ){
							if( ( bNode.visited() == false )){
								b.add( bNode );
								numberX.add( bNode.getValue() );
							}
						}
						aNode.setVisited( true );
						toDo.addAll( b );
					}
				}
				currentActor.setVisited( true );
			}
		}
		ArrayList<String> noConnections = new ArrayList<String>();
		for( HashSet<GraphNode> aSet : theMap.values() ){
			for( GraphNode aNode : aSet ){
				if( ( aNode.visited() == false ) && ( ! listContains( aNode.getValue(), baconNumbers ) && ( ! aNode.getValue().matches(".*[0-9].*") )) ){
					noConnections.add( aNode.getValue() );
				}
			}
		}
		baconNumbers.add( noConnections );	
		
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( new FileOutputStream( "BaconNumbers.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int limit = baconNumbers.size();
		ArrayList<String> current = baconNumbers.remove();
		Queue<ArrayList<String>> finalNumbers = new LinkedList<ArrayList<String>>();
		ArrayList<String> kevinBacon = new ArrayList<String>();
		kevinBacon.add( "Bacon, Kevin");
		finalNumbers.add( kevinBacon );
		finalNumbers.add( current );
		for( int i = 2; i < limit; i++ ){
			ArrayList<String> next = baconNumbers.remove();
			Set<String> hs = new HashSet<String>();
			hs.addAll( current );
			ArrayList<String> done = new ArrayList<String>();
			done.addAll( hs );
			next.removeAll( done );
			current = next;
			
			finalNumbers.add( done );
			
		}
		
		int count = 0;
		int sum = 0;
		int totalNum = 0;
		for( ArrayList<String> anotherList : finalNumbers ){
			if( anotherList.size() > 0 ){
				writer.println( "||||||| Bacon Number :: " + count + " |||||||" );
				Collections.sort( anotherList );
				sum += ( anotherList.size() * count );
				totalNum += anotherList.size();
				for( String theString : anotherList ){
					writer.println( theString );
				}
				count++;
				writer.println("\n");
			}
		}
		writer.println( "||||||| Actors with no connections to Kevin Bacon |||||||" );
		Collections.sort( noConnections );
		for( String aString : noConnections ){
			writer.println( aString );
		}
		
		
		System.out.println("BaconNumbers.txt  was generated");
		
		totalNum += noConnections.size();
		int average = ( sum / totalNum );
		
		writer.println( "\n||||||||||\nThe average Bacon Number is " + average + "\n||||||||||" );
		writer.flush();
		writer.close();
		
	}
	
	public boolean listContains( String aString, Queue<ArrayList<String>> aList ){
		
		/** returns true is the specified list contains the specified String.
		 * @param aString, aList
		 * @return boolean
		 */
		
		for( ArrayList<String> someList : aList ){
			if( someList.contains( aString )){
				return true;
			}
		}
		return false;
	}
	
	public HashSet<GraphNode> get( String aString ){
		
		/** returns the HashSet<GraphNode> (value) of the specified String (key).
		 * @param aString
		 * @return HashSet<GraphNode>
		 */
		
		return theMap.get( aString );
	}
}
