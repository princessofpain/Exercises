package main.java.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.java.controller.LoanResource;
import main.java.model.Loan;
import main.java.model.LoanType;

public class UI extends JFrame {

    private JPanel contentPane;
    private JTextField textAmount;
    private JTextField textYears;
    private JTextField textInterest;

    private int amount;
    private int years;
    private double interest;
    private JTextField textBullet;
    private JTextField textAmortizing;
    private JTextField textAnnuity;
    private LoanResource myCredit;
    private JCheckBox checkBullet;
    private JCheckBox checkAmortizing;
    private JCheckBox checkAnnuity;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UI frame = new UI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    private UI() {
        setTitle("Check your loan");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 525, 381);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setBounds(22, 62, 60, 14);
        contentPane.add(lblAmount);

        JLabel lblCalculateThe = new JLabel("Find the best payment method for your loan!");
        lblCalculateThe.setForeground(Color.BLACK);
        lblCalculateThe.setFont(new Font("Arial", Font.BOLD, 14));
        lblCalculateThe.setBounds(22, 26, 340, 14);
        contentPane.add(lblCalculateThe);

        textAmount = new JTextField();
        textAmount.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                removeWrong(key);
            }

            void removeWrong(int key) {
                if((key == 44) || (key >= 65 && key <= 90) || key == 46) {
                    textAmount.setBackground(new Color(240, 30, 30));
                    JOptionPane.showMessageDialog(null, "Please use the format 000");
                    String input = textAmount.getText();
                    if(input.length() < 2) {
                        textAmount.setText(input.substring(0, input.length()-1));
                    } else {
                        textAmount.setText(input);
                    }
                    textAmount.setBackground(Color.WHITE);
                }
            }
        });

        textAmount.setBounds(79, 59, 78, 20);
        contentPane.add(textAmount);
        textAmount.setColumns(10);

        textYears = new JTextField();
        textYears.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                removeWrong(key);
            }

            void removeWrong(int key) {
                if((key == 44) || (key >= 65 && key <= 90) || key == 46) {
                    textYears.setBackground(new Color(240, 30, 30));
                    JOptionPane.showMessageDialog(null, "Please use the format 000");
                    String input = textYears.getText();

                    if(input.length() > 1) {
                        textYears.setText(input.substring(0, input.length()-1));
                    } else {
                        textYears.setText(input.substring(0, 1));
                    }

                    textYears.setBackground(Color.WHITE);
                }
            }
        });
        textYears.setColumns(10);
        textYears.setBounds(79, 88, 78, 20);
        contentPane.add(textYears);

        JLabel lblYears = new JLabel("Years:");
        lblYears.setBounds(22, 91, 60, 14);
        contentPane.add(lblYears);

        JLabel lblInterest = new JLabel("Interest:");
        lblInterest.setBounds(22, 123, 60, 14);
        contentPane.add(lblInterest);

        textInterest = new JTextField();
        textInterest.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                removeWrong(key);
            }

            void removeWrong(int key) {
                if((key == 44) || (key >= 65 && key <= 90)) {
                    textInterest.setBackground(new Color(240, 30, 30));
                    JOptionPane.showMessageDialog(null, "Please use the format 0.00");
                    String input = textInterest.getText();
                    textInterest.setText(input.substring(0, input.length()-1));
                    textInterest.setBackground(Color.WHITE);
                }
            }
        });
        textInterest.setBackground(Color.WHITE);
        textInterest.setColumns(10);
        textInterest.setBounds(79, 120, 78, 20);
        contentPane.add(textInterest);

        JLabel labelResultBullet = new JLabel("Bullet");
        labelResultBullet.setEnabled(true);
        labelResultBullet.setBounds(50, 227, 78, 16);
        contentPane.add(labelResultBullet);

        JLabel labelResultAmoretizing = new JLabel("Amortizing");
        labelResultAmoretizing.setEnabled(true);
        labelResultAmoretizing.setBounds(50, 258, 78, 16);
        contentPane.add(labelResultAmoretizing);

        JLabel labelResultAnnuity = new JLabel("Annuity");
        labelResultAnnuity.setEnabled(true);
        labelResultAnnuity.setBounds(50, 290, 78, 16);
        contentPane.add(labelResultAnnuity);

        checkBullet = new JCheckBox();
        checkBullet.setSelected(true);
        checkBullet.setBounds(18, 205, 60, 60);
        checkBullet.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(checkBullet);

        checkAmortizing = new JCheckBox();
        checkAmortizing.setSelected(true);
        checkAmortizing.setBounds(18, 237, 60, 60);
        checkAmortizing.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(checkAmortizing);

        checkAnnuity = new JCheckBox();
        checkAnnuity.setSelected(true);
        checkAnnuity.setBounds(18, 268, 60, 60);
        checkAnnuity.setMargin(new Insets(0, 0, 0, 0));
        contentPane.add(checkAnnuity);

        textBullet = new JTextField();
        textBullet.setEditable(false);
        textBullet.setColumns(10);
        textBullet.setBounds(160, 227, 98, 20);
        contentPane.add(textBullet);

        textAmortizing = new JTextField();
        textAmortizing.setEditable(false);
        textAmortizing.setColumns(10);
        textAmortizing.setBounds(160, 258, 98, 20);
        contentPane.add(textAmortizing);

        textAnnuity = new JTextField();
        textAnnuity.setEditable(false);
        textAnnuity.setColumns(10);
        textAnnuity.setBounds(160, 288, 98, 20);
        contentPane.add(textAnnuity);

        JLabel lblRepayment = new JLabel("Repayment");
        lblRepayment.setFont(new Font("Dialog", Font.BOLD, 14));
        lblRepayment.setEnabled(true);
        lblRepayment.setBackground(Color.WHITE);
        lblRepayment.setForeground(Color.BLACK);
        lblRepayment.setBounds(50, 190, 98, 16);
        contentPane.add(lblRepayment);

        JLabel lblTotalAmount = new JLabel("Total Amount");
        lblTotalAmount.setFont(new Font("Dialog", Font.BOLD, 14));
        lblTotalAmount.setEnabled(false);
        lblTotalAmount.setForeground(Color.BLACK);
        lblTotalAmount.setBackground(Color.WHITE);
        lblTotalAmount.setBounds(160, 190, 118, 16);
        contentPane.add(lblTotalAmount);

        JButton btnCalculate = new JButton("Calculate ");
        btnCalculate.addActionListener(new ActionListener() {
            public void cleanUp() {
                textBullet.setText("");
                textAmortizing.setText("");
                textAnnuity.setText("");
            }

            public void actionPerformed(ActionEvent e) {
                cleanUp();
                amount = Integer.parseInt(textAmount.getText());
                years = Integer.parseInt(textYears.getText());
                interest = Double.parseDouble(textInterest.getText());

                myCredit = new LoanResource();
                List<Loan> loans = myCredit.getLoans(amount, years, interest);

                Optional<Integer> bulletResult = loans.stream()
                                            .filter(loan -> loan.getLoanType().equals(LoanType.BULLET))
                                            .findFirst()
                                            .map(Loan::getTotal)
                                            .map(BigDecimal::intValue)
                                            .filter(loan -> checkBullet.isEnabled());;

                Optional<Integer> amortizingResult = loans.stream()
                                                .filter(loan -> loan.getLoanType().equals(LoanType.AMORTIZING))
                                                .findFirst()
                                                .map(Loan::getTotal)
                                                .map(BigDecimal::intValue)
                                                .filter(loan -> checkAmortizing.isEnabled());

//                Optional<Integer> annuityResult = loans.stream()
//X                                                         .filter(loan -> loan.getLoanType().equals(LoanType.ANNUITY))
//                                                          .findFirst()
//                                                          .map(Loan::getTotal)
//                                                          .filter(loan -> checkAnnuity.isEnabled());

                displayInformation(bulletResult, amortizingResult, Optional.of(0)); //annuityResult);
                enableFields();
            }

            void displayInformation(Optional<Integer> bullet, Optional<Integer> amortizing, Optional<Integer> annuity) {
                if(checkBullet.isSelected()) {
                    textBullet.setText(unwrapResult(bullet));
                }

                if(checkAmortizing.isSelected()) {
                    textAmortizing.setText(unwrapResult(amortizing));
                }

                if(checkAnnuity.isSelected()) {
                    textAnnuity.setText(unwrapResult(annuity));
                }

                highlightBestOf(bullet, amortizing, annuity);
            }

            private String unwrapResult(Optional<Integer> result) {
                if(!result.isPresent()) {
                    return "";
                }

                // actually it's already sure here that it's not empty, but it returns Optional<x> if I don't do this
                return result.orElse(0).toString();
            }

            void enableFields() {
                textBullet.setEnabled(true);
                textAmortizing.setEnabled(true);
                textAnnuity.setEnabled(true);
                labelResultAmoretizing.setEnabled(true);
                labelResultAnnuity.setEnabled(true);
                labelResultBullet.setEnabled(true);
                lblRepayment.setEnabled(true);
                lblTotalAmount.setEnabled(true);
            }

            void highlightBestOf(Optional<Integer> bullet, Optional<Integer> amortizing, Optional<Integer> annuity) {

                List<Optional<Integer>> a = Arrays.asList(bullet, amortizing, annuity);
                Integer bestLoan = a.stream()
                                    .filter(result -> result.equals(0))
                                    .sorted()
                                    .findFirst()
                                    .flatMap(r -> r)
                                    .orElse(-1);


                if(bestLoan == bullet.orElseGet(() -> 0)) {
                    textBullet.setForeground(Color.GREEN);
                    textBullet.setFont(new Font("Dialog", Font.BOLD, 12));
                } else if(bestLoan == amortizing.orElseGet(() -> -1)) {
                    textAmortizing.setForeground(Color.GREEN);
                    textAmortizing.setFont(new Font("Dialog", Font.BOLD, 12));
                } else if(bestLoan == annuity.orElseGet(() -> -1)) {
                    textAnnuity.setForeground(Color.GREEN);
                    textAnnuity.setFont(new Font("Dialog", Font.BOLD, 12));
                }
            }
        });
        btnCalculate.setBounds(202, 72, 98, 52);
        contentPane.add(btnCalculate);

        JButton btnDownloadFileBullet = new JButton("Download details");
        btnDownloadFileBullet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //myCredit.;
            }
        });
        btnDownloadFileBullet.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnDownloadFileBullet.setBounds(385, 227, 114, 20);
        contentPane.add(btnDownloadFileBullet);

        JButton btnDownloadFileAmortizing = new JButton("Download Details");
        btnDownloadFileAmortizing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //myCredit.generateAmortizingPlanForDownload();
            }
        });
        btnDownloadFileAmortizing.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnDownloadFileAmortizing.setBounds(385, 258, 114, 20);
        contentPane.add(btnDownloadFileAmortizing);

        JButton btnDownloadFileAnnuity = new JButton("Download Details");
        btnDownloadFileAnnuity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //myCredit.generateAnnuityPlanForDownload();
            }
        });
        btnDownloadFileAnnuity.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnDownloadFileAnnuity.setBounds(385, 288, 114, 20);
        contentPane.add(btnDownloadFileAnnuity);
    }
}
