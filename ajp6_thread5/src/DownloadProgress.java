import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class DownloadProgress extends Thread {
    private String url;
    private String location;
    private JLabel labela;

    public DownloadProgress(String url, String location, JLabel labela) {
        this.url = url;
        this.location = location;
        this.labela = labela;
    }

    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(location)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            long totalBytes = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                totalBytes += bytesRead;
                labela.setText(String.format("Downloaded: %.2f MB", totalBytes / 1048576.0));
                sleep(20);
            }
            labela.setText("Download complete!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
