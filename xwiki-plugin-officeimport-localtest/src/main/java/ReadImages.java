import java.io.File;

import org.apache.log4j.Logger;


public class ReadImages
{
    private static final Logger logger = Logger.getLogger(ReadImages.class);
    public void saveImage(File dir) {
        if(!dir.exists()) {
            logger.error("the temp folder of images do not exist!");
            return;
        }
        File[] images = dir.listFiles();
        for(File image : images) {
            if(image.isDirectory())
                continue;
            //new BufferedReader(new FileInputStream(image));
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ReadImages reader = new ReadImages();

    }

}
