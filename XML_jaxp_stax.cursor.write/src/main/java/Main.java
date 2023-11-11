import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, XMLStreamException {
        XMLOutputFactory factory =XMLOutputFactory.newInstance();
        XMLStreamWriter writer =factory.createXMLStreamWriter(new FileWriter("output.xml"));

 writer.writeStartDocument();
 writer.writeStartElement("books");
 writer.writeStartElement("book");
 writer.writeAttribute("id","01");
 writer.writeAttribute("isbn","12345");

 writer.writeStartElement("title");
 writer.writeCharacters("childhoods end");
 writer.writeEndElement();

        writer.writeStartElement("author");
      writer.writeCharacters("Artur Clarke");
       writer.writeEndElement();

       writer.writeEndElement();  //zatvarajuci elementi,stvar jasna.
       writer.writeEndElement();

       writer.flush();
       writer.close();



    }
}
