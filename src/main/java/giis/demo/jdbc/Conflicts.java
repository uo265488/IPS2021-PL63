package giis.demo.jdbc;

public class Conflicts {
    
	
	private int numOfConflicts;
	private int newConflict;
	
	public Conflicts() {
		this.numOfConflicts = 0;
		this.newConflict = 21343;
	}
	
	public int getNumOfConflicts() {
		return numOfConflicts + 1;
	}
}
