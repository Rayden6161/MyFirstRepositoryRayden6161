import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
     /*  String testUrl ="https://www.bensound.com/bensound-music/bensound-hey.mp3";
       String testLocation =System.getenv("Resources")+ "/ajp_6/bensound-hey.mp3";
       DownloadProgress test =new DownloadProgress(testUrl,testLocation);
       test.start(); */

        JFrame window =new JFrame();
        JPanel mainPanel = new JPanel()    ;
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));

        JTextField txtURL1 =new JTextField();
        mainPanel.add(txtURL1);
        JTextField txtURL2 =new JTextField();
        mainPanel.add(txtURL2);
        JTextField txtURL3 =new JTextField();
        mainPanel.add(txtURL3);

        JLabel labelURL1 =new JLabel("                      " );
        mainPanel.add(labelURL1);
        JLabel labelURL2 =new JLabel("                      " );
        mainPanel.add(labelURL2);
        JLabel labelURL3 =new JLabel("                      " );
        mainPanel.add(labelURL3);

        JButton buttonDownload = new JButton("Download files");
        mainPanel.add(buttonDownload);
        //znaci kad kliknemo na dugme buttonDownload trebalo bi da iz nasih text inputa pokupimo
        //linkove i onda da zapocnem download progress i sad ovde koristimo lambdu
        buttonDownload.addActionListener((e )-> {
            String url1 = txtURL1.getText();
            if(url1.trim().length()>0){
                DownloadProgress dp1 =new DownloadProgress(url1,getSaveLocation(url1),labelURL1);
       //znaci metodi saljemo nas url1,lokacija nam je "" blank znaci jer hocemo da napravimo
                //pomocnu metodu da dobijemo ime samog fajla iz URL-a i saljem labelu gde
                //zelim da prikazem nas download progress labelURL1...
               dp1.start();


            }
            String url2 = txtURL2.getText();
            if(url1.trim().length()>0){
                DownloadProgress dp2 =new DownloadProgress(url2,getSaveLocation(url2),labelURL2);
                //znaci metodi saljemo nas url1,lokacija nam je "" blank znaci jer hocemo da napravimo
                //pomocnu metodu da dobijemo ime samog fajla iz URL-a i saljem labelu gde
                //zelim da prikazem nas download progress labelURL1...
                dp2.start();


            }
            String url3 = txtURL3.getText();
            if(url3.trim().length()>0){
                DownloadProgress dp3 =new DownloadProgress(url3,getSaveLocation(url3),labelURL3);
                //znaci metodi saljemo nas url1,lokacija nam je "" blank znaci jer hocemo da napravimo
                //pomocnu metodu da dobijemo ime samog fajla iz URL-a i saljem labelu gde
                //zelim da prikazem nas download progress labelURL1...
                dp3.start();


            }

        });

       // getSaveLocation("https://www.bensound.com/bensound-music/bensound-hey.mp3");
        window.setSize(400,300);
        window.add(mainPanel);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);



    }
    public static String getSaveLocation(String url){
String []splitUrl  =  url.split("/") ;  //znaci ovo ce tretirati kao obican slash karakter  /
        //znaci mi iz gornjeg URLa pod tekstom moramo samo da dobijemo ime     /bensound-hey.mp3
        //zato smo odradili ovaj split
        System.out.println(Arrays.toString(splitUrl));
        String fileName =splitUrl[splitUrl.length-1];
        return System.getenv("d:\\KURS\\IT AKADEMIJA\\JAVA PROJECTS\\Resources\\"  + "\\ajp_6\\" +fileName ;
        //ovo nam daje file location

    }
}   //  d:\\KURS\\IT AKADEMIJA\\JAVA PROJECTS\\Resources\\ajp_6\\ inace ovo je path do mog foldera/