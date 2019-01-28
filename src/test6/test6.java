package test6;

import java.io.*;

public class test6 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        File f = new File("D:", "first.txt");//文件1里有内容
        File s = new File("D:", "second.txt");//文件2为空
        try {
            FileReader fr = new FileReader(f);//读文件1
            FileWriter sw = new FileWriter(s);//写文件2
            BufferedReader bufr = new BufferedReader(fr);//文件1缓存机制
            String str = "";
            while ((str = bufr.readLine()) != null) {//当读取的字符串不为空时
                sw.write(str);//在文件2中写入文件1内容
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
