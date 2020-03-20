package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoanResource;

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
    private JTextField rateBullet;
    private JTextField rateAmortizing;
    private JTextField rateAnnuity;
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

        JLabel labelResultAmortizing = new JLabel("Amortizing");
        labelResultAmortizing.setEnabled(true);
        labelResultAmortizing.setBounds(50, 258, 78, 16);
        contentPane.add(labelResultAmortizing);

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

        rateBullet = new JTextField();
        rateBullet.setEditable(false);
        rateBullet.setColumns(10);
        rateBullet.setBounds(270, 227, 98, 20);
        contentPane.add(rateBullet);

        rateAmortizing = new JTextField();
        rateAmortizing.setEditable(false);
        rateAmortizing.setColumns(10);
        rateAmortizing.setBounds(270, 258, 98, 20);
        contentPane.add(rateAmortizing);

        rateAnnuity = new JTextField();
        rateAnnuity.setEditable(false);
        rateAnnuity.setColumns(10);
        rateAnnuity.setBounds(270, 288, 98, 20);
        contentPane.add(rateAnnuity);

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

        JLabel lblMonthlyRate = new JLabel("Rate");
        lblMonthlyRate.setFont(new Font("Dialog", Font.BOLD, 14));
        lblMonthlyRate.setEnabled(false);
        lblMonthlyRate.setForeground(Color.BLACK);
        lblMonthlyRate.setBackground(Color.WHITE);
        lblMonthlyRate.setBounds(270, 190, 118, 16);
        contentPane.add(lblMonthlyRate);

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
                Map<String, List<Integer>> loans = myCredit.getLoans(amount, years, interest);

                List<Integer> bulletResult = loans.get("BULLET");
                List<Integer> amortizingResult = loans.get("AMORTIZING");
                List<Integer> annuityResult = loans.get("ANNUITY");

                displayInformation(bulletResult, amortizingResult, annuityResult);
                enableFields();
            }

            void displayInformation(List<Integer> bullet, List<Integer> amortizing, List<Integer> annuity) {
                int total = 0;
                int monthlyRate = 1;

                if(checkBullet.isSelected()) {
                    textBullet.setText(String.valueOf(bullet.get(total)));
                    rateBullet.setText(String.valueOf(bullet.get(monthlyRate)));

                }

                if(checkAmortizing.isSelected()) {
                    textAmortizing.setText(String.valueOf(amortizing.get(total)));
                    rateAmortizing.setText("~ " + amortizing.get(monthlyRate));
                }

                if(checkAnnuity.isSelected()) {
                    textAnnuity.setText(String.valueOf(annuity.get(total)));
                    rateAnnuity.setText(String.valueOf(annuity.get(monthlyRate)));
                }

                highlightBestOf(bullet.get(0), amortizing.get(0), annuity.get(0));
            }

            void enableFields() {
                textBullet.setEnabled(true);
                textAmortizing.setEnabled(true);
                textAnnuity.setEnabled(true);
                labelResultAmortizing.setEnabled(true);
                labelResultAnnuity.setEnabled(true);
                labelResultBullet.setEnabled(true);
                lblRepayment.setEnabled(true);
                lblTotalAmount.setEnabled(true);
                lblMonthlyRate.setEnabled(true);
            }

            void highlightBestOf(Integer bullet, Integer amortizing, Integer annuity) {

                List<Integer> a = Arrays.asList(bullet, amortizing, annuity);
                Integer bestLoan = a.stream()
                                    .sorted()
                                    .findFirst()
                                    .orElse(-1);

                if(bestLoan.equals(bullet)) {
                    textBullet.setForeground(Color.GREEN);
                    textBullet.setFont(new Font("Dialog", Font.BOLD, 12));
                } else if(bestLoan.equals(amortizing)) {
                    textAmortizing.setForeground(Color.GREEN);
                    textAmortizing.setFont(new Font("Dialog", Font.BOLD, 12));
                } else if(bestLoan.equals(annuity)) {
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
                myCredit.getLoanDownloads("BULLET");
            }
        });
        btnDownloadFileBullet.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnDownloadFileBullet.setBounds(385, 227, 114, 20);
        contentPane.add(btnDownloadFileBullet);

        JButton btnDownloadFileAmortizing = new JButton("Download Details");
        btnDownloadFileAmortizing.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                myCredit.getLoanDownloads("AMORTIZING");
            }
        });
        btnDownloadFileAmortizing.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnDownloadFileAmortizing.setBounds(385, 258, 114, 20);
        contentPane.add(btnDownloadFileAmortizing);

        JButton btnDownloadFileAnnuity = new JButton("Download Details");
        btnDownloadFileAnnuity.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                myCredit.getLoanDownloads("ANNUITY");
            }
        });
        btnDownloadFileAnnuity.setFont(new Font("Tahoma", Font.PLAIN, 9));
        btnDownloadFileAnnuity.setBounds(385, 288, 114, 20);
        contentPane.add(btnDownloadFileAnnuity);
    }
}
