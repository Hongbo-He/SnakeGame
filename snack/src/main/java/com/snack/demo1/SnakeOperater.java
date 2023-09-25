package com.snack.demo1;

import com.snack.demo1.domin.SnakeBean;
import com.snack.demo1.gamelogic.GameJudge;
import com.snack.demo1.gamelogic.GameMap;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author: HongBo
 * @version: v1.0
 * @description:
 * @date: 2023/07/29 11:38:00
 */

//定义,用于操作传入的当前蛇对象
public class SnakeOperater implements SNAKE_ALL_ARGS {

    private Thread thread = null;

    private boolean threadFlag = false;

    static Graphics graphics= GameMap.myGetGraphics();
    static GameMap gameMap =GameMap.getInStance();
    static GameJudge gameJudge= new GameJudge();

    private SnakeBean snake;

    public SnakeOperater(SnakeBean snack) {
        this.snake = snack;
    }

    public SnakeBean getSnack() {
        return snake;
    }

    public void setSnack(SnakeBean snack) {
        this.snake = snack;
    }

    public boolean isThreadFlag() {
        return threadFlag;
    }

    public void setThreadFlag(boolean threadFlag) {
        this.threadFlag = threadFlag;
    }

    //创建一个新的蛇对象
    public void createANewSnake(){
        snake = new SnakeBean();
        initSnake();
    }

    //初始化蛇
    public  void initSnake() {
        //初始化蛇的身体节点列表
        LinkedList<Point> bodyList = new LinkedList<>();

        // 确定蛇的初始位置 需要先确定图的中间
        int x = ROWSWIDTH / 2;
        int y = COLSHEIGHT / 2;
        bodyList.addFirst(new Point(x - 1, y));
        bodyList.addFirst(new Point(x, y));
        bodyList.addFirst(new Point(x + 1, y));

        //将该节点列表赋值给蛇
        snake.setSnakeList(bodyList);

        //设置基本参数
        snake.setSpeed(300);
        snake.setSnakeDirection(DirectionEnum.RIGHT);

        System.out.println("初始化蛇完毕....");
    }
    //吃到食物时,添加自身节点
    private void addSnake(Point point, DirectionEnum direction) {
        point = GameJudge.changePosition(point, direction);
        this.snake.getSnakeList().addFirst(point);
    }
    //蛇身体移动
    public void snakeMove(DirectionEnum direction) {
        // 获取蛇头坐标
        Point head = snake.getSnakeList().getFirst();
        boolean flag = judgeDirectionValidaton(direction);// 判断是不是执行了反向操作
        if (flag) {
            switch (direction) {
                case UP:
                    // 上
                    addSnake(new Point(head.x, head.y - 1),direction);
                    break;
                case DOWN:
                    // 下
                    addSnake(new Point(head.x, head.y + 1),direction);
                    break;
                case LEFT:
                    // 左
                    addSnake(new Point(head.x - 1, head.y),direction);
                    break;
                case RIGHT:
                    // 右
                    addSnake(new Point(head.x + 1, head.y),direction);
                    break;
                default:
                    break;
            }

            snake.setSnakeDirection(direction);
            GameMap.getInStance().getRepaint();
        }
        // 反向操作的话，就直接不用管
    }
    //判断方向的有效性
    private boolean judgeDirectionValidaton(DirectionEnum nextDirection) {
        DirectionEnum current = snake.getSnakeDirection();
        switch (nextDirection) {
            case UP:
                if (current == DirectionEnum.DOWN) {
                    return false;
                }
                break;
            case DOWN:
                if (current == DirectionEnum.UP) {
                    return false;
                }
                break;
            case LEFT:
                if (current == DirectionEnum.RIGHT) {
                    return false;
                }
                break;
            case RIGHT:
                if (current == DirectionEnum.LEFT) {
                    return false;
                }
                break;
            default:
                break;
        }
        return true;
    }

    //新线程 用来控制自动行走
    private void newThreadForAutoMove() {
        if (!threadFlag) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!(new GameJudge().isGameOver())) {
                        snakeMove(snake.getSnakeDirection());
                        try {
                            thread.sleep(snake.getSpeed());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            thread = null;
                            threadFlag = false;
                            break;
                        }
                    }
                    System.out.println("自動行走線程退出");
                }
            });
            thread.setName("自动运行");
            thread.start();
            threadFlag = true;
        }

    }

    //启动自动行走线程
    public void startAutoMoveThread() {
        newThreadForAutoMove();
    }

    //结束自动行走线程
    public void stopAutoMoveThread() {
        if (thread != null) {
            if (thread.isAlive()) {
                thread.interrupt();
                threadFlag = false;
            }
        }
    }



}
