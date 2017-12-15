package hut.composite.querycreator;

public class QueryUtil {
	
	public static final String[][] FILTER_STRING={{"Equals","Bằng"},{"Contains","Chứa"},{"Begins with","Bắt đầu với"},{"Ends with","Kết thúc với"}};
//	public static final String[][] FILTER_DATE={{"Date >","Date >"},{"Date =","Date ="},{"Date <","Date <"},{"Year >","Năm >"},{"Year =","Năm ="},{"Year <","Năm <"},{"Month >","Tháng >"},{"Month =","Tháng ="},{"Month <","Tháng <"},{"Day >","Ngày >"},{"Day =", "Ngày ="},{"Day <","Ngày <"}};
	public static final String[][] FILTER_DATE={{"Date >","Sau ngày"},{"Date =","Trong ngày"},{"Date <","Trước ngày"}};
	public static final String[][] FILTER_DATE_TIME={{"Date Time >","Sau ngày"},{"Date Time =","Trong ngày"},{"Date Time <","Trước ngày"}};
	public static final String[] FILTER_NUMBER={">","=","<"};
	public static final String[] FILTER_BOOLEAN={"true", "false"};
	
	public static String prefix="SRC";
	public static int indexData;
	public static int indexObject;
	public static int indexClass;
	public static String dataName="x";
	public static String objectName="y";
	public static String className="z";
	public static void reset()
	{
		indexData=0;
		indexObject=0;
		indexClass=0;
		prefix="SRC";
	}
	
	public static String DataTriple(String variable, String prefix, String property, String filter, String value, Boolean optional)
	{
		/*
		 * prefix=null: property da co day du namespace
		 * prefix="": lay prefix cua lop queryUtil
		 * con lai: lay prefix theo thong so dua vao
		 */
		String close="";//Dong ngoac khi can
		if (prefix==null)
		{
			prefix="<";
			close=">";
		}
		else if (prefix.equals(""))
			prefix= QueryUtil.prefix+":";
		else
			prefix += ":";
		
		String result="";
		String variable_filter="?"+dataName+indexData;
		result += "?"+variable +" "+prefix+property+close+" "+variable_filter;
		indexData++;
		for (int i=0;i<FILTER_BOOLEAN.length;++i)
		{
			if (filter.equals(FILTER_BOOLEAN[i]))
			{
				result += " filter("+variable_filter+"=xsd:boolean('"+filter+"')).";
			}
		}
		for (int i=0;i<FILTER_STRING.length;++i)
		{
			if (filter.equals(FILTER_STRING[i][0]) || filter.equals(FILTER_STRING[i][1]))
			{
				switch (i) {
				case 0:
					result += " filter(fn:lower-case("+variable_filter+")='"+value.toLowerCase()+"').";
					break;				
				case 1:
					result += " filter(fn:contains(fn:lower-case("+variable_filter+"),'"+value.toLowerCase()+"')).";
					break;
				case 2:
					result += " filter(fn:starts-with(fn:lower-case("+variable_filter+"),'"+value.toLowerCase()+"')).";
					break;
				case 3:
					result += " filter(fn:ends-with(fn:lower-case("+variable_filter+"),'"+value.toLowerCase()+"')).";
					break;
				default:
					break;
				}
			}
		}
		for (int i=0;i<FILTER_DATE.length;++i)
		{
			if (filter.equals(FILTER_DATE[i][0]) || filter.equals(FILTER_DATE[i][1]))
			{
				switch (i) {
				case 0:
					result += " filter("+variable_filter+">xsd:date('"+value+"')).";
					break;				
				case 1:
					result += " filter("+variable_filter+"=xsd:date('"+value+"')).";
					break;
				case 2:
					result += " filter("+variable_filter+"<xsd:date('"+value+"')).";
					break;
				
				default:
					break;
				}
			}
		}
		for (int i=0;i<FILTER_DATE_TIME.length;++i)
		{
			if (filter.equals(FILTER_DATE_TIME[i][0]) || filter.equals(FILTER_DATE_TIME[i][1]))
			{
				switch (i) {
				case 0:
					result += " filter("+variable_filter+">xsd:dateTime('"+value+"')).";
					break;				
				case 1:
					result += " filter("+variable_filter+"=xsd:dateTime('"+value+"')).";
					break;
				case 2:
					result += " filter("+variable_filter+"<xsd:dateTime('"+value+"')).";
					break;
				
				default:
					break;
				}
			}
		}
		for (int i=0;i<FILTER_NUMBER.length;++i)
		{
			if (filter.equals(FILTER_NUMBER[i]) || filter.equals(FILTER_NUMBER[i]))
			{
				switch (i) {
				case 0:
					result += " filter("+variable_filter+">"+value+").";
					break;				
				case 1:
					result += " filter("+variable_filter+"="+value+").";
					break;
				case 2:
					result += " filter("+variable_filter+"<"+value+").";
					break;
				default:
					break;
				}
			}
		}
		if (optional)
			result="OPTIONAL{"+result+"}";
		
		result="\n"+result;
		System.out.println(result);
		return result;
	}
	public static String ObjectTriple(String subject, String prefix, String predicate, String object, Boolean optional)
	{
		String result="";
		/*
		 * prefix=null: property da co day du namespace
		 * prefix="": lay prefix cua lop queryUtil
		 * con lai: lay prefix theo thong so dua vao
		 */
		String close="";//Dong ngoac khi can
		if (prefix==null)
		{
			prefix="<";
			close=">";
		}
		else if (prefix.equals(""))
			prefix= QueryUtil.prefix+":";
		else
			prefix += ":";
		result="?"+subject+" "+prefix+predicate+close+" ?"+object+".";
		
		if (optional)
			result="OPTIONAL{"+result+"}";
		
		result="\n"+result;
		
		return result;
	}
}