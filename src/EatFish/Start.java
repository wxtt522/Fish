package EatFish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import EatFish.FishGame.DrawGame;

public class Start {
	Image img;
	int count = 0;
	FishGame game;

	public Start(Image img) {
		this.img = img;
	}

	public void drawStart(Graphics g, DrawGame game) {
		g.drawImage(img, 0, 0, 1280, 980, game);
		g.setColor(Color.orange);
		g.fillRoundRect(500, 600, count, 20, 15, 15);
		g.setColor(Color.red);
		Font f = new Font("", Font.BOLD, 20);
		g.setFont(f);
		g.drawRoundRect(499, 599, 202, 22, 15, 15);
		count += 5;

	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
