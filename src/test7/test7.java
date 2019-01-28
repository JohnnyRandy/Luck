package test7;

import java.io.*;

public class test7 {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader("Input.txt"));
            bw = new BufferedWriter(new FileWriter("Output.txt"));
            String str = "";
            //int i = 1;
            while((str = br.readLine()) != null){
                //bw.write(i+" ");
                bw.write(str);
                System.out.println(str);
                bw.newLine();
                //i++;
            }
            bw.flush();
            bw.close();
            br.close();
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
