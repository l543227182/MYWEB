package cn.lc.utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.stream.FileImageInputStream;

public class WebResource {

	public static  List<String> geturlResouce(String s,String regex) throws Exception	
	{
		URL url=new URL(s);
		BufferedInputStream bf=new BufferedInputStream(url.openStream());
		//BufferedOutputStream bfo=new BufferedOutputStream();
		String text="";
		int len=0;
		byte[] buffer=new byte[1024*2];
		while((len=bf.read(buffer))!=-1)
		{
			text+=new String(buffer,"utf-8");
		}
		bf.close();
		int temp=0;
		//System.out.println(text);
		Pattern p1=Pattern.compile("<div id=\"kb_list\">[\\w\\W]+<div class=\"block_arrow\">");
		Matcher m1=p1.matcher(text);		
		while(m1.find())
		{
			text=m1.group();
		}
		//System.out.println("-----------------------------------------------------------------------------------------");
		//System.out.println(text);
		List<String> list=new ArrayList<String>();
		Pattern p=Pattern.compile(regex);
		Matcher m=p.matcher(text);
		int div=0;
		int div2=0;
		while(m.find()){
			String group=m.group().trim();
			
				group=group.replace("<h2 class=\"kb_entry\">", "<div>");
				group=group.replace("</h2>", "</div>");				
                list.add(group); 
                //System.out.println(group+"\n\n\n");
                temp++;
               if(temp==5)
        	    break;
		}
		//System.out.println(list+"  "+temp);
       	return list;
	}
}
