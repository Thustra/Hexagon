package world;

public class cubeCoordinate extends Coordinate {


	/**
	 * Convert cube to axial
	 *  
	 * x = q
	 * z = r
	 * y = -x-z
	 * 
	 */
	
	private double x;
	private double y;
	private double z;
	
	
	public cubeCoordinate (int x, int y, int z){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}
	
	public cubeCoordinate (double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public axialCoordinate Convert() {
		int q = (int) x;
		int r = (int) z;
		return new axialCoordinate(q, r);
	}
	
	public axialCoordinate ConvertDouble() {
		double q = x;
		double r = z;
		return new axialCoordinate(q, r);
	}
	
	public void hex_round() {
		double rx = Math.round(x);
		double ry =  Math.round(y);	
		double rz =  Math.round(z);
		
		double x_diff = Math.abs(rx - x);
		double y_diff = Math.abs(ry - y);
		double z_diff = Math.abs(rz - z);
		
		if ( x_diff > y_diff && x_diff > z_diff){
			rx = -ry-rz;
		}else if (y_diff > z_diff){
			ry = -rx-rz;
		}else{
			rz = -rx-ry;
		}
		this.x = rx;
		this.y = ry;
		this.z = rz;
		
	}

	public int getX() {
		return (int) x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return (int) y;
	}

	private void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return (int) z;
	}

	private void setZ(int z) {
		this.z = z;
	}

}
