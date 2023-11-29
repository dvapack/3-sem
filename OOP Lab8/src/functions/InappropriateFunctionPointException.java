package functions;

public class InappropriateFunctionPointException extends Exception
{
    public InappropriateFunctionPointException()
    {
        super();
    }
    public InappropriateFunctionPointException(String error_message)
    {
        super(error_message);
    }
}
