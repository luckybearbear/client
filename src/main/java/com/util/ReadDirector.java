package com.util;



import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
/**
 * 读取目录文件并且按照时间进行排序
 * 
 */
public class ReadDirector {
   
    /**
     * 读取目录文件
     * @param dirname 目录名称
     * @return list集合
     */
    public static String getFiles(String dirname)throws Exception{
        File dir=new File(dirname);
        if(dir.exists()){
            File []files=dir.listFiles();
            //排序
            Arrays.sort(files, new CompratorByLastModified());
            /*for(int i=0;i<files.length;i++){
                //获取当文件最后修改时间
                String creatime=ReadDirector.format("yyyy-MM-dd hh:mm:ss",new Date(files[i].lastModified()));
                if(files[i].isHidden()){//判断是隐藏文件
                    file_names.add("创建时间："+creatime+"<=它是一个隐藏文件"+"=>"+files[i].getName());
                }else if(files[i].isDirectory()){//判断是目录
                    file_names.add("创建时间："+creatime+"<=它是一个文件夹"+"=>"+files[i].getName());
                }else{//普通文件
                    file_names.add("创建时间："+creatime+"<=它是一个普文件"+"=>"+files[i].getName());
                }
            }*/
            return files[files.length-1].getName();
        }else{
            System.out.println("该目录没有任何文件信息！");
            return null;
        }
    }
    
    
    /**
     * 进行文件排序时间
     * 
     */
    private static class CompratorByLastModified implements Comparator<File>{
        public int compare(File f1, File f2) {
            long diff = f1.lastModified()-f2.lastModified();
            if(diff>0)
                return 1;
            else if(diff==0)
                return 0;
            else
                return -1;
        }
        public boolean equals(Object obj){
            return true;
        }
    }
}