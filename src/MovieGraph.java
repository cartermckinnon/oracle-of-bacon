import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class MovieGraph implements Iterable<MovieGraphNode>{

	private HashSet<MovieGraphNode> myNodes;
	private int myEdges;
	
	public MovieGraph(){
		myNodes = new HashSet<MovieGraphNode>();
	}
	
	public int edges(){
		
		/** returns the number of edges in the MovieGraph.
		 */
		
		return myEdges;
	}
	
	public boolean exists( String v ){
		
		/** returns true if v is a vertex in the MovieGraph.
		 */
		
		for( MovieGraphNode aNode : myNodes ){
			if( v.equalsIgnoreCase( ( aNode.getMovie().getTitle() ) ) ){
				return true;
			}
		}
		return false;
	}
	
	public boolean exists( String v, String w ){
		
		/** returns true if v-w is an edge in the MovieGraph.
		 */
		
		MovieGraphNode theV = null;
		MovieGraphNode theW = null;
		for( MovieGraphNode aNode : myNodes ){
			if( v.equalsIgnoreCase( aNode.getMovie().getTitle() ) ){
				theV = aNode;
			}
		}
		if( theV != null ){
			for( MovieGraphNode anNode : myNodes ){
				if( w.equalsIgnoreCase( anNode.getMovie().getTitle() ) ){
					theW = anNode;
				}
			}
		}
		if( ( theV != null ) && ( theW != null ) ){
			return theV.getLinks().contains( theW );
		}
		return false;
	}
	
	/*
	public Iterable<MovieGraphNode> BreadthFirstSearch( String source, String dest ){
		
		/** performs a breadth-first search from source to dest and returns an iterable object of the discovered path
		 */
		
	// }
	
	
	public ArrayList<MovieGraphNode> findPath( String aStart, String anEnd ){
		
		ArrayList<MovieGraphNode> result = new ArrayList<MovieGraphNode>();
		ArrayList<MovieGraphNode> toDo = new ArrayList<MovieGraphNode>();
		ArrayList<MovieGraphNode> beenDone = new ArrayList<MovieGraphNode>();
		HashMap<MovieGraphNode,MovieGraphNode> map = new HashMap<MovieGraphNode, MovieGraphNode>();
		if( ! exists( aStart ) ){
			return result;
		}
		else if( ! exists( anEnd ) ){
			return result;
		}
		MovieGraphNode theStart = getNode( aStart );
		MovieGraphNode theEnd = getNode( anEnd );
		toDo.add( theStart );
		while( ! toDo.isEmpty() ){
			MovieGraphNode current = toDo.remove( 0 );
			if( current == theEnd ){
				result.add( current );
				MovieGraphNode temp = map.get( current );
				while( temp != theStart ){
					result.add( temp );
					temp = map.get( temp );
				}
				result.add( temp );
				return result;
			}
			if( ! beenDone.contains( current ) ){
				beenDone.add( current );
				Iterator<MovieGraphNode> neighbors = current.iterator();
				while( neighbors.hasNext() ){
					MovieGraphNode neighbor = neighbors.next();
					if( ! beenDone.contains( neighbor ) ){
						toDo.add( neighbor );
						map.put( neighbor, current );
					}
				}
			}
		}
		return result;
	}
	
	public MovieGraphNode getNode( String aValue ){
		for( MovieGraphNode aNode : myNodes ){
			if( aNode.getMovie().getTitle().equals( aValue ) ){
				return aNode;
			}
		}
		return null;
	}
	
	@Override
	public Iterator<MovieGraphNode> iterator() {
		return myNodes.iterator();
	}

	public void loadDataFromFile( String aFileName ){
		
		/** loads the information from the specified file into a MoveGraph.
		 *  The format of the file must be the same as the "cast-mpaa.txt" file.
		 *  @param aFileName
		*/
			
		Scanner theScanner = null;
		String movieData; 
		try {
			theScanner = new Scanner(new FileInputStream( aFileName ));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (theScanner.hasNextLine()){
			// DETERMINING TITLE AND YEAR
			movieData = theScanner.nextLine(); // assigned one film worth of raw movie data (title, year, and all actors)
			String[] separatedData = movieData.split("/"); // data is initially divided
			String[] movieInfo = separatedData[0].split("\\("); // title and year are isolated
			String movieTitle = movieInfo[0].trim(); // title is assigned
			String movieReleaseYear = movieInfo[(movieInfo.length - 1)].substring(0,4); // year is assigned
			int releaseYear = Integer.parseInt(movieReleaseYear); // year converted to type int
			HashSet<Actor> actorsInMovieX = new HashSet<Actor>(); // used to store an individual movie's actors
			// DETERMINING ACTOR(S) AND CREATING ACTOR OBJECT(S)
			for ( int i = 1; i < separatedData.length; i++ ){
				if( separatedData[i].contains( "," ) ){
					String[] actorName = separatedData[i].split(","); // first and last names are split
					Actor anActor = new Actor( actorName[( actorName.length - 1 )].trim(),actorName[0] ); // names are used to create Actor object
					actorsInMovieX.add( anActor ); // actor added to individual movie's collection of actors
				}
				else{
					Actor anActor = new Actor( separatedData[i].trim() );
					actorsInMovieX.add( anActor );
				}
			} 
			// CREATING MOVIE OBJECT AND SAVING TO MOVIEGRAPHNODE IN MOVIEGRAPH
			Movie aMovie = new Movie( movieTitle, releaseYear, actorsInMovieX ); // Movie object is created with discerned title, year, and actors
			MovieGraphNode newNode = new MovieGraphNode( aMovie );
			// System.out.println( aMovie.getActors() );
			for( MovieGraphNode aNode : myNodes ){
				System.out.println( aNode.getActors() );
				HashSet<Actor> intersection = newNode.getActors();
				System.out.println( intersection.size() );
				intersection.retainAll( aNode.getActors() );
				System.out.println( intersection.size() );
			}
			myNodes.add( newNode ); // Movie is added to "database" (ArrayList<Movie>)
			actorsInMovieX = null; // ArrayList<Actor> reset to allow next film's actors to be assigned
			
		}
		theScanner.close(); // closes the Scanner
		System.out.println( myNodes.size() + " movies were loaded."); // prints number of films loaded

		// CREATE EDGES (LINKS) BETWEEN MOVIES WITH COMMON ACTORS
		
		for( MovieGraphNode aNode : myNodes ){
			// System.out.println( aNode.getMovie().getActors() );
			/*
			for( MovieGraphNode bNode : myNodes ){
				if( bNode != aNode ){
					ArrayList<Actor> aNodeActors = aNode.getActors();
					System.out.println( aNodeActors.retainAll( bNode.getActors() ) );
					if( aNodeActors.size() > 0 ){
						aNode.addLink( bNode );
						bNode.addLink( aNode );
						System.out.println( "Link added" );
					}
				}
			}
			*/
		}
		
		
	}
	
}	
	