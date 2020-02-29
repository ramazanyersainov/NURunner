public abstract class Assessment extends Entity{

	public int points;

	public Assessment(String name, Vector2D position, State state, Common common, int points){
		super(name, position, state, common);
		this.points = points;
	}

}