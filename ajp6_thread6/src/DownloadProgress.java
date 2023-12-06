import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;



public class DownloadProgress extends Thread{
    private String url;
    private String location;
    private JLabel labela;
    public DownloadProgress(String url,String location,JLabel labela){
        this.location=location;
        this.url  = url;
        this.labela=labela;

    }
    public void run(){

        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             /*da bi buffer mogao da radi on ce uzeti ovaj link pa ce ucitavati na svakih 1024 dole
             * u dataBuffer i onda mozemo ispod da vidimo koliko je procitano u  byteRead*/
             FileOutputStream fileOutputStream = new FileOutputStream(location)) {
            System.out.println("Filr transfer started " + url);

            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            long totalBytes=0; //na total bytes dodam broj ucitanih bajtova
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {//najveci buffer koji moze da prihvati je 1024 bajta.
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                        totalBytes +=bytesRead  ;
                        labela.setText(String.format("Downloaded : %.2f",totalBytes/1048576.0)); //1024 *1024
            }//obavezno stavimo zarez  1048576.0 da bi bila realna vrednost
            //znc jednom kad mnozimo sa 1024 dobicemo kb a jos jednom kad mnozimo sa 1024 dobicemo MB...
            //BAJTE kad podelim sa 1048576.0 dobijem koliko je MB
            labela.setText("Download complete!");
            System.out.println("File saved to location "+ location );

        } catch (IOException e) {
            e.printStackTrace();
        }

        }

    }

