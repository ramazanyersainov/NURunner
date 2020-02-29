public class Rest extends State {

	public int steps;
	public int current_step;
	public Rest(int steps) {
		super();
		this.steps = steps;
		this.current_step = 0;
	}

	@Override
	public void step(Entity e){
		if (current_step >= steps)
			e.state.isOver = true;
		current_step++;
	}

	@Override
	public final String toString(){
		return "Rest";
	}


}