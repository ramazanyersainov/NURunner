public class LabFactory extends AssessmentFactory {

	public LabFactory(Common common){
		super(common);
	}

	@Override
	public Assessment createAssessment(Vector2D position) {

		return new Lab("Lab",position,new Stationary(),this.common,this.common.randomInt(2,4));

	}

}