import java.awt.*;
import javax.swing.*;

public class Quiz extends Assessment {

	public Quiz(String name, Vector2D position, State state, Common common, int points){
		super(name, position, state, common, points);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setPaint(Color.BLUE);
		int x = (int)this.position.x;
		int y = (int)this.position.y;
		g2d.fillPolygon(new int[] {x+4, x + 14, x + 24}, new int[] {y+24, y+4, y+24}, 3);

		g2d.setPaint(Color.BLACK);
		g2d.drawString(Integer.toString(this.points),(int)this.position.x+10,(int)this.position.y+21);
	}

}