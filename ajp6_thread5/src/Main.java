import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JTextField txtURL1 = new JTextField();
        mainPanel.add(txtURL1);
        JTextField txtURL2 = new JTextField();
        mainPanel.add(txtURL2);
        JTextField txtURL3 = new JTextField();
        mainPanel.add(txtURL3);
        JLabel labelUrl1 = new JLabel("             ");
        mainPanel.add(labelUrl1);
        JLabel labelUrl2 = new JLabel("             ");
        mainPanel.add(labelUrl2);
        JLabel labelUrl3 = new JLabel("             ");
        mainPanel.add(labelUrl3);
        JButton buttonDownload = new JButton("Download Files");
        mainPanel.add(buttonDownload);

        buttonDownload.addActionListener((e) -> {
            String url1 = txtURL1.getText();
            if(url1.trim().length() > 0) {
                DownloadProgress dp1 = new DownloadProgress(url1,getSaveLocation(url1), labelUrl1);
                dp1.start();
            }
            String url2 = txtURL2.getText();
            if(url2.trim().length() > 0) {
                DownloadProgress dp2 = new DownloadProgress(url2,getSaveLocation(url2), labelUrl2);
                dp2.start();
            }
            String url3 = txtURL3.getText();
            if(url3.trim().length() > 0) {
                DownloadProgress dp3 = new DownloadProgress(url3,getSaveLocation(url3), labelUrl3);
                dp3.start();
            }
        });
        window.setSize(400,300);
        window.add(mainPanel);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static String getSaveLocation(String url) {
        String[] splitURL = url.split("/");
        String filename = splitURL[splitURL.length-1];
        return System.getenv("JAVA_RESOURCES") + "/ajp6/" + filename;
    }
}