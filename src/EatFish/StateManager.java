package EatFish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import EatFish.FishGame.DrawGame;

public class StateManager {
	int size;
	Image bgImg, img1, img2, img3, img4;

	public StateManager(Image bgImg, Image img1, Image img2, Image img3, Image img4) {
		this.bgImg = bgImg;
		this.img1 = img1;
		this.img2 = img2;
		this.img3 = img3;
		this.img4 = img4;
	}

	public void drawState(Graphics g, int score, int height, DrawGame game) {
		if (score < 100)
			size = 1;
		else if (score >= 100 && score < 300)
			size = 2;
		else if (score >= 300 && score < 500)
			size = 3;
		else if (score >= 500)
			size = 4;

		g.drawImage(bgImg, 320, 615 - height, game);
		g.drawImage(img1, 385, 625 - height, game);
		if (score >= 100)
			g.drawImage(img2, 440, 615 - height, 505, 675 - height, 0, 0, 180, 180, game);
		if (score >= 300)
			g.drawImage(img3, 490, 615 - height, 585, 675 - height, 0, 0, 250, 175, game);
		if (score >= 500)
			g.drawImage(img4, 570, 625 - height, 680, 655 - height, 0, 30, 375, 140, game);
		g.setColor(Color.orange);

		if (score < 100)
			g.fillRect(396, 672 - height, (int) (score * 2.46), 10);
		else if (score >= 100 && score < 300)
			g.fillRect(396, 672 - height, (int) ((score - 100) * 1.23), 10);
		else if (score >= 300 && score < 500)
			g.fillRect(396, 672 - height, (int) ((score - 300) * 1.23), 10);
		else if (score >= 500)
			g.fillRect(396, 672 - height, 246, 10);
		g.setColor(Color.red);
		Font f = new Font("", Font.BOLD, 20);
		g.setFont(f);
		g.drawString("" + score, 800, 650 - height);

		g.drawString("�ȼ�", 905, 650 - height);

		g.drawString("" + size, 923, 685 - height);

		g.setColor(Color.green);
		g.fillRect(754, 676 - height, size * 26 + 1, 10);

	}
}
