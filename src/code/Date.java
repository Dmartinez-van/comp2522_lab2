/**
 * Represents a date with day, month, and year components.
 * Provides validation for day, month, and year values, including leap year handling.
 * <p>
 * This class ensures that dates are valid according to the Gregorian calendar rules,
 * including checks for valid ranges of days in each month and leap year considerations for February.
 * </p>
 *
 * @author Yehor Skudilov,
 *         David Martinez,
 *         Jason Firkus,
 *         Daniel Do
 * @version 2.0
 */
public final class Date
{
    private static final int JANUARY   = 1;
    private static final int FEBRUARY  = 2;
    private static final int MARCH     = 3;
    private static final int APRIL     = 4;
    private static final int MAY       = 5;
    private static final int JUNE      = 6;
    private static final int JULY      = 7;
    private static final int AUGUST    = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER   = 10;
    private static final int NOVEMBER  = 11;
    private static final int DECEMBER  = 12;

    private static final int DAYS_IN_JANUARY   = 31;
    private static final int DAYS_IN_FEBRUARY  = 28;
    private static final int DAYS_IN_FEBRUARY_LEAP_YEAR = 29;
    private static final int DAYS_IN_MARCH     = 31;
    private static final int DAYS_IN_APRIL     = 30;
    private static final int DAYS_IN_MAY       = 31;
    private static final int DAYS_IN_JUNE      = 30;
    private static final int DAYS_IN_JULY      = 31;
    private static final int DAYS_IN_AUGUST    = 31;
    private static final int DAYS_IN_SEPTEMBER = 30;
    private static final int DAYS_IN_OCTOBER   = 31;
    private static final int DAYS_IN_NOVEMBER  = 30;
    private static final int DAYS_IN_DECEMBER  = 31;

    private static final String JANUARY_NAME   = "January";
    private static final String FEBRUARY_NAME  = "February";
    private static final String MARCH_NAME     = "March";
    private static final String APRIL_NAME     = "April";
    private static final String MAY_NAME       = "May";
    private static final String JUNE_NAME      = "June";
    private static final String JULY_NAME      = "July";
    private static final String AUGUST_NAME    = "August";
    private static final String SEPTEMBER_NAME = "September";
    private static final String OCTOBER_NAME   = "October";
    private static final String NOVEMBER_NAME  = "November";
    private static final String DECEMBER_NAME  = "December";

    private static final int SATURDAY  = 0;
    private static final int SUNDAY    = 1;
    private static final int MONDAY    = 2;
    private static final int TUESDAY   = 3;
    private static final int WEDNESDAY = 4;
    private static final int THURSDAY  = 5;
    private static final int FRIDAY    = 6;

    // Month codes for the day of the week calculation algorithm
    private static final int MONTH_CODE_JANUARY   = 1;
    private static final int MONTH_CODE_FEBRUARY  = 4;
    private static final int MONTH_CODE_MARCH     = 4;
    private static final int MONTH_CODE_APRIL     = 0;
    private static final int MONTH_CODE_MAY       = 2;
    private static final int MONTH_CODE_JUNE      = 5;
    private static final int MONTH_CODE_JULY      = 0;
    private static final int MONTH_CODE_AUGUST    = 3;
    private static final int MONTH_CODE_SEPTEMBER = 6;
    private static final int MONTH_CODE_OCTOBER   = 1;
    private static final int MONTH_CODE_NOVEMBER  = 4;
    private static final int MONTH_CODE_DECEMBER  = 6;

    private static final int MINIMUM_YEAR               = 1800;
    private static final int CURRENT_YEAR               = 2025;
    private static final int TWENTY_FIRST_CENTURY_START = 2000;
    private static final int NINETEENTH_CENTURY_START   = 1800;
    private static final int MINIMUM_DAY_IN_MONTH       = 1;

    private final int day;
    private final int month;
    private final int year;

