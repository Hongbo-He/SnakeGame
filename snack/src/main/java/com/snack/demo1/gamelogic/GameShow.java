package com.snack.demo1.gamelogic;

import com.snack.demo1.Food;
import com.snack.demo1.GameMain;
import com.snack.demo1.SNAKE_ALL_ARGS;
import com.snack.demo1.domin.SnakeBean;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class GameShow implements SNAKE_ALL_ARGS {
    private static Graphics graphics = GameMap.myGetGraphics();
    private static Point food = Food.food;
    // 画地图
    public static void showMap() {
        graphics = GameMap.myGetGraphics();
        for (int rows = 0; rows < mapArray.length; rows++) {
            for (int cols = 0; cols < mapArray[rows].length; cols++) {
                if (mapArray[rows][cols]) {
                    graphics.setColor(Color.GRAY);
                } else {
                    graphics.setColor(Color.WHITE);
                }
                graphics.fill3DRect(cols * CELLWIDTH, rows * CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
            }
        }
    }
    // 画蛇
    public static void showSnake() {
        // 显示地图
        showMap();
        // 把蛇占据的方框涂色

        try {
            SnakeBean snakeBean = GameMain.snakeOperater.getSnack();
            if (snakeBean.getSnakeList() == null) {
                return;
            }
            Point head = snakeBean.getSnakeList().getFirst();

            // 先画蛇头
            graphics.setColor(Color.RED);
            graphics.fill3DRect(head.x * CELLHEIGHT, head.y * CELLWIDTH, CELLWIDTH, CELLWIDTH, true);
            // 画蛇身体
            graphics.setColor(Color.GREEN);
            for (int i = 1; i < snakeBean.getSnakeList().size(); i++) {
                Point point = snakeBean.getSnakeList().get(i);
                graphics.fill3DRect(point.x * CELLWIDTH, point.y * CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 显示食物
    public static void showFood() {
        food = Food.food;
        graphics.setColor(Color.PINK);
        graphics.fill3DRect(food.x * CELLWIDTH, food.y * CELLHEIGHT, CELLWIDTH, CELLHEIGHT, true);
    }
}
