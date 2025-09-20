/**
 * Dragon class, child of Creature.
 * Has class specific data for:
 * <ul>
 *     <li>firePower; datetype: int</li>
 * </ul>
 * Has class specific methods for:
 * <ul>
 *     <li>int breathFire()</li>
 *     <li>void restoreFirePower()</li>
 * </ul>
 *
 * @author David Martinez, Daniel Do
 * @version 1.0
 */
public class Dragon extends Creature
{

    public static final int MINIMUM_FIRE_POWER = 1;
    public static final int MAXIMUM_FIRE_POWER = 100;
    public static final int FIRE_POWER_ACTIVATION = 10;
    public static final int FIRE_BREATH_DAMAGE = 20;

    private int firePower;

    /**
     * Full constructor for Dragon.
     *
     * @param name        of the dragon
     * @param dateOfBirth of the dragon
     * @param health      of the dragon
     * @param firePower   of the dragon
     */
    public Dragon(final String name,
                  final Date dateOfBirth,
                  final int health,
                  final int firePower)
    {
        super(name, dateOfBirth, health);

        checkFirePower(firePower);
        this.firePower = firePower;
    }

    private void checkFirePower(final int firePower)
    {
        final boolean firePowerLowerBound;
        final boolean firePowerUpperBound;

        firePowerLowerBound = firePower < MINIMUM_FIRE_POWER;
        firePowerUpperBound = firePower > MAXIMUM_FIRE_POWER;

        if (firePowerLowerBound || firePowerUpperBound)
        {
            throw new IllegalArgumentException("Fire power must be between "
                    + MINIMUM_FIRE_POWER
                    + " and "
                    + MAXIMUM_FIRE_POWER + ".");
        }
    }

    /**
     * Returns the dragon's details.
     * <ul>
     *     <li>Name</li>
     *     <li>Date of birth</li>
     *     <li>Age</li>
     *     <li>Health</li>
     *     <li>Fire Power</li>
     * </ul>
     *
     * @return the dragon's details
     */
    @Override
    public String getDetails()
    {
        final StringBuilder sb;

        sb = new StringBuilder(super.getDetails());
        sb.append("Fire Power: ");
        sb.append(firePower);
        sb.append("\n");

        return sb.toString();
    }

    /**
     * Attempts to use dragon's fire breath.
     * Costs {@value FIRE_POWER_ACTIVATION} amount each cast.
     *
     * @return the amount of damage as an int.
     * @throws LowFirePowerException if firePower is insuffcient for cast cost.
     */
    public int breatheFire() throws LowFirePowerException
    {
        if (firePower < FIRE_POWER_ACTIVATION)
        {
            throw new LowFirePowerException("Fire power must be at least 10");
        }

        this.firePower -= FIRE_POWER_ACTIVATION;

        return FIRE_BREATH_DAMAGE;
    }

    /**
     * Restores dragon's firePower.
     * Cannot exceed {@value MAXIMUM_FIRE_POWER}
     *
     * @param amount the amount to restore firePower by.
     */
    public void restoreFirePower(final int amount)
    {
        this.firePower += amount;

        if (this.firePower > MAXIMUM_FIRE_POWER)
        {
            this.firePower = MAXIMUM_FIRE_POWER;
        }
    }
}














