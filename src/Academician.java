import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Academician extends Entity {

	BufferedImage image;

	public Academician(String name, Vector2D position, State state, Common common, String filename){
		super(name, position, state, common);
		try{
			image = ImageIO.read(new File(filename)) ;
		} catch (IOException e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(this.image,(int)this.position.x,(int)this.position.y,40,55,null);
		g2d.setPaint(Color.BLACK);

		int nameWidth = g2d.getFontMetrics().stringWidth(this.name);
		g2d.drawString(this.name,(int)this.position.x-(nameWidth-40)/2,(int)this.position.y-5);

		String state = this.state.toString();
		int stateWidth = g2d.getFontMetrics().stringWidth(state);
		g2d.drawString(state,(int)this.position.x-(stateWidth-40)/2,(int)this.position.y+65);

	}

	public Assessment createAssessment(){

		int choice = this.common.randomInt(0,2);

		Vector2D position = new Vector2D(((int)this.position.x + this.common.randomInt(20, 150) - 30),((int)this.position.y + this.common.randomInt(20, 150) - 30));
		Assessment assessment = null;
		switch (choice) {
			case 0:
				assessment = this.common.quizFactory.createAssessment(position);
				break;
			case 1:
				assessment = this.common.labFactory.createAssessment(position);
				break;
			case 2:
				assessment = this.common.homeworkFactory.createAssessment(position);
				break;
		}
		common.assessments.add(assessment);
		return assessment;

	}

}