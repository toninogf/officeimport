import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;


public class SaveImage
{
    
    private static final Logger logger = Logger.getLogger(SaveImage.class);
    public void saveImage(File dir) throws IOException
    {
        if (!dir.exists()) {
            logger.error("the temp folder of images do not exist!");
            return;
        }
        File[] images = dir.listFiles();
        for (File image : images) {
            if (image.isDirectory())
                continue;
            System.out.println("save image:" + image.getAbsoluteFile());
            InputStream is = new FileInputStream(image);
            long length = image.length();
            System.out.println("image length :" + length);
            if (length > Integer.MAX_VALUE) {
                // File is too large
                logger.error("File is too large: " + image.getName());
                throw new IOException("File is too large: " + image.getName());
            }

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int) length];
            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            // Ensure all the bytes have been read in
            if (offset > bytes.length) {
                throw new IOException("Could not completely read file " + image.getName());
            }

            // Close the input stream and return bytes
            is.close();
            
            //image.deleteOnExit();
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        SaveImage saver = new SaveImage();
        File dir = new File("src/test/resources");
        try {
            saver.saveImage(dir);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
