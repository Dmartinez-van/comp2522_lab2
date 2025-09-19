/**
 * A Creature class
 *
 * @author David Martinez, Daniel Do
 * @version 1.0
 */
public class Creature
{
    private static final int MIN_HEALTH = 0;
    private static final int MAX_HEALTH = 100;
    private static final int CURRENT_YEAR = 2025;

    private final String name;
    private final Date dateOfBirth;
    private final int health;

    Creature(final String name,
             final Date dateOfBirth,
             final int health)
    {
        checkName(name);
        checkBirthDate(dateOfBirth);
        checkHealth(health);

        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.health = health;
    }

    private void checkName(final String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    private void checkBirthDate(final Date dateOfBirth)
    {
        /*
        Add more 'date in future' checks?
        Would need
        - year month check
        - year month day check
         */
        final boolean yearCheck = dateOfBirth.getYear() > CURRENT_YEAR;

        if (yearCheck)
        {
            throw new IllegalArgumentException("Date cannot be in the future");
        }
    }

    private void checkHealth(final int health)
    {
        if (health < MIN_HEALTH || health > MAX_HEALTH)
        {
            throw new IllegalArgumentException("Health cannot exceed" +
                                               " min or max");
        }
    }

    /**
     * Checks if creature is alive or not (boolean)
     * @return true if health is greater than {@value MIN_HEALTH}
     */
    public boolean isAlive()
    {
        return health > MIN_HEALTH;
    }

}
