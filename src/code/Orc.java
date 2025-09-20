public class Orc extends Creature
{
    /**
     * Represents an Orc, a type of Creature with a rage attribute.
     * Provides methods to access details and perform berserk attacks.
     *
     * @author Daniel Do, David Martinez
     * @version 1.0
     */

    private static final int RAGE_INCREASE_POINTS = 5;
    private static final int MIN_RAGE_POINTS = 5;
    private static final int RAGE_THRESHOLD_POINTS = 20;
    private static final int DAMAGE_NORMAL_HP_POINTS = 15;
    private static final int DAMAGE_DOUBLE_HP_POINTS = 30;

    private int rage;

    /**
     * Constructs an Orc with the specified name, date of birth, health, and rage.
     *
     * @param name        the name of the orc, must not be null or empty
     * @param dateOfBirth the date of birth of the orc, must not be null
     * @param health      the health of the orc, must be positive
     * @param rage        the initial rage of the orc, must be non-negative
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Orc(final String name, final Date dateOfBirth, final int health, final int rage)
    {
        super(name, dateOfBirth, health);
        checkRage(rage);
        this.rage = rage;
    }

    private void checkRage(int rage)
    {
        if (rage < 0)
        {
            throw new IllegalArgumentException("Rage must be non-negative.");
        }
    }

    /**
     * Returns the details of the orc, including rage.
     *
     * @return a String containing the orc's details and rage value
     */
    @Override
    public final String getDetails()
    {
        return super.getDetails() + ", Rage: " + rage;
    }

    /**
     * Performs a berserk attack on the specified target creature.
     * <p>
     * This method increases the orc's rage by {@value RAGE_INCREASE_POINTS} points.
     * If the resulting rage is less than {@value MIN_RAGE_POINTS}, a LowRageException is thrown.
     * If the resulting rage exceeds {@value RAGE_THRESHOLD_POINTS}, the orc deals double damage
     * ({@value DAMAGE_DOUBLE_HP_POINTS} health points) to the target creature.
     * Otherwise, the orc deals normal damage ({@value DAMAGE_NORMAL_HP_POINTS} health points).
     * <p>
     * The rage value is immutable in this implementation; to allow rage to increase, remove the final modifier.
     *
     * @throws LowRageException         if the resulting rage is less than {@value MIN_RAGE_POINTS}
     * @throws IllegalArgumentException if targetCreature is null
     */
    public final int berserk() throws LowRageException
    {

        final int newRage = this.rage + 5;
        if (newRage < 5)
        {
            throw new LowRageException("Rage is too low to go berserk.");
        }
        if (newRage > 20)
        {
            return DAMAGE_DOUBLE_HP_POINTS;
        } else
        {
            return DAMAGE_NORMAL_HP_POINTS;
        }
    }
}
