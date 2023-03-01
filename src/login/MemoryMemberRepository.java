package login;

import java.util.Scanner;

public class MemoryMemberRepository implements MemberRepository{

    static int usernum = 0;
    static Member[] user = new Member[10];
    static Scanner scanner = new Scanner(System.in);
    private static String inputId, inputPassword, inputNickname;

    static void createAccount() {
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

    static void loginAccount() {
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

    static boolean lookAccount() {
        for (Member users : user) {
            if (users.getNickname() != null) {
                System.out.println(users.getNickname());
                return true;
            }
        }
        System.out.println("ログインしてください。");
        return false;
    }

    static void deleteAccount() {
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
