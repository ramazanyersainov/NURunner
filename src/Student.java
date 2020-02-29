import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;

public class Student extends Entity {

	public int grade;

	public Student(String name, Vector2D position, State state, Common common){
		super(name, position, state, common);
		this.grade = 0;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setPaint(Color.BLACK);

		int nameWidth = g2d.getFontMetrics().stringWidth(this.name);
		g2d.drawString(this.name,(int)this.position.x-(nameWidth-40)/2,(int)this.position.y-5);

		g2d.setPaint(new Color(176224230)); //rgb for lightblue
		if (this.grade >= 100)
			g2d.setPaint(Color.MAGENTA);
		g2d.fillOval((int)this.position.x,(int)this.position.y,40,40);

		g2d.setPaint(Color.BLACK);
		int gradeWidth = g2d.getFontMetrics().stringWidth(Integer.toString(this.grade));
		g2d.drawString(Integer.toString(this.grade),(int)this.position.x-(gradeWidth-40)/2,(int)this.position.y+25);

		String state = this.state.toString();
		int stateWidth = g2d.getFontMetrics().stringWidth(state);
		g2d.drawString(state,(int)this.position.x-(stateWidth-40)/2,(int)this.position.y+50);

	}

}