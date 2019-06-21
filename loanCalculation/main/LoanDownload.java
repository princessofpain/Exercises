package main;

public class LoanDownload extends Loan {
    public LoanDownload(Loan loan) {
        super(loan.interest, loan.years, loan.amount);
    }

    public void generateBulletPlanForDownload() {
        GenerateXMLFile generator = new GenerateXMLFile();
        generator.generateXML("Bullet Repayment", calculateBullet());
    }

    public void generateAmortizingPlanForDownload(String loanType) {
        GenerateXMLFile generator = new GenerateXMLFile();
        generator.generateXML("Amortizing Repayment", calculateAmortizing());
    }

    public void generateAnnuityPlanForDownload() {
        GenerateXMLFile generator = new GenerateXMLFile();
        generator.generateXML("Annuity Repayment", calculateAnnuity());
    }
}
