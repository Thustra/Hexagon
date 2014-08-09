package logic;

public class Path {
	
	private PathFinder strategy;

	public Path() {
		// TODO Auto-generated constructor stub
	}
	
	public void setStrategy(PathFinder strategy){
		this.strategy = strategy;
	}
	
	public void findPath(){
		strategy.findPath(null, null);
	}

}
