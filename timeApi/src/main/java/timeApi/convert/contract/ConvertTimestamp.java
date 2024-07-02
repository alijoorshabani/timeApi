package timeApi.convert.contract;

import net.time4j.calendar.PersianCalendar;

import java.sql.Timestamp;

public interface ConvertTimestamp {
    Timestamp convertDateToTimestamp(String dateTime);

    String convertTimestampToDateTime(Timestamp timestamp);

    Timestamp convertPersianCalendarToTimestamp(PersianCalendar persianCalendar, int hour, int minute, int second);

    PersianCalendar convertTimestampToPersianCalendar(Timestamp timestamp);
}
