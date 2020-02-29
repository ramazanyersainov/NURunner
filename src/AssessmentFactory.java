public abstract class AssessmentFactory {

	Common common;

	public AssessmentFactory(Common common){
		this.common = common;
	}

	public abstract Assessment createAssessment(Vector2D position);
}