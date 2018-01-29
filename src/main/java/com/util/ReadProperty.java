package com.util;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ReadProperty {
    public String propertyRead(String propertyName,String key) {
        Properties prop = new Properties();
        String pro = propertyName+".properties";
        Path path = Paths.get("D:/socket-server","pro",pro);
        try{
            //读取属性文件Client.properties
            InputStream inStream=Files.newInputStream(path);//获取配置文件输入流
            prop.load(inStream);     ///加载属性列表
            inStream.close();
            return prop.getProperty(key);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public String updateProperty(String propertyName,String key,String value) {
    	 Properties prop = new Properties();
         String pro = propertyName+".properties";
         Path path = Paths.get("D:/socket-server","pro",pro);
    	// 文件输出流   
         FileInputStream fis;  
         try {  
        	 fis = new FileInputStream(path.toString());// 属性文件输入流  
        	  prop.load(fis);// 将属性文件流装载到Properties对象中
        	  fis.close();// 关闭流  
        	  prop.setProperty(key, value);
             // 将Properties集合保存到流中   
        	 OutputStream fos = Files.newOutputStream(path);
             prop.store(fos, "update"); 
             fos.close();// 关闭流
         } catch (FileNotFoundException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
             return "false";  
         } catch (IOException e) {  
             // TODO Auto-generated catch block  
             e.printStackTrace();  
             return "false";  
         }  
         return "true";  
    }

}