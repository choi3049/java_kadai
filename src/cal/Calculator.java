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
    private String num = "";
    private String prev_operation = "";
    private ArrayList<String> equation = new ArrayList<String>();

    public Calculator() {
        setLayout(null);

        // JTextField 生成
        inputSpace = new JTextField();
        inputSpace.setEditable(false);
        inputSpace.setBackground(Color.WHITE);
        inputSpace.setHorizontalAlignment(JTextField.RIGHT);
        inputSpace.setFont(new Font("Arial", Font.BOLD, 50));
        inputSpace.setBounds(8, 10, 270, 70);

        // ボタンパネル生成
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBounds(8, 90, 270, 235);

        // パネルにボタン生成
        String button_names[] = { "abs", "ceil", "sqrt", ".", "C", "÷", "×", "=", "7",
                "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "0" };
        JButton buttons[] = new JButton[button_names.length];

        for (int i = 0; i < button_names.length; i++) {
            buttons[i] = new JButton(button_names[i]);

            // 書体
            if (i <= 3){
                buttons[i].setFont(new Font("Arial", Font.BOLD, 13));
            } else {
                buttons[i].setFont(new Font("Arial", Font.BOLD, 20)); }

            // ボタンの色
            if (button_names[i] == "C") buttons[i].setBackground(Color.RED);
            else if ((i >= 8 && i <= 10) || (i >= 12 && i <= 14) || (i >= 16 && i <= 18)) buttons[i].setBackground(Color.BLACK);
            else buttons[i].setBackground(Color.GRAY);

            buttons[i].setForeground(Color.WHITE);  //　文字色指定
            buttons[i].setBorderPainted(false);  //　枠線

            // ボタン追加
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

    // 動作処理
    class PadActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String operation = e.getActionCommand();
            String result = "";

            if (operation.equals("C")) {
                inputSpace.setText("");
            } else if (operation.equals("=")) {
                result = Double.toString(calculate(inputSpace.getText()));
                inputSpace.setText("" + result);
                num = "";

            } else if (operation.equals("+") || operation.equals("-") || operation.equals("×") || operation.equals("÷")) {
                if (inputSpace.getText().equals("") && operation.equals("-")) {
                    result = inputSpace.getText() + e.getActionCommand();
                    inputSpace.setText(result);

                } else if (!inputSpace.getText().equals("") && !prev_operation.equals("+") && !prev_operation.equals("-") && !prev_operation.equals("×") && !prev_operation.equals("÷")) {
                    result = inputSpace.getText() + e.getActionCommand();
                    inputSpace.setText(result);
                }

                // 機能追加
            } else if (operation.equals("abs") || operation.equals("ceil") || operation.equals("sqrt")) {
                result = inputSpace.getText();
                String test = Double.toString(calculate2(result));
                inputSpace.setText("" + test);

            } else {
                result = inputSpace.getText() + e.getActionCommand();
                inputSpace.setText(result);
            }


            prev_operation = e.getActionCommand();
        }
    }


    private void fullTextParsing(String inputText) {
        equation.clear();

        //계산식의 글자를 하나하나 거쳐감
        for (int i = 0; i < inputText.length(); i++) {
            char ch = inputText.charAt(i);

            //연산기호가 나오면 ArrayList에 추가되고 초기화
            if (ch == '-' || ch == '+' || ch == '×' || ch == '÷') {
                equation.add(num);
                num = "";
                equation.add(ch + "");
            } else {
                num = num + ch;
            }
        }
        equation.add(num);
        equation.remove("");
    }

    public double calculate(String inputText) {
        fullTextParsing(inputText);

        double prev = 0;
        double current = 0;
        String mode = "";

        for (int i = 0; i < equation.size(); i++) {
            String s = equation.get(i);

            if (s.equals("+")) {
                mode = "add";
            } else if (s.equals("-")) {
                mode = "sub";
            } else if (s.equals("×")) {
                mode = "mul";
            } else if (s.equals("÷")) {
                mode = "div";
            } else {
                //전에 있던 연산자가 곱셈 혹은 나눗셈이고 현재 인덱스의 값이 숫자일 때 연산 진행
                if ((mode.equals("mul") || mode.equals("div")) && !s.equals("+") && !s.equals("-") && !s.equals("×") && !s.equals("÷")) {
                    Double one = Double.parseDouble(equation.get(i - 2));
                    Double two = Double.parseDouble(equation.get(i));
                    Double result = 0.0;

                    //mode에 따라서 계산
                    if (mode.equals("mul")) {
                        result = one * two;
                    } else if (mode.equals("div")) {
                        result = one / two;
                    }
                    //result값을 ArrayList에 추가
                    equation.add(i + 1, Double.toString(result));

                    for (int j = 0; j < 3; j++) {
                        equation.remove(i - 2);
                    }

                    //예를 들어 3 + 5 x 6에서  3 + 30이 되었으므로 인덱스를 2만큼 되돌아감
                    i -= 2;	// 결과값이 생긴 인덱스로 이동
                }
            }
        }	// 곱셈 나눗셈을 먼저 계산한다

        //+일경우 add, -일경우 sub
        for (String s : equation) {
            if (s.equals("+")) {
                mode = "add";
            } else if (s.equals("-")) {
                mode = "sub";

                //곱셈, 나눗셈 연산 삭제됨
            }  else {
                //숫자일 경우 문자열을 Double로 형변환
                current = Double.parseDouble(s);

                //mode값에 따라 처리, prev는 계속 계산값이 갱신됨
                if (mode.equals("add")) {
                    prev += current;
                } else if (mode.equals("sub")) {
                    prev -= current;
                } else {
                    prev = current;
                }
            }
            //소수점 여섯번 째 자리에서 반올림 -> 화면 표시 제한이 있기때문
            prev = Math.round(prev * 1000) / 1000.0;
        }

        //값 반환
        return prev;
    }

    public double calculate2(String inputText) {
        double prev = 0;
        prev = Math.abs(Double.parseDouble(inputText));
        return prev;
    }
}