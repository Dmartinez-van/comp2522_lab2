public class Elf extends Creature
{
    public static final int MINIMUM_MANA = 0;
    public static final int MAXIMUM_MANA = 50;
    public static final int SPELL_COST = 5;
    public static final int SPELL_DAMAGE = 10;

    private int mana;

    public Elf(String name, Date dateOfBirth, int health, int mana)
    {
        super(name, dateOfBirth, health);
        checkMana(mana);
        this.mana = mana;
    }

    private void checkMana(int mana)
    {
        if (mana < MINIMUM_MANA || mana > MAXIMUM_MANA)
        {
            throw new IllegalArgumentException("Mana must be between "
                    + MINIMUM_MANA
                    + " and "
                    + MAXIMUM_MANA
                    + ".");
        }
    }

    @Override
    public String getDetails()
    {
        return super.getDetails() + ", Mana: " + mana;
    }

    public int castSpell() throws LowManaException
    {
        if (mana < SPELL_COST)
        {
            throw new LowManaException("Not enough mana to cast a spell.");
        }

        this.mana -= SPELL_COST;

        return SPELL_DAMAGE;
    }

    public void restoreMana(int amount)
    {
        this.mana += amount;

        if (this.mana > MAXIMUM_MANA)
        {
            this.mana = MAXIMUM_MANA;
        }
    }

}
