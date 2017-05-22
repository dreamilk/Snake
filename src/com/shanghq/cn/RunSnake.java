package com.shanghq.cn;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class RunSnake implements KeyListener {
	JFrame frame;
	MyPanel myPanel;
	Snake snake = new Snake();
	Food food;
	boolean action = true;

	public void PaintGui() {
		frame = new JFrame("Snake");
		myPanel = new MyPanel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(myPanel);
		frame.setVisible(true);
		frame.setSize(485, 515);
		frame.setResizable(false);
		frame.addKeyListener(this);
		food = new Food();
		while (action && snake.getDirection() != -1) {
			try {
				Thread.sleep(300 - snake.mList.size() * 10);
				switch (snake.getDirection()) {
				case 0:
					snake.ToUp();
					break;
				case 1:
					snake.ToDown();
					break;
				case 2:
					snake.ToLeft();
					break;
				case 3:
					snake.ToRight();
					break;
				default:
					break;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (snake.mList.getFirst().getX() == food.getX() && snake.mList.getFirst().getY() == food.getY()) {
				snake.mList.addLast(snake.getTail());
				food = new Food();
			}
			myPanel.repaint();
			frame.setTitle("Snake|move speed:" + (int) (200 + snake.mList.size() * 10));
		}
	}

	@SuppressWarnings("serial")
	class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, 500, 500);
			g.setColor(Color.WHITE);
			g.fillRect(15, 15, 450, 450);
			for (Point point : snake.mList) {
				g.setColor(Color.BLACK);
				g.fillRect(point.getX() - 5, point.getY() - 5, 10, 10);
			}
			g.setColor(Color.BLUE);
			g.fillRect(food.getX() - 5, food.getY() - 5, 10, 10);
			g.setColor(Color.GRAY);
			g.setColor(Color.RED);
			g.fillRect(snake.mList.getFirst().getX() - 5, snake.mList.getFirst().getY() - 5, 10, 10);
		}

	}

	public static void main(String[] args) {
		new RunSnake().PaintGui();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			snake.setDirection(snake.TURN_DOWN);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			snake.setDirection(snake.TURN_UP);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			snake.setDirection(snake.TURN_RIGHT);
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			snake.setDirection(snake.TURN_LEFT);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	class Food extends Point {
		public Food() {
			Point point = new Point((int) (Math.random() * 45) * 10 + 20, (int) (Math.random() * 45) * 10 + 20);
			while (check(point)) {
				point = new Point((int) (Math.random() * 45) * 10 + 10, (int) (Math.random() * 45) * 10 + 10);
			}
			;
			this.setX(point.getX());
			this.setY(point.getY());
		}

	}

	private boolean check(Point point) {
		for (int i = 0; i < snake.mList.size(); i++) {
			if (snake.mList.get(i).getX() == point.getX() && snake.mList.get(i).getY() == point.getY()) {
				return true;
			}
		}
		return false;

	}

}
