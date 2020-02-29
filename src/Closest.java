public class Closest extends State {

	public Assessment closestAssessment;
	public int steps;
	public int current_step;
	public Closest(int steps, Assessment closestAssessment) {
		super(false, true);
		this.steps = steps;
		this.closestAssessment = closestAssessment;
		this.current_step = 0;

	}

	@Override
	public void step(Entity e){
		if (closestAssessment.position.distanceTo(e.position) <= 5.0) {
			e.position.set(closestAssessment.position);
			closestAssessment = e.common.findClosest(e,closestAssessment);
		}
		else {
			Vector2D diff = closestAssessment.position.minus(e.position);
			diff = diff.normalize();
			diff.x = diff.x*2.5;
			diff.y = diff.y*2.5;
			e.position = e.position.plus(diff);
		}

		if (current_step == steps)
			e.state.isOver = true;

		current_step++;
	}

	@Override
	public final String toString(){
		return "Closest";
	}


}