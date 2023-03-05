package login;

public class LoginApplication {

    public static void login() {

        for (int i=0;i<10;i++)
        { MemoryMemberRepository.user[i] = new Member(); } //NullpointerException방지 배열 초기화

        boolean run = true;

        do {
            System.out.println("番号を選んでください: ");
            System.out.println("1.会員登録 2.ログイン 3.会員照会 4.会員退会 5.終了");
            System.out.print("選択 > ");

            int select = MemoryMemberRepository.scanner.nextInt();
            if (select == 1){
                if (MemoryMemberRepository.usernum == 10) {
                    System.out.println("会員登録失敗");
                    break;
                }
                else
                    MemoryMemberRepository.createAccount();
            } else if (select == 2) {
                MemoryMemberRepository.loginAccount();
            } else if (select == 3) {
                MemoryMemberRepository.lookAccount();
            } else if (select == 4) {
                MemoryMemberRepository.deleteAccount();
            } else {
                run = false;
            }
        }while (run);
        System.out.println("システム終了");
    }
}