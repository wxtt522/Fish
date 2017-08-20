package EatFish;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;

public class PlayerInfo {

	private String name;
	private int score;

	public PlayerInfo(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public PlayerInfo() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public static List<PlayerInfo> getInfo() {
		List<PlayerInfo> list = new LinkedList<PlayerInfo>();
		FileInputStream fin = null;
		DataInputStream din = null;
		File file = new File("D:\\info.txt");
		String pname;
		try {
			fin = new FileInputStream(file);
			din = new DataInputStream(fin);

			for (int i = 0; i < 10; i++) {
				PlayerInfo p = new PlayerInfo();
				pname = din.readUTF();
				if (pname == null)
					break;
				p.setName(pname);
				p.setScore(din.readInt());
				list.add(p);

			}
			din.close();
			fin.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block

		} finally {
			try {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return list;
	}

	private static void storeInfo(List<PlayerInfo> list) {
		try {
			FileOutputStream fos = new FileOutputStream("D:\\info.txt");
			DataOutputStream dos = new DataOutputStream(fos);
			for (int i = 0; i < list.size(); i++) {
				dos.writeUTF(list.get(i).getName());
				dos.writeInt(list.get(i).getScore());
			}
			dos.flush();
			dos.close();
			fos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addPlayer(PlayerInfo p) {

		List<PlayerInfo> plist = null;
		System.out.println("1");

		plist = PlayerInfo.getInfo();

		System.out.println("2");
		if (plist.size() == 0) {
			System.out.println(p.getName() + p.getScore());
			plist = new LinkedList<PlayerInfo>();
			plist.add(p);
		} else if (plist.size() < 10 && plist.size() > 0) {
			for (int i = 0; i <= plist.size() - 1; i++) {
				System.out.println(p.getName() + p.getScore());
				if (i == 0 && p.getScore() > plist.get(i).getScore()) {
					plist.add(i, p);
					break;
				} else if (plist.get(plist.size() - 1).getScore() >= p.getScore()) {
					plist.add(p);
					break;
				} else if (plist.get(i + 1).getScore() < p.getScore() && p.getScore() <= plist.get(i).getScore()) {
					plist.add(i + 1, p);
					break;
				}
			}
		} else {
			for (int i = 0; i < plist.size() - 1; i++) {
				if (i == 0 && p.getScore() > plist.get(i).getScore()) {
					plist.add(i, p);
					plist.remove(plist.size() - 1);
					break;
				} else if (plist.get(i + 1).getScore() < p.getScore() && p.getScore() <= plist.get(i).getScore()) {
					plist.add(i + 1, p);
					plist.remove(plist.size() - 1);
					break;
				}
			}
		}
		PlayerInfo.storeInfo(plist);
	}

}
