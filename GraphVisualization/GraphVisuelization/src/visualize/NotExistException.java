package visualize;

public class NotExistException extends Exception { // exception if are deleting node that doesnt exist

	private String message;
	
	public NotExistException(String m) {
		
		message=m;
		
	}
	
	public String toString() {
		
		return message;
		
	}
}
