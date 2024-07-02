package timeApi.calendar.formatters.contract;

import net.time4j.calendar.PersianCalendar;
import net.time4j.engine.Chronology;
import net.time4j.format.expert.ChronoFormatter;
import timeApi.calendar.formatters.enums.FormatType;

import java.sql.Timestamp;
import java.util.Locale;

public interface DateFormat {

    ChronoFormatter<PersianCalendar> getPersianFormatDate(Chronology<PersianCalendar> chronology, Locale locale);

    String getDateFormat(Timestamp timestamp, Locale locale, FormatType formatType);
}
