import junit.framework.TestCase;


public class MyConverterTest extends TestCase
{
    MyConverter myConverter;
    String inputFilename;
    protected void setUp() throws Exception
    {
        myConverter = new MyConverter();
    }

    public void testConvert()
    {
        inputFilename = "/home/daning/test/UCR.odt";
        myConverter.convert(inputFilename);
    }

}
