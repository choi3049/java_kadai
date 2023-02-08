import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CSVReader {
    public static void main(String[] args) {


        File file = null;
        BufferedWriter bw = null;
        String NEWLINE = System.lineSeparator();

        //　今日の日付
        String today;
        Date date = new Date();
        today = new SimpleDateFormat("yyyyMMdd").format(date);

        String fileName = "C:\\Users\\choeb\\OneDrive\\デスクトップ\\研修\\01.課題\\3.kadai_"+ today +".csv";

        //　ファイル読み込み
        try {
            file = new File(fileName);
            bw = new BufferedWriter(new FileWriter(file));

            bw.write("佐藤");
            bw.write(NEWLINE);
            bw.write("佐藤");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
