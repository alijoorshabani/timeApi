package timeApi.calendar.imp;

import net.time4j.*;
import net.time4j.calendar.PersianCalendar;
import net.time4j.tz.olson.ASIA;
import org.springframework.stereotype.Component;
import timeApi.calendar.contract.PersianToGregorian;


@Component
public class PersianToGregorianConverter implements PersianToGregorian
{

    @Override
    public PlainTimestamp convertPersianToGregorian(PersianCalendar persianDate)
    {
        return PlainTimestamp.of(
                persianDate.transform(PlainDate.class),
                SystemClock.currentMoment().inZonalView(ASIA.TEHRAN).toTimestamp().getWallTime());
    }


    @Override
    public PlainTimestamp convertPersianToGregorian(PersianCalendar persianDate, int hour, int minute, int second)
    {
        return PlainTimestamp.of(
                persianDate.transform(PlainDate.class),
                PlainTime.of(hour, minute, second));
    }


    @Override
    public PlainTimestamp convertPersianToGregorian(int persianYear, int persianMonth, int persianDay, int hour, int minute, int second)
    {
        return PlainTimestamp.of(
                PersianCalendar.of(persianYear, persianMonth, persianDay).transform(PlainDate.class),
                PlainTime.of(hour, minute, second));
    }

}
