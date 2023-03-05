package cal;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {

    private JTextField inputSpace;
    //계산식의 숫자를 담을 변수 num
    private String num = "";
    //계산 기능을 구현하기 위해 ArrayList에 숫자와 연산 기호를 하나씩 구분해 담음
    private ArrayList<String> equation = new ArrayList<String>();

    public Calculator() {

        // JTextField 生成
        setLayout(null);
        inputSpace = new JTextField();
        inputSpace.setEditable(false);
        inputSpace.setBackground(Color.WHITE);
        inputSpace.setHorizontalAlignment(JTextField.RIGHT);
        inputSpace.setFont(new Font("Arial", Font.BOLD, 50));
        inputSpace.setBounds(8, 10, 270, 70);

        // ボタンパネル生成
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBounds(8, 90, 270, 235);


        // パネルにボタン生成
        String button_names[] = { "C", "÷", "×", "=", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "0" };
        JButton buttons[] = new JButton[button_names.length];

        for (int i = 0; i < button_names.length; i++) {
            buttons[i] = new JButton(button_names[i]);

            // 書体
            if (i <= 3){
                buttons[i].setFont(new Font("Arial", Font.BOLD, 13));
            } else {
                buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            }

            // ボタンの色
            if (button_names[i] == "C") buttons[i].setBackground(Color.RED);
            else if ((i >= 4 && i <= 6) || (i >= 8 && i <= 10) || (i >= 12 && i <= 14)) buttons[i].setBackground(Color.BLACK);
            else buttons[i].setBackground(Color.GRAY);

            buttons[i].setForeground(Color.WHITE); //　文字色指定
            buttons[i].setBorderPainted(false); //　枠線

            // 設定ボタン追加
            buttons[i].addActionListener(new PadActionListener());
            buttonPanel.add(buttons[i]);
        }

        add(inputSpace);
        add(buttonPanel);

        // 画面調整
        setTitle("電卓");
        setVisible(true);
        setSize(300,370);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 動作Listener
    class PadActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            // クリックしたボタン読み込み
            String operation = e.getActionCommand();

            if (operation.equals("C")) {
                inputSpace.setText("");

            } else if (operation.equals("=")) {
                String result = Double.toString(calculate(inputSpace.getText()));
                inputSpace.setText("" + result);
                num = "";

            } else {
                inputSpace.setText(inputSpace.getText() + e.getActionCommand());
            }
        }
    }

    //計算処理
    public double calculate(String inputText) {
        /**
         * 製造
         * **/
        fullTextParsing(inputText);
        return 0;
    }

    // Listで計算処理
    private void fullTextParsing(String inputText) {
        equation.clear();

        for (int i = 0; i < inputText.length(); i++) {
            char ch = inputText.charAt(i);

            if (ch == '-' || ch == '+' || ch == '×' || ch == '÷') {
                equation.add(num);
                num = "";
                equation.add(ch + "");
            } else {
                num = num + ch;
            }
        }
        equation.add(num);
    }

}