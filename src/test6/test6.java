package test6;

import java.io.*;

public class test6 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        File f = new File("D:", "first.txt");//�ļ�1��������
        File s = new File("D:", "second.txt");//�ļ�2Ϊ��
        try {
            FileReader fr = new FileReader(f);//���ļ�1
            FileWriter sw = new FileWriter(s);//д�ļ�2
            BufferedReader bufr = new BufferedReader(fr);//�ļ�1�������
            String str = "";
            while ((str = bufr.readLine()) != null) {//����ȡ���ַ�����Ϊ��ʱ
                sw.write(str);//���ļ�2��д���ļ�1����
                sw.write("\n");
            }
            fr.close();
            sw.close();
            bufr.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
