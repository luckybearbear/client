package com.task;

import org.springframework.stereotype.Component;

import com.client.Main;
import com.util.ReadProperty;


@Component
public class Task {
	 public Task(){
	        System.out.println("jobs...............");
	    }
	    public void run() {
	        System.out.println(" Task is running");
	        ReadProperty r = new ReadProperty();
	        String ip = r.propertyRead("Client","SERVER_IP");
	        int port = Integer.parseInt(r.propertyRead("Client","SERVER_PORT"));
			try {
				Main m = new Main();
				m.receinverByServer(ip, port);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
}
