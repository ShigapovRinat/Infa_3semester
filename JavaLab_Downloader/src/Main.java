public class Main {

//    System.getProperty("user.dir")

    public static void main(String[] args) {
        Downloader[] downloaders = new Downloader[args.length];
        for (int i = 0; i < args.length; i++) {
            downloaders[i] = new Downloader(args[i], i + 1 + "");
            downloaders[i].start();
        }
        for (int i = 0; i < downloaders.length; i++) {
            try {
                downloaders[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