    /**
     * Constructs a new Date object with the specified day, month, and year.
     * <p>
     * Validates the provided year, month, and day values to ensure they
     * represent a valid date according to the Gregorian calendar.
     * The year must be between {@value MINIMUM_YEAR} and {@value CURRENT_YEAR},
     * the month must be between {@value JANUARY} and {@value DECEMBER},
     * and the day must be valid for the specified month and year
     * (including leap year handling for February).
     * </p>
     *
     * @param day   the day of the month
     * @param month the month of the year
     * @param year  the year
     * @throws IllegalArgumentException if any parameter is invalid
     * */
    Date(final int day,
         final int month,
         final int year)
    {
        checkPositive(year);
        checkPositive(month);
        checkPositive(day);

        checkYear(year);
        checkMonth(month);
        checkDay(day, month, year);

        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Checks if a number is less than or equal to zero.
     *
     * @param number the number to check
     * @throws IllegalArgumentException if the number is less than or equal to zero
     */
    private void checkPositive(final int number)
    {
        if (number <= 0)
        {
            throw new IllegalArgumentException("Value cannot be negative or non-zero.");
        }
    }

    /**
     * Checks if the provided year is within the valid range.
     * The valid range is between {@value MINIMUM_YEAR} and {@value CURRENT_YEAR}, inclusive.
     *
     * @param year the year to validate
     * @throws IllegalArgumentException if year is not between the bounds
     */
    public static void checkYear(final int year)
    {
        if (year <= MINIMUM_YEAR || year > CURRENT_YEAR)
        {
            throw new IllegalArgumentException("Year must be between " +
                    MINIMUM_YEAR + " and " + CURRENT_YEAR + ".");
        }
    }

    /**
     * Checks if the provided month is within the valid range.
     * The valid range is between {@link #JANUARY} and
     * {@link #DECEMBER}, inclusive.
     *
     * @param month the month to validate
     * @throws IllegalArgumentException if month is not between
     *                                  {@value JANUARY} and {@value DECEMBER}
     */
    public static void checkMonth(final int month)
    {
        if (month < JANUARY || month > DECEMBER)
        {
            throw new IllegalArgumentException("Month must be between " +
                    JANUARY + " and " + DECEMBER + ".");
        }
    }

    /**
     * Checks if the provided day is within the valid range for the
     * given month and year. The valid range is between
     * {@value MINIMUM_DAY_IN_MONTH} and the maximum day for the specified
     * month and year, inclusive.
     *
     * @param day   the day to validate
     * @param month the month to validate
     * @param year  the year to validate
     * @throws IllegalArgumentException if day is not valid for the
     *                                  given month and year
     */
    private static void checkDay(final int day,
                                 final int month,
                                 final int year)
    {
        final int daysInMonth;

        if (day < MINIMUM_DAY_IN_MONTH)
        {
            throw new IllegalArgumentException("The day cannot be less than " +
                    MINIMUM_DAY_IN_MONTH + ".");
        }

        daysInMonth = Date.getDaysInMonth(month, year);

        if (day > daysInMonth)
        {
            throw new IllegalArgumentException("The day cannot be more than " +
                    daysInMonth + " in month " + month + ".");
        }
    }

    private static int getDaysInMonth(int month, int year)
    {
        if (month == JANUARY)
        {
            return DAYS_IN_JANUARY;
        }
        else if (month == FEBRUARY)
        {
            if (isLeapYear(year))
            {
                return DAYS_IN_FEBRUARY_LEAP_YEAR;
            }
            return DAYS_IN_FEBRUARY;
        }
        else if (month == MARCH)
        {
            return DAYS_IN_MARCH;
        }
        else if (month == APRIL)
        {
            return DAYS_IN_APRIL;
        }
        else if (month == MAY)
        {
            return DAYS_IN_MAY;
        }
        else if (month == JUNE)
        {
            return DAYS_IN_JUNE;
        }
        else if (month == JULY)
        {
            return DAYS_IN_JULY;
        }
        else if (month == AUGUST)
        {
            return DAYS_IN_AUGUST;
        }
        else if (month == SEPTEMBER)
        {
            return DAYS_IN_SEPTEMBER;
        }
        else if (month == OCTOBER)
        {
            return DAYS_IN_OCTOBER;
        }
        else if (month == NOVEMBER)
        {
            return DAYS_IN_NOVEMBER;
        }
        else if (month == DECEMBER)
        {
            return DAYS_IN_DECEMBER;
        }
        else
        {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    /**
     * Determines if the specified year is a leap year according to the Gregorian calendar.
     *
     * @param year the year to check
     * @return true if the year is a leap year, false otherwise
     */
    public static boolean isLeapYear(final int year)
    {
        final int leapYearEquationStepOne;
        final int leapYearEquationStepTwo;
        final int leapYearEquationStepThree;
        final int leapYearEquationZero;
        boolean leapYear;

        leapYearEquationStepOne   = 4;
        leapYearEquationStepTwo   = 100;
        leapYearEquationStepThree = 400;
        leapYearEquationZero      = 0;

        leapYear = (year % leapYearEquationStepOne == leapYearEquationZero);
        leapYear = (leapYear && year % leapYearEquationStepTwo != leapYearEquationZero);
        leapYear = (leapYear || year % leapYearEquationStepThree == leapYearEquationZero);

        return leapYear;
    }

    /**
     * Gets the day of the month for this date.
     *
     * @return the day of the month
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Gets the month of the year for this date.
     *
     * @return the month of the year
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Gets the full month name for this date.
     *
     * @return the month name as a String
     */
    public String getMonthName()
    {
        if (month == JANUARY)
        {
            return JANUARY_NAME;
        }
        else if (month == FEBRUARY)
        {
            return FEBRUARY_NAME;
        }
        else if (month == MARCH)
        {
            return MARCH_NAME;
        }
        else if (month == APRIL)
        {
            return APRIL_NAME;
        }
        else if (month == MAY)
        {
            return MAY_NAME;
        }
        else if (month == JUNE)
        {
            return JUNE_NAME;
        }
        else if (month == JULY)
        {
            return JULY_NAME;
        }
        else if (month == AUGUST)
        {
            return AUGUST_NAME;
        }
        else if (month == SEPTEMBER)
        {
            return SEPTEMBER_NAME;
        }
        else if (month == OCTOBER)
        {
            return OCTOBER_NAME;
        }
        else if (month == NOVEMBER)
        {
            return NOVEMBER_NAME;
        }
        else if (month == DECEMBER)
        {
            return DECEMBER_NAME;
        }
        else
        {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    /**
     * Gets the year for this date.
     *
     * @return the year
     */
    public int getYear()
    {
        return this.year;
    }

    /**
     * Gets this date in YYYY-MM-DD format.
     *
     * @return the date as a String in the format YYYY-MM-DD
     */
    public String getYYYYMMDD()
    {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    /**
     * Calculates the day of the week for this date using a custom algorithm.
     * <p>
     * The algorithm works as follows:
     * <ul>
     *   <li>For dates in the 1800s, add 2 at the start.</li>
     *   <li>For dates in the 1900s, add 0 at the start.</li>
     *   <li>For dates in the 2000s, add 6 at the start.</li>
     *   <li>For January/February in leap years, add 6 at the start.</li>
     *   <li>Step 1: Calculate the number of twelves in the last two digits
     *   of the year.</li>
     *   <li>Step 2: Calculate the remainder after dividing by 12.</li>
     *   <li>Step 3: Calculate the number of fours in the remainder.</li>
     *   <li>Step 4: Add the day of the month.</li>
     *   <li>Step 5: Add the equation month code</li>
     *   <li>Step 6: Add all previous numbers and mod by 7.</li>
     *   <li>Step 7: Map the result to the day of the week
     * </ul>
     * </p>
     *
     * @return the day of the week as a String
     */
    public String getDayOfTheWeek()
    {
        final int    stepZeroConstantJanFebLeapYear;
        final int    stepZeroConstant2000s;
        final int    stepZeroConstant1800s;
        final int    findTensValue;
        final int    tensValueDivision;
        final int    stepOneDivision;
        final int    stepThreeDivision;
        final int    stepSixDivision;
        final String finalValue;

        int stepZeroValue;
        int stepOneValue;
        int stepTwoValue;
        int stepThreeValue;
        int stepFourValue;
        int stepFiveValue;
        int stepSixValue;

        stepZeroConstantJanFebLeapYear = 6;
        stepZeroConstant2000s          = 6;
        stepZeroConstant1800s          = 2;
        tensValueDivision              = 100;
        stepOneDivision                = 12;
        stepThreeDivision              = 4;
        stepSixDivision                = 7;
        stepZeroValue                  = 0;

        if (isLeapYear(year) && (month == JANUARY || month == FEBRUARY))
        {
            stepZeroValue += stepZeroConstantJanFebLeapYear;
        }

        if (year >= TWENTY_FIRST_CENTURY_START)
        {
            stepZeroValue += stepZeroConstant2000s;
        }
        else if (year <= NINETEENTH_CENTURY_START)
        {
            stepZeroValue += stepZeroConstant1800s;
        }

        findTensValue  = year % tensValueDivision;

        stepOneValue   = findTensValue / stepOneDivision;
        stepTwoValue   = findTensValue % stepOneDivision;
        stepThreeValue = stepTwoValue  / stepThreeDivision;

        stepFourValue  = stepZeroValue +
                         stepOneValue +
                         stepTwoValue +
                         stepThreeValue +
                         day;

        stepFiveValue  = stepFourValue +
                         getMonthCode(month);

        stepSixValue   = stepFiveValue % stepSixDivision;

        finalValue     = getDayOfWeekName(stepSixValue);

        return finalValue;
    }

    /**
     * Returns the month code for the specified month.
     * The month code is used in the day of the week calculation algorithm.
     *
     * @param month the month to get the code for
     * @return the month code as an int
     * @throws IllegalArgumentException if the month is not valid
     */
    private int getMonthCode(final int month)
    {
        if (month == JANUARY)
        {
            return MONTH_CODE_JANUARY;
        }
        else if (month == FEBRUARY)
        {
            return MONTH_CODE_FEBRUARY;
        }
        else if (month == MARCH)
        {
            return MONTH_CODE_MARCH;
        }
        else if (month == APRIL)
        {
            return MONTH_CODE_APRIL;
        }
        else if (month == MAY)
        {
            return MONTH_CODE_MAY;
        }
        else if (month == JUNE)
        {
            return MONTH_CODE_JUNE;
        }
        else if (month == JULY)
        {
            return MONTH_CODE_JULY;
        }
        else if (month == AUGUST)
        {
            return MONTH_CODE_AUGUST;
        }
        else if (month == SEPTEMBER)
        {
            return MONTH_CODE_SEPTEMBER;
        }
        else if (month == OCTOBER)
        {
            return MONTH_CODE_OCTOBER;
        }
        else if (month == NOVEMBER)
        {
            return MONTH_CODE_NOVEMBER;
        }
        else if (month == DECEMBER)
        {
            return MONTH_CODE_DECEMBER;
        }

        throw new IllegalArgumentException("Invalid month: " + month);
    }

    /**
     * Returns the name of the day of the week.
     *
     * @param dayIndex the index of the day
     * @return the name of the day of the week as a String
     * @throws IllegalArgumentException if the day index does not correspond to
     *                                  a valid day
     */
    private String getDayOfWeekName(final int dayIndex)
    {
        if (dayIndex == SATURDAY)
        {
            return "Saturday";
        }
        else if (dayIndex == SUNDAY)
        {
            return "Sunday";
        }
        else if (dayIndex == MONDAY)
        {
            return "Monday";
        }
        else if (dayIndex == TUESDAY)
        {
            return "Tuesday";
        }
        else if (dayIndex == WEDNESDAY)
        {
            return "Wednesday";
        }
        else if (dayIndex == THURSDAY)
        {
            return "Thursday";
        }
        else if (dayIndex == FRIDAY)
        {
            return "Friday";
        }
        throw new IllegalArgumentException("Invalid day index: " + dayIndex);
    }
}
