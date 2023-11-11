import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public Main() throws XPathExpressionException, FileNotFoundException {
    }

    public static void main(String[] args) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
//parsiranje
        DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true);
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document doc= db.parse("catalog.xml");

       /* Element root =doc.getDocumentElement();

NodeList books =root.getElementsByTagName("catalog");
        for (int i = 0; i < books.getLength(); i++) {
   Node book =books.item(i);
            System.out.println("price  : " +book.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            System.out.println("publish_date  : " +book.getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            }
*/
        //adresiranje


        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath =xPathFactory.newXPath();
        XPathExpression xPathExpression = xPath.compile("//book[price > 10 and translate(publish_date, '-', '') > 20050101]");
        Element root =doc.getDocumentElement();

      /*  File  file = new File("catalog.xml");
        InputSource inputSource =new InputSource(new FileInputStream(file));*/
        Object object =xPathExpression.evaluate(doc, XPathConstants.NODESET);

        NodeList nodelist=(NodeList)object;

        for (int i = 0; i <nodelist.getLength() ; i++) {
            System.out.println("Element : " + nodelist.item(i).getNodeName());//ime cvora
            System.out.println("author :" +nodelist.item(i).getFirstChild().getTextContent());
            System.out.println("title :" +nodelist.item(i).getFirstChild().getNextSibling().getTextContent());
            System.out.println("genre :" +nodelist.item(i).getFirstChild().getNextSibling().getNextSibling().getTextContent());
            System.out.println("price :" +nodelist.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            System.out.println("publish date :" +nodelist.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
            System.out.println("Description :" +nodelist.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent() + "\n\n\n");

        }
        }

        }

