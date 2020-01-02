//圖形化介面版本
package eatwhat;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EatWhatGUI extends EatWhat {

    public static void main(String[] args) throws IOException {
        EatWhat ew = new EatWhat();

        //JFrame視窗
        JFrame f = new JFrame("Eat What 晚餐隨機抽籤");
        f.setSize(400, 500);
        f.setLocation(500, 300);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        //設定開啟/關閉時的程序(讀檔/寫檔)
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    ew.start();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ew.end();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //顯示文字的地方ta
        JTextArea ta = new JTextArea();
        ta.setPreferredSize(new Dimension(0, 180));
        ta.setLineWrap(true);
        ta.setText("請按抽籤決定晚餐");
        ta.setFont(ta.getFont().deriveFont(20f));
        f.add(ta, BorderLayout.NORTH);

        //"get"功能按鈕        
        JButton get = new JButton("抽籤");
        get.setPreferredSize(new Dimension(0, 60));
        get.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                if(ew.get()==null || ew.get().isEmpty())
                    ta.setText("資料庫中還沒有任何餐廳，請先輸入餐廳");
                else
                    ta.setText(ew.get());
            }
        });
        f.add(get, BorderLayout.CENTER);

        //center
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        f.add(center, BorderLayout.SOUTH);

        //輸入區(enter area)
        JPanel enterArea = new JPanel();
        //輸入欄位"enter"功能               
        JLabel enterHint = new JLabel("請輸入餐廳名稱：");
        JTextField enter = new JTextField();
        enter.setPreferredSize(new Dimension(100, 20));
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = enter.getText();
                boolean added = Data.hsData.add(str);
                if(!str.isEmpty() && !added)
                    JOptionPane.showMessageDialog(f, "資料庫中已經有這間餐廳了");
                if(added)
                    JOptionPane.showMessageDialog(f, "新增成功");
                enter.setText("");
            }
        });
        enterArea.add(enterHint);
        enterArea.add(enter);
        center.add(enterArea, BorderLayout.CENTER);

/*------- buttons --------------------------------------- */ 
        JPanel buttons = new JPanel();
        
        //"show"功能按鈕
        JButton show = new JButton("資料庫內有哪些餐廳");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.setText(Data.hsData.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
            }
        });
        buttons.add(show);

        //"delete"功能按鈕
        JButton delete = new JButton("刪除資料庫中的餐廳");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = JOptionPane.showInputDialog(f, "請輸入要刪除的餐廳名稱");
                if (str != null && str.length() > 0) {
                    boolean dl = Data.hsData.remove(str);
                    if (dl) {
                        JOptionPane.showMessageDialog(f, "刪除成功");
                    } else {
                        JOptionPane.showMessageDialog(f, "資料庫中沒有這間餐廳");
                    }
                }
            }
        });
        buttons.add(delete);
        center.add(buttons, BorderLayout.SOUTH);
        
        //把JPanel加入視窗，顯示        
        f.setVisible(true);
    }

}
