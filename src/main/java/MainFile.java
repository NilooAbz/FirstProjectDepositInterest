package main.java;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

/**
 * Created by DotinSchool2 on 7/13/2016.
 */
public class MainFile {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
/*        System.out.println(1);
        try {
            System.out.println(2);
            throw new NullPointerException();
        } catch (NullPointerException e) {
            System.out.println(3);
        } catch(Exception e) {
            System.out.println(4);
        } finally {
            System.out.println(5);
        }
        System.out.println(6);
        */

        List<Deposit> deposits = FileParser.parseFile();

        Collections.sort(deposits);

        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        for (Deposit deposit : deposits) {
            //System.out.println(deposit.getCustomerNumber() + "#" + deposit.getPayedInterest());
            out.println(deposit.getCustomerNumber() + "#" + deposit.getPaidInterest());
        }
        out.close();
    }
}
