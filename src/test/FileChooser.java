package test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class FileChooser extends JFrame implements ActionListener {

    JButton open = null;
    JPanel jp;

    public static void main(String[] args) {
        new FileChooser("JFileChooser²âÊÔ");
    }

    public FileChooser(String title) {

        jp = new JPanel();
        open = new JButton("Ñ¡ÔñÎÄ¼þ ");
        open.addActionListener(this);
        jp.add(open);
        this.add(jp);

        this.setBounds(400, 200, 200, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();
        if (e.getSource() == open) {
            jFileChooser.showOpenDialog(this);
            File file = jFileChooser.getSelectedFile();
            String string = file.getAbsolutePath();
            JOptionPane.showMessageDialog(this, string);
        }


    }


}  