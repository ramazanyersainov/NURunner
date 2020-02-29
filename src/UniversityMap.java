import java.awt.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class UniversityMap extends Entity {

	BufferedImage image;

	public UniversityMap(String name, Vector2D position, State state, Common common, String filename){
		super(name,position,state,common);
		try{
			image = ImageIO.read(new File(filename)) ;
		} catch (IOException e){
			System.err.println(e.getMessage());
		}
	}

	@Override
	public void draw(Graphics2D g2d){

		g2d.drawImage(this.image,0,0,this.common.windowWidth,this.common.windowHeight,null);

	}
}