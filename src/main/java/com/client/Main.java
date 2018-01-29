package com.client;


import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.util.ReadProperty;

public class Main
{
	private Socket socket;

    private DataInputStream dis;

    private FileOutputStream fos;
	public String receinverByServer(String ip,int port){
        try {
        	socket = new Socket(ip, port);//创建一个客户端连接
            dis = new DataInputStream(socket.getInputStream());
            // 文件名和长度
            String fileName = dis.readUTF();
            ReadProperty rp = new ReadProperty();
            String dirname = rp.propertyRead("Client", "dir");
            File directory = new File(dirname);
            if(!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
            fos = new FileOutputStream(file);

            // 开始接收文件
            byte[] bytes = new byte[1024];
            int length = 0;
            while((length = dis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, length);
                fos.flush();
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(new Date());
            return "执行成功"+time;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        } finally {
            try {
                if(fos != null)
                    fos.close();
                if(dis != null)
                    dis.close();
                socket.close();
            } catch (Exception e) {}
        }
    }
}