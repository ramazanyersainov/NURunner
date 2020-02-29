import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class NURunner {
	JFrame window;
	Common common;
	Display display;



	public NURunner(JFrame window, Common common, Display display){
		this.window = window;
		this.display = display;
		this.common = common;
	}

	public static void main(String[] args) {

		int windowWidth = 1200;
		int windowHeight = 600;
		int studentsNumber = 10;

		JFrame main_window = new JFrame("NURunner");
		Common common = new Common(windowWidth,windowHeight,studentsNumber);
		Display display = new Display(common);

		NURunner runner = new NURunner(main_window,common,display);
		runner.window.add(display);
		runner.window.pack();

		SwingUtilities.invokeLater(new Runnable() {public void run() {runner.window.setVisible(true);}
									});


		ActionListener myListener = new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											runner.common.stepAllEntities();
											display.repaint();
										}
									};

		Timer timer = new Timer(30,myListener);
		timer.start();

	}

}