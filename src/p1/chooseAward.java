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

    JLabel l_identNumber = new JLabel();//“手机号”文本
    // 手机号码的文本域
    JTextField t_identNumber = new JTextField();

    JLabel l_information = new JLabel();//提示信息
    JLabel l_sysinformation = new JLabel("系统信息:");

    Vector<String> v_Number = new Vector<String>();//存放读取出来的手机号
    Vector<String> v_name = new Vector<String>();//存放姓名

    ChooseThread awardThrad = null;
    int choosetime = 0;


    //用数组元素为向量赋值
    public void addDate() {
        String[] name = {"11", "22", "33", "44", "55", "66", "77", "88", "99", "00"};
        String[] tel = {"111", "222", "333", "444", "555", "666", "777", "888", "999", "000"};
        for (int k = 0; k < name.length; k++) {
            v_Number.add(k, tel[k]);
            v_name.add(k, name[k]);
        }
    }

    public chooseAward() {
        super("小型抽奖系统");
        this.addDate();//加载数据
        //菜单
        fileMenu.add(jm1);
        fileMenu.add(jm2);
        fileMenu.add(jm3);
        fileMenu.add(jm4);

        jm1.addActionListener(this);

        menubar.add(fileMenu);
        menubar.add(setMenu);
        menubar.add(aboutMenu);
        this.setJMenuBar(menubar);

        //字体设置
        Font f1 = new Font("null", Font.PLAIN, 30);
        l_identNumber.setFont(f1);
        l_identNumber.setHorizontalAlignment(0);//居中对齐
        l_identNumber.setText("手机号");

        Font f2 = new Font("null", Font.PLAIN, 30);//设置为粗体
        t_identNumber.setFont(f2);
        t_identNumber.setHorizontalAlignment(0);
        t_identNumber.setForeground(Color.red);//设置字体颜色
        t_identNumber.setEditable(false);

        //North区域
        p_north.setLayout(new GridLayout(2, 1));
        p_north.add(l_identNumber);
        p_north.add(t_identNumber);
        this.add(p_north, "North");

        //center区域
        p_center.setLayout(new GridLayout(1, 2, 10, 0));
        p_center.add(b_start);
        p_center.add(b_stop);
        this.add(p_center);
        b_start.addActionListener(this);
        b_stop.addActionListener(this);

        //south区域
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
            //判断存储姓名和电话号码的集合是否有值
            if (v_Number.size() <= 0 || v_name.size() <= 0) {
                l_information.setText("数据没有加载，请加载数据！");
            } else {
                //判断按下“停止”按钮的次数是否大于6
                if (choosetime > 6) {
                    l_information.setText("抽奖结束，若要再进行需重新启动程序！");
                } else {
                    /*
                     * 启动线程
                     * 设置相关标签的文本
                     * 禁用“开始”按钮
                     * 启用“停止”按钮
                     */
                    awardThrad = new ChooseThread(this);
                    awardThrad.setRunflg(true);
                    l_information.setText("将产生:特等(1名),一(1名),二(2名),三(3名)奖");
                    l_identNumber.setText("选取中.....");
                    b_start.setEnabled(false);
                    b_stop.setEnabled(true);
                }
            }
        }

        if (e.getSource() == b_stop) {
            //结束线程
            awardThrad.setRunflg(false);
            choosetime++;//第几次按下停止按钮
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
                    l_identNumber.setText("三等奖");
                    b_start.setText("继续 ");
                    message = "第" + choosetime + "位三等奖得主为：" + str_name;
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
                    l_identNumber.setText("二等奖");

                    int serial = choosetime - 3;//第几位得主，排名序号
                    message = "第" + serial + "位二等奖得主为：" + str_name;
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
                    l_identNumber.setText("一等奖");
                    int x = choosetime - 5;//第几位得主，排名序号
                    message = "第" + x + "位一等奖得主为：" + str_name;
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
                    l_identNumber.setText("特等奖");
                    int s = choosetime - 6;//第几位得主，排名序号
                    message = "第" + s + "位特等奖得主为：" + str_name;
                    JOptionPane.showMessageDialog(this, message);
                    l_information.setText("此次抽奖全部结束，保存抽奖结果");
                    break;
                default:
            }
            b_start.setEnabled(true);
            b_stop.setEnabled(false);
        }
    }

}

//线程类，循环显示手机号码
class ChooseThread extends Thread {
    private boolean runflg;//决定线程是否运行的标志

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
            //定义一个随机数
            Random randomNumber = new Random();
            int num = randomNumber.nextInt(chooseAward.v_Number.size());

            //根据随机数取集合中的元素显示在文本框中
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