package com.snack.demo1.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class FrameUtil {
	static Toolkit toolkit =Toolkit.getDefaultToolkit();
	static Dimension dimension= toolkit.getScreenSize();
	static int x= (int) dimension.getWidth();
	static int y= (int) dimension.getHeight();
	public static void initFrame(JFrame jFrame,int width,int height) {

		jFrame.setBounds((x-width)/2, (y-height)/2, width, height);
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void initFrameWithOutExit(JFrame jFrame,int width,int height) {
		jFrame.setBounds((x-width)/2, (y-height)/2, width, height);
		jFrame.setVisible(true);
	}
}
