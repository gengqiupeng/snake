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
	// ��ʱ��
	Timer time = new Timer();

	public MainActivity() {
		super("̰����");
		this.setSize(1000, 800);// ���ô��ڴ�С
		setVisible(true);// ��ʾ����
		// this.setTitle("̰����");
		time.schedule(new MyTimerTask(this), 1000, 300);
		creatFood();
		// this.addKeyListener(new MyListener(this));//�ⲿ�����
		// this.addKeyListener(new InnerListener());//�ڲ������
		this.addKeyListener(new KeyAdapter() {// ���������
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

	class InnerListener extends KeyAdapter {// �ڲ������
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

	@Override // ��д���෽��,��ͼ
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, 1000, 800);
		// ������
		for (Snake sk : link) {
			g.setColor(Color.black);
			g.fillRect(sk.x, sk.y, 30, 30);
		}

		g.setColor(Color.green);
		g.fillRect(food.x, food.y, 30, 30);

	}

	// ����ʳ��
	public void creatFood() {
		food.x = (int) (Math.random() * 500 / 30 * 30 + 30);
		food.y = (int) (Math.random() * 400 / 30 * 30 + 30);
	}

	// ��ʳ��
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

	// �÷ִ洢�ͻ�ȡ
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
			System.out.println("��ϲ����");
		} else if (myTime > 6000 && link.size() < 4) {
			System.out.println("ʧ�ܣ������");
		} else {
			// System.out.println(myTime);
		}
	}

	public static void main(String[] args) {
		new MainActivity();

	}

	// ������
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
