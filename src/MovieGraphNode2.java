import java.util.HashSet;
import java.util.Iterator;


public class MovieGraphNode2 {

	private Movie myMovie;
	private HashSet<Actor> myActors;
	private HashSet<MovieGraphNode> myLinks;
	
	public MovieGraphNode2( Movie aMovie ){
		myMovie = aMovie;
		myLinks = new HashSet<MovieGraphNode>();
		myActors = aMovie.getActors();
	}
	
	public void addLink( MovieGraphNode aLink ){
		myLinks.add( aLink );
	}
	
	public HashSet<MovieGraphNode> getLinks(){
		return myLinks;
	}
	
	public Movie getMovie(){
		return myMovie;
	}
	
	public HashSet<Actor> getActors(){
		return myActors;
	}
	
	public Iterator<MovieGraphNode> iterator(){
		// return myLinks.iterator();
		// ^^ NOT CORRECT
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((myLinks == null) ? 0 : myLinks.hashCode());
		result = prime * result + ((myMovie == null) ? 0 : myMovie.hashCode());
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
		MovieGraphNode other = (MovieGraphNode) obj;
		if (myLinks == null) {
			if (other.getLinks() != null)
				return false;
		} else if (!myLinks.equals(other.getLinks() ))
			return false;
		if (myMovie == null) {
			if (other.getMovie() != null)
				return false;
		} else if (!myMovie.equals(other.getMovie() ))
			return false;
		return true;
	}

	public void print(){
		System.out.println( myMovie.toString() );
	}
	
}