/**
 * CreatureTest class is for testing and checking all classes for Lab2.
 *
 * @author David Martinez, Daniel Do
 * @version 1.0
 */
public class CreatureTest {
    public static void main(final String[] args) throws LowFirePowerException, LowManaException
    {
        final Creature dragon;
        final Creature elf;
        final Creature orc;

        final String dragonName;
        final String elfName;
        final String orcName;

        final Date dragonDOB;
        final Date elfDOB;
        final Date orcDOB;

        final int dDay;
        final int dMonth;
        final int dYear;

        final int eDay;
        final int eMonth;
        final int eYear;

        final int oDay;
        final int oMonth;
        final int oYear;

        final int startingHealth;
        final int startingFirePower;
        final int startingMana;
        final int startingRage;

        dDay    = 12;
        dMonth  = 1;
        dYear   = 1801;

        eDay    = 3;
        eMonth  = 3;
        eYear   = 1897;

        oDay    = 13;
        oMonth  = 5;
        oYear   = 2012;

        startingHealth     = 100;
        startingFirePower  = 100;
        startingMana       = 30;
        startingRage       = 0;

        dragonName = "Onyxia";
        dragonDOB  = new Date(dDay,dMonth,dYear);

        elfName    = "Jarlaxle Baenre";
        elfDOB     = new Date(eDay,eMonth,eYear);

        orcName    = "King Obould Many-Arrows";
        orcDOB     = new Date(oDay,oMonth,oYear);

        dragon = new Dragon(dragonName,
                dragonDOB,
                startingHealth,
                startingFirePower);

        elf    = new Elf(elfName,
                elfDOB,
                startingHealth,
                startingMana);

        orc    = new Orc(orcName,
                orcDOB,
                startingHealth,
                startingRage);

        System.out.println("Dragon Details:\n" + dragon.getDetails());
        System.out.println("Elf Details:\n"    + elf.getDetails());
        System.out.println("Orc Details:\n"    + orc.getDetails());

        System.out.println("dragon instance of Creature?: " +
                (dragon instanceof Creature));
        System.out.println("dragon instance of Dragon?: " +
                (dragon instanceof Dragon) +
                "\n");

        System.out.println("elf instance of Creature?: " +
                (elf instanceof Creature));
        System.out.println("elf instance of Elf?: " +
                (elf instanceof Elf) +
                "\n");

        System.out.println("orc instance of Creature?: " +
                (orc instanceof Creature));
        System.out.println("orc instance of Orc?: " +
                (orc instanceof Orc) +
                "\n");

        System.out.println("dragon.getClass() -> "  + dragon.getClass());
        System.out.println("elf.getClass() ----> "  + elf.getClass());
        System.out.println("orc.getClass() ----> "  + orc.getClass());
        System.out.println();

        System.out.println("Casting each creature to their child class...\n");
        final Dragon dragonCasted;
        final Elf    elfCasted;
        final Orc    orcCasted;

        dragonCasted = (Dragon) dragon;
        elfCasted    = (Elf) elf;
        orcCasted    = (Orc) orc;

        /*
        Test Unchecked Exceptions
        1. DamageException (Any)
        2. HealingException (Any)
        3. LowRageException (Orc)

        Test Checked Exceptions
        4. LowFirePowerException (Dragon)
        5. LowManaException (Elf)
        */

        // Test 1. DamageException (Any)
        final int damageValid;
        final int damageInvalid;

        damageValid   = 38;
        damageInvalid = -1;

        System.out.println("DAMAGE CHECK");

        System.out.println("Attacking dragon with " + damageValid + " damage");
        System.out.println("Before Dragon hp: " + dragon.getCurrentHealth());
        dragon.takeDamage(damageValid);
        System.out.println("After Dragon hp: " + dragon.getCurrentHealth());
        System.out.println();

        System.out.println("Attacking orc with " + damageInvalid + " damage");
        System.out.println("Before Orc hp: " + orc.getCurrentHealth());
//        orc.takeDamage(damageInvalid);
        System.out.println("After Orc hp: " + orc.getCurrentHealth());
        System.out.println();

        //2. HealingException (Any)
        final int healValid;
        final int healInvalid;

        healValid   = 40;
        healInvalid = -1;

        System.out.println("HEAL CHECK");

        System.out.println("Healing dragon by " + healValid);
        System.out.println("Before Dragon hp " + dragon.getCurrentHealth());
        dragon.heal(healValid);
        System.out.println("After Dragon hp " + dragon.getCurrentHealth());
        System.out.println();

        System.out.println("Healing orc by " + healInvalid);
        System.out.println("Before Orc hp " + orc.getCurrentHealth());
//        orc.heal(healInvalid);
        System.out.println("After Orc hp " + orc.getCurrentHealth());
        System.out.println();

        //3. LowRageException (Orc)
        final Orc lotsOfRageOrc;
        final int lotsOfRage;
        final String lotsOfRageName;

        lotsOfRageName = "RAGEORC";
        lotsOfRage = 30;
        lotsOfRageOrc = new Orc(lotsOfRageName,
                                orcDOB,
                                startingHealth,
                                lotsOfRage);

        System.out.println("BERSERK RAGE CHECK");

//        System.out.println("Orc details before berserk: " +
//                orcCasted.getDetails());
//        System.out.println("Berserk casted, damage = " + orcCasted.berserk());
//        System.out.println("Orc details after berserk: " +
//                orcCasted.getDetails());
//        System.out.println();

        System.out.println("Orc details before berserk: " +
                lotsOfRageOrc.getDetails());

        System.out.println("Berserk casted, damage = " +
                lotsOfRageOrc.berserk() + "\n");

        System.out.println("Orc details after berserk: " +
                lotsOfRageOrc.getDetails());
        System.out.println();

        // 4. LowFirePowerException (Dragon)
        System.out.println("FIREPOWER CHECK");

        System.out.println("Before Dragon details: " +
                dragonCasted.getDetails());

        try
        {
            System.out.println("Cast Firebreath(), damage = " +
                    dragonCasted.breatheFire());
        } catch (Exception e)
        {
            throw new LowFirePowerException("Low fire power!");
        }

        System.out.println("After Dragon details: " +
                dragonCasted.getDetails());
        System.out.println();

        // 5. LowManaException (Elf)
        System.out.println("LOW MANA CHECK");

        System.out.println("Before Elf details: " +
                elfCasted.getDetails());

        try
        {
            System.out.println("Cast castSpell(), damage = " +
                    elfCasted.castSpell());
            // Cast it 5 more times
            elfCasted.castSpell();
            elfCasted.castSpell();
            elfCasted.castSpell();
            elfCasted.castSpell();
            elfCasted.castSpell();

            // Will fail on 6th cast, b/c elf has 0 mana now
//            elfCasted.castSpell();

        } catch (Exception e)
        {
            throw new LowManaException("Low mana!");
        }

        System.out.println("After Elf details: " +
                elfCasted.getDetails());
        System.out.println();

        // Make Creatures fight
        System.out.println("FIGHT!!!!");
        System.out.println("Orc attacks Dragon!");

        int orcDamage;
        orcDamage = lotsOfRageOrc.berserk();

        dragon.takeDamage(orcDamage);
        System.out.println(orcDamage + " damage taken by Dragon!");
        System.out.println("Dragon HP: " + dragon.getCurrentHealth());
        System.out.println("Is dragon alive? " + dragon.isAlive());

        System.out.println("Dragon attacks Orc!");
        int dragonDamage;
        dragonDamage = dragonCasted.breatheFire();
        orc.takeDamage(dragonDamage);
        System.out.println(dragonDamage + " damage taken by Orc!");
        System.out.println("Orc HP: " + orc.getCurrentHealth());
        System.out.println("Is orc alive? " + orc.isAlive());

        System.out.println("Dragon's details before restoring firepower:\n" +
                dragonCasted.getDetails());
        System.out.println("Dragon restores firepower!");
        final int firePowerRestored;
        firePowerRestored = 50;
        dragonCasted.restoreFirePower(firePowerRestored);
        System.out.println("Dragon's details are:\n" + dragonCasted.getDetails());

        System.out.println("Orc attacks 4 more times!");
        orcDamage = 0;
        orcDamage += lotsOfRageOrc.berserk();
        orcDamage += lotsOfRageOrc.berserk();
        orcDamage += lotsOfRageOrc.berserk();
        orcDamage += lotsOfRageOrc.berserk();

        dragon.takeDamage(orcDamage);
        System.out.println(orcDamage + " damage taken by Dragon!");
        System.out.println("Dragon HP: " + dragon.getCurrentHealth());

        System.out.println("Orc attacks 1 more time!");
        orcDamage = 0;
        orcDamage += lotsOfRageOrc.berserk();

        dragon.takeDamage(orcDamage);
        System.out.println(orcDamage + " damage taken by Dragon!");
        System.out.println("Dragon HP: " + dragon.getCurrentHealth());
        System.out.println("Is dragon alive? " + dragon.isAlive());

        final int manaRestored;
        manaRestored = 30;
        System.out.println("Elf restores " + manaRestored + " mana!");
        elfCasted.restoreMana(manaRestored);
        System.out.println("Elf's details AFTER Restore mana:\n" +
                elfCasted.getDetails());

        System.out.println("Elf casts a spell 3 times!");
        elfCasted.castSpell();
        elfCasted.castSpell();
        elfCasted.castSpell();
        System.out.println("Elf's details are:\n" + elfCasted.getDetails());
    }
}
