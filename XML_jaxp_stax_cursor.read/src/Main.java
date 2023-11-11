import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
//kursor i iterator(principi...)  iliti stream model i event model

        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        Reader reader =new FileReader("books.xml");

        //cursor
//ako koristimo tip sa kursorom
        XMLStreamReader xmlStreamReader=xmlInputFactory.createXMLStreamReader(reader);

        //iterator ide ispod znaci umesto XMLStreamReadera koristimo XMLEventReader...

       // XMLEventReader xmlEventReader =xmlInputFactory.createXMLEventReader(reader);
        while(xmlStreamReader.hasNext()){ //sada je samo neophhodno da citamo kursore i pomeramo elemente...
//vraca true ili false u zavisnosti da li postoji drugi element za citanje
            switch(xmlStreamReader.next()){ //pomeramo kursor elementa i vracamo tip...
//u ovoj while petlji se obavlja parsiranje
  //znaci pomeramo kursor i citamo elemente u ovoj switch konstrukciji
                //ova next metoda vraca tip   int      koji su izrazeni kroz konstante XMLStreamReader interfejsa
                //imamo ispod jednu konstantu...

        case XMLStreamReader.START_ELEMENT :{ //ovo su konstante naseg toka...

        //System.out.println("start element- " + xmlStreamReader.getName());
            String elementName=xmlStreamReader.getName().toString();
            switch(elementName){
                case "book":
                    for(int i =0; i<xmlStreamReader.getAttributeCount();i++){

                        //znaci ovom metodom dobijamo ukupan broj atributa...
                  String attributeName=xmlStreamReader.getAttributeLocalName(i);
                  String attributeValue=xmlStreamReader.getAttributeValue(i);
                        System.out.println("Attribute name :" +attributeName );
                        System.out.println("Attribute value :" + attributeValue);

                    }
                    break;
                case "title":
                    System.out.println("title =" + xmlStreamReader.getElementText ());
                    break;

                case "author :":
                    System.out.println("author =" + xmlStreamReader.getElementText());
                    break;
            }


             }

        break; //znaci ovo je nakon start elementa(konstante) ovaj break!
        /*Takodje moram paziti ubuduce na pozicioniranje ovog break-a  jer nije prikazivao gresku a opet
        * nista nije prikazao u outputu tj procitao..
        * Ali cim sam promenio polozaj ovog break-a onda je proradilo.*/

    /*case XMLStreamReader.END_ELEMENT : //ovo su konstante naseg toka...
        System.out.println("end element- " + xmlStreamReader.getName());
        break;*/


}

        }

    }
}