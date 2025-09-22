/**
 * Represents a dragon, which is a type of creature with a unique firepower attribute and abilities.
 * <p>
 * This class models a dragon's firepower, which is an integer value (unitless) ranging from
 * {@link #MINIMUM_FIRE_POWER} to {@link #MAXIMUM_FIRE_POWER}. The firepower determines the dragon's
 * ability to use its fire breath attack and can be restored up to its maximum value.
 * This class models a dragon's firepower, which is an integer
 * value (unitless) ranging from {@link #MINIMUM_FIRE_POWER} to
 * {@link #MAXIMUM_FIRE_POWER}. The firepower determines the dragon's
 * ability to use its fire breath attack and can be restored up
 * to its maximum value.
 * </p>
 * <p>
 * <b>Class-specific data:</b>
 * <ul>
 *     <li><b>firePower</b>: The current firepower level of the dragon (int, unitless, range: {@link #MINIMUM_FIRE_POWER} to {@link #MAXIMUM_FIRE_POWER}).</li>
 *     <li><b>firePower</b>: The current firepower level of the dragon
 *     (int, unitless, range: {@link #MINIMUM_FIRE_POWER} to
 *     {@link #MAXIMUM_FIRE_POWER}).</li>
 * </ul>
 * </p>
 * <p>
 * <b>Class-specific methods:</b>
 * <ul>
 *     <li>{@code int breatheFire()}: Attempts to use the dragon's fire breath,
 *     reducing {@code firePower} by {@link #FIRE_POWER_ACTIVATION} and
 *     returning {@link #FIRE_BREATH_DAMAGE}.
 *     Throws {@code LowFirePowerException} if {@code firePower}
 *     is insufficient.</li>
 *     <li>{@code void restoreFirePower(final int amount)}: Restores the
 *     dragon's {@code firePower} by the specified amount, not exceeding
 *     {@link #MAXIMUM_FIRE_POWER}.</li>
 * </ul>
 * </p>
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
     * Constructs a new Dragon with the specified name, date of birth,
     * health, and firepower.
     * <p>
     * This constructor initializes a Dragon object by setting its name,
     * date of birth, health, and firepower. The firepower must be within the
     * range defined by {@link #MINIMUM_FIRE_POWER} and
     * {@link #MAXIMUM_FIRE_POWER}.
     * If the firepower is outside this range, an
     * {@link IllegalArgumentException} is thrown.
     * </p>
     *
     * @param name        the name of the dragon as a {@link String}
     * @param dateOfBirth the date of birth of the dragon as a {@link Date}
     * @param health      the health value of the dragon as an {@code int}
     * @param firePower   the initial fire power of the dragon as an {@code int}, must be between {@link #MINIMUM_FIRE_POWER} and {@link #MAXIMUM_FIRE_POWER}
     * @throws IllegalArgumentException if {@code firePower} is less than {@link #MINIMUM_FIRE_POWER} or greater than {@link #MAXIMUM_FIRE_POWER}
     * @param health the health value of the dragon as an {@code int}
     * @param firePower the initial fire power of the dragon as an {@code int},
     *                 must be between {@link #MINIMUM_FIRE_POWER} and
     *                 {@link #MAXIMUM_FIRE_POWER}
     * @throws IllegalArgumentException if {@code firePower} is less than
     *                 {@link #MINIMUM_FIRE_POWER} or greater than
     *                 {@link #MAXIMUM_FIRE_POWER}
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
        if (firePower < MINIMUM_FIRE_POWER ||
            firePower > MAXIMUM_FIRE_POWER)
        {
            final StringBuilder errorMessage;
            errorMessage = new StringBuilder();

            errorMessage.append("Fire power must be between ");
            errorMessage.append(MINIMUM_FIRE_POWER);
            errorMessage.append(" and ");
            errorMessage.append(MAXIMUM_FIRE_POWER);
            errorMessage.append(".");

            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    /**
     * Returns the details of the dragon, including its firepower.
     *
     * @return the dragon's details
     */
    @Override
    public String getDetails()
    {
        final StringBuilder detailsBuilder;
        detailsBuilder = new StringBuilder();

        detailsBuilder.append(super.getDetails());
        detailsBuilder.append("Fire Power: ");
        detailsBuilder.append(firePower);
        detailsBuilder.append("\n");

        return detailsBuilder.toString();
    }

    /**
     * Attempts to use dragon's fire breath.
     * Costs {@value FIRE_POWER_ACTIVATION} amount each cast.
     *
     * @return the amount of damage as an int.
     * @throws LowFirePowerException if firePower is insufficient for cast cost.
     */
    public int breatheFire() throws LowFirePowerException
    {
        if (firePower < FIRE_POWER_ACTIVATION)
        {
            throw new LowFirePowerException("Fire power must be at least " +
                    FIRE_POWER_ACTIVATION);
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














