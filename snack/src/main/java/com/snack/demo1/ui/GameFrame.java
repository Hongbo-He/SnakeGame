package com.snack.demo1.ui;

import com.snack.demo1.SNAKE_ALL_ARGS;
import com.snack.demo1.utils.FrameUtil;

import javax.swing.JFrame;

public class GameFrame implements SNAKE_ALL_ARGS {
	
	public static void initFrame(JFrame jFrame) {
		FrameUtil.initFrame(jFrame, ROWSWIDTH*CELLWIDTH + 15, COLSHEIGHT*CELLHEIGHT+62);//最后一个是菜单的高度
	}

}
