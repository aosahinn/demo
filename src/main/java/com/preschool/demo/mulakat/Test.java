package com.preschool.demo.mulakat;

public class Test
{
    public static abstract class Loan
    {
        // Soyut metod
        public abstract double calculateLoan();

        // Somut metod
        public double getInterestRate()
        {
            return 5.0;
        }
    }

    public static class CommercialCreditLoan extends Loan
    {
        @Override
        public double calculateLoan()
        {
            return 21;
        }
    }

    public static class VehicleLoan extends Loan
    {
        @Override
        public double calculateLoan()
        {
            return 23;
        }
    }

    public static class HomeLoan extends Loan
    {
        @Override
        public double calculateLoan()
        {
            return 20;
        }
    }

    public static void main(String[] args)
    {
        Loan commercialLoan = new CommercialCreditLoan();
        Loan vehicleLoan = new VehicleLoan();
        Loan homeLoan = new HomeLoan();

        System.out.println("Commercial Loan: " + commercialLoan.calculateLoan());
        System.out.println("Vehicle Loan: " + vehicleLoan.calculateLoan());
        System.out.println("Home Loan: " + homeLoan.calculateLoan());

        // Somut metod kullanılabilir
        System.out.println("Interest Rate: " + commercialLoan.getInterestRate());
    }

}
