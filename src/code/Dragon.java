public class Dragon extends Creature
{

    public static final int MINIMUM_FIRE_POWER = 1;
    public static final int MAXIMUM_FIRE_POWER = 100;
    public static final int FIRE_POWER_ACTIVATION = 10;
    public static final int FIRE_BREATH_DAMAGE = 20;

    private int firePower;

    public Dragon(String name, Date dateOfBirth, int health, int firePower)
    {
        super(name, dateOfBirth, health);

        checkFirePower(firePower);
        this.firePower = firePower;
    }

    private void checkFirePower(int firePower)
    {
        if (firePower < MINIMUM_FIRE_POWER || firePower > MAXIMUM_FIRE_POWER)
        {
            throw new IllegalArgumentException("Fire power must be between "
                    + MINIMUM_FIRE_POWER
                    + " and "
                    + MAXIMUM_FIRE_POWER + ".");
        }
    }

    @Override
    public String getDetails()
    {
        return super.getDetails() + ", Fire Power: " + firePower;
    }

    public int breatheFire() throws LowFirePowerException
    {
        if (firePower < FIRE_POWER_ACTIVATION)
        {
            throw new LowFirePowerException("Fire power must be at least 10");
        }

        this.firePower -= FIRE_POWER_ACTIVATION;

        return FIRE_BREATH_DAMAGE;
    }

    public void restoreFirePower(int amount)
    {
        this.firePower += amount;

        if (this.firePower > MAXIMUM_FIRE_POWER)
        {
            this.firePower = MAXIMUM_FIRE_POWER;
        }
    }
}














