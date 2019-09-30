import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Downloader extends Thread {
    private String urlStr;
//    private String fileDirectory;

    public Downloader(String urlStr) {
        this.urlStr = urlStr;
//        this.fileDirectory = fileDirectory;
    }

    public void download() throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        String fileName = urlStr.substring(urlStr.lastIndexOf('/') + 1);
        FileOutputStream fis = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        int count = 0;
        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }

    @Override
    public void run() {
        try {
                download();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
