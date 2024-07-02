package timeApi.convert.imp;

import net.time4j.PlainDate;
import net.time4j.calendar.PersianCalendar;
import org.springframework.stereotype.Component;
import timeApi.convert.contract.ConvertTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class ConvertTimestampImp implements ConvertTimestamp {
    @Override
    public Timestamp convertDateToTimestamp(String dateTime)
    {
        LocalDateTime dateTimeConvert = LocalDateTime.parse(dateTime);
        return Timestamp.valueOf(dateTimeConvert);
    }

    @Override
    public String convertTimestampToDateTime(Timestamp timestamp)
    {
        return timestamp.toLocalDateTime().toLocalDate().toString();
    }

    @Override
    public Timestamp convertPersianCalendarToTimestamp(PersianCalendar persianCalendar, int hour, int minute, int second)
    {
        PlainDate plainDate = persianCalendar.transform(PlainDate.class);
        LocalDateTime localDateTime = plainDate.toTemporalAccessor().atTime(LocalTime.of(hour, minute, second));

        return Timestamp.valueOf(localDateTime);
    }

    @Override
    public PersianCalendar convertTimestampToPersianCalendar(Timestamp timestamp)
    {
        PlainDate date = PlainDate.from(timestamp.toLocalDateTime().toLocalDate());
        return date.transform(PersianCalendar.class);
    }
}
