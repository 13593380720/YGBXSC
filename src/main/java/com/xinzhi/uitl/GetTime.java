package com.xinzhi.uitl;
import java.text.SimpleDateFormat;


public class GetTime {
	/**
	 * 20λĩβ������id
	 */
	public static int Guid=100;

	public static String getGuid() {
		
		GetTime.Guid+=1;

		long now = System.currentTimeMillis();  
		//��ȡ4λ�������  
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");  
		//��ȡʱ���  
		String time=dateFormat.format(now);
		String info=now+"";
		int ran=0;
		if(GetTime.Guid>999){
			GetTime.Guid=100;    	
		}
		ran=GetTime.Guid;
				
		return time+info.substring(0, info.length())+ran;  
	}

}
