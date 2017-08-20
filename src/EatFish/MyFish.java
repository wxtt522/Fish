package EatFish;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import EatFish.FishGame.DrawGame;

public class MyFish {
	private int x;
	private int y;
	private Image[] img;
	/**
	 * 鱼自身图片的下标
	 */
	int index = 5;
	FishGame game;
	int dir = 0;
	/**
	 * 是否吃到鱼（1.吃到 0.没吃到）
	 */
	int station = 0;
	/**
	 * 鱼的尺寸(0鱼表示等级一时的尺寸，同理1、2、3依次表示后面等级的尺寸)
	 */
	int size = 0;
	int width = 125;
	int higth = 105;
	/**
	 * 鱼说话时图片显示的下标
	 */
	static int wordIndex = 10;// 鱼说话图片下标

	public MyFish(int x, int y, Image img[]) {
		this.x = x;
		this.y = y;
		this.img = img;
	}

	public void drawMyFish(Graphics g, DrawGame drawGame) {
		if (station == 0) {
			if (dir == 0)
				g.drawImage(img[index], x, y, x + this.getWidth(), y + this.getHigth(), 0, 0, 125, 105, drawGame);
			else if (dir == 1)
				g.drawImage(img[index], x, y, x + this.getWidth(), y + this.getHigth(), 125, 0, 0, 105, drawGame);
			index++;

			if (index == 10)
				index = 5;
		} else if (station == 1) {
			if (dir == 0) {
				g.drawImage(img[index], x, y, x + this.getWidth(), y + this.getHigth(), 0, 0, 125, 105, drawGame);
				g.drawImage(img[wordIndex], x + 120, y - 50, drawGame);
				if (index == 5) {
					wordIndex++;
					if (wordIndex == 14)
						wordIndex = 10;
					station = 0;
				}
			} else if (dir == 1) {
				g.drawImage(img[index], x, y, x + width, y + higth, 125, 0, 0, 105, drawGame);
				g.drawImage(img[wordIndex], x - 120, y - 50, drawGame);
				if (index == 5) {
					wordIndex++;
					if (wordIndex == 14)
						wordIndex = 10;
					station = 0;
				}
			}
			index++;

		}
	}

	public int getHigth() {

		return higth;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setWidth(int size) {
		switch (size) {
			case 0:
				this.width = 80;
				break;
			case 1:
				this.width = 160;
				break;
			case 2:
				this.width = 240;
				break;
			case 3:
				this.width = 320;
				break;
			default:
				this.width = 125;
		}
	}

	public void setHigth(int size) {
		switch (size) {
			case 0:
				this.higth = 65;
				break;
			case 1:
				this.higth = 130;
				break;
			case 2:
				this.higth = 195;
				break;
			case 3:
				this.higth = 260;
				break;
			default:
				this.higth = 125;
		}
	}

	public int getWidth() {
		return width;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	public void moveFish(int key) {
		switch (key) {
			case 1://上
				if (y > 0)
					y -= 12;
				break;
			case 2://下
				if (y < 900)
					y += 12;
				break;
			case 3://左
				if (x > 0) {
					dir = 1;
					x -= 12;
				}
				break;
			case 4://右
				if (x < 1180) {
					dir = 0;
					x += 12;
				}
				break;
			case 5://右上
				if (x < 1180 & y > 0) {
					dir = 0;//dir可能是方向，保留左右方向
					x += 8;
					y -= 8;
				}
				break;
			case 6://左上
				if(x>0&y>0){
					dir =1;
					x-=8;
					y-=8;
				}
				break;
			case 7://右下
				if(x<1180&y<900){
					dir=0;
					x+=8;
					y+=8;
				}
				break;
			case 8://左下
				if(x>0&y<900){
					dir=1;
					x-=8;
					y+=8;
				}
				break;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
