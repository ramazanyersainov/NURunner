public abstract class State {

	public boolean isOver;
	public boolean isVisible;

	public State() {
		this.isOver = false;
		this.isVisible = true;
	}

	public State(boolean isOver, boolean isVisible){

		this.isOver = isOver;
		this.isVisible = isVisible;

	}

	public abstract void step(Entity e);



}