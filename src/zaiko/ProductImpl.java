package zaiko;

import login.Member;
import login.MemoryMemberRepository;

public class ProductImpl {

    public static void productMain(){
        boolean run = true;

        do {
        System.out.println("番号を選んでください: ");
        System.out.println("1.商品リスト 2.購入 3.商品管理 4.終了");
        System.out.print("選択 > ");

        int select = MemoryProductRepository.scanner.nextInt();
        if (select == 1){
            MemoryProductRepository.createAccount();
        } else if (select == 2) {
            MemoryProductRepository.loginAccount();
        } else if (select == 3) {
            MemoryProductRepository.lookAccount();
        } else {
            run = false;
        }
        }while (run);
        System.out.println("システム終了");
    }
}
