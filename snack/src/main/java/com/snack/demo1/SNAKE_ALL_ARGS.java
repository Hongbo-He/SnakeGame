package com.snack.demo1;

import java.awt.Font;

public interface SNAKE_ALL_ARGS {
	        // 行列
			final static int ROWSWIDTH = 30;
			final static int COLSHEIGHT = 30;
			 // 格子的宽和高

			final static int CELLWIDTH = 20;
			final static int CELLHEIGHT = 20;
			// 定义蛇运动方向的标识
			final static int DIRECTION_UP = 1; // 向上移动
			final static int DIRECTION_DOWN = -1; // 向下移动
			final static int DIRECTION_LEFT = 2; // 向左移动
			static boolean[][] mapArray = new boolean[COLSHEIGHT][ROWSWIDTH];
			static Font font=new Font("宋体",Font.PLAIN,15);
}