package com.start;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.client.Main;
import com.util.ReadProperty;

public class Start {
	private static Logger Log = Logger.getLogger(Start.class);
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JLabel portPanel;
	private JTextField Jport;
	private JTextField Jip;
	/**
	 * 入口
	 * 
	 * @param
	 */

	public Start() {
		prepareGUI();
	}

	public static void main(String[] args) {
		try {
			Log.error("============启动客户端==============");
			Start s = new Start();
			s.showButtonDemo();
			args = new String[] {
					"classpath*:applicationContext-task.xml"
					};
			ApplicationContext actx = new FileSystemXmlApplicationContext(args);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.error("main:"+e); 
		}
	}

	private void prepareGUI() {
		mainFrame = new JFrame("socket客户端");
		mainFrame.setSize(400, 200);
		mainFrame.setLayout(new GridLayout(3, 1));
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
		});
		ReadProperty r = new ReadProperty();
		String port = r.propertyRead("Client","SERVER_PORT");
		String ip = r.propertyRead("Client", "SERVER_IP");
		JPanel jp = new JPanel();  
        jp.setLayout(new GridLayout(1,4));  //2行2列的面板jp（网格布局）   
		portPanel = new JLabel("ip地址+端口号："); 
		Jip = new JTextField(10);
		Jport = new JTextField(5);
		JButton updatePortbtn = new JButton("修改");
		Jport.setText(port);
		Jip.setText(ip);
		jp.add(portPanel);
		jp.add(Jip);
		jp.add(Jport);
		jp.add(updatePortbtn);
		jp.setLayout(new FlowLayout());
		
		updatePortbtn.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				// TODO 修改server.properties文件
				try {
					ReadProperty r = new ReadProperty();
					r.updateProperty("Client", "SERVER_IP",Jip.getText());
					r.updateProperty("Client", "SERVER_PORT",Jport.getText());
					statusLabel.setText("修改成功");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		headerLabel = new JLabel("", JLabel.CENTER);
		statusLabel = new JLabel("", JLabel.CENTER);
		statusLabel.setSize(350, 100);
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		mainFrame.add(jp,BorderLayout.CENTER);//将整块面板定义在中间
		mainFrame.add(statusLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}

	private void showButtonDemo() {
		headerLabel.setText("Control in action: Button");
		JButton okButton = new JButton("测试连接状态");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        int port = Integer.parseInt(Jport.getText());
		        String ip = Jip.getText();
				try {
					Main m = new Main();
					statusLabel.setText(m.receinverByServer(ip, port));
//					statusLabel.setText("可以点击");
				} catch (Exception rr) {
					statusLabel.setText("不可以点击");
				}
			}
		});
		controlPanel.add(okButton);
		mainFrame.setVisible(true);
	}

}
