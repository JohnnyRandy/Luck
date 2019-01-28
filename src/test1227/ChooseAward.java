package test1227;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;

public class ChooseAward extends JFrame  implements ActionListener {
	// 开始、停止按钮
	JButton b_start = new JButton("开始");
	JButton b_stop = new JButton("停止");

	JPanel p_north = new JPanel();
	JPanel p_center = new JPanel();
	JPanel p_south = new JPanel();

	// 菜单栏
	JMenuBar menubar = new JMenuBar();
	// 菜单
	JMenu fileMenu = new JMenu("文件");
	JMenu setMenu = new JMenu("设置");
	JMenu aboutMenu = new JMenu("关于");

	// 菜单项
	JMenuItem jm1 = new JMenuItem("选择文件");
	JMenuItem jm2 = new JMenuItem("另存为..");
	JMenuItem jm3 = new JMenuItem("保存");
	JMenuItem jm4 = new JMenuItem("退出");

	// 身份证号的文本域
	JTextField t_identNumber = new JTextField();

	JLabel l_information = new JLabel();

	JLabel l_identNumber = new JLabel();

	JLabel l_sysinformation = new JLabel("系统信息:");

	Vector<String> v_identNumber = new Vector<String>(); // 存放读取出来的手机号

	Vector<String> v_name = new Vector<String>(); // 存放姓名
	
	// 选择随机数的线程
	public ChooseThread awardThread = null;

	int chooseTime = 0; // 按下停止按钮的次数,也就是抽奖的次数
	
	
	JFileChooser filechooser = new JFileChooser("src/p1_JFileChooser"); // 文件选择器

	//用数组元素为向量赋值
	public void addDate(){
		String[] name = { "张三", "李四", "王五", "赵六", "aa", "bb", "cc", "dd", "ee",
				"ff" };
		String[] tel = { "12356372287", "12356371187", "12356371234",
				"12312342287", "12567872287", "12356772217", "12356456787",
				"12354567287", "12351111287", "12352222287" };

		for(int k=0;k<name.length;k++){
			v_identNumber.add(k, tel[k]);
			v_name.add(k, name[k]);
		}
	}



