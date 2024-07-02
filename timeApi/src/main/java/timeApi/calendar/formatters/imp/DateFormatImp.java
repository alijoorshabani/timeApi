package timeApi.calendar.formatters.imp;

import net.time4j.calendar.PersianCalendar;
import net.time4j.engine.Chronology;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.format.expert.PatternType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import timeApi.calendar.formatters.contract.DateFormat;
import timeApi.calendar.formatters.enums.FormatType;
import timeApi.convert.contract.ConvertTimestamp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Component
public class DateFormatImp implements DateFormat {

    @Autowired
    private ConvertTimestamp convertTimestamp;

    @Override
    public ChronoFormatter<PersianCalendar> getPersianFormatDate(Chronology<PersianCalendar> chronology, Locale locale)
    {
        return ChronoFormatter.setUp(chronology, locale)
                .addPattern("yyyy/MM/dd", PatternType.CLDR_DATE)
                .build();
    }

    @Override
    public String getDateFormat(Timestamp timestamp, Locale locale, FormatType formatType)
    {
        SimpleDateFormat datetimeFormatter = new SimpleDateFormat("h:mm", locale);

        if (locale.getCountry().equals("IR"))
        {
            PersianCalendar persianCalendar = convertTimestamp.convertTimestampToPersianCalendar(timestamp);

            switch (formatType)
            {
                case SLASH_DATE_TIME:
                    return ChronoFormatter.ofPattern(
                            "yyyy/MM/dd", PatternType.CLDR, locale,
                            PersianCalendar.axis()).format(persianCalendar) + " " + datetimeFormatter.format(timestamp);
                case DASHE_DATE_TIME:
                    return ChronoFormatter.ofPattern(
                            "yyyy-MM-dd", PatternType.CLDR, locale,
                            PersianCalendar.axis()).format(persianCalendar) + " " + datetimeFormatter.format(timestamp);
                case TIME:
                    return datetimeFormatter.format(timestamp).toString();
                default:
                    return "";
            }
        }
        else
        {
            switch (formatType)
            {
                case SLASH_DATE_TIME:
                    datetimeFormatter.applyPattern("yyyy/MM/dd hh:mm:ss");
                    break;
                case DASHE_DATE_TIME:
                    datetimeFormatter.applyPattern("yyyy-MM-dd hh:mm:ss");
                    break;
                case TIME:
                    datetimeFormatter.applyPattern("h:mm a");
                    break;
                default:
                    break;
            }

            return datetimeFormatter.format(timestamp).toString();
        }
    }

}
