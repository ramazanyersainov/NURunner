public class GotoXY extends State {

	public Vector2D dest;
	public int steps;
	public int current_step;

	public GotoXY(int steps, Vector2D dest) {
		super();
		this.steps = steps;
		this.dest = dest;
		this.current_step = 0;
	}

	@Override
	public void step(Entity e){

		if (dest.distanceTo(e.position) <= 5.00) {
			e.position.set(dest);
			e.state.isOver = true;
		}
		else {
			Vector2D diff = dest.minus(e.position);
			diff = diff.normalize();
			diff.x = diff.x*2.5;
			diff.y = diff.y*2.5;
			e.position = e.position.plus(diff);
		}
		if (current_step >= steps)
			e.state.isOver = true;

		current_step ++;
	}

	@Override
	public final String toString(){
		return "GotoXY";
	}


}