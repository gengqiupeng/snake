package snak;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class MainActivity extends JFrame {

	int dir = 3;
	int myTime = 0;
	static LinkedList<Snake> link;

	static {
		link = new LinkedList<Snake>();
		Snake a = new Snake(120, 60);
		Snake b = new Snake(90, 60);
		Snake c = new Snake(60, 60);
		link.add(a);
		link.add(b);
		link.add(c);
		getL();
	}

	Snake food = new Snake(150, 150);
	// 计时器
	Timer time = new Timer();

	public MainActivity() {
		super("贪吃蛇");
		this.setSize(1000, 800);// 设置窗口大小
		setVisible(true);// 显示窗口
		// this.setTitle("贪吃蛇");
		time.schedule(new MyTimerTask(this), 1000, 300);
		creatFood();
		// this.addKeyListener(new MyListener(this));//外部类调用
		// this.addKeyListener(new InnerListener());//内部类调用
		this.addKeyListener(new KeyAdapter() {// 匿名类调用
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case 37:
					dir = 1;
					break;
				case 38:
					dir = 2;
					break;
				case 39:
					dir = 3;
					break;
				case 40:
					dir = 4;
					break;
				default:
					break;
				}
			}
		});

	}

	class InnerListener extends KeyAdapter {// 内部类调用
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case 37:
				dir = 1;
				break;
			case 38:
				dir = 2;
				break;
			case 39:
				dir = 3;
				break;
			case 40:
				dir = 4;
				break;
			default:
				break;
			}
		}
	}

	@Override // 重写父类方法,绘图
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 1000, 800);
		// 绘制蛇
		for (Snake sk : link) {
			g.setColor(Color.black);
			g.fillRect(sk.x, sk.y, 30, 30);
		}

		g.setColor(Color.green);
		g.fillRect(food.x, food.y, 30, 30);

	}

	// 创建食物
	public void creatFood() {
		food.x = (int) (Math.random() * 500 / 30 * 30 + 30);
		food.y = (int) (Math.random() * 400 / 30 * 30 + 30);
	}

	// 吃食物
	public void eat(Snake newHead) {
		if (link.getFirst().equals(food)) {
			link.addFirst(newHead);
			creatFood();
			store();
		} else {
			link.addFirst(newHead);
			link.removeLast();
		}
		repaint();

	}

	// 得分存储和获取
	public void store() {
		File file = new File("d:/snake.txt");
		try {
			file.createNewFile();
			FileWriter writer = new FileWriter(new File("d:/snake.txt"));
			writer.write(link.size());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getL() {
		File f = new File("d:/snake.txt");
		try {
			FileReader rea = new FileReader(f);
			int x = rea.read();
			System.out.println(x);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void guoGuan() {
		myTime = myTime + 300;
		if (myTime < 6000 && link.size() >= 4) {
			System.out.println("恭喜过关");
		} else if (myTime > 6000 && link.size() < 4) {
			System.out.println("失败，请从来");
		} else {
			// System.out.println(myTime);
		}
	}

	public static void main(String[] args) {
		new MainActivity();

	}

	// 任务类
	class MyTimerTask extends TimerTask {
		MainActivity ma;

		public MyTimerTask(MainActivity ma) {
			this.ma = ma;
		}

		@Override
		public void run() {
			int x = MainActivity.link.getFirst().x;
			int y = MainActivity.link.getFirst().y;
			switch (dir) {
			case 1:
				x -= 30;
				break;
			case 2:
				y -= 30;
				break;
			case 3:
				x += 30;
				break;
			case 4:
				y += 30;
				break;
			default:
				break;
			}
			Snake newHead = new Snake(x, y);
			eat(newHead);
			ma.guoGuan();
			// ma.repaint();
		}
	}

}
