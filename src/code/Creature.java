package ca.bcit.comp2522.lab2;

import ca.bcit.comp2522.bank.*;

/**
 * A Creature class
 *
 * @author David Martinez, Daniel Do
 * @version 1.0
 */
public class Creature
{
    private final String name;
    private final Date dateOfBirth;
    private final int health;

    Creature(final String name,
             final Date dateOfBirth,
             final int health)
    {
        checkName(name);
        checkDate(dateOfBirth);
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
}
