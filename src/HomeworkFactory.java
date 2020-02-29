public class HomeworkFactory extends AssessmentFactory {

	public HomeworkFactory(Common common){
		super(common);
	}

	@Override
	public Assessment createAssessment(Vector2D position) {

		return new Homework("Homework",position,new Stationary(),this.common,this.common.randomInt(1,3));

	}

}