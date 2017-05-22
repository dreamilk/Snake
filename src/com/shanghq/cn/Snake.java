package com.shanghq.cn;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Snake {
	LinkedList<Point> mList = new LinkedList<>();
	private Point point;
	private int direction;
	public final int TURN_UP = 0;
	public final int TURN_DOWN = 1;
	public final int TURN_LEFT = 2;
	public final int TURN_RIGHT = 3;
	private Point tail;
	public Point head;

	public Snake() {
		direction = TURN_RIGHT;
		point = new Point((int) (Math.random() * 10) * 10 + 20, (int) (Math.random() * 10) * 10 + 20);
		mList.add(point);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void ToLeft() {
		direction = TURN_LEFT;
		head = mList.getFirst();
		point = new Point(head.getX() - 10, head.getY());
		gogo(point);
	}

	public void ToRight() {
		direction = TURN_RIGHT;
		head = mList.getFirst();
		point = new Point(head.getX() + 10, head.getY());
		gogo(point);
	}

	public void ToUp() {
		direction = TURN_UP;
		head = mList.getFirst();
		point = new Point(head.getX(), head.getY() - 10);
		gogo(point);
	}

	public void ToDown() {
		direction = TURN_DOWN;
		head = mList.getFirst();
		point = new Point(head.getX(), head.getY() + 10);
		gogo(point);
	}

	public void gogo(Point point) {
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i).getX() == point.getX() && mList.get(i).getY() == point.getY()) {
				JOptionPane.showMessageDialog(null, "game over! score:" + mList.size(), "you eat yourself!",
						JOptionPane.ERROR_MESSAGE);
				direction = -1;
			}
		}
		if (point.getX() < 15 || point.getX() > 465) {
			JOptionPane.showMessageDialog(null, "game over! score:" + mList.size(), "you cash the wall",
					JOptionPane.ERROR_MESSAGE);
			direction = -1;
		} else if (point.getY() < 15 || point.getY() > 465) {
			JOptionPane.showMessageDialog(null, "game over! score:" + mList.size(), "you cash the wall",
					JOptionPane.ERROR_MESSAGE);
			direction = -1;
		} else {
			mList.addFirst(point);
			tail = mList.removeLast();
		}
	}

	public Point getTail() {
		return tail;
	}

}
