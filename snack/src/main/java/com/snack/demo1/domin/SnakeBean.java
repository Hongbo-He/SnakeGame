package com.snack.demo1.domin;

import com.snack.demo1.DirectionEnum;

import java.awt.*;
import java.util.LinkedList;

/**
 * @author: HongBo
 * @version: v1.0
 * @description:
 * @date: 2023/07/29 11:20:00
 */

public class SnakeBean {
    //蛇的头部朝向
    private DirectionEnum snakeDirection = DirectionEnum.RIGHT;
    //蛇的身体节点
    private LinkedList<Point> snakeList =null;

    //蛇的移动速度
    private int speed;

    public SnakeBean() {}

    public SnakeBean(DirectionEnum snakeDirection, LinkedList<Point> snakeList) {
        this.snakeDirection = snakeDirection;
        this.snakeList = snakeList;
    }

    public DirectionEnum getSnakeDirection() {
        return snakeDirection;
    }

    public void setSnakeDirection(DirectionEnum snakeDirection) {
        this.snakeDirection = snakeDirection;
    }

    public LinkedList<Point> getSnakeList() {
        return snakeList;
    }

    public void setSnakeList(LinkedList<Point> snakeList) {
        this.snakeList = snakeList;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
