import java.util.HashSet;

/** CLASS defining a Movie with a title, release year, and 
 * an array list of starring actors. There is a general
 * constructor that takes all three of these parameters,
 * a constructor that takes only a title, and a constructor
 * that takes a title and a year. There are getter methods for 
 * each of the three parameters, along with a toString method. 
 * 
 * @author Carter McKinnon
 * 	created 01.31.15
 */

public class Movie implements Comparable<Movie> {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((theActors == null) ? 0 : theActors.hashCode());
		result = prime * result + theReleaseYear;
		result = prime * result
				+ ((theTitle == null) ? 0 : theTitle.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		if (theActors == null) {
			if (other.theActors != null)
				return false;
		} else if (!theActors.equals(other.theActors))
			return false;
		if (theReleaseYear != other.theReleaseYear)
			return false;
		if (theTitle == null) {
			if (other.theTitle != null)
				return false;
		} else if (!theTitle.equals(other.theTitle))
			return false;
		return true;
	}

	// VARIABLES
	private String theTitle;
	private int theReleaseYear;
	private HashSet<Actor> theActors = new HashSet<Actor>();
	private static final HashSet<Actor> noActors = null;
	private static final int noReleaseYear = 0;
	
// CONSTRUCTORS
	public Movie( String aTitle ){
		this( aTitle, noReleaseYear , noActors );
	}
	public Movie( String aTitle, int aReleaseYear ){
		this( aTitle, aReleaseYear, noActors );
	}
	public Movie(String aTitle, int aYear, HashSet<Actor> someActors){
		theTitle = aTitle;
		theReleaseYear = aYear;
		theActors = someActors;
	}
	
// METHODS
	public String getTitle(){
		
		/** returns the title of a Movie object
		 */
		
		return theTitle;
	}
	public int getYear(){
		
		/** returns the release year of a Movie object
		 */
		
		return theReleaseYear;
	}
	public HashSet<Actor> getActors(){
		
		/** returns an HashSet of the starring Actors of a Movie object
		 */
		
		return theActors;
	}
	
	public int compareTo( Movie aMovie ){
		if( theReleaseYear < aMovie.theReleaseYear ){
			return -1;
		}
		else if( theReleaseYear == aMovie.theReleaseYear ){
			if( theTitle.compareTo( aMovie.theTitle ) < 0 ){
				return -1;
			}
			else if( theTitle.compareTo( aMovie.theTitle ) == 0 ){
				return 0;
			}
			else{
				return 1;
			}
		}
		return 1;
	}
	
	public String toString(){
		
		/** returns a String representation of a Movie object: [ title + ( release year ) ]
		 */
		
		if( theReleaseYear != 0 ){
			return (theTitle + " (" + theReleaseYear + ")");
		}
		else{
			return theTitle;
		}
		
	}
}

