import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Display extends JPanel{

	public Common common;

	public Display(Common common){

		this.common = common;

	}

	public Dimension getPreferredSize(){
		return new Dimension(this.common.windowWidth, this.common.windowHeight);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		common.drawAllEntities(g2d);


	}

}