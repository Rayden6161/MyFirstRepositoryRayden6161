import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

public class Opseg {
    public static void search(double donjiOpsegProizvodnje, double gornjiOpsegProizvodnje) {
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse("cars.xml");

            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression xPathExpression = xpath.compile("//car[production-year = " + donjiOpsegProizvodnje + " or production-year = " + gornjiOpsegProizvodnje + " ]");


            NodeList cars = (NodeList) xPathExpression.evaluate(doc, XPathConstants.NODESET);
            System.out.println("\n\n" +
                    "display data about cars that meet the criteria : " + cars.getLength());


            for (int i = 0; i <cars.getLength(); i++ ) {

                Node node = cars.item ( i );

                String manufacturer = xpath.compile("./manufacturer").evaluate( node );
                System.out.println("Manufacter: " + manufacturer);
                //znaci prilikom kompajliranja pretvaramo ovo sto smo kompajlirali u cvor jer prolazimo kroz listu tih cvorova.

                String model = xpath.compile("./model").evaluate( node );
                System.out.println("Model: " + model);

                double productionYear = Double.parseDouble(xpath.compile("//production-year").evaluate( node ));
                System.out.println("Production year: " + productionYear);

                String horsepower = xpath.compile("./horsepower").evaluate( node );
                System.out.println("\tHorse Power: " + horsepower);

                double consumption = Double.parseDouble(xpath.compile("./consumption").evaluate( node ));
                System.out.println("Consumption: " + consumption);

                int  price = Integer.parseInt(xpath.compile("./price").evaluate( node ));
                System.out.println("Price: " + price);



                String carAttr = xpath.compile("//@id").evaluate( node );
                System.out.println("\tId: " + carAttr);

                System.out.println("\n");
                System.out.println("----------------------------------------------------------------------");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
