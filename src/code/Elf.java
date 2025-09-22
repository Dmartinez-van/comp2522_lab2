/**
 * Represents an Elf, a type of {@link Creature} that uses mana to cast spells.
 * An Elf has a name, date of birth, health, and mana. Mana is used to cast spells,
 * and can be restored up to a maximum value. If an Elf attempts to cast a spell
 * without enough mana, a {@link LowManaException} is thrown.
 *
 * @author Daniel Do, David Martinez
 * @version 1.0
 */
public class Elf extends Creature
{
    private static final int MINIMUM_MANA = 0;
    private static final int MAXIMUM_MANA = 50;
    private static final int SPELL_COST = 5;
    private static final int SPELL_DAMAGE = 10;

    /**
     * The current mana points of the Elf.
     * Mana is used to cast spells and is limited between {@value #MINIMUM_MANA}
     * and {@value #MAXIMUM_MANA}.
     */
    private int mana;

    /**
     * Constructs a new Elf with the specified name, date of birth,
     * health, and mana.
     * Mana must be between {@value #MINIMUM_MANA} and
     * {@value #MAXIMUM_MANA}, inclusive.
     *
     * @param name        the name of the Elf
     * @param dateOfBirth the date of birth of the Elf
     * @param health      the initial health points of the Elf
     * @param mana        the initial mana points of the Elf; must be between
     *                    {@value #MINIMUM_MANA} and {@value #MAXIMUM_MANA}
     * @throws IllegalArgumentException if mana is outside the valid range
     */
    public Elf(final String name,
               final Date dateOfBirth,
               final int health,
               final int mana)
    {
        super(name, dateOfBirth, health);
        checkMana(mana);
        this.mana = mana;
    }

    /**
     * Checks if the provided mana value is within the valid range.
     * Throws an {@link IllegalArgumentException} if the value is invalid.
     *
     * @param mana the mana value to check
     * @throws IllegalArgumentException if mana is less than
     *         {@value #MINIMUM_MANA} or greater than {@value #MAXIMUM_MANA}
     */
    private void checkMana(final int mana)
    {
        if (mana < MINIMUM_MANA || mana > MAXIMUM_MANA)
        {
            final StringBuilder errorMessage;
            errorMessage = new StringBuilder();

            errorMessage.append("Mana must be between ");
            errorMessage.append(MINIMUM_MANA);
            errorMessage.append(" and ");
            errorMessage.append(MAXIMUM_MANA);
            errorMessage.append(".");

            throw new IllegalArgumentException(errorMessage.toString());
        }
    }

    /**
     * Returns the details of the Elf, including mana.
     *
     * @return a string with the Elf's details and current mana
     */
    @Override
    public String getDetails()
    {
        return super.getDetails() + "Mana: " + mana + "\n";
    }

    /**
     * Attempts to cast a spell, reducing mana by {@value #SPELL_COST}
     * and returning the spell's damage.
     * If there is not enough mana, a {@link LowManaException} is thrown.
     *
     * @return the amount of damage dealt by the spell, which
     *         is {@value #SPELL_DAMAGE}
     * @throws LowManaException if the Elf does not have at least
     *         {@value #SPELL_COST} mana
     */
    public int castSpell() throws LowManaException
    {
        if (mana < SPELL_COST)
        {
            throw new LowManaException("Not enough mana to cast a spell.");
        }

        this.mana -= SPELL_COST;

        return SPELL_DAMAGE;
    }

    /**
     * Restores the Elf's mana by the specified amount.
     * Mana will not exceed {@value #MAXIMUM_MANA} after restoration.
     *
     * @param amount amount of mana to restore
     */
    public void restoreMana(final int amount)
    {
        this.mana += amount;

        if (this.mana > MAXIMUM_MANA)
        {
            this.mana = MAXIMUM_MANA;
        }
    }
}