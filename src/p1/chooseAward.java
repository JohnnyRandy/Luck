package p1;

import test.FileChooser;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class chooseAward extends JFrame implements ActionListener {
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

    JLabel l_identNumber = new JLabel();//���ֻ��š��ı�
    // �ֻ�������ı���
    JTextField t_identNumber = new JTextField();

    JLabel l_information = new JLabel();//��ʾ��Ϣ
    JLabel l_sysinformation = new JLabel("ϵͳ��Ϣ:");

    Vector<String> v_Number = new Vector<String>();//��Ŷ�ȡ�������ֻ���
    Vector<String> v_name = new Vector<String>();//�������

    ChooseThread awardThrad = null;
    int choosetime = 0;


    //������Ԫ��Ϊ������ֵ
    public void addDate() {
        String[] name = {"11", "22", "33", "44", "55", "66", "77", "88", "99", "00"};
        String[] tel = {"111", "222", "333", "444", "555", "666", "777", "888", "999", "000"};
        for (int k = 0; k < name.length; k++) {
            v_Number.add(k, tel[k]);
            v_name.add(k, name[k]);
        }
    }

    public chooseAward() {
        super("С�ͳ齱ϵͳ");
        this.addDate();//��������
        //�˵�
        fileMenu.add(jm1);
        fileMenu.add(jm2);
        fileMenu.add(jm3);
        fileMenu.add(jm4);

        jm1.addActionListener(this);

        menubar.add(fileMenu);
        menubar.add(setMenu);
        menubar.add(aboutMenu);
        this.setJMenuBar(menubar);

        //��������
        Font f1 = new Font("null", Font.PLAIN, 30);
        l_identNumber.setFont(f1);
        l_identNumber.setHorizontalAlignment(0);//���ж���
        l_identNumber.setText("�ֻ���");

        Font f2 = new Font("null", Font.PLAIN, 30);//����Ϊ����
        t_identNumber.setFont(f2);
        t_identNumber.setHorizontalAlignment(0);
        t_identNumber.setForeground(Color.red);//����������ɫ
        t_identNumber.setEditable(false);

        //North����
        p_north.setLayout(new GridLayout(2, 1));
        p_north.add(l_identNumber);
        p_north.add(t_identNumber);
        this.add(p_north, "North");

        //center����
        p_center.setLayout(new GridLayout(1, 2, 10, 0));
        p_center.add(b_start);
        p_center.add(b_stop);
        this.add(p_center);
        b_start.addActionListener(this);
        b_stop.addActionListener(this);

        //south����
        p_south.setLayout(new FlowLayout(FlowLayout.LEFT));
        l_information.setForeground(Color.blue);
        p_south.add(l_sysinformation);
        p_south.add(l_information);
        this.add(p_south, "South");


        this.setSize(350, 220);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        new chooseAward();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jm1) {

        }
        if (e.getSource() == b_start) {
            //�жϴ洢�����͵绰����ļ����Ƿ���ֵ
            if (v_Number.size() <= 0 || v_name.size() <= 0) {
                l_information.setText("����û�м��أ���������ݣ�");
            } else {
                //�жϰ��¡�ֹͣ����ť�Ĵ����Ƿ����6
                if (choosetime > 6) {
                    l_information.setText("�齱��������Ҫ�ٽ�����������������");
                } else {
                    /*
                     * �����߳�
                     * ������ر�ǩ���ı�
                     * ���á���ʼ����ť
                     * ���á�ֹͣ����ť
                     */
                    awardThrad = new ChooseThread(this);
                    awardThrad.setRunflg(true);
                    l_information.setText("������:�ص�(1��),һ(1��),��(2��),��(3��)��");
                    l_identNumber.setText("ѡȡ��.....");
                    b_start.setEnabled(false);
                    b_stop.setEnabled(true);
                }
            }
        }

        if (e.getSource() == b_stop) {
            //�����߳�
            awardThrad.setRunflg(false);
            choosetime++;//�ڼ��ΰ���ֹͣ��ť
            String str_name = "";
            String message;

            switch (choosetime) {
                case 1:
                case 2:
                case 3:
                    for (int k = 0; k < v_Number.size(); k++) {
                        if (t_identNumber.getText().equals(v_Number.get(k))) {
                            str_name = v_name.get(k);
                            v_Number.removeElementAt(k);
                            v_name.removeElementAt(k);
                            break;
                        }
                    }
                    l_identNumber.setText("���Ƚ�");
                    b_start.setText("���� ");
                    message = "��" + choosetime + "λ���Ƚ�����Ϊ��" + str_name;
                    JOptionPane.showMessageDialog(this, message);
                    break;
                case 4:
                case 5:
                    for (int k = 0; k < v_Number.size(); k++) {
                        if (t_identNumber.getText().equals(v_Number.elementAt(k))) {
                            str_name = v_name.elementAt(k);
                            v_Number.removeElementAt(k);
                            v_name.removeElementAt(k);
                            break;
                        }
                    }
                    l_identNumber.setText("���Ƚ�");

                    int serial = choosetime - 3;//�ڼ�λ�������������
                    message = "��" + serial + "λ���Ƚ�����Ϊ��" + str_name;
                    JOptionPane.showMessageDialog(this, message);
                    break;
                case 6:
                    for (int k = 0; k < v_Number.size(); k++) {
                        if (t_identNumber.getText().equals(v_Number.elementAt(k))) {
                            str_name = v_name.elementAt(k);
                            v_Number.removeElementAt(k);
                            v_name.removeElementAt(k);
                            break;
                        }
                    }
                    l_identNumber.setText("һ�Ƚ�");
                    int x = choosetime - 5;//�ڼ�λ�������������
                    message = "��" + x + "λһ�Ƚ�����Ϊ��" + str_name;
                    JOptionPane.showMessageDialog(this, message);
                    break;
                case 7:
                    for (int k = 0; k < v_Number.size(); k++) {
                        if (t_identNumber.getText().equals(v_Number.elementAt(k))) {
                            str_name = v_name.elementAt(k);
                            v_Number.removeElementAt(k);
                            v_name.removeElementAt(k);
                            break;
                        }
                    }
                    l_identNumber.setText("�صȽ�");
                    int s = choosetime - 6;//�ڼ�λ�������������
                    message = "��" + s + "λ�صȽ�����Ϊ��" + str_name;
                    JOptionPane.showMessageDialog(this, message);
                    l_information.setText("�˴γ齱ȫ������������齱���");
                    break;
                default:
            }
            b_start.setEnabled(true);
            b_stop.setEnabled(false);
        }
    }

}

//�߳��࣬ѭ����ʾ�ֻ�����
class ChooseThread extends Thread {
    private boolean runflg;//�����߳��Ƿ����еı�־

    public boolean isRunflg() {
        return runflg;
    }

    public void setRunflg(boolean runflg) {
        this.runflg = runflg;
    }

    private chooseAward chooseAward = null;

    public ChooseThread(chooseAward chooseAward) {
        this.chooseAward = chooseAward;
        runflg = false;
        this.start();
    }

    public void run() {
        while (runflg) {
            //����һ�������
            Random randomNumber = new Random();
            int num = randomNumber.nextInt(chooseAward.v_Number.size());

            //���������ȡ�����е�Ԫ����ʾ���ı�����
            String tel = chooseAward.v_Number.elementAt(num);
            chooseAward.t_identNumber.setText(tel);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}