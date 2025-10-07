/**
 * Runtime Exception for low rage.
 *
 * @author David Martinez,
 *         Daniel Do
 * @version 1.0
 */
public class LowRageException extends RuntimeException
{
    /**
     * LowRageException constructor.
     * @param message the message to display.
     */
    public LowRageException (final String message)
    {
        super(message);
    }
}
