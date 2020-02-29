public class Stationary extends State {

	public Stationary(boolean isOver, boolean isVisible) {
		super(isOver, isVisible);
	}

	public Stationary(){
		super();
	}

	@Override
	public final void step(Entity e){//does nothing since stationary 
	}

	@Override
	public final String toString(){
		return "Stationary";
	}


}