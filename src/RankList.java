import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.filechooser.FileSystemView;

public class RankList {
	public static String path;

	public static void main(String[] args) {
		System.out.println("读取数据...");
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com = fsv.getHomeDirectory(); // 这便是读取桌面路径的方法了
		path = com.getPath() + "\\RankList";
		/* 读取数据 */
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(new File(path + "\\1.txt")), "UTF-8"));
			String lineTxt = null;

			while ((lineTxt = br.readLine()) != null) {
				buffer.append(lineTxt);
			}
			br.close();
		} catch (Exception e) {
			System.err.println("read errors :" + e);
		}
		System.out.println("正在排序...");
		doPost(buffer.toString());
		System.out.println("完成！");
	}

	private static void doPost(String date) {
		List<Member> list = new ArrayList<>();
		List<Ranker> rankerList = new ArrayList<>();
		String dd = date.replaceAll("[^\u4e00-\u9fa5a-zA-Z0-9]", "").trim();
		String source = dd.replaceAll("[\\pP\\p{Punct}\\s\n]", "");
		String[] strs = source.replaceAll("[0-9]", " ").split(" ");
		String[] mos = source.replaceAll("[\\u4e00-\\u9fa5 a-zA-Z]", " ").split(" ");

		List<String> names = Utils.getTrimName(strs);
		List<Integer> moneys = Utils.getTrimMoney(mos);

		for (int i = 0; i < names.size(); i++) {
			if (moneys.get(i) >= 300) {
				// 去掉客服
				String name_kfu = names.get(i);
				if (moneys.get(i) < 400) {
					if (name_kfu.indexOf("客服") != -1) {
						// System.out.println(name_kfu);
						name_kfu = name_kfu.replace("客服", "");
					}
				}
				Member member = new Member();
				member.setName(names.get(i));
				member.setMoney(moneys.get(i));
				list.add(member);
			}
		}

		// 相同钱数的归类
		for (int i = 0; i < list.size(); i++) {
			int isExist = -1;
			for (int j = 0; j < rankerList.size(); j++) {
				if (list.get(i).getMoney() == rankerList.get(j).getMoney()) {
					isExist = j;
					break;
				}
			}
			if (isExist != -1) {
				rankerList.get(isExist).getList().add(list.get(i).getName());
			} else {
				Ranker ranker = new Ranker();
				ranker.setMoney(list.get(i).getMoney());
				ranker.getList().add(list.get(i).getName());
				rankerList.add(ranker);
			}

		}
		Collections.sort(rankerList, new Comparator<Ranker>() {

			@Override
			public int compare(Ranker o1, Ranker o2) {
				if (o1.getMoney() > o2.getMoney()) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(new File(path + "\\排行.txt")), "UTF-8"));
			StringBuffer br = new StringBuffer();
			br.append("881团");
			br.append(Utils.getDate());

			br.append("会员日结工资排名榜\r\n\r\n");
			bw.write(br.toString());
			for (int i = 0; i < rankerList.size(); i++) {
				Ranker ranker = rankerList.get(i);
				StringBuffer st = new StringBuffer();
				for (int j = 0; j < ranker.getList().size(); j++) {
					if (j == 0) {
						st.append("第" + ChineseNumber.NumberToChn(i + 1) + "名：");
					}
					if ((j - 1) % 3 == 0 || j == ranker.getList().size() - 1) {
						st.append(ranker.getList().get(j) + ranker.getMoney() + "\r\n\r\n");

					} else {
						st.append(ranker.getList().get(j) + ranker.getMoney() + "   ");

					}
				}
				bw.write(st.toString());
			}
			bw.write(Utils.END);
			bw.newLine();
			bw.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
