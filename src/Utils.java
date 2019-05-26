import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
	public static String END = "*������881���սṤ�����а񣬵���300����ʱ�����������и����Ա�����չ��ʺ��տ��ʽ����ȷ�ģ����첹���£����ʲ����˵��ҿͷ��Ͳ����ʵ���˶�ʱ�����12���Ժ����ϻ�Ա���ʷ�����ϣ����Ǽ�ְ�����룡��Ҽ����ˣ�Ҳ�������а�������ʦ����ѧϰ����׬Ǯ�������տ�ʱ��д����ȷ��ʽ����������Ϊ����13�㵽19�㣡����ÿ�췢����ʱ��Ϊ00:00��07��00ǰ�������!";

	// ��ȡ��������
	public static List getTrimName(String[] string) {
		List<String> names = new ArrayList<>();
		for (int i = 0; i < string.length; i++) {

			if (string[i].equals("") || string == null) {

			} else {
				names.add(string[i]);
			}
		}
		return names;
	}

	// ��ȡǮ������
	public static List getTrimMoney(String[] string) {
		List<Integer> moneys = new ArrayList<>();
		for (int i = 0; i < string.length; i++) {
			if (string[i].equals("") || string == null) {

			} else {
				moneys.add(Integer.parseInt(string[i]));
			}
		}
		return moneys;
	}

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		String day = sdf.format(date);
		return day;
	}
}
