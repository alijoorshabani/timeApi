package timeApi.utility;

import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.PlainTimestamp;

import java.sql.Timestamp;

public class Time4jUtil
{

    public static PlainTimestamp convertTimeStampToPlainTimestamp(Timestamp timestamp)
    {
        PlainDate date = PlainDate.from(timestamp.toLocalDateTime().toLocalDate());
        PlainTime time = PlainTime.from(timestamp.toLocalDateTime().toLocalTime());

        return PlainTimestamp.of(date, time);
    }

    public static Timestamp convertPlainTimestampToTimestamp(PlainTimestamp plainTimestamp)
    {
        java.time.LocalDateTime localDateTime = java.time.LocalDateTime.of(plainTimestamp.getYear(),
                plainTimestamp.getMonth(),
                plainTimestamp.getDayOfMonth(),
                plainTimestamp.getHour(),
                plainTimestamp.getMinute(),
                plainTimestamp.getSecond());

        return Timestamp.valueOf(localDateTime);
    }

}
