package timeApi.calendar.contract;

import net.time4j.PlainTimestamp;
import net.time4j.calendar.PersianCalendar;

public interface PersianToGregorian
{

    /**
     * Converts a persian date represented by a PersianCalendar to a Gregorian date.
     *
     * @param persianDate the persian date to be converted
     * @return the corresponding Gregorian date as a PlainTimestamp
     */
    PlainTimestamp convertPersianToGregorian(PersianCalendar persianDate);



    /**
     * Converts a persian date represented by a PersianCalendar and time to a Gregorian Timestamp.
     *
     * @param persianDate the persian date to be converted
     * @param hour the hour of the time
     * @param minute the minute of the time
     * @param second the second of the time
     * @return the corresponding Gregorian date as a PlainTimestamp
     */
    PlainTimestamp convertPersianToGregorian(PersianCalendar persianDate, int hour, int minute, int second);




    /**
     * Converts a persian date represented by year, month, and day to a Gregorian date.
     *
     * @param persianYear  the persian year
     * @param persianMonth the persian month
     * @param persianDay   the persian day
     * @param hour the hour of the time
     * @param minute the minute of the time
     * @param second the second of the time
     * @return the corresponding Gregorian date as a PersianCalendar
     */
    PlainTimestamp convertPersianToGregorian(int persianYear, int persianMonth, int persianDay, int hour, int minute, int second);

}
