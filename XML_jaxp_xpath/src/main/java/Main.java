import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.xpath.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws XPathExpressionException, FileNotFoundException {
        //posluzicemo se funkcionalnostima iz dom biblioteke...
        XPathFactory xPathFactory= XPathFactory.newInstance();

        XPath xpAth=xPathFactory.newXPath();
//   XPathExpression xPathExpression = xpAth.compile("//book/title");
//drugi nacin za linkovanje,rezultat identican kao ispod...Znaci // predstavlja root element u ovom slucaju to je books..
//a moguce je cak i ukloniti i ovaj book elemenat,rezultat bi bio isti...

// XPathExpression xPathExpression = xpAth.compile("books/book/title"); //znaci na ovaj nacin pristupamo book elementima unutar books elementa,znaci kad
//hocemo da linkujemo na podelement onda parametrizujemo   /    izmedju elemenata
//sad smo selektovali i title unutar books.xml

//XPathExpression xPathExpression = xpAth.compile("//book/@id");
//za pristup atributima zeljenog elementa...

//   XPathExpression xPathExpression = xpAth.compile("//book/@*");
//mogli smo da napisemo umesto @id recimo @*(ovo je joker)i ispisuje sve atribute book elementa
//




//   XPathExpression xPathExpression = xpAth.compile("book[1]");
//PREDIKATI znaci koriscenjem predikata mozemo doci do samo jedne knjige

//ovim ispisujemo sve nazive knjige gde je broj stranica preko 200
//  XPathExpression xPathExpression = xpAth.compile("//book[pages>200]/title");


      //sad hocemo da ispisemo autora prve dve knjige
        XPathExpression xPathExpression = xpAth.compile("//book[position()<=2]/author |  //book[last()]/author");
        //ovim selektujem prvog   i(|)   zadnjeg autora.




        File file = new File("books.xml");

        InputSource  inputSource=new InputSource(new FileInputStream(file)); //u tok dodajemo fajl

        //da bi izvrsili XPath upit na ovom dokument(books.xml) i to postizemo metodom evaluate xPathExpression objekta

        Object object =xPathExpression.evaluate(inputSource,XPathConstants.NODESET); //ovo su te dom funkcionalnosti
        //ovaj cemo objekat kastovati u kolekciju cvorova

        NodeList nodeList=(NodeList) object; //vrsimo kastovanje objekta

        for (int i = 0; i <nodeList.getLength() ; i++) {
            System.out.println("node name: " + nodeList.item(i).getNodeName());//ime cvora
            System.out.println("node name: " + nodeList.item(i).getFirstChild().getNodeValue());//procitacemo iz liste vrednost cvora
//ide ovaj getFirstChild jer je sadrzaj elementa(cvora)sadrzan u jos jednom podsloju.
            //znaci do cvora dolazimo metodom getFirstChild(U ovoj situaciji) i njoj prosledjujemo metodu za vrednost cvora.

            //takodje smo moglil upotrebiti getLastChild jer je to jedini pod Node ...
            System.out.println(); //prelaz u novi red;

             //da bi smo u ovom elementu naveli koreni cvor mozemo navesti njegov naziv kao books,znaci ispod,zapravo ovo je kod od iznad,skoro skroz pri vrhu stranice ove...
         //   XPathExpression xPathExpression = xpAth.compile("books");

        }

    }
}
