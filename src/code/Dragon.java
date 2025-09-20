public class Dragon extends Creature
{

    public static final int MINIMUM_FIRE_POWER = 1;
    public static final int MAXIMUM_FIRE_POWER = 100;

    private final int firePower;

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

    public void breatheFire() {

    }

}
