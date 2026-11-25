package practice_Org_Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JavaBasics {

	public static void main(String[] args) {
		
		Date datObj = new Date();
		
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");	
	    String actdt =  	sim.format(datObj);
		System.out.println(actdt);
	    Calendar cal =  sim.getCalendar();
	   cal.add(Calendar.DAY_OF_MONTH, +70);
	String dateRequires = sim.format(cal.getTime());
	System.out.println(dateRequires);
		
	}

	private static void getCalendar() {

		
	}

}
