package Moorhuhn.src.functions;
import processing.core.PApplet;

import java.awt.Dimension;
import java.awt.Toolkit;

public abstract class Screenoptions {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	//double width = screenSize.getWidth();
	//double height = screenSize.getHeight();
	
	public static double getScreenWidth() {
		return screenSize.getWidth();
	}
	
	public static double getScreenHeight() {
		return screenSize.getHeight();
	}
}
