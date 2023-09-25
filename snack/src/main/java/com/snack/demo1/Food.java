package com.snack.demo1;

import java.awt.Point;
import java.util.Random;

public class Food implements SNAKE_ALL_ARGS {
    public static Point food = null;

    // 生成食物
    public static void createFood() {
        // 食物的位置随机出现
        Random random = new Random();
        boolean flag = true;
        while (true) {
            int x = random.nextInt(ROWSWIDTH);
            int y = random.nextInt(COLSHEIGHT);
            // 检验生成的位置是否合法 检查是不是被占据了
            boolean subFlag = false;    //声明一个标记,用于判断食物是否在在蛇身上生成,若是就变成true;
            for (Point point : GameMain.snakeOperater.getSnack().getSnakeList()) {
                //去掉感叹号就可以只在身上生成
                if (point.equals(food = new Point(x, y))) {
                    System.out.println("snake :" + point);
                    System.out.println("food :" + food);
                    subFlag = true;
                    break;
                }
            }
            if (mapArray[y][x] == false && !subFlag) {
                food = new Point(x, y);
                break;
            }

//				if (mapArray[y][x] == false) {
//					food = new Point(x, y);
//					break;
//				}
        }
    }

}
