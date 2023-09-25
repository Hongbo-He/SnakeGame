package com.snack.demo1.gamelogic;

import com.snack.demo1.Food;
import com.snack.demo1.GameMain;
import com.snack.demo1.SNAKE_ALL_ARGS;

import java.awt.Graphics;

import javax.swing.JPanel;

//实现 map 此类管理map的数据 以及关于map的方法
public class GameMap extends JPanel implements SNAKE_ALL_ARGS {
    private static final long serialVersionUID = -7514713864613522286L;

    private static GameMap gameMap = null;

    private GameMap() {}

    public static GameMap getInStance() {
        if (gameMap == null) {
            gameMap = new GameMap();
        }
        return gameMap;
    }

    // 地图数组
    static Graphics graphics;

    public static Graphics myGetGraphics() {
        return graphics;
    }

    static GameJudge gameJudge = new GameJudge();
    boolean isFirst = true;

    // 初始化地图
    public void initMap() {
        for (int rows = 0; rows < mapArray.length; rows++) {
            for (int cols = 0; cols < mapArray[rows].length; cols++) {
                if (rows == 0 || rows == COLSHEIGHT - 1 || cols == 0 || cols == ROWSWIDTH - 1) {
                    //添加地圖邊界
//						mapArray[rows][cols] = true;	
//						mapArray[rows][cols] = false;
                }
            }
        }
        System.out.println("地图初始化---Finish");
    }

    @Override
    public void paint(Graphics g) {
        GameMap.graphics = g;
        GameShow.showSnake();
        GameShow.showFood();
    }

    public void getRepaint() {
        //判断游戏是否结束
        if (gameJudge.isGameOver()) {
            GameJudge.ifRestartGame();
        } else {
            //判断吃到了食物没有
            if (gameJudge.isAte()) {
                Food.createFood();
                int speed = GameMain.snakeOperater.getSnack().getSpeed();
                if ( speed>= 10) {
                    GameMain.snakeOperater.getSnack().setSpeed(speed -= 5);  ;
                }
            } else {
                GameMain.snakeOperater.getSnack().getSnakeList().removeLast();
            }
        }
        this.repaint();
    }

}
