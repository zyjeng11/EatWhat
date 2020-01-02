//文字模式版本
package eatwhat;

import java.io.IOException;
import java.util.Scanner;

public class EatWhatMain extends EatWhat{

    public static void main(String[] args) throws IOException {
        EatWhat ew = new EatWhat();
        ew.start();
        boolean quit = false;
        System.out.println("操作選項：\n" + "e(enter): 輸入資料\n" + "g(get): 從資料內取得抽籤結果\n"
                + "s(show): 顯示儲存的資料\n" + "d(delete): 刪除資料" + "q(quit): 離開");
        do {
            System.out.print(">>(e/g/s/d/q)：");
            Scanner sc = new Scanner(System.in);
            String doWhat = sc.nextLine();
            switch (doWhat) {
                case "e":
                    ew.setData();
                    break;
                case "g":
                    ew.getRandom();
                    break;
                case "s":
                    System.out.println(Data.hsData);
                    break;
                case "d":
                    ew.delete();
                    break;
                case "q":
                    quit = true;
                    break;
                default:
                    System.out.println("沒有此項功能，請輸入enter/getRandom/show/remove");
            }
        } while (!quit);

        ew.end();
        System.out.println("執行結束");
    }
    
}
