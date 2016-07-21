package main.java;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created by niloofar-Abz on 7/10/2016.
 */

public class Deposit implements Comparable<Deposit> {

    String customerNumber;
    BigDecimal durationInDays;
    BigDecimal depositBalance;
    BigDecimal paidInterest;
    DepositType depositType;

    public Deposit(DepositType depositType, BigDecimal depositBalance, BigDecimal durationInDays, String customerNumber) {
        this.depositType = depositType;
        this.depositBalance = depositBalance;
        this.durationInDays = durationInDays;
        this.customerNumber = customerNumber;
        setPayedInterest();
    }


    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public BigDecimal getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(BigDecimal durationInDays) throws DurationInDayException {
        if (durationInDays.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DurationInDayException("The Duration in days is zero or negetive");
        }
        else {
            this.durationInDays = durationInDays;
        }
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) throws BalanceException{
        int depositBalanceCompare = depositBalance.compareTo(BigDecimal.valueOf(0));
        if (depositBalanceCompare == -1) {
            throw new BalanceException("The Deposit Balance is less than zero");
        }
        else {
            this.depositBalance = depositBalance;
        }
    }

    public BigDecimal getPaidInterest() {
        return paidInterest;
    }

    public BigDecimal setPayedInterest() {
        paidInterest = new BigDecimal(depositType.getInterestRate()).multiply(depositBalance).multiply(durationInDays).divide(new BigDecimal(36500), RoundingMode.HALF_UP);
        //paidInterest = new BigDecimal(depositType.interestRate).multiply(depositBalance).multiply(durationInDays).divide(new BigDecimal(36500), RoundingMode.HALF_UP);
        return paidInterest;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public int compareTo(Deposit deposit) {
        //descending order
        //int compareQuantity = deposit.getPaidInterest().compareTo(paidInterest);
        //ascending order
        return -1 * paidInterest.compareTo(deposit.getPaidInterest());
    }
}
