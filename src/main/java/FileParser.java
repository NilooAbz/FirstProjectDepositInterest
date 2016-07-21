package main.java;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by niloofar_Abz on 7/11/2016.
 */
public class FileParser {


    public static List<Deposit> parseFile() throws IOException, SAXException, ParserConfigurationException {
        File fxmlFile = new File("input.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        List<Deposit> deposits = new ArrayList<Deposit>();

        System.out.println(fxmlFile.getAbsolutePath());
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fxmlFile);
        doc.getDocumentElement().normalize();

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("deposit");
        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String customerNumber = eElement.getElementsByTagName("customerNumber").item(0).getTextContent();
                String depositTypeString = eElement.getElementsByTagName("depositType").item(0).getTextContent();
                BigDecimal depositBalance = new BigDecimal(eElement.getElementsByTagName("depositBalance").item(0).getTextContent());
                BigDecimal durationInDays = new BigDecimal(eElement.getElementsByTagName("durationInDays").item(0).getTextContent());
                Class cls;
                try {
                    cls = Class.forName("main.java.com.convention." + depositTypeString);
                    DepositType depositType = (DepositType) cls.newInstance();
                    Deposit deposit = new Deposit(depositType, depositBalance, durationInDays, customerNumber);
                    deposit.setDurationInDays(durationInDays);
                    deposit.setDepositBalance(depositBalance);
                    deposits.add(deposit);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                    System.out.println("The deposit type is not supported");
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (BalanceException e) {
                    e.printStackTrace();
                } catch (DurationInDayException e) {
                    e.printStackTrace();
                }
            }
        }
        return deposits;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Deposit.class.getDeclaredMethods()));
    }
}