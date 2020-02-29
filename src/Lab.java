import java.awt.*;
import javax.swing.*;

public class Lab extends Assessment {

	public Lab(String name, Vector2D position, State state, Common common, int points){
		super(name, position, state, common, points);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setPaint(Color.RED);
		int x = (int)this.position.x;
		int y = (int)this.position.y;
		g2d.fillOval(x+4, y+4, 20, 20);

		g2d.setPaint(Color.BLACK);
		g2d.drawString(Integer.toString(this.points),(int)this.position.x+10,(int)this.position.y+20);
	}

}