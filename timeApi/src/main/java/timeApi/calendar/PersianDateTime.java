package timeApi.calendar;

import net.time4j.*;
import net.time4j.calendar.PersianCalendar;
import timeApi.apis.enums.SourceTimeType;
import timeApi.ntp.contract.NtpTime;
import timeApi.ntp.imp.NtpTimeImpApacheNet;
import timeApi.ntp.enums.NtpEnum;
import timeApi.utility.Time4jUtil;


public class PersianDateTime
{

    private final PersianCalendar persianDate;
    private final PlainTime time;


    public PersianDateTime(PersianCalendar persianCalendar, PlainTime plainTime)
    {
        this.persianDate = persianCalendar;
        this.time = plainTime;
    }


    public static PersianDateTime from(PlainTimestamp gregorianTimeStamp, SourceTimeType timeType)
    {
        NtpTime ntpTime = new NtpTimeImpApacheNet();
        PersianCalendar date = gregorianTimeStamp.getCalendarDate().transform(PersianCalendar.class);

        switch (timeType)
        {
            case SERVERTIME:
                return new PersianDateTime(date, PlainTime.nowInSystemTime());
            case NTPTIME:
                return new PersianDateTime(date, Time4jUtil.convertTimeStampToPlainTimestamp(ntpTime.getDateFromNtp(NtpEnum.PERSIAN1.getUrl())).getWallTime());
            default:
                return new PersianDateTime(date, PlainTime.midnightAtEndOfDay());
        }

    }

    public static PersianDateTime from(int year, int month, int day, int hour, int minute, int second)
    {
        PersianCalendar date = PlainDate.of(year, month, day).transform(PersianCalendar.class);
        PlainTime time = PlainTime.of(hour, minute, second);

        return new PersianDateTime(date, time);
    }


    public PersianCalendar getPersianDate()
    {
        return persianDate;
    }


    public PlainTime getTime()
    {
        return time;
    }

    @Override
    public String toString()
    {
        return "PersianDateTime{" +
                " date=" + persianDate +
                ", time=" + time +
                '}';
    }

}
