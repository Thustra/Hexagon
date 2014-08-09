package world;

public class axialCoordinate extends Coordinate {
	
	private int result = 17;

	/**
	 * Convert cube to axial
	 *  
	 * q = x
	 * r = z
	 * 
	 */
	
	private double q;
	private double r;
	
	public axialCoordinate (int q, int r){
		this.setQ(q);
		this.setR(r);
	}
	
	public axialCoordinate (double q, double r){
		this.q = q;
		this.r = r;
	}
	
	public cubeCoordinate Convert() {
		int x = getQ();
		int z = getR();
		int y = - x - z;
		return new cubeCoordinate(x, y, z);
	}
	
	public cubeCoordinate ConvertDouble() {
		double x = q;
		double z = r;
		double y = - x - z;
		return new cubeCoordinate(x, y, z);
	}


	public int getQ() {
		return (int) q;
	}

	private void setQ(int q) {
		this.q = q;
	}

	public int getR() {
		return (int) r;
	}

	private void setR(int r){
		this.r = r;
	}
	
	@Override
	public int hashCode(){
		long f = Double.doubleToLongBits(q);
		long g = Double.doubleToLongBits(r);
		
		int hashresult = 31 * result + (int) ( f ^ (f >>> 32));
		hashresult = 31 * hashresult + (int) ( g ^ (g >>> 32));
		
		return hashresult;
	}
	@Override
	public boolean equals(Object other){
		if (other == null || other.getClass() != getClass())
			return false;
		return this.q == ((axialCoordinate) other).getQ() && this.r == ((axialCoordinate)other).getR();
	}
	
	@Override
	public String toString(){
		return "q: " + q + " " + "r: " + r;
	}

}
