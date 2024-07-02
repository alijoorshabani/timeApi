package timeApi.calendar.contract;

import net.time4j.PlainTimestamp;
import timeApi.apis.enums.SourceTimeType;
import timeApi.calendar.PersianDateTime;


public interface GregorianToPersian
{
    /**
     * Converts a Gregorian date represented by a PlainDate to a Persian date.
     *
     * @param date the Gregorian date as a PlainDate
     * @param type the type of the time which is TNP or Server
     * @return the corresponding Persian date as a PersianDateTime
     */
    PersianDateTime getPersianDateTime(PlainTimestamp date, SourceTimeType type);


    /**
     * Converts a Gregorian date represented by year, month, and day to a Persian date.
     *
     * @param year  the Gregorian year
     * @param month the Gregorian month
     * @param day   the Gregorian day
     * @param hour the time hour
     * @param minute the time minute
     * @param second the time second
     * @return the corresponding Persian date as a PersianDateTime
     */
    PersianDateTime getPersianDateTime(int year, int month, int day, int hour, int minute, int second);


}
