package timeApi.calendar.imp;

import net.time4j.PlainTimestamp;
import org.springframework.stereotype.Component;
import timeApi.apis.enums.SourceTimeType;
import timeApi.calendar.PersianDateTime;
import timeApi.calendar.contract.GregorianToPersian;


@Component
public class GregorianToPersianConverter implements GregorianToPersian
{

    @Override
    public PersianDateTime getPersianDateTime(PlainTimestamp gregorianTimeStamp, SourceTimeType type)
    {

        return PersianDateTime.from(gregorianTimeStamp, type);
    }


    @Override
    public PersianDateTime getPersianDateTime(int year, int month, int day, int hour, int minute, int second)
    {
        return PersianDateTime.from(year, month, day, hour, minute, second);
    }

}
