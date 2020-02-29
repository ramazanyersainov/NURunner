import java.awt.*;
import javax.swing.*;

public class Homework extends Assessment {

	public Homework(String name, Vector2D position, State state, Common common, int points){
		super(name, position, state, common, points);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setPaint(Color.GREEN);
		int x = (int)this.position.x;
		int y = (int)this.position.y;
		g2d.fillRect(x+4,y+4,20,20);

		g2d.setPaint(Color.BLACK);
		g2d.drawString(Integer.toString(this.points),(int)this.position.x+10,(int)this.position.y+20);
	}

}