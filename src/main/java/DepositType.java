package main.java;

/**
 * Created by niloofar-Abz on 7/11/2016.
 */
public abstract class DepositType {
    protected double interestRate;

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
