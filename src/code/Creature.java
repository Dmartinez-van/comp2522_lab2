/**
 * A Creature class
 *
 * @author David Martinez, Daniel Do
 * @version 1.0
 */
public class Creature
{
    // We will probably end up making Creature
    // Abstract. In which case we can copy + paste method functionality
    // to the children classes.

    private static final int MIN_HEALTH = 0;
    private static final int MAX_HEALTH = 100;
    private static final int MIN_DAMAGE = 0;
    private static final int CURRENT_YEAR = 2025;

    private final String name;
    private final Date dateOfBirth;

    private int health;

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

    /**
     * Creature's health is reduced by damage taken.
     * Damage taken cannot be below {@value MIN_DAMAGE}.
     * If creature's health goes below {@value MIN_HEALTH},
     * it is set to {@value MIN_HEALTH}.
     * @param damageTaken the amount of damage taken
     */
    public void takeDamage(final int damageTaken)
    {
        if (damageTaken < MIN_DAMAGE)
        {
            throw new DamageExeption("Damage cannot be below " +
                                      MIN_DAMAGE);
        }

        final int newHealth;
        newHealth = health - damageTaken;

        if (newHealth < MIN_HEALTH)
        {
            health = MIN_HEALTH;
        }
    }

    /**
     * Heals a creature by a non-negative amount.
     * Will set Creature's health to {@value MAX_HEALTH} if
     * they were to be healed past the max ({@value MAX_HEALTH}).
     * @param healAmount the amount to heal
     */
    public void heal(final int healAmount)
    {
        // check for healAmount exceeding 100

        // check for negative heal amount -> throw new HealingException

        health += healAmount;

        if (health > MAX_HEALTH)
        {
            health = MAX_HEALTH;
        }
    }

    /**
     * Calculates the creatures age in years based on {@value CURRENT_YEAR}.
     * @return the age of creature; unit: years
     */
    public int getAgeYears()
    {
        final int ageYears;

        ageYears= dateOfBirth.getYear() - CURRENT_YEAR;

        return ageYears;
    }

    /**
     * Returns the creatures details.
     * <ul>
     *     <li>Name</li>
     *     <li>Date of birth</li>
     *     <li>Age</li>
     *     <li>Health</li>
     * </ul>
     * @return a string message of the creature's details
     */
    public String getDetails()
    {
        final int age;
        final String finalMessage;

        String messageBuilder;

        age = getAgeYears();

        messageBuilder = "Name: " + name + "\n";
        messageBuilder += "Date of birth: " + dateOfBirth + "\n";
        messageBuilder += "Age: " + age + "\n";
        messageBuilder += "Health: " + health + "\n";

        finalMessage = messageBuilder;

        return finalMessage;
    }
}
