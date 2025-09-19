/**
 * Represents a date with day, month, and year components.
 * Provides validation for day, month, and year values, including leap year handling.
 * <p>
 * This class ensures that dates are valid according to the Gregorian calendar rules,
 * including checks for valid ranges of days in each month and leap year considerations for February.
 * </p>
 *
 * @author Daniel Do, Jason Firkus, David Martinez, Yehor Skudilov
 * @version 1.0
 */
public final class Date
{

    private static final int MINIMUM_YEAR = 1800;
    private static final int CURRENT_YEAR = 2025;
    private static final int JANUARY_MONTH_CODE = 1;
    private static final int DECEMBER = 12;
    private static final int MINIMUM_DAY_IN_MONTH = 1;
    private static final int MAX_DAYS_IN_FEBRUARY_LEAP_YEAR = 29;
    private static final int TWENTY_FIRST_CENTURY_START = 2000;
    private static final int NINETEENTH_CENTURY_START = 1800;
    private static final int ZERO = 0;
    private static final int MONTH_INDEX_OFFSET = 1;
    private static final String JANUARY_NAME = "January";

    private final int day;
    private final int month;
    private final int year;

    /**
     * Constructs a new Date object with the specified day, month, and year.
     * <p>
     * Validates the provided year, month, and day values to ensure they represent a valid date
     * according to the Gregorian calendar. The year must be between MINIMUM_YEAR and CURRENT_YEAR,
     * the month must be between MINIMUM_MONTH and MAXIMUM_MONTH, and the day must be valid for the
     * specified month and year (including leap year handling for February).
     * </p>
     *
     * @param day   the day of the month; unit: day
     * @param month the month of the year; unit: month (1 = January)
     * @param year  the year; unit: year
     * @throws IllegalArgumentException if any parameter is out of valid range or does not form a valid date
     */
    Date(final int day, final int month, final int year) {
        Validator.checkLessThanZero(year);
        Validator.checkLessThanZero(month);
        Validator.checkLessThanZero(day);

        checkYear(year);
        checkMonth(month);
        checkDay(day, month, year);

        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Checks if the provided year is within the valid range.
     * The valid range is between MINIMUM_YEAR and CURRENT_YEAR, inclusive.
     * Throws IllegalArgumentException if the year is out of bounds.
     *
     * @param year the year to validate; unit: year
     * @throws IllegalArgumentException if year is not between MINIMUM_YEAR and CURRENT_YEAR
     */
    public static void checkYear(final int year) {
        if (year < MINIMUM_YEAR || year > CURRENT_YEAR) {
            throw new IllegalArgumentException("Year must be between " + MINIMUM_YEAR + " and " + CURRENT_YEAR + ".");
        }
    }

    /**
     * Checks if the provided month is within the valid range.
     * The valid range is between MINIMUM_MONTH and MAXIMUM_MONTH, inclusive.
     * Throws IllegalArgumentException if the month is out of bounds.
     *
     * @param month the month to validate; unit: month (1 = January)
     * @throws IllegalArgumentException if month is not between MINIMUM_MONTH and MAXIMUM_MONTH
     */
    public static void checkMonth(final int month) {
        if (month < MINIMUM_MONTH || month > MAXIMUM_MONTH) {
            throw new IllegalArgumentException("Month must be between " + MINIMUM_MONTH + " and " + MAXIMUM_MONTH + ".");
        }
    }

    /**
     * Checks if the provided day is within the valid range for the given month and year.
     * The valid range is between MINIMUM_DAY and the maximum day for the specified month and year, inclusive.
     * Throws IllegalArgumentException if the day is out of bounds.
     *
     * @param day   the day to validate; unit: day
     * @param month the month to validate; unit: month (1 = January)
     * @param year  the year to validate; unit: year
     * @throws IllegalArgumentException if day is not valid for the given month and year
     */
    private static void checkDay(final int day,
                                 final int month,
                                 final int year) {

        if (day < MINIMUM_DAY_IN_MONTH)
        {
            throw new IllegalArgumentException("The day cannot be less than " + MINIMUM_DAY_IN_MONTH + ".");
        }

        if (month - MONTH_INDEX_OFFSET == FEBRUARY_INDEX && isLeapYear(year)) {
            if (day > MAX_DAYS_IN_FEBRUARY_LEAP_YEAR) {
                throw new IllegalArgumentException("The day cannot be more than " + MAX_DAYS_IN_FEBRUARY_LEAP_YEAR + " in February of a leap year.");
            }
            return;
        }

        if (day > DAYS_IN_MONTH[month - MONTH_INDEX_OFFSET]) {
            throw new IllegalArgumentException("The day cannot be more than " + DAYS_IN_MONTH[month - MONTH_INDEX_OFFSET] + " in month " + month + ".");
        }
    }

    /**
     * Determines if the specified year is a leap year according to the Gregorian calendar.
     * <p>
     * A year is a leap year if:
     * <ul>
     *   <li>It is divisible by 4, AND</li>
     *   <li>It is not divisible by 100, OR</li>
     *   <li>It is divisible by 400</li>
     * </ul>
     * </p>
     *
     * @param year the year to check; unit: year
     * @return true if the year is a leap year, false otherwise
     */
    public static boolean isLeapYear(final int year) {
        final int shortLeapYears;
        final int moderateLeapYears;
        final int longLeapYears;

        boolean leapYear;


        shortLeapYears    = 4;
        moderateLeapYears = 100;
        longLeapYears     = 400;

        leapYear = (year % shortLeapYears == ZERO);
        leapYear = (leapYear && year % moderateLeapYears != ZERO);
        leapYear = (leapYear || year % longLeapYears == ZERO);

        return leapYear;
    }

    /**
     * Gets the day of the month for this date.
     *
     * @return the day of the month; unit: day
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Gets the month of the year for this date.
     *
     * @return the month of the year; unit: month (1 = January)
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Gets the full month name for this date (e.g., "January").
     *
     * @return the month name as a String
     */
    public String getMonthName() {
        return monthNames[this.month - MONTH_INDEX_OFFSET];
    }

    /**
     * Gets the year for this date.
     *
     * @return the year; unit: year
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Gets this date in YYYY-MM-DD format.
     *
     * @return the date as a String in the format YYYY-MM-DD
     */
    public String getYYYYMMDD() {
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
     *   <li>Step 1: Calculate the number of twelves in the last two digits of the year.</li>
     *   <li>Step 2: Calculate the remainder after dividing by 12.</li>
     *   <li>Step 3: Calculate the number of fours in the remainder.</li>
     *   <li>Step 4: Add the day of the month.</li>
     *   <li>Step 5: Add the month code (January=1, February=4, March=4, April=0, May=2, June=5, July=0, August=3, September=6, October=1, November=4, December=6).</li>
     *   <li>Step 6: Add all previous numbers and mod by 7.</li>
     *   <li>Step 7: Map the result to the day of the week (0=Saturday, 1=Sunday, 2=Monday, 3=Tuesday, 4=Wednesday, 5=Thursday, 6=Friday).</li>
     * </ul>
     * </p>
     *
     * @return the day of the week as a String (e.g., "Monday")
     */
    public String getDayOfTheWeek() {

        final int stepZeroConstantJanFebLeapYear;
        final int stepZeroConstant2000s;
        final int stepZeroConstant1800s;
        final int findTensValue;
        final int tensValueDivision;
        final int stepOneDivision;
        final int stepThreeDivision;
        final int stepSixDivision;
        final String finalValue;

        stepZeroConstantJanFebLeapYear = 6;
        stepZeroConstant2000s = 6;
        stepZeroConstant1800s = 2;
        tensValueDivision = 100;
        stepOneDivision = 12;
        stepThreeDivision = 4;
        stepSixDivision = 7;

        final int[] monthCodes =
                {
                        1, // January
                        4, // February
                        4, // March
                        0, // April
                        2, // May
                        5, // June
                        0, // July
                        3, // August
                        6, // September
                        1, // October
                        4, // November
                        6  // December
                };

        final String[] dayNames =
                {
                        "Saturday", // 0
                        "Sunday",   // 1
                        "Monday",   // 2
                        "Tuesday",  // 3
                        "Wednesday",// 4
                        "Thursday", // 5
                        "Friday"    // 6
                };

        int stepZeroValue;
        int stepOneValue;
        int stepTwoValue;
        int stepThreeValue;
        int stepFourValue;
        int stepFiveValue;
        int stepSixValue;

        stepZeroValue = ZERO;

        if (isLeapYear(year) && (month == JANUARY_INDEX || month == FEBRUARY_INDEX)) {
            stepZeroValue += stepZeroConstantJanFebLeapYear;
        }

        if (year >= TWENTY_FIRST_CENTURY_START) {
            stepZeroValue += stepZeroConstant2000s;
        } else if (year <= NINETEENTH_CENTURY_START) {
            stepZeroValue += stepZeroConstant1800s;
        }

        findTensValue = year % tensValueDivision;

        stepOneValue = findTensValue / stepOneDivision;

        stepTwoValue = findTensValue % stepOneDivision;

        stepThreeValue = stepTwoValue / stepThreeDivision;

        stepFourValue =
                stepZeroValue +
                        stepOneValue +
                        stepTwoValue +
                        stepThreeValue +
                        day;

        stepFiveValue = stepFourValue +
                monthCodes[month - MONTH_INDEX_OFFSET];

        stepSixValue = stepFiveValue % stepSixDivision;

        finalValue = dayNames[stepSixValue];

        return finalValue;
    }


}
