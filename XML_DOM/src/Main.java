import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        DocumentBuilderFactory dbf= DocumentBuilderFactory.newInstance();
        dbf.setIgnoringElementContentWhitespace(true); //aha ovde je bio problem sto nije htelo
        //da prikazuje imena knjiga i autora u outputu...
        //ZNACI OVO SMO STAVILI NA TRUE JER PRILIKOM PARSIRANJA JE PARSIRAO I PRAZNA MESTA TJ
        //TRETIRAO IH JE KAO ELEMENTE I ZATO NIJE MOGLO DA SE ISPARSIRA PA JE PRIJAVLJIVAO GRESKU
        //OVOM NAREDBOM SMO ODREDILI IGNORISANJE TIH PRAZNIH MESTA A MOGLI SMO JEDNOSTAVNO
        //DA SVE ELEMENTE IZ books.xml stavimo u jedan red(znaci bez praznih mesta i prelazak u novi red).

        DocumentBuilder db =dbf.newDocumentBuilder();

      Document doc=  db.parse("books.xml"); //dokument predstavlja dokument u formi stabla sa kojim smo se vec upoznali
//posle ove linije dokument ce biti parsiran i smesten u memoriju.
     //Preostaje nam da iz ovog objekta izvucemo informacije koje nas interesuju...
//NODE JE GLAVNI ELEMENT U XML-U I NA VRHU JE HIJERARHIJE

        Element root =doc.getDocumentElement();
        //znaci jasno nam je da zelimo da dodjemo do korenog element a to se postice metodom getDocumentElement();

        // System.out.println(root.getNodeName()); //metoda vraca ime cvora
        //System.out.println(root.getNodeType());//tip cvora

        /*System.out.println(root.getFirstChild().getNextSibling().getTextContent());
        Element book=doc.getElementById("02");
        System.out.println(book.getTextContent());*/

       /* System.out.println(root.getElementsByTagName("book"));
        NodeList books=root.getElementsByTagName("book"); //kolekcija cvorova koje hocemo da pretvorimo u objekte
        for(int i= 0;i<books.getLength();i++){ //prodjemo kroz tu listu
            Node book = books.item(i);//znaci svaki clan ove kolekcije se tretira kao jedan cvor
            //System.out.println(book.getTextContent()); //prikaz sadrzaja.
            System.out.println("book name: " +book.getFirstChild().getTextContent());
            System.out.println("Author: " + book.getLastChild().getTextContent());
            System.out.println("book id:  " + book.getAttributes().item(0).getNodeValue());
            System.out.println("isbn:  " + book.getAttributes().item(1).getNodeValue()); */

        //znaci ovo iznad smo zaomentarisali jer bi ispod pravilo problem.

   Element book = doc.createElement("book");
        Element title = doc.createElement("title");
        Element author = doc.createElement("author");
        book.appendChild(title) ;
        book.appendChild(author);

        root.appendChild(book); //znaci ovo sve je cista hijerarhija.

        book.setAttribute("id","01");
        book.setAttribute("isbn","333");

        title.setTextContent("the caves full of rocks...");
        author.setTextContent("Jovica Kozic je kurac od pevaca!");

        DOMSource xmlDoc =new DOMSource(doc); //Ovaj doc je nas books.xml dokument od gore
        //Znaci sa hocemo da izvrismo kreiranje ili mozda upis u neki fajl.

        String  outXML= "books_edit.xml";

        StreamResult  result= new StreamResult(new FileOutputStream(outXML)); //znaci u ouput posaljemo novi fajl za upis ili kreiranje.

        TransformerFactory tf =TransformerFactory.newInstance();
        Transformer t =tf.newTransformer();

        t.transform(xmlDoc,result); //znaci na kraju nam je kreiran books_edit.xml

        }

    }
