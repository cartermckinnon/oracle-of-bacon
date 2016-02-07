import java.util.HashSet;

/** CLASS defining an Actor with a first name and last name.
 * There are constructors for one name, two names, and three
 * names, respectively; they cascade to a default two name 
 * constructor. There are getter and mutator methods for 
 * first and last names. There is a toString method that 
 * returns the full name (first + last).
 * 
 * @author Carter McKinnon
 * 	created 01.31.15
 */

public class Actor {
// VARIABLES
	private String firstName;
	private String lastName;
	private HashSet<Movie> myMovies;
	
// CONSTRUCTORS ( cascading to Actor( String, String ) )
	// single name
	public Actor( String aName ){
		this(aName, "");
	}
	// two names
	public Actor( String aFirstName, String aLastName ){
		firstName = aFirstName;
		lastName = aLastName;
		myMovies = new HashSet<Movie>();
	}
	// three names
	public Actor ( String aFirstName, String aSecondName, String aLastName ){
		this( aFirstName, aSecondName + " " + aLastName );
	}
	
// METHODS
	// getters
	public String getFirstName(){
		
		/** returns the first name of an Actor object
		 */
		
		return firstName;
	}
	public String getLastName(){
		
		/** returns the last name of an Actor object
		 */
		
		return lastName;
	}
	
	public HashSet<Movie> getMovies(){
		return myMovies;
	}
	
	public void addMovie( Movie aMovie ){
		myMovies.add( aMovie );
	}
	
	// mutators
	public void setFirstName( String aFirstName ){
		
		/** sets the first name of an Actor object to specified string
		 * @param aFirstName
		 */
		
		firstName = aFirstName;
	}
	public void setLastName( String aLastName ){
		
		/** sets the last name of an Actor object to specified string
		 * @param aLastName
		 */
		
		lastName = aLastName;
	}
	
	// other
	public String toString(){
		
		/** returns a String representation of an Actor object: [ first name + last name ] 
		 * if a last name doesn't exist, only the first name is returned
		 */
		
		if( lastName.isEmpty() == false ){
			return firstName + " " + lastName;
		}
		else{
			return firstName;
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		Actor other = (Actor) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
