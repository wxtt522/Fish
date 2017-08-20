package EatFish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class RankView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<PlayerInfo> list = new ArrayList<PlayerInfo>();
	Image fishbg;

	/**
	 * 排行榜页面
	 */
	public RankView() {
		super("排行榜");

		list = PlayerInfo.getInfo();
		try {
			fishbg = ImageIO.read(FishGame.class.getResource("fishbg.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setSize(200, 300);
		this.setLocation(500, 200);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(fishbg, 0, 0, this);
		Font f = new Font("", Font.BOLD, 20);
		g.setColor(Color.red);
		g.setFont(f);
		String s = "名次   姓 名   分数";
		g.drawString(s, 10, 50);

		Font f1 = new Font("", Font.BOLD, 20);
		g.setColor(Color.blue);
		g.setFont(f1);
		for (int i = 0; i < list.size(); i++) {
			int a = i + 1;
			g.drawString(a + "", 25, 53 + 19 * a);
			g.drawString(list.get(i).getName(), 65, 50 + 19 * a);
			g.drawString(list.get(i).getScore() + "", 140, 53 + 19 * a);
		}
	}

	public static void main(String[] args) {
		new RankView();

	}
}
