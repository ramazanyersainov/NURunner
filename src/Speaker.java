import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Speaker extends Entity {

	BufferedImage image;

	public Speaker(String name, Vector2D position, State state, Common common, String filename){
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

	}

}