	public ChooseAward() {
		super("小型抽奖系统");

		// 菜单定义
		fileMenu.add(jm1);
		fileMenu.add(jm2);
		fileMenu.add(jm3);
		fileMenu.add(jm4);
		
		jm1.addActionListener(this);
	 
		menubar.add(fileMenu);
		menubar.add(setMenu);
		menubar.add(aboutMenu);
		this.setJMenuBar(menubar);

		// 设置的一些显示方式，字体，大小等,为了尽量美观一点
		Font xuehao = new Font("null", Font.PLAIN, 30);
		l_identNumber.setFont(xuehao);
		// 字体的居中对齐
		l_identNumber.setHorizontalAlignment(0);
		l_identNumber.setText("手机号");

		Font number = new Font("null", Font.BOLD, 30);// 粗体
		t_identNumber.setFont(number);
		// 设置字体颜色
		t_identNumber.setForeground(Color.red);
		t_identNumber.setHorizontalAlignment(0);
		t_identNumber.setEditable(false);

		// North区域
		p_north.setLayout(new GridLayout(2, 1));
		p_north.add(l_identNumber);
		p_north.add(t_identNumber); // 身份证号
		this.add(p_north, BorderLayout.NORTH);

		// 中间区域
		p_center.setLayout(new GridLayout(1, 2, 10, 0));
		p_center.add(b_start);
		p_center.add(b_stop);
		
		b_start.addActionListener(this);
		b_stop.addActionListener(this);
		 
		this.add(p_center, BorderLayout.CENTER);

		// 将p_south设计为一个状态栏，显示一些操作过程中的信息 创建一个左对齐方式的流布局
		p_south.setLayout(new FlowLayout(FlowLayout.LEFT));
		l_information.setForeground(Color.blue);
		p_south.add(l_sysinformation);// "系统信息："
		p_south.add(l_information);
		this.add(p_south, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(333, 209);
		this.setLocationRelativeTo(null);// 居中显示
		this.setVisible(true);
		this.setAlwaysOnTop(true);// 置顶

	}

	/*
	 * 程序的入口
	 */
	public static void main(String[] args) {
		new ChooseAward();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 //开始按钮事件
		if (e.getSource() == b_start) {
			
			//加载数据
			this.addDate();

			// 判断存储两个标记的向量中是否为空
			if (v_identNumber.size() <= 0 || v_name.size() <= 0) {
				l_information.setText("数据没有加载,请加载数据!");
			} else {
				if (chooseTime > 6) {
					l_information.setText("抽奖结束,若要再进行一次须重新启动程序!");
				} else// 执行....
				{
					awardThread = new ChooseThread(this);
					awardThread.changeflag_start();
					l_information.setText("将产生:特(1名),一(1名),二(2名),三(3名)等奖");
					l_identNumber.setText("选取中...");
					b_start.setEnabled(false);
					b_stop.setEnabled(true);
				}
			}
		}
		
		
		//停止按钮的事件
		 if(e.getSource()==b_stop) {
		 // 将跳转的数字置于停止状态
		 awardThread.changeflag_stop();
		 String awardmessage = "";
		 chooseTime++;// 第几次按停止按钮
		 String str_name = "";
		 String message = "";
		 
		 // 根据第几次来按停止按钮来决定是产生几等奖
		 // 先产生三个三等奖，再两个二等奖，再一个一等奖，一个特等奖
			switch (chooseTime) {
			case 1:
			case 2:
			case 3:// 按前三次都是产生三等奖

				// 寻找停止在号码框中的电话号码对应的名字
				for (int k = 0; k < v_identNumber.size(); k++) {
					// 找到了对应的号码（取出停止时文本框中的号码与存储号码的向量比较）
					if ((t_identNumber.getText()).equals(v_identNumber
							.elementAt(k))) {
						// 取出这个号码对应的名字
						str_name = (String) v_name.elementAt(k);
						
						// 为防止下次抽的时候再抽到相同的号码，所以把它们从向量中移除掉
						v_identNumber.removeElementAt(k);
						v_name.removeElementAt(k);
						break;// 跳出循环
					}
				}
				l_identNumber.setText("三等奖");
				b_start.setText("继续");
			 
				//显示提示对话框
				message = "第" + chooseTime + "位三等奖得主为：  " + str_name;
				JOptionPane.showMessageDialog(this, message);
				break;
			case 4:
			case 5:
				for (int k = 0; k < v_identNumber.size(); k++) {
					if (t_identNumber.getText().equals(v_identNumber.elementAt(k))) {
						str_name = (String) v_name.elementAt(k);
						v_identNumber.removeElementAt(k);
						v_name.removeElementAt(k);
						break;
					}
				}
				l_identNumber.setText("二等奖");
				 
				
				int serial = chooseTime - 3;// 第几位得主，排名序号
				message = "第" + serial + "位二等奖得主为：  " + str_name;
				JOptionPane.showMessageDialog(this, message);
				break;
			case 6:
				for (int k = 0; k < v_identNumber.size(); k++) {
					if (t_identNumber.getText().equals(v_identNumber.elementAt(k))) {
						str_name = (String) v_name.elementAt(k);
						v_identNumber.removeElementAt(k);
						v_name.removeElementAt(k);
						break;
					}
				}
				l_identNumber.setText("一等奖");
				 

				serial = chooseTime - 5;
				message = "第" + serial + "位一等奖得主为：  " + str_name;
				// message = "一等奖得主为：  " + str_name;
				JOptionPane.showMessageDialog(this, message);
				break;
			case 7:
				for (int k = 0; k < v_identNumber.size(); k++) {
					if (t_identNumber.getText().equals(v_identNumber.elementAt(k))) {
						str_name = (String) v_name.elementAt(k);
						v_identNumber.removeElementAt(k);
						v_name.removeElementAt(k);
						break;
					}
				}
				l_identNumber.setText("特等奖");
				 
				serial = chooseTime-6;
				message = "第" + serial + "位一等奖得主为：  " + str_name;
               //	message = "特等奖得主为：  " + str_name;
				JOptionPane.showMessageDialog(ChooseAward.this, message);
				l_information.setText("此次抽奖全部结束,保存抽奖结果！");
				break;
		 }
		 b_start.setEnabled(true);
		 b_stop.setEnabled(false);
		 }
		 
		// 选择文件菜单事件
			if (e.getSource() == jm1) {
				
				// 此方法会返回一个int值
				int i = filechooser.showOpenDialog(this); // 显示打开文件对话框

				// 选择对话框上的确定按钮
				if (i == JFileChooser.APPROVE_OPTION) { // 点击对话框中打开选项
					File f = filechooser.getSelectedFile(); // 得到所选择的文件
//					System.out.print("您选择的文件是："+f.getName());
					
					
					//读取文件
					try {
						l_information.setText("数据加载中，请稍等...");
						BufferedReader reader = null;
						// 读取字符流
						reader = new BufferedReader(new FileReader(f));
						String data = null;
						// 读取一行数据，分解后把数据分别存入v_identNumber和v_name中
						while ((data = reader.readLine()) != null) {
							// 控制台输出读取的数据（测试用）
							System.out.println(data);
						}
						l_information.setText("数据加载完成！");
					} catch (Exception ex) {
						ex.printStackTrace(); // 输出出错信息
					}
				}
			}
		 
	}
	
}

/*
 * 定义的线程类,该线程是循环的显示号码
 */
class ChooseThread extends Thread {
	private boolean runFlag = true;// 决定此线程是否运行的标记
	// 需要该对象来读取文本框字段，不用创建它，申明下就好
	private ChooseAward chooseAward = null;
	// 创建一个新的随机数生成器
	Random randomNumber = new Random();

	public ChooseThread(Object obj) {
		runFlag=false;
		chooseAward = (ChooseAward) obj;
		this.start();
	}

	public void changeflag_start() {
		runFlag = true;
	}

	public void changeflag_stop() {
		runFlag = false;
	}

	// 实现文本框滚动的效果
	
	public void run() {
		while (runFlag) {
			/*
			 * 返回一个伪随机数，它是从此随机数生成器的序列中取出的 、在 0（包括）和指定值（不包括）之间均匀分布的 int值
			 */
			// 返回向量中存储了几个号码的随便一个序号
			int num = randomNumber.nextInt(chooseAward.v_identNumber.size());
			// 显示那个选种的序号对应的号码
			chooseAward.t_identNumber
					.setText((String) chooseAward.v_identNumber.elementAt(num));
			try {
				sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

