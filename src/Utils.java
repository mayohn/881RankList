import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {
	public static String END = "*以上是881团日结工资排行榜，低于300的暂时不排名！还有个别会员忘记收工资和收款格式不正确的，今天补收下！工资不到账的找客服和财务核实！核对时间次日12点以后！以上会员工资发放完毕，这是兼职日收入！大家加油了！也想上排行榜的找你的师傅，学习快速赚钱方法！收款时请写好正确格式！补发工资为次日13点到19点！财务每天发工资时间为00:00到07：00前发放完毕!";

	// 获取姓名数组
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

	// 获取钱数数组
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
