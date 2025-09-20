/**
 * Main class is to run and spot check the other
 * classes for Comp 2522 Lab 2
 *
 * @author David Martinez, Daniel Do
 * @version 1.0
 */
public class Main
{
    public static void main(final String[] args)
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
        dYear   = 1432;

        eDay    = 3;
        eMonth  = 3;
        eYear   = 1857;

        oDay    = 13;
        oMonth  = 5;
        oYear   = 2012;

        startingHealth     = 100;
        startingFirePower  = 100;
        startingMana       = 30;
        startingRage       = 0;

        dragonName       = "Onyxia";
        dragonDOB        = new Date(dDay,dMonth,dYear);

        elfName          = "Jarlaxle Baenre";
        elfDOB           = new Date(eDay,eMonth,eYear);

        orcName          = "King Obould Many-Arrows";
        orcDOB           = new Date(oDay,oMonth,oYear);

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

        System.out.println("Dragon Details:\n " + dragon.getDetails());
        System.out.println("Elf Details:\n "    + elf.getDetails());
        System.out.println("Orc Details:\n "    + orc.getDetails());
        System.out.println();

        System.out.println("dragon instance of Creature?: " +
                            (dragon instanceof Dragon));
        System.out.println("elf instance of Creature?: "    +
                            (elf instanceof Elf));
        System.out.println("orc instance of Creature?: "    +
                            (orc instanceof Orc));
        System.out.println();

        System.out.println("dragon.getClass() -> "  + dragon.getClass());
        System.out.println("elf.getClass() -> "     + elf.getClass());
        System.out.println("orc.getClass() -> "     + orc.getClass());
        System.out.println();

        System.out.println();
    }
}
