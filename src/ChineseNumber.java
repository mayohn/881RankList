import java.util.HashMap;
import java.util.Map;

public class ChineseNumber {
	static String CHN_NUMBER[] = { "��", "һ", "��", "��", "��", "��", "��", "��", "��", "��" };
	static String CHN_UNIT[] = { "", "ʮ", "��", "ǧ" }; // Ȩλ
	static String CHN_UNIT_SECTION[] = { "", "��", "��", "����" }; // ��Ȩλ

	public static String getNumberToChnResult(int num) {
		String result = NumberToChn(num);
		switch (result) {
		case "һʮ":
			return "ʮ";
		case "һʮһ":
			return "ʮһ";
		case "һʮ��":
			return "ʮ��";
		case "һʮ��":
			return "ʮ��";
		case "һʮ��":
			return "ʮ��";
		case "һʮ��":
			return "ʮ��";
		case "һʮ��":
			return "ʮ��";
		case "һʮ��":
			return "ʮ��";
		case "һʮ��":
			return "ʮ��";
		case "һʮ��":
			return "ʮ��";
		default:
			return result;
		}
	}

	/**
	 * ����������ת��Ϊ�������ֵĺ����㷨ʵ�֡�
	 * 
	 * @param numΪ��Ҫת��Ϊ�������ֵİ��������֣����޷��ŵ�������
	 * @return
	 */
	public static String NumberToChn(int num) {
		StringBuffer returnStr = new StringBuffer();
		Boolean needZero = false;
		int pos = 0; // ��Ȩλ��λ��
		if (num == 0) {
			// ���numΪ0���������⴦��
			returnStr.insert(0, CHN_NUMBER[0]);
		}
		while (num > 0) {
			int section = num % 10000;
			if (needZero) {
				returnStr.insert(0, CHN_NUMBER[0]);
			}
			String sectionToChn = SectionNumToChn(section);
			// �ж��Ƿ���Ҫ��Ȩλ
			sectionToChn += (section != 0) ? CHN_UNIT_SECTION[pos] : CHN_UNIT_SECTION[0];
			returnStr.insert(0, sectionToChn);
			needZero = ((section < 1000 && section > 0) ? true : false); // �ж�section�е�ǧλ���ǲ���Ϊ�㣬��Ϊ��Ӧ�����һ���㡣
			pos++;
			num = num / 10000;
		}
		return returnStr.toString();
	}

	/**
	 * ����λ��sectionת��Ϊ��������
	 * 
	 * @param section
	 * @return
	 */
	public static String SectionNumToChn(int section) {
		StringBuffer returnStr = new StringBuffer();
		int unitPos = 0; // ��Ȩλ��λ�ñ�ţ�0-3����Ϊ��ʮ��ǧ;

		Boolean zero = true;
		while (section > 0) {

			int v = (section % 10);
			if (v == 0) {
				if ((section == 0) || !zero) {
					zero = true; /* ��Ҫ��0��zero��������ȷ���������Ķ��0��ֻ��һ�������� */
					// chnStr.insert(0, chnNumChar[v]);
					returnStr.insert(0, CHN_NUMBER[v]);
				}
			} else {
				zero = false; // ������һ�����ֲ���0
				StringBuffer tempStr = new StringBuffer(CHN_NUMBER[v]);// ����v����Ӧ����������
				tempStr.append(CHN_UNIT[unitPos]); // ����v����Ӧ������Ȩλ
				returnStr.insert(0, tempStr);
			}
			unitPos++; // ��λ
			section = section / 10;
		}
		return returnStr.toString();
	}

	/**
	 * ����ת���ɰ��������֣������ַ������˰���0-9�����ĺ��֣�������ʮ���٣�ǧ�����Ȩλ�� �˴�����ɶ���ЩȨλ�����Ͷ��塣
	 * name��ָ��ЩȨλ�ĺ����ַ����� value��ָȨλ���Ӧ����ֵ�Ĵ�С�����磺ʮ��Ӧ��ֵ�Ĵ�СΪ10���ٶ�ӦΪ100��
	 * secUnit��Ϊtrue�������ȨλΪ��Ȩλ�������ڣ����ڵ�
	 */
	public static class Chn_Name_value {
		String name;
		int value;
		Boolean secUnit;

		public Chn_Name_value(String name, int value, Boolean secUnit) {
			this.name = name;
			this.value = value;
			this.secUnit = secUnit;
		}
	}

	static Chn_Name_value chnNameValue[] = { new Chn_Name_value("ʮ", 10, false), new Chn_Name_value("��", 100, false),
			new Chn_Name_value("ǧ", 1000, false), new Chn_Name_value("��", 10000, true),
			new Chn_Name_value("��", 100000000, true) };

	/**
	 * �����������ֺ�������Ӧ�İ��������֣���str��Ϊ�������֣��򷵻�-1
	 * 
	 * @param str
	 * @return
	 */
	public static int ChnNumToValue(String str) {
		for (int i = 0; i < CHN_NUMBER.length; i++) {
			if (str.equals(CHN_NUMBER[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * �������ĺ���Ȩλ��chnNameValue����������Ӧ�������ţ�����Ϊ���ĺ���Ȩλ���򷵻�-1
	 * 
	 * @param str
	 * @return
	 */
	public static int ChnUnitToValue(String str) {
		for (int i = 0; i < chnNameValue.length; i++) {
			if (str.equals(chnNameValue[i].name)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * �������������ַ�������Ӧ��int���͵İ���������
	 * 
	 * @param str
	 * @return
	 */
	public static int ChnStringToNumber(String str) {
		int returnNumber = 0;
		int section = 0;
		int pos = 0;
		int number = 0;
		while (pos < str.length()) {
			int num = ChnNumToValue(str.substring(pos, pos + 1));
			// ��num>=0�������λ�ã�pos��������Ӧ�������ֲ���Ȩλ����С��0�����ʾΪȨλ
			if (num >= 0) {
				number = num;
				pos++;
				// pos�����һλ��ֱ�ӽ�number���뵽section�С�
				if (pos >= str.length()) {
					section += number;
					returnNumber += section;
					break;
				}
			} else {
				int chnNameValueIndex = ChnUnitToValue(str.substring(pos, pos + 1));
				// chnNameValue[chnNameValueIndex].secUnit==true����ʾ��λ������Ӧ��Ȩλ�ǽ�Ȩλ��
				if (chnNameValue[chnNameValueIndex].secUnit) {
					section = (section + number) * chnNameValue[chnNameValueIndex].value;
					returnNumber += section;
					section = 0;
				} else {
					section += number * chnNameValue[chnNameValueIndex].value;
				}
				pos++;
				number = 0;
				if (pos >= str.length()) {
					returnNumber += section;
					break;
				}
			}
		}
		return returnNumber;
	}

}
