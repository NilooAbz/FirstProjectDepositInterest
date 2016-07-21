package main.java;

/**
 * Created by DotinSchool2 on 7/16/2016.
 */
public class Exceptions extends Exception{

    public class DurationInDayException extends Exception {
        public DurationInDayException(String message) {
            super(message);
        }
    }

    public class BalanceException extends Exception
    {
        public BalanceException(String message) {
            super(message);
        }
    }
}
