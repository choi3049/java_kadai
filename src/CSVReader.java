import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CSVReader {
    public static void main(String[] args) {

        List<String> listA = new ArrayList<String>();

        String fileName = "C:\\Users\\choeb\\OneDrive\\デスクトップ\\研修\\01.課題\\1.csv_kadai.csv";

        // ファイル存在チェック
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.print("ファイルが存在しません");
            return;
        }

        //　今日の日付
        String today;
        Date date = new Date();
        today = new SimpleDateFormat("yyyyMMdd").format(date);

        //　ファイル読み込み
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] arrayStr = line.split(",");

                // 1行ずつ読み込む
                for (String str : arrayStr) {
                    if (str.contains("park")) {
                        listA.add(str);
                        System.out.println(str);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        // ファイル生成変数定義
        // ファイル生成変数定義
        File file_w = null;
        BufferedWriter bw = null;
        String NEWLINE = System.lineSeparator();

        String fileName_w = "C:\\Users\\choeb\\OneDrive\\デスクトップ\\研修\\01.課題\\3.kadai_"+ today +".csv";

        //　ファイル書き込み
        try {
            file_w = new File(fileName_w);
            bw = new BufferedWriter(new FileWriter(file_w));

            for(String text :listA ) {
                bw.write(text);
                bw.write(NEWLINE);
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
