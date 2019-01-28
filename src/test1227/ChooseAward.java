package test1227;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
import java.util.*;

public class ChooseAward extends JFrame  implements ActionListener {
	// ��ʼ��ֹͣ��ť
	JButton b_start = new JButton("��ʼ");
	JButton b_stop = new JButton("ֹͣ");

	JPanel p_north = new JPanel();
	JPanel p_center = new JPanel();
	JPanel p_south = new JPanel();

	// �˵���
	JMenuBar menubar = new JMenuBar();
	// �˵�
	JMenu fileMenu = new JMenu("�ļ�");
	JMenu setMenu = new JMenu("����");
	JMenu aboutMenu = new JMenu("����");

	// �˵���
	JMenuItem jm1 = new JMenuItem("ѡ���ļ�");
	JMenuItem jm2 = new JMenuItem("���Ϊ..");
	JMenuItem jm3 = new JMenuItem("����");
	JMenuItem jm4 = new JMenuItem("�˳�");

	// ���֤�ŵ��ı���
	JTextField t_identNumber = new JTextField();

	JLabel l_information = new JLabel();

	JLabel l_identNumber = new JLabel();

	JLabel l_sysinformation = new JLabel("ϵͳ��Ϣ:");

	Vector<String> v_identNumber = new Vector<String>(); // ��Ŷ�ȡ�������ֻ���

	Vector<String> v_name = new Vector<String>(); // �������
	
	// ѡ����������߳�
	public ChooseThread awardThread = null;

	int chooseTime = 0; // ����ֹͣ��ť�Ĵ���,Ҳ���ǳ齱�Ĵ���
	
	
	JFileChooser filechooser = new JFileChooser("src/p1_JFileChooser"); // �ļ�ѡ����

