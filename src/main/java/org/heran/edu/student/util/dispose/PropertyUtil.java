package org.heran.edu.student.util.dispose;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyUtil {
	
	private  static String[] getKeys(String packagePath){
		InputStream is = null;
		Properties prop = null;
		try{
			String filePath  = PropertyUtil.class.getClassLoader().getResource(packagePath).getPath();
			is = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(is);
			String fieldSort = prop.getProperty("field.sort");
			String[] keys = fieldSort.split(",");
			for (int i = 0; i < keys.length; i++) {
				keys[i] = keys[i].trim();
			}
			return keys;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close(is);
		}
	}
	
	
	public  static String[] getKeys(String packagePath,String fields){
		InputStream is = null;
		Properties prop = null;
		try{
			String filePath  = PropertyUtil.class.getClassLoader().getResource(packagePath).getPath();
			is = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(is);
			String fieldSort = prop.getProperty((null ==  fields) ?  "field.sort" : fields);
			String[] keys = fieldSort.split(",");
			for (int i = 0; i < keys.length; i++) {
				keys[i] = keys[i].trim();
			}
			return keys;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close(is);
		}
	}
	
	public static String[] getKeysByValues(String packagePath , String fields, String[] values ){
		if(null == values || values.length < 0)
			return null;
		InputStream is = null;
		Properties prop = null;
		List<String> keyList = new ArrayList<String>();
		try{
			String filePath  = PropertyUtil.class.getClassLoader().getResource(packagePath).getPath();
			is = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(is);
			String[] keystemp = getKeys(packagePath);
			String[] valuestemp = getValues(packagePath, fields);
			for (int i = 0; i < values.length; i++) {
				for (int j = 0; j < valuestemp.length; j++) {
						if(values[i].equals(valuestemp[j])){
							keyList.add(keystemp[i]);
							break;
						}
				}
			}
			String[] keys = keyList.toArray(new String[keyList.size()]);
			return keys;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			close(is);
		}
	}
	public static String[] getValues(String packagePath , String fields) {
		String filePath  = PropertyUtil.class.getClassLoader().getResource(packagePath).getPath();
		InputStream is = null;
		Properties prop = null;
		try{
			 is = new FileInputStream(filePath);
			prop = new Properties();
			prop.load(is);
			String[] keys = getKeys(packagePath,fields);
			String[] values = new String[keys.length];
			for (int i = 0; i < keys.length; i++) {
				String value = prop.getProperty(keys[i]);
				values[i] = new String(value.getBytes("ISO8859-1"), "utf-8");
				values[i] = values[i].trim();
			}
			return values;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			close(is);
		}
	}
	
	public static void close(InputStream is){
		if( null != is ){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void print(String[] values){
		if (null == values || values.length  == 0) {
			return; 
		}
		for (int i = 0; i < values.length; i++) {
			System.out.print( values[i] + "\t");
		}
		System.out.println();
		
	}
	
	/*public static void main(String[] args) throws Exception {
		String path = "com/tky/tunnel/entity/DayReport.properties";
		String[] keys= getKeys(path, "field.import");
		String[] values= getValues(path, "field.import");
	    
		//getKeysByValues(path, values);
		print(keys);
		print(values);
		print(getKeysByValues(path, "field.export" ,values));
		//System.out.println(keys[0]);
		//System.out.println(values[0]);
		//System.out.println(values.length);
		
	}*/

}
