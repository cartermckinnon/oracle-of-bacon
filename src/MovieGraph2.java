import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;


public class MovieGraph2 {

	private HashSet<Actor> myNodes;
	private int edges = 0;
	
	public MovieGraph2(){
		myNodes = new HashSet<Actor>();
	}
	
	public void loadData( String aFileName ){
		
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
					Actor anActor = new Actor( actorName[( actorName.length - 1 )].trim(), actorName[0] ); // names are used to create Actor object
					if( myNodes.contains( anActor ) ){
						// HOW CAN I REFERENCE THIS OBJECT TO ADD A MOVIE TO ITS LINK?
					}
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

		
	}
}
