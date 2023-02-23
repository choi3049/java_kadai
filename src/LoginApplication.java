import java.util.Scanner;

public class LoginApplication {
    private static int usernum = 0;
    private static Member user[] = new Member[10];
    private static Scanner scanner = new Scanner(System.in);
    private static String inputId, inputPassword, inputNickname;
public static void main(String[] args) {
        for (int i=0;i<10;i++)
        { user[i] = new Member(); } //NullpointerException방지 배열 초기화

        boolean run = true;

        do {
            System.out.println("番号を選んでください: ");
            System.out.println("1.会員登録 2.ログイン 3.会員照会 4.会員退会 5.終了");
            System.out.print("選択 > ");

            int select = scanner.nextInt();
            if (select == 1){
                if (usernum == 10) {
                    System.out.println("会員登録失敗");
                    break;
                }
                else
                    createAccount();
            } else if (select == 2) {
                loginAccount();
            } else if (select == 3) {
                lookAccount();
            } else if (select == 4) {
                deleteAccount();
            } else {
                run = false;
            }
        }while (run);
        System.out.println("システム終了");
    }

    private static void createAccount() {
        System.out.print("IDを入力してください : ");
        inputId = scanner.next();
        user[usernum].setId(inputId);

        System.out.print("パスワードを入力してください : ");
        inputPassword = scanner.next();
        user[usernum].setPassword(inputPassword);

        System.out.print("ニックネームを入力してください : ");
        inputNickname = scanner.next();
        user[usernum].setNickname(inputNickname);

        usernum++;
    }

    private static void loginAccount() {
        System.out.print("IDを入力してください : ");
        inputId = scanner.next();

        System.out.print("パスワードを入力してください : ");
        inputPassword = scanner.next();

        int loginSuccess = 0;
        for (int i=0;i < user.length;i++){
            if (inputId.equals(user[i].getId()) && inputPassword.equals(user[i].getPassword()))
            {
                System.out.println("ログイン成功");
                System.out.println(user[i].getId() + " " + user[i].getNickname());
                loginSuccess = 1;
                break;
            }
        }
        if (loginSuccess == 0)
            System.out.println("ログイン失敗");
    }

    private static void lookAccount() {
        for (Member users : user) {
            if (users.getNickname() != null)
                System.out.println(users.getNickname());
        }
    }

    private static void deleteAccount() {
        System.out.print("IDを入力してください : ");
        inputId = scanner.next();
        System.out.print("パスワードを入力してください : ");
        inputPassword = scanner.next();
        int deleteSuccess = 0;
        for (int i=0;i < user.length;i++){
            if (inputId.equals(user[i].getId()) && inputPassword.equals(user[i].getPassword()))
            {
                System.out.println("会員退会成功");
                user[i].setId(null);
                user[i].setPassword(null);
                user[i].setNickname(null);
                deleteSuccess = 1;
            }
        }
        if (deleteSuccess == 0)
            System.out.println("会員情報が存在しません");
    }
}