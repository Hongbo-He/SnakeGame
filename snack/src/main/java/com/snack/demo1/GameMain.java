package com.snack.demo1;

import com.snack.demo1.domin.SnakeBean;
import com.snack.demo1.gamelogic.GameMap;
import com.snack.demo1.ui.GameFrame;
import com.snack.demo1.utils.CreateMenuItem;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;


public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L;
    public static JFrame jFrame = null;
    private static GameMain gameMain;
    public static SnakeOperater snakeOperater = null; //蛇的控制器
    public static int currentDirection = -2;

    private GameMain() {
    }

    public static GameMain getinStance() {
        if (gameMain == null) {
            gameMain = new GameMain();
        }
        return gameMain;
    }
    private static GameMap gameMap = GameMap.getInStance();


    //游戏入口
    public static void main(String[] args) {
        jFrame = new JFrame("贪吃蛇");        //初始化Frame框架
        settingMenu();          //把菜单加入Frame


        jFrame.add(gameMap);    //把地图加到框架中
        jFrame.setResizable(false);
        GameFrame.initFrame(jFrame);

        listener(jFrame);//设置监听事件

        //生成一个默认的蛇对象
        snakeOperater = new SnakeOperater(new SnakeBean());        //一个蛇的控制器
        snakeOperater.initSnake();//调用初始化蛇的方法

        gameMap.initMap();      //初始化地图

        Food.createFood();//初始化食物

    }

    //设置按键监听
    public static void listener(JFrame jFrame) {
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        snakeOperater.snakeMove(DirectionEnum.UP);
                        System.out.println("上");
                        break;
                    case KeyEvent.VK_DOWN:
//                        snake.snakeMove(-1, 1);
                        System.out.println("下");
                        snakeOperater.snakeMove(DirectionEnum.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
//                        snake.snakeMove(2, 1);
                        System.out.println("左");
                        snakeOperater.snakeMove(DirectionEnum.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
//                        snake.snakeMove(-2, 1);
                        System.out.println("右");
                        snakeOperater.snakeMove(DirectionEnum.RIGHT);
                        break;
                    default:
                        //未知按鍵,不響應
                        break;
                }
            }
        });
    }

    //设置菜单
    public static void settingMenu() {
        JMenuBar menuBar = new JMenuBar();
        String jMenuString1 = "开始";
        String[] startGameITEMString1 = new String[]{"开始游戏"};
        CreateMenuItem.initJMenu(jFrame, menuBar, jMenuString1, startGameITEMString1);

        String jMenuString2 = "关于";
        String[] jMenuItemString1 = new String[]{"帮助", "版本信息"};
        CreateMenuItem.initJMenu(jFrame, menuBar, jMenuString2, jMenuItemString1);

        jFrame.add(menuBar, BorderLayout.NORTH);
    }
}
