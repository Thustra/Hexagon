package logic;

public class Path {
	
	private PathFinder strategy;

	public Path() {
		// TODO Auto-generated constructor stub
		// Adding some stuff for git testing purposes.
	}
	
	public void setStrategy(PathFinder strategy){
		this.strategy = strategy;
	}
	
	public void findPath(){
		strategy.findPath(null, null);
	}

}
