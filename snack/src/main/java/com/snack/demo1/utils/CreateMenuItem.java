package com.snack.demo1.utils;

import com.snack.demo1.Food;
import com.snack.demo1.gamelogic.GameJudge;
import com.snack.demo1.GameMain;
import com.snack.demo1.SNAKE_ALL_ARGS;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;


public class CreateMenuItem implements SNAKE_ALL_ARGS {
		
	
	public static void initJMenu(JFrame jFrame,JMenuBar jMenuBar,String jMenuString,String[] jMenuItemString) {
		//JMenu有多少个就循环多少次

			JMenu jMenu =new JMenu(jMenuString);
			for (int j = 0; j < jMenuItemString.length; j++) {
				JMenuItem jMenuItem =new JMenuItem(jMenuItemString[j]);
				switch (jMenuString) {
				case "开始":
					initStartGame(jMenuItem, jFrame,j);
					break;
				case "关于":
					initAbout(jMenuItem, jFrame, j);
					break;
				case "历史成绩":
					initSettingMenuEvent(jMenuItem, jFrame, j);
					break;
				default:
					break;
				}
				
				jMenu.add(jMenuItem);
			}
			jMenuBar.add(jMenu);
			jFrame.add(jMenuBar);
	}
	
	//处理 开始菜单下的事件
		public static void initStartGame(JMenuItem jMenuItem, JFrame jFrame, int id) {
		
		jMenuItem.addActionListener((new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (id) {
				case 0:
					//开始游戏的逻辑

					//初始化蛇
					GameJudge.isGameOverFlag =false;
					GameMain.snakeOperater.initSnake();
					//初始化食物
					Food.createFood();
					GameMain.snakeOperater.setThreadFlag(false);
					GameMain.snakeOperater.startAutoMoveThread();
					break;
				default:
					break;
				}
			}
		}));
}
	//这个用于处理 设置 菜单下的事件
		public static void initSettingMenuEvent(JMenuItem jMenuItem,JFrame jFrame,int id) {
			jMenuItem.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					switch (id) {
					case 0:
					
						break;

					default:
						break;
					}
				}
			});
		}
	//处理 关于菜单下的事件
		public static void initAbout(JMenuItem jMenuItem,JFrame jFrame,int id) {
			jMenuItem.addActionListener((new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					switch (id) {
					case 0:
						JFrame helpjFrame =new JFrame("游戏帮助");
						ScrollPane scrollPane =new ScrollPane();
						JTextArea textField=new JTextArea(30,20);
						textField.setFont(font);
						textField.setEditable(false);
						textField.setLineWrap(true);
						textField.append("一、游戏说明\r\n");
						textField.append("    1.游戏操作 ：\r\n    点击 开始 中的 开始游戏，然后按下 任意按键 就可以开始游戏。上下左右控制蛇运动\r\n\r\n");
						textField.append("    2.游戏速度介绍:\r\n    蛇初始速度为0.3秒运动一下，每吃一个食物速度加快0.05秒，直到蛇每0.01秒运动一下时，才不会减少。");
						textField.append("         \r\n速度计算公式: V=300-5*X \r\n ");
						textField.append("             X：吃到的食物数量  \r\n  ");
						textField.append("             速度单位:毫秒/格 ");
						
						helpjFrame.add(scrollPane);
						scrollPane.add(textField);
						
						
						FrameUtil.initFrameWithOutExit(helpjFrame,400,300);
//						GameMain.listener(jFrame);
						break;
					case 1:
						JFrame versionjFrame =new JFrame("游戏版本");
						JTextArea versiontextField=new JTextArea(20, 30);
						ScrollPane versionscrollPane =new ScrollPane();
						versiontextField.setFont(font);
						versiontextField.setEditable(false);
						versiontextField.setLineWrap(true);
						versiontextField.append("版本号 ：v1.0.1 \r\n");
						versiontextField.append("当前版本说明：\r\n    1.游戏结束后，点击重新开始或者取消后，游戏重新开始时会由于之前线程没有关闭，导致点击了之后蛇就会马上运动，暂时没有处理 \r\n");
						versiontextField.append("\r\n    2.由于多线程操作蛇的移动，所以当按下按钮时可能蛇会自动移动，这时会出现按一下蛇移动了两个格子的感觉，暂时没有处理 ");
						versiontextField.append("\r\n    3.由于多线程操作蛇的移动，按键速度过快蛇可能会暴毙，多线程不熟悉，暂时修复不了。\r\n\r\n");
						versiontextField.append("日期： 2019年9月20日14:10:28");

						versiontextField.append("\r\n\r\n");
						versiontextField.append("bug修复: ");
						versiontextField.append("\r\n 1.修复死亡后点击重新开始时无法正常启动游戏");
						versiontextField.append("\r\n 1.修复死亡后点击开始后按下一次按钮蛇移动多次的问题");
						versiontextField.append("日期：2023年7月29日10:04:49");

						versionscrollPane.add(versiontextField);
						versionjFrame.add(versionscrollPane);
						FrameUtil.initFrameWithOutExit(versionjFrame,500,300);
//						GameMain.listener(jFrame);
						break;
					default:
						break;
					}
				}
			}));
		}
}		
