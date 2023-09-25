package com.snack.demo1.gamelogic;

import com.snack.demo1.DirectionEnum;
import com.snack.demo1.Food;
import com.snack.demo1.GameMain;
import com.snack.demo1.SNAKE_ALL_ARGS;

import java.awt.Point;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class GameJudge implements SNAKE_ALL_ARGS {

    static GameMap gameMap = GameMap.getInStance();
//    private static SnakeOperater snakeOperater = GameMain.snakeOperater;

    public static boolean isGameOverFlag = false;

    public void setIsGameOverFlag(boolean flag) {
        this.isGameOverFlag = flag;
    }

    //死亡后是否再次游戏
    public static void ifRestartGame() {
        GameMain.snakeOperater.stopAutoMoveThread();
        System.out.println("游戏结束");
        int code = new JOptionPane().showConfirmDialog(GameMain.jFrame, "当前移速： " + GameMain.snakeOperater.getSnack().getSpeed() + "\r\n游戏结束，是否重新开始", "游戏结束", JOptionPane.INFORMATION_MESSAGE);

        //清除标记
        GameMain.snakeOperater.setThreadFlag(false);        //将线程标志清空
        GameJudge.isGameOverFlag = false;    //将游戏失败标记清除
        GameMain.currentDirection = -2;    //初始化方向

        switch (code) {
            //确定
            case 0:
                //重新开始,
                GameMain.snakeOperater.createANewSnake();    //重新开始,所以需要新建一条蛇
                GameShow.showSnake();
                GameMain.snakeOperater.startAutoMoveThread();
                break;
                //asd
            //关闭和取消
            default:
                //重新开始
                /**1.重置地图
                 * 2.把蛇数据重置
                 * 3.把之前的子线程停下来
                 * 4.绘制地图和蛇
                 */
                GameMain.snakeOperater.stopAutoMoveThread();        //停止正在活动的自动移动线程
                GameMain.snakeOperater.createANewSnake();//重新开始,所以需要新建一条蛇
                gameMap.initMap();
                break;
        }
    }

    //吃食物的判断
    public boolean isAte() {
        if (GameMain.snakeOperater.getSnack().getSnakeList().getFirst().equals(Food.food)) {
            return true;
        } else {
            return false;
        }

    }

    //游戏是否结束
    public boolean isGameOver() {

        Point head = GameMain.snakeOperater.getSnack().getSnakeList().getFirst();
        //撞墙
        //头的坐标值为true就凉了

//				  if (mapArray[head.x][head.y]==true) {
//						System.out.println("撞墙了啊");
//						flag=true;
//			}
//
        //遍历蛇身体 如果撞倒了身体就凉了
        int index = 0;
        LinkedList<Point> linkedList  = GameMain.snakeOperater.getSnack().getSnakeList();
        for (Point point : linkedList) {
            if (index == 0) {
                index++;
                continue;
            }
            //咬到了自己
            if (head.equals(point)) {
                isGameOverFlag = true;
                break;
            }
        }
        return isGameOverFlag;
    }

    //判断蛇是不是在边界
    public static Point changePosition(Point point, int currentDirection) {
        if (point.x < 0 && currentDirection == 2) {
            point.x = gameMap.ROWSWIDTH - 1;
            return point;
        } else if (point.x == gameMap.ROWSWIDTH && currentDirection == -2) {
            point.x = 0;
            return point;
        } else if (point.y < 0 && currentDirection == 1) {
            point.y = gameMap.COLSHEIGHT - 1;
            return point;
        } else if (point.y == gameMap.COLSHEIGHT && currentDirection == -1) {
            point.y = 0;
            return point;
        }
        return point;
    }


    public static Point changePosition(Point point, DirectionEnum currentDirection) {
        if (point.x < 0 && currentDirection == (DirectionEnum.LEFT)) {
            point.x = gameMap.ROWSWIDTH - 1;
            return point;
        } else if (point.x == gameMap.ROWSWIDTH && currentDirection == DirectionEnum.RIGHT) {
            point.x = 0;
            return point;
        } else if (point.y < 0 && currentDirection == DirectionEnum.UP) {
            point.y = gameMap.COLSHEIGHT - 1;
            return point;
        } else if (point.y == gameMap.COLSHEIGHT && currentDirection == DirectionEnum.DOWN) {
            point.y = 0;
            return point;
        }
        return point;
    }

}
