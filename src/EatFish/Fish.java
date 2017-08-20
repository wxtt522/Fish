package EatFish;

import java.awt.Graphics;
import java.awt.Image;

import EatFish.FishGame.DrawGame;

public class Fish {
	private int x;
	private int y;
	private int speed;
	int index = 0;
	FishGame game;
	Image img[];
	int dir = 0;
	int score = 0;

	public Fish(int x, int y, int speed, Image img[]) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.img = img;
	}

	public void drawFish(Graphics g, DrawGame drawGame) {

	}

	public boolean isHit(int m, int n, int size) {
		return false;
	}

	public boolean isEat(int size) {
		return false;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getX() {
		return x;
	}

	public int getScore() {
		return score;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isToEnd() {
		if (dir == 0 && this.getX() >= 1200)
			return true;
		else if (dir == 1 && this.getX() <= 0)
			return true;
		return false;
	}

}

class Fish1 extends Fish {
	int index = 0;

	public Fish1(int x, int y, int speed, Image[] img) {
		super(x, y, speed, img);
	}

	public void drawFish(Graphics g, DrawGame drawGame) {
		if (index < 5) {
			if (dir == 0) {
				g.drawImage(img[index], this.getX(), this.getY(), game);
				this.setX(this.getX() + this.getSpeed());
			} else if (dir == 1) {
				g.drawImage(img[index], this.getX(), this.getY(), this.getX() + 65, this.getY() + 48, 65, 0, 0, 48,
						game);
				this.setX(this.getX() - this.getSpeed());
			}
			index++;
			if (index == 5)
				index = 0;
		}
	}

	public boolean isHit(int m, int n, int size) {
		if (this.getX() + 65 > m + 20 && this.getX() < m + 70 * (size + 1) && this.getY() + 48 > n + 20
				&& this.getY() < n + 50 * (size + 1))
			return true;
		return false;
	}

	public boolean isEat(int size) {
		return true;
	}

	public int getScore() {
		return 10;
	}
}

class Fish2 extends Fish {

	public Fish2(int x, int y, int speed, Image[] img) {
		super(x, y, speed, img);
	}

	public void drawFish(Graphics g, DrawGame drawGame) {
		if (index < 5) {
			if (dir == 0) {
				g.drawImage(img[index], this.getX(), this.getY(), game);
				this.setX(this.getX() + this.getSpeed());
			} else if (dir == 1) {
				g.drawImage(img[index], this.getX(), this.getY(), this.getX() + 180, this.getY() + 180, 180, 0, 0, 180,
						game);
				this.setX(this.getX() - this.getSpeed());
			}
			index++;
			if (index == 5)
				index = 0;
		}
	}

	public boolean isHit(int m, int n, int size) {

		if (this.getX() + 150 > m + 15 && this.getX() + 30 < m + 65 * (size + 1) && this.getY() + 130 > n + 20
				&& this.getY() + 30 < n + 50 * (size + 1))
			return true;
		return false;
	}

	public boolean isEat(int size) {
		if (size >= 1)
			return true;
		return false;
	}

	public int getScore() {
		return 15;
	}
}

class Fish3 extends Fish {

	public Fish3(int x, int y, int speed, Image[] img) {
		super(x, y, speed, img);
	}

	public void drawFish(Graphics g, DrawGame drawGame) {
		if (index < 5) {
			if (dir == 0) {
				g.drawImage(img[index], this.getX(), this.getY(), game);
				this.setX(this.getX() + this.getSpeed());
			} else if (dir == 1) {
				g.drawImage(img[index], this.getX(), this.getY(), this.getX() + 250, this.getY() + 175, 250, 0, 0, 175,
						game);
				this.setX(this.getX() - this.getSpeed());
			}
			index++;
			if (index == 5)
				index = 0;
		}
	}

	public boolean isHit(int m, int n, int size) {
		if (this.getX() + 220 > m + 30 && this.getX() + 30 < m + 65 * (size + 1) && this.getY() + 140 > n + 30
				&& this.getY() + 30 < n + 50 * (size + 1))
			return true;
		return false;
	}

	public boolean isEat(int size) {
		if (size >= 2)
			return true;
		return false;
	}

	public int getScore() {
		return 30;
	}
}

class Fish4 extends Fish {

	public Fish4(int x, int y, int speed, Image[] img) {
		super(x, y, speed, img);
	}

	public void drawFish(Graphics g, DrawGame drawGame) {
		if (index < 5) {
			if (dir == 0) {
				g.drawImage(img[index], this.getX(), this.getY(), game);
				this.setX(this.getX() + this.getSpeed());
			} else if (dir == 1) {
				g.drawImage(img[index], this.getX(), this.getY(), this.getX() + 375, this.getY() + 140, 375, 0, 0, 140,
						game);
				this.setX(this.getX() - this.getSpeed());
			}
			index++;
			if (index == 5)
				index = 0;
		}
	}

	public boolean isEat(int size) {
		if (size == 3)
			return true;
		return false;
	}

	public boolean isHit(int m, int n, int size) {
		if (this.getX() + 300 > m + 60 && this.getX() + 40 < m + 65 * (size + 1) && this.getY() + 100 > n + 30
				&& this.getY() + 30 < n + 50 * (size + 1))
			return true;
		return false;
	}

	public int getScore() {
		return 50;
	}

}