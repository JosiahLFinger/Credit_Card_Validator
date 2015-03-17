package com.josiah;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by es3062nv on 3/17/2015.
 */
public class CCValidator extends JFrame{
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JPanel rootPanel;
    private JLabel validMessageLabel;

    public CCValidator() {

        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //takes user input number and saves it to string
                String ccNumber = creditCardNumberTextField.getText();
                //runs validation on string and changes boolean to true or false
                boolean valid = ccValid(ccNumber);
                //based on boolean, displays message
                if (valid) {
                    validMessageLabel.setText("Credit card number is valid");
                } else {
                    validMessageLabel.setText("Credit card number is NOT valid");
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private static boolean ccValid(String cc) {

        //makes sure card number starts with 4 and is 16 digits long
        if (!cc.startsWith("4") || (cc.length() != 16)){
            System.out.println("Doesnt start with 4 or length wrong");
            return false;
        }

        int sum = 0;

        //maths for validating card number
        for (int i = 0; i < 16 ; i++ ) {
            int thisDigit = Integer.parseInt((cc.substring(i, i+1)));
            if (i % 2 == 1) {
                sum = sum + thisDigit;
            } else {
                int doubled = thisDigit * 2;
                if (doubled > 9 ) {
                    int toAdd = 1 + (doubled % 10);
                    sum = sum + toAdd;
                } else {
                    sum = sum + (thisDigit * 2);
                }
            }
        }

        if (sum % 10 == 0) {
            return true;
        }

        return false;

    }
}
