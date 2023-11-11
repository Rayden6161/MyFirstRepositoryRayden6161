import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory =XMLInputFactory.newFactory();
        XMLEventReader reader=factory.createXMLEventReader(new FileReader("books.xml"));

        while (reader.hasNext()){
            XMLEvent event=reader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamReader.START_ELEMENT: {
                    StartElement startElement = event.asStartElement();
                    System.out.println("Start element: " + startElement.getName().toString());

                    Iterator itr = startElement.getAttributes();
                    while(itr.hasNext()){

                        Attribute attribute = (Attribute) itr.next();

                        System.out.println("attribute name:" + attribute.getName() + "\nAttribute value : " + attribute.getValue());


                    }}
                break;
                case XMLStreamReader.END_ELEMENT: {
                    EndElement endElement = event.asEndElement();
                    System.out.println("ENd element " + endElement.getName().toString());
                }
                break;
            }
        }
        }

    }

