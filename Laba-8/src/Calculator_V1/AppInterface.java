package Calculator_V1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import javax.swing.border.Border;
public class AppInterface extends JFrame{

    //Кнопки с 0-9
    private javax.swing.JButton button0;
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    private javax.swing.JButton button3;
    private javax.swing.JButton button4;
    private javax.swing.JButton button5;
    private javax.swing.JButton button6;
    private javax.swing.JButton button7;
    private javax.swing.JButton button8;
    private javax.swing.JButton button9;

    //Кнопки + - * / корень степень точка = С
    private javax.swing.JButton buttonPlus;
    private javax.swing.JButton buttonMinus;
    private javax.swing.JButton buttonPlusMinus;

    private javax.swing.JButton buttonMultiplication;
    private javax.swing.JButton buttonDivision;
    private javax.swing.JButton buttonSqrt;
    private javax.swing.JButton buttonPow;
    private javax.swing.JButton buttonDot;
    private javax.swing.JButton buttonCalculate;
    private javax.swing.JButton buttonClear;

    private javax.swing.JTextArea textArea;

    private DataCalculator dataCalculator;

    private boolean flagDot = false;
    private boolean flagOperation = false;
    private boolean flagResult = false;

    public AppInterface()
    {
        initComponents();
    }

    private void initComponents()
    {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        button1 = createButton("1", 5, 215, null);
        panel.add(button1);

        button2 = createButton("2", 75, 215, null);
        panel.add(button2);

        button3 = createButton("3", 145, 215, null);
        panel.add(button3);

        button4 = createButton("4", 5, 160, null);
        panel.add(button4);

        button5 = createButton("5", 75, 160, null);
        panel.add(button5);

        button6 = createButton("6", 145, 160, null);
        panel.add(button6);

        button7 = createButton("7", 5, 105, null);
        panel.add(button7);

        button8 = createButton("8", 75, 105, null);
        panel.add(button8);

        button9 = createButton("9", 145, 105, null);
        panel.add(button9);

        button0 = createButton("0", 75, 270, null);
        panel.add(button0);

        buttonPlusMinus = createButton("/-", 5, 270, this::buttonPlusMinusf);
        panel.add(buttonPlusMinus);

        buttonDot = createButton(".", 145, 270, this::buttonDotActionPerformed);
        panel.add(buttonDot);

        buttonClear = createButton("C", 5, 50, this::buttonClearActionPerformed);
        buttonClear.setForeground(new Color(198, 55, 41));

        panel.add(buttonClear);

        buttonPlus = createButton("+", 215, 215, evt -> buttonPlusMinusMulPowActionPerformed('+'));
        panel.add(buttonPlus);

        buttonMultiplication = createButton("*", 215, 105, evt -> buttonPlusMinusMulPowActionPerformed('*'));
        panel.add(buttonMultiplication);

        buttonDivision = createButton("/", 215, 50, this::buttonDivActionPerformed);
        panel.add(buttonDivision);

        buttonMinus = createButton("-", 215, 160, evt -> buttonPlusMinusMulPowActionPerformed('-'));
        panel.add(buttonMinus);

        buttonSqrt = createButton("√", 75, 50, this::buttonSqrtActionPerformed);
        panel.add(buttonSqrt);

        buttonPow = createButton("^", 145, 50, evt -> buttonPlusMinusMulPowActionPerformed('^'));
        panel.add(buttonPow);

        buttonCalculate = createButton("=", 215, 270, this::buttonEquallyActionPerformed);
        panel.add(buttonCalculate);

        textArea = new javax.swing.JTextArea();
        textArea.setBounds(5, 5, 270, 40);
        textArea.setText("0");
        textArea.setFont(new Font("ARIAL", Font.PLAIN, 30));
        panel.add(textArea);
        textArea.setEditable(false);
        textArea.addKeyListener(new KeyListener()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getKeyChar())
                {
                    case '1' -> button1.doClick();
                    case '2' -> button2.doClick();
                    case '3' -> button3.doClick();
                    case '4' -> button4.doClick();
                    case '5' -> button5.doClick();
                    case '6' -> button6.doClick();
                    case '7' -> button7.doClick();
                    case '8' -> button8.doClick();
                    case '9' -> button9.doClick();
                    case '0' -> button0.doClick();
                    case '-' -> buttonMinus.doClick();
                    case '+' -> buttonPlus.doClick();
                    case '*' -> buttonMultiplication.doClick();
                    case '/' -> buttonDivision.doClick();
                    case '=' -> buttonCalculate.doClick();
                    case '^' -> buttonPow.doClick();
                    case '.' -> buttonDot.doClick();
                    case 's' -> buttonSqrt.doClick();
                    case KeyEvent.VK_DELETE -> buttonClear.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {}

            @Override
            public void keyTyped(KeyEvent e)
            {}

        });

