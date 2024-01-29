
public class Main
{
    enum Types
    {
        JPEG("jpg", true),
        PNG("png", false),
        BMP("bmp", false);

        private String type;
        private boolean lossy;

        Types(String type, boolean lossy)
        {
            this.type = type;
            this.lossy = lossy;
        }

        public String getType()
        {
            return type;
        }

        public boolean isLossy()
        {
            return lossy;
        }
    }

    public static void main(String[] args)
    {

    }
}