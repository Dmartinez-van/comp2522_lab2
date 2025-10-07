/**
 * Checked Exception for low mana.
 *
 * @author David Martinez,
 *         Daniel Do
 * @version 1.0
 */
public class LowManaException extends Exception
{
    /**
     * LowManaException constructor.
     * @param message the message to display.
     */
    public LowManaException(final String message)
    {
        super(message);
    }

}