        dataCalculator = new DataCalculator();

        getContentPane().add(panel);
        setPreferredSize(new Dimension(295, 360));
    }

    private void buttonDotActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (!flagDot)
        {
            textArea.setText(textArea.getText() + ".");
            flagDot = true;
        }
    }
    private void buttonPlusMinusf(java.awt.event.ActionEvent evt)
    {
        textArea.setText("-");
    }
    private void buttonPlusMinusMulPowActionPerformed(char symbol)
    {
        if (isDivisionByZero())
        {
            if (flagResult)
            {
                dataCalculator.setValue2(Double.parseDouble(textArea.getText()));
                textArea.setText(dataCalculator.calculate());
            }
            else
            {
                flagResult = true;
            }
        }
        dataCalculator.setValue1(Double.parseDouble(textArea.getText()));

        if (symbol == '+') { dataCalculator.setOperator('+'); }
        else if (symbol == '-') { dataCalculator.setOperator('-'); }
        else if (symbol == '*') { dataCalculator.setOperator('*'); }
        else if (symbol == '^') { dataCalculator.setOperator('^'); }

        flagOperation = true;
        flagDot = false;
    }

    private void buttonDivActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (flagResult)
        {
            dataCalculator.setValue2(Double.parseDouble(textArea.getText()));
            if (dataCalculator.getValue2() == 0)
            {
                JOptionPane.showMessageDialog(null, "Ошибка! Деление на 0!");
                textArea.setText("0");
                flagDot = false;
                flagResult = false;
                dataCalculator.clear();
            }
            else
            {
                dataCalculator.setValue2(Double.parseDouble(textArea.getText()));
                textArea.setText(dataCalculator.calculate());
            }
        }
        else
        {
            flagResult = true;
        }
        dataCalculator.setValue1(Double.parseDouble(textArea.getText()));
        dataCalculator.setOperator('/');
        flagOperation = true;
        flagDot = false;
    }

    private void buttonEquallyActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (flagResult)
        {
            dataCalculator.setValue2(Double.parseDouble(textArea.getText()));
            if (dataCalculator.getValue2() == 0 && dataCalculator.getOperator() == '/')
            {
                JOptionPane.showMessageDialog(null, "Ошибка! Деление на 0!");
                textArea.setText("0");
                flagDot = false;
                flagResult = false;
                dataCalculator.clear();
            }
            else
            {
                textArea.setText(dataCalculator.calculate());
                dataCalculator.setValue1(Double.parseDouble(textArea.getText()));
            }
        }
        flagOperation = true;
        flagDot = true;
        flagResult = false;
    }

    private void buttonSqrtActionPerformed(java.awt.event.ActionEvent evt)
    {
        if (isDivisionByZero())
        {
            if (Double.parseDouble(textArea.getText()) < 0)
            {
                JOptionPane.showMessageDialog(null, "Нельзя вычислить корень из отрицательного числа!");
                textArea.setText("0");
                flagDot = false;
                flagResult = false;
                dataCalculator.clear();
            }
            else
            {
                dataCalculator.setValue2(Double.parseDouble(textArea.getText()));
                dataCalculator.setOperator('s');
                textArea.setText(dataCalculator.calculate());
                flagDot = false;
            }
        }
    }

    private void buttonClearActionPerformed(java.awt.event.ActionEvent evt)
    {
        textArea.setText("0");
        flagDot = false;
        flagResult = false;
        dataCalculator.clear();
    }

    private boolean isDivisionByZero()
    {
        if (("0".equals(textArea.getText())) && dataCalculator.getOperator() == '/')
        {
            JOptionPane.showMessageDialog(null, "Деление на 0 невозможно!");
            textArea.setText("0");
            flagDot = false;
            flagResult = false;
            dataCalculator.clear();
            return false;
        }
        return true;
    }

    private JButton createButton(String text, int x, int y, ActionListener listener)
    {

        JButton button = new JButton();
        button.setText(text);
        button.setBackground(new Color(219, 226, 234));
//        if(text.equals("C")) button.setBounds(x, y, 130, 50);
        button.setForeground(new Color(26, 35, 52));

        button.setBounds(x, y, 60, 50);
        button.setFont(new Font("ARIAL", Font.PLAIN, 30));
        button.addActionListener(Objects.requireNonNullElseGet(listener, () -> new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                buttonActionPerformed(text);
            }
        }));

        return button;
    }

    private void deleteInsignificantZero()
    {
        if (textArea.getText().equals("0"))
        {
            textArea.setText("");
        }
    }

    private void buttonActionPerformed(String text)
    {
        deleteInsignificantZero();
        if (flagOperation)
        {
            textArea.setText(text);
            flagOperation = false;
        }
        else
        {
            textArea.setText(textArea.getText() + text);
        }
    }
}
