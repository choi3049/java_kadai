import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class CSVReader {
    public static void main(String[] args) {
        String fileName = "ファイルパス";

        // ファイル存在チェック
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.print("ファイルが存在しません");
            return;
        }

        //　ファイル読み込み
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrayStr = line.split(",");

                // 1行ずつ読み込む
                for (String str : arrayStr) {
                    System.out.println(str);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}