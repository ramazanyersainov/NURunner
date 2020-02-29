public class ZigZag extends State {

	public int steps;
	public Vector2D way1;
	public Vector2D way2;
	public Vector2D dest;
	public int current_step;
	public int steps_one_way;

	public ZigZag(int steps, Vector2D way1, Vector2D way2) {
		super();
		this.steps = steps;
		this.way1 = way1;
		this.way2 = way2;
		current_step = 0;
		steps_one_way = 10;
		dest = way1;
	}

	@Override
	public void step(Entity e){
		if (current_step % steps_one_way == 0)
			changeWay();

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

	public void changeWay() {
		if (dest == way1)
			dest = way2;
		else
			dest = way1;
	}

	@Override
	public final String toString(){
		return "ZigZag";
	}


}