	//������Ԫ��Ϊ������ֵ
	public void addDate(){
		String[] name = { "����", "����", "����", "����", "aa", "bb", "cc", "dd", "ee",
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
		super("С�ͳ齱ϵͳ");

		// �˵�����
		fileMenu.add(jm1);
		fileMenu.add(jm2);
		fileMenu.add(jm3);
		fileMenu.add(jm4);
		
		jm1.addActionListener(this);
	 
		menubar.add(fileMenu);
		menubar.add(setMenu);
		menubar.add(aboutMenu);
		this.setJMenuBar(menubar);

		// ���õ�һЩ��ʾ��ʽ�����壬��С��,Ϊ�˾�������һ��
		Font xuehao = new Font("null", Font.PLAIN, 30);
		l_identNumber.setFont(xuehao);
		// ����ľ��ж���
		l_identNumber.setHorizontalAlignment(0);
		l_identNumber.setText("�ֻ���");

		Font number = new Font("null", Font.BOLD, 30);// ����
		t_identNumber.setFont(number);
		// ����������ɫ
		t_identNumber.setForeground(Color.red);
		t_identNumber.setHorizontalAlignment(0);
		t_identNumber.setEditable(false);

		// North����
		p_north.setLayout(new GridLayout(2, 1));
		p_north.add(l_identNumber);
		p_north.add(t_identNumber); // ���֤��
		this.add(p_north, BorderLayout.NORTH);

		// �м�����
		p_center.setLayout(new GridLayout(1, 2, 10, 0));
		p_center.add(b_start);
		p_center.add(b_stop);
		
		b_start.addActionListener(this);
		b_stop.addActionListener(this);
		 
		this.add(p_center, BorderLayout.CENTER);

		// ��p_south���Ϊһ��״̬������ʾһЩ���������е���Ϣ ����һ������뷽ʽ��������
		p_south.setLayout(new FlowLayout(FlowLayout.LEFT));
		l_information.setForeground(Color.blue);
		p_south.add(l_sysinformation);// "ϵͳ��Ϣ��"
		p_south.add(l_information);
		this.add(p_south, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(333, 209);
		this.setLocationRelativeTo(null);// ������ʾ
		this.setVisible(true);
		this.setAlwaysOnTop(true);// �ö�

	}

	/*
	 * ��������
	 */
	public static void main(String[] args) {
		new ChooseAward();
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 //��ʼ��ť�¼�
		if (e.getSource() == b_start) {
			
			//��������
			this.addDate();

			// �жϴ洢������ǵ��������Ƿ�Ϊ��
			if (v_identNumber.size() <= 0 || v_name.size() <= 0) {
				l_information.setText("����û�м���,���������!");
			} else {
				if (chooseTime > 6) {
					l_information.setText("�齱����,��Ҫ�ٽ���һ����������������!");
				} else// ִ��....
				{
					awardThread = new ChooseThread(this);
					awardThread.changeflag_start();
					l_information.setText("������:��(1��),һ(1��),��(2��),��(3��)�Ƚ�");
					l_identNumber.setText("ѡȡ��...");
					b_start.setEnabled(false);
					b_stop.setEnabled(true);
				}
			}
		}
		
		
		//ֹͣ��ť���¼�
		 if(e.getSource()==b_stop) {
		 // ����ת����������ֹͣ״̬
		 awardThread.changeflag_stop();
		 String awardmessage = "";
		 chooseTime++;// �ڼ��ΰ�ֹͣ��ť
		 String str_name = "";
		 String message = "";
		 
		 // ���ݵڼ�������ֹͣ��ť�������ǲ������Ƚ�
		 // �Ȳ����������Ƚ������������Ƚ�����һ��һ�Ƚ���һ���صȽ�
			switch (chooseTime) {
			case 1:
			case 2:
			case 3:// ��ǰ���ζ��ǲ������Ƚ�

				// Ѱ��ֹͣ�ں�����еĵ绰�����Ӧ������
				for (int k = 0; k < v_identNumber.size(); k++) {
					// �ҵ��˶�Ӧ�ĺ��루ȡ��ֹͣʱ�ı����еĺ�����洢����������Ƚϣ�
					if ((t_identNumber.getText()).equals(v_identNumber
							.elementAt(k))) {
						// ȡ����������Ӧ������
						str_name = (String) v_name.elementAt(k);
						
						// Ϊ��ֹ�´γ��ʱ���ٳ鵽��ͬ�ĺ��룬���԰����Ǵ��������Ƴ���
						v_identNumber.removeElementAt(k);
						v_name.removeElementAt(k);
						break;// ����ѭ��
					}
				}
				l_identNumber.setText("���Ƚ�");
				b_start.setText("����");
			 
				//��ʾ��ʾ�Ի���
				message = "��" + chooseTime + "λ���Ƚ�����Ϊ��  " + str_name;
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
				l_identNumber.setText("���Ƚ�");
				 
				
				int serial = chooseTime - 3;// �ڼ�λ�������������
				message = "��" + serial + "λ���Ƚ�����Ϊ��  " + str_name;
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
				l_identNumber.setText("һ�Ƚ�");
				 

				serial = chooseTime - 5;
				message = "��" + serial + "λһ�Ƚ�����Ϊ��  " + str_name;
				// message = "һ�Ƚ�����Ϊ��  " + str_name;
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
				l_identNumber.setText("�صȽ�");
				 
				serial = chooseTime-6;
				message = "��" + serial + "λһ�Ƚ�����Ϊ��  " + str_name;
               //	message = "�صȽ�����Ϊ��  " + str_name;
				JOptionPane.showMessageDialog(ChooseAward.this, message);
				l_information.setText("�˴γ齱ȫ������,����齱�����");
				break;
		 }
		 b_start.setEnabled(true);
		 b_stop.setEnabled(false);
		 }
		 
		// ѡ���ļ��˵��¼�
			if (e.getSource() == jm1) {
				
				// �˷����᷵��һ��intֵ
				int i = filechooser.showOpenDialog(this); // ��ʾ���ļ��Ի���

				// ѡ��Ի����ϵ�ȷ����ť
				if (i == JFileChooser.APPROVE_OPTION) { // ����Ի����д�ѡ��
					File f = filechooser.getSelectedFile(); // �õ���ѡ����ļ�
//					System.out.print("��ѡ����ļ��ǣ�"+f.getName());
					
					
					//��ȡ�ļ�
					try {
						l_information.setText("���ݼ����У����Ե�...");
						BufferedReader reader = null;
						// ��ȡ�ַ���
						reader = new BufferedReader(new FileReader(f));
						String data = null;
						// ��ȡһ�����ݣ��ֽ������ݷֱ����v_identNumber��v_name��
						while ((data = reader.readLine()) != null) {
							// ����̨�����ȡ�����ݣ������ã�
							System.out.println(data);
						}
						l_information.setText("���ݼ�����ɣ�");
					} catch (Exception ex) {
						ex.printStackTrace(); // ���������Ϣ
					}
				}
			}
		 
	}
	
}

/*
 * ������߳���,���߳���ѭ������ʾ����
 */
class ChooseThread extends Thread {
	private boolean runFlag = true;// �������߳��Ƿ����еı��
	// ��Ҫ�ö�������ȡ�ı����ֶΣ����ô������������¾ͺ�
	private ChooseAward chooseAward = null;
	// ����һ���µ������������
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

	// ʵ���ı��������Ч��
	
	public void run() {
		while (runFlag) {
			/*
			 * ����һ��α����������ǴӴ��������������������ȡ���� ���� 0����������ָ��ֵ����������֮����ȷֲ��� intֵ
			 */
			// ���������д洢�˼�����������һ�����
			int num = randomNumber.nextInt(chooseAward.v_identNumber.size());
			// ��ʾ�Ǹ�ѡ�ֵ���Ŷ�Ӧ�ĺ���
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

