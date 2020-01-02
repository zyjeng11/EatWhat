package eatwhat;

import java.io.*;
import java.util.*;

public class EatWhat {

    protected void start() throws IOException {
        File tempFile = new File(".\\EatWhatData.txt");
        boolean exists = tempFile.exists();
        StringBuilder sb = new StringBuilder();
        if (!exists) {
            FileWriter fw = new FileWriter(".\\EatWhatData.txt");
            fw.close();
        } else {
            FileReader fr = new FileReader(".\\EatWhatData.txt");

            while (fr.ready()) {
                char c = (char) fr.read();
                sb.append(c);
            }
            fr.close();
        }
        
        if (sb.length() > 0) {
            String[] sa = sb.toString().trim().split(",");
            for (int i = 0; i < sa.length; i++) {
                sa[i] = sa[i].trim();
            }
            Data.hsData = new HashSet<String>(Arrays.asList(sa));            
        } else {
            Data.hsData = new HashSet<String>();
        }

    }

    protected void setData() {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入餐廳名：");
        String str = sc.nextLine();
        Data.hsData.add(str);
    }

    protected String get() {
        Iterator<String> it = Data.hsData.iterator();
        int hsLength = Data.hsData.size();
        int r = (int) (Math.random() * hsLength);
        for (int i = 0; i < hsLength; i++) {
            if (i == r) {
                return it.next();
            }
            it.next();
        }
        return null;
    }

    protected void getRandom() {
        Iterator<String> it = Data.hsData.iterator();
        int hsLength = Data.hsData.size();
        int r = (int) (Math.random() * hsLength);
        for (int i = 0; i < hsLength; i++) {
            if (i == r) {
                System.out.println(it.next());
                break;
            }
            it.next();
        }
    }

    protected void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入要刪除的項目：");
        String str = sc.nextLine();
        boolean dl = Data.hsData.remove(str);
        if (dl) {
            System.out.println("刪除成功");
        } else {
            System.out.println("資料中沒有這個餐廳");
        }
    }

    protected void end() throws IOException {
        FileWriter fw = new FileWriter(".\\EatWhatData.txt");
        if (!Data.hsData.isEmpty()) {
            String save = Data.hsData.toString();
            save = save.replaceAll("\\[", "").replaceAll("\\]", "");
            fw.write(save);
        }
        fw.close();
    }
}
