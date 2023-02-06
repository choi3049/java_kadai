import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CSVReader {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\choeb\\OneDrive\\デスクトップ\\研修\\01.課題\\2.kadai.csv";

        // ファイル存在チェック
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.print("ファイルが存在しません");
            return;
        }

        //　今日の日付
        String today;
        Date date = new Date();
        today = new SimpleDateFormat("yyyy-MM-dd").format(date);

        //　ファイル読み込み
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrayStr = line.split("\n");

                // 1行ずつ読み込む
                for (String str : arrayStr) {

                    // 100文字以上の場合
                    if (str.length() >= 100) {
                        continue;
                    }

                    // 名前、日付変更
                    if (str.contains("000") || str.contains("data")) {
                        str = str.replace("000", "choe").replace("date", today);
                    }

                    // 名前大文字変更
                    if (str.contains(":")){
                        int a = str.indexOf(":");
                        str = str.substring(0, a).toUpperCase() + str.substring(a).toLowerCase();
                    }
                    str = str.replace("#", "").trim(); // 左右空白と"#"削除

                    System.out.println(str);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
