package EatFish;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javazoom.jl.player.Player;

public class FishGame extends JFrame implements Runnable, KeyListener, ActionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Image bgImag, startImg, logoImg, hudImg;
	Image myFish[] = new Image[14];
	Image eatedFish[] = new Image[20];
	Image fish1Img[] = new Image[5];
	Image fish2Img[] = new Image[5];
	Image fish3Img[] = new Image[5];
	Image fish4Img[] = new Image[5];
	MyFish mf;
	LinkedList<Fish> eFish = new LinkedList<Fish>();
	int x = 0;
	BufferedImage buf;
	Graphics g1;
	int score = 0;
	/**
	 * 游戏结束标志
	 */
	boolean flag = true;
	int dct = 0;// 键值代号
	boolean U = false, D = false, L = false, R = false;// 用布尔值来判定myfish的移动，用以解决键盘延迟问题
	JMenuBar bar;
	JMenu menu;
	JMenuItem startItem, pauseItem, continueItem, chengjiItem, closeItem, explainItem, heightItem, rankItem;
	boolean begin = false;
	Start st;
	StateManager manager;
	int time = 64;
	boolean setTime = false;
	/**
	 * 保存分数标志
	 */
	boolean record = false;
	int height = 0;
	/**
	 * 排行榜中的玩家记录
	 */
	List<PlayerInfo> playerList = null;

	public FishGame() throws IOException {
		for (int i = 0; i < 5; i++) {
			myFish[i] = ImageIO.read(FishGame.class.getResource("aer" + (i + 1) + ".png"));
			myFish[i + 5] = ImageIO.read(FishGame.class.getResource("asr" + (i + 1) + ".png"));
			fish1Img[i] = ImageIO.read(FishGame.class.getResource("bsr" + (i + 1) + ".png"));
			fish2Img[i] = ImageIO.read(FishGame.class.getResource("jsr" + (i + 1) + ".png"));
			fish3Img[i] = ImageIO.read(FishGame.class.getResource("msr" + (i + 1) + ".png"));
			fish4Img[i] = ImageIO.read(FishGame.class.getResource("ssr" + (i + 1) + ".png"));
		}
		for (int i = 10; i < 14; i++) {
			myFish[i] = ImageIO.read(FishGame.class.getResource("pic" + (i - 9) + ".png"));
		}
		bgImag = ImageIO.read(FishGame.class.getResource("back.jpg"));
		startImg = ImageIO.read(FishGame.class.getResource("load.jpg"));
		logoImg = ImageIO.read(FishGame.class.getResource("logo.png"));
		hudImg = ImageIO.read(FishGame.class.getResource("hudbg.jpg"));
		st = new Start(startImg);

		manager = new StateManager(hudImg, fish1Img[1], fish2Img[1], fish3Img[1], fish4Img[1]);
		mf = new MyFish(300, 300, myFish);

		buf = new BufferedImage(1280, 960, BufferedImage.TYPE_INT_BGR);
		g1 = buf.getGraphics();

		bar = new JMenuBar();
		menu = new JMenu("操作");
		startItem = new JMenuItem("开始新的游戏");
		pauseItem = new JMenuItem("暂停游戏");
		continueItem = new JMenuItem("继续游戏");
		explainItem = new JMenuItem("游戏说明");
		heightItem = new JMenuItem("调整状态栏位置");
		chengjiItem = new JMenuItem("保存成绩");
		rankItem = new JMenuItem("查看排行榜");
		closeItem = new JMenuItem("退出");
		startItem.addActionListener(this);
		pauseItem.addActionListener(this);
		continueItem.addActionListener(this);
		explainItem.addActionListener(this);
		heightItem.addActionListener(this);
		closeItem.addActionListener(this);
		chengjiItem.addActionListener(this);
		rankItem.addActionListener(this);
		menu.add(startItem);
		menu.add(pauseItem);
		menu.add(continueItem);
		menu.add(explainItem);
		menu.add(heightItem);
		menu.add(chengjiItem);
		menu.add(rankItem);
		menu.add(closeItem);

		bar.add(menu);

		playerList = PlayerInfo.getInfo();

		this.add(bar, BorderLayout.NORTH);
		this.add(new DrawGame(), BorderLayout.CENTER);

		new Thread(this).start();
		this.addKeyListener(this);
		this.setSize(1280, 1024);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

		new Thread(new Runnable() {
			//背景音乐
			@Override
			public void run() {
				play("EatFish/bg2.mp3");

			}
		}).start();
	}

	public static void main(String[] args) {
		try {
			new FishGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class DrawGame extends JPanel {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g) {
			if (begin == false) {
				g.drawImage(bgImag, 0, 0, this);
				g.drawImage(logoImg, 500, 100, this);
			} else if (begin == true) {
				if (st.count <= 200) {
					st.drawStart(g1, this);
					g.drawImage(buf, 0, 0, this);
				} else {
					g1.drawImage(bgImag, 0, 0, this);
					mf.drawMyFish(g1, this);
					for (int i = 0; i < eFish.size(); i++) {
						eFish.get(i).drawFish(g1, this);
						if (eFish.get(i).isHit(mf.getX(), mf.getY(), mf.getSize())) {
							if (eFish.get(i).isEat(mf.getSize())) {
								score += eFish.get(i).getScore();
								// 加个音效试试看
                                new Thread(() -> play("EatFish/game_eat.mp3")).start();
								eFish.remove(i);
								mf.setIndex(0);
								mf.setStation(1);
							} else{
								play("EatFish/ouch.wav");
								flag = false;
							}
						} else if (eFish.get(i).isToEnd()) // 鱼到边缘处从链表中清理出去
							eFish.remove(i);

					}

					Font f2 = new Font("", Font.BOLD, 30);
					g1.setColor(Color.red);
					g1.setFont(f2);
					g1.drawString("剩余时间: " + time, 500, 100);

					manager.drawState(g1, score, height, this);
					g.drawImage(buf, 0, 0, this);
					if (flag == false || time == 0) {
						Font f1 = new Font("", Font.BOLD, 80);
						g.setColor(Color.red);
						g.setFont(f1);
						g.drawString("GAME OVER！！！", 300, 400);
						flag = false;
					}
					if (score >= 1000) {
						Font f3 = new Font("", Font.BOLD, 80);
						g.setColor(Color.red);
						g.setFont(f3);
						g.drawString("恭喜您通关啦！！！", 300, 400);
						flag = false;
					}
					if (flag == false)
						record = true;

					mf.moveFish(dct);// 移动
				}
			}
		}
	}

	// 播放音乐
	public void play(String filename) {
		try {
			ClassLoader loader = FishGame.class.getClassLoader();
			InputStream is = loader.getResourceAsStream(filename);
			BufferedInputStream buffer = new BufferedInputStream(is);
			Player player = new Player(buffer);
			player.play();
		} catch (Exception e) {
			e.printStackTrace();;
		}
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (begin == true && flag == true) {
				x++;
				if (x % 15 == 0) {
					creatNewFish();
				}
				if (x % 10 == 0)
					time--;
				changeMyFishSize();
				changeTime();
				repaint();

			}

		}
	}

	public void changeTime() {
		if (score >= 100 && score < 300 && setTime) {
			time = 60;
			setTime = false;
		}
		if (score >= 300 && score < 500 && setTime) {
			time = 40;
			setTime = false;
		}
		if (score >= 500 && setTime) {
			time = 30;
			setTime = false;
		}
	}

	public void changeMyFishSize() {
		int k = mf.getSize();
		if (score < 100) {
			mf.setSize(0);
			mf.setHigth(0);
			mf.setWidth(0);
		} else if (score >= 100 && score < 300) {
			mf.setSize(1);
			mf.setHigth(1);
			mf.setWidth(1);
		} else if (score >= 300 && score < 500) {
			mf.setSize(2);
			mf.setHigth(2);
			mf.setWidth(2);
		} else if (score >= 500) {
			mf.setSize(3);
			mf.setHigth(3);
			mf.setWidth(3);
		}
		if (k < mf.getSize());
		setTime = true;
	}

	public void creatNewFish() {// 随机产生不同速度、种类、初始位置的鱼
		int dir = (int) (Math.random() * 2);
		int fx;
		if (dir == 0)
			fx = -200;
		else
			fx = 1280;
		int fy = (int) (Math.random() * 800);// y坐标随机产生
		int index; // 当前产生鱼的种类数
		if (score < 100)
			index = (int) (Math.random() * 2 + 1);
		else if (score >= 100 & score < 300)
			index = (int) (Math.random() * 3 + 1);
		else
			index = (int) (Math.random() * 4 + 1);
		int speed = (int) (Math.random() * 10 + 3);
		Fish newFish;
		switch (index) {
			case 1:
				newFish = new Fish1(fx, fy, speed, fish1Img);
				newFish.setDir(dir);
				eFish.add(newFish);
				break;
			case 2:
				newFish = new Fish2(fx, fy, speed, fish2Img);
				newFish.setDir(dir);
				eFish.add(newFish);
				break;
			case 3:
				newFish = new Fish3(fx, fy, speed, fish3Img);
				newFish.setDir(dir);
				eFish.add(newFish);
				break;
			case 4:
				newFish = new Fish4(fx, fy, speed, fish4Img);
				newFish.setDir(dir);
				eFish.add(newFish);
				break;
		}
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				L = true;
				break;
			case KeyEvent.VK_UP:
				U = true;
				break;
			case KeyEvent.VK_RIGHT:
				R = true;
				break;
			case KeyEvent.VK_DOWN:
				D = true;
				break;
		}
		getDirection();
	}

	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				L = false;
				break;
			case KeyEvent.VK_UP:
				U = false;
				break;
			case KeyEvent.VK_RIGHT:
				R = false;
				break;
			case KeyEvent.VK_DOWN:
				D = false;
				break;
		}
		getDirection();

	}

	public void keyTyped(KeyEvent e) {
	}

	public void getDirection() {// 多加几个方向溜得快
		if (U && !L && !R && !D)
			dct = 1;
		else if (D && !U && !R && !L)
			dct = 2;
		else if (L && !U && !R && !D)
			dct = 3;
		else if (R && !U && !L && !D)
			dct = 4;
		else if (R && U && !L && !D)
			dct = 5;// 右上
		else if (!R && U && L && !D)
			dct = 6;// 左上
		else if (R && !U && !L && D)
			dct = 7;// 右上
		else if (!R && !U && L && D)
			dct = 8;// 左下
		else if (!L && !U && !R && !D)
			dct = 0;

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startItem) {
			begin = true;
			flag = true; // 游戏结束后重启的标志
			st.setCount(0);// 显示登陆动画
			eFish.clear(); // 清除链表所有元素
			score = 0;
			mf.setSize(0);
			time = 64;
		} else if (e.getSource() == pauseItem)
			flag = false;
		else if (e.getSource() == continueItem)
			flag = true;
		else if (e.getSource() == explainItem)
			JOptionPane.showMessageDialog(this, "要在既定的时间段内升到下一级，游戏内的状态栏会清楚显示当前鱼的状态" + "\n"
					+ "                                            祝您游戏愉快！！！");
		else if (e.getSource() == heightItem) {
			int k = Integer.parseInt(JOptionPane.showInputDialog(this, "请输入要上调的距离,要求为整数(负数为下调),单位为1像素点"));
			height += k;
		} else if (e.getSource() == closeItem)
			System.exit(0);
		else if (e.getSource() == rankItem) {
			new RankView();
		} else if (e.getSource() == chengjiItem) {
			if (flag == false && record == true) {
				if (playerList == null || playerList.size() < 10
						|| playerList.get(playerList.size() - 1).getScore() < score) {
					String sname = JOptionPane.showInputDialog(this, "恭喜你已进入排行榜，请输入您的大名：");
					if (sname.length() > 3)
						sname = sname.substring(0, 3) + "..";
					System.out.println(sname + score);
					PlayerInfo.addPlayer(new PlayerInfo(sname, score));
				} else
					JOptionPane.showMessageDialog(this, "您还要多多加油才行~");
			} else
				JOptionPane.showMessageDialog(this, "游戏还没结束呦~");
		}
	}
}
