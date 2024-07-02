package timeApi.timeManipulate.imp;

import net.time4j.CalendarUnit;
import net.time4j.ClockUnit;
import net.time4j.PlainTimestamp;
import org.springframework.stereotype.Component;
import timeApi.timeManipulate.enums.TimeType;
import timeApi.timeManipulate.contract.TimeManipulate;

@Component
public class TimeManipulateImp implements TimeManipulate

{
    public PlainTimestamp plusTimeStamp(PlainTimestamp timestamp, int amount, TimeType type)
    {
        switch (type)
        {
            case YEAR:
                return timestamp.plus(amount, CalendarUnit.YEARS);
            case MONTH:
                return timestamp.plus(amount, CalendarUnit.MONTHS);
            case DAY:
                return timestamp.plus(amount, CalendarUnit.DAYS);
            case HOUR:
                return timestamp.plus(amount, ClockUnit.HOURS);
            case MINUTE:
                return timestamp.plus(amount, ClockUnit.MINUTES);
            case SECOND:
                return timestamp.plus(amount, ClockUnit.SECONDS);
            default:
//                "throw new TypeNotFoundException"
                return null;

        }

    }

    @Override
    public PlainTimestamp minusTimeStamp(PlainTimestamp timestamp, int amount, TimeType type)
    {
        switch (type)
        {
            case YEAR:
                return timestamp.minus(amount, CalendarUnit.YEARS);
            case MONTH:
                return timestamp.minus(amount, CalendarUnit.MONTHS);
            case DAY:
                return timestamp.minus(amount, CalendarUnit.DAYS);
            case HOUR:
                return timestamp.minus(amount, ClockUnit.HOURS);
            case MINUTE:
                return timestamp.minus(amount, ClockUnit.MINUTES);
            case SECOND:
                return timestamp.minus(amount, ClockUnit.SECONDS);
            default:
//                "throw new TypeNotFoundException"
                return null;

        }
    }

    @Override
    public Long differentBetweenTwoPlainTimeStamp(PlainTimestamp firstTime, PlainTimestamp secondTime, TimeType type)
    {
        switch (type)
        {
            case YEAR:
                return firstTime.until(secondTime, CalendarUnit.YEARS);
            case MONTH:
                return firstTime.until(secondTime, CalendarUnit.MONTHS);
            case DAY:
                return firstTime.until(secondTime, CalendarUnit.DAYS);
            case HOUR:
                return firstTime.until(secondTime, ClockUnit.HOURS);
            case MINUTE:
                return firstTime.until(secondTime, ClockUnit.MINUTES);
            case SECOND:
                return firstTime.until(secondTime, ClockUnit.SECONDS);
            default:
//                "throw new TypeNotFoundException"
                return null;

        }

    }

    @Override
    public int get(PlainTimestamp timestamp, TimeType type)
    {
        switch (type)
        {
            case YEAR:
                return timestamp.getYear();
            case MONTH:
                return timestamp.getMonth();
            case DAY:
                return timestamp.getDayOfMonth();
            case HOUR:
                return timestamp.getHour();
            case MINUTE:
                return timestamp.getMinute();
            case SECOND:
                return timestamp.getSecond();
            default:
//                "throw new TypeNotFoundException"
                return 0;

        }
    }
    
}

