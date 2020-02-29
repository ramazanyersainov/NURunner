public class Vector2D {

	public double x;
	public double y;

	public Vector2D(double x, double y){
		this.x = x;
		this.y = y;
	}

	public void set(Vector2D v){
		this.x = v.x;
		this.y = v.y;
	}

	public double distanceTo(Vector2D other){
		return Math.sqrt(Math.pow(this.x-other.x,2) + Math.pow(this.y-other.y,2));
	}

	public double distanceTo(double x, double y) {
		return Math.sqrt(Math.pow(this.x-x,2) + Math.pow(this.y-y,2));
	}

	public Vector2D normalize(){
		final double magnitude = Math.sqrt(Math.pow(this.x,2) + Math.pow(this.y,2));
		return new Vector2D(this.x/magnitude, this.y/magnitude);
	}

	public Vector2D plus(Vector2D other){
		return new Vector2D(this.x + other.x, this.y + other.y);
	}

	public Vector2D minus(Vector2D other){
		return new Vector2D(this.x - other.x, this.y - other.y);
	}


}