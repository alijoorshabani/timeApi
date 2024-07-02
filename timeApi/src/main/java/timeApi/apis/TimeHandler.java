package timeApi.apis;

import net.time4j.calendar.PersianCalendar;
import timeApi.apis.enums.NtpType;
import timeApi.apis.enums.SourceTimeType;
import timeApi.calendar.formatters.contract.DateFormat;
import timeApi.calendar.formatters.enums.FormatType;
import timeApi.calendar.formatters.imp.DateFormatImp;
import timeApi.convert.contract.ConvertTimestamp;
import timeApi.convert.imp.ConvertTimestampImp;
import timeApi.ntp.contract.NtpTime;
import timeApi.ntp.imp.NtpTimeImpApacheNet;
import timeApi.ntp.imp.NtpTimeImpJavaNet;
import timeApi.ntp.imp.ServerTimeImp;
import timeApi.ntp.enums.NtpEnum;
import timeApi.timeManipulate.enums.TimeType;
import timeApi.timeManipulate.imp.TimeManipulateImp;
import timeApi.utility.Time4jUtil;
import timeApi.ntp.contract.ServerTime;
import timeApi.timeManipulate.contract.TimeManipulate;
import java.sql.Timestamp;
import java.util.Locale;


public class TimeHandler
{
    private DateFormat dateFormat;
    private Timestamp timeStamp;
    private boolean is_persian = false;

    public TimeHandler(TimeHandlerBuilder builder)
    {
        this.timeStamp = builder.timeStamp;
        this.is_persian = builder.is_persian;
    }

    public Timestamp getTimeStamp()
    {
        return timeStamp;
    }

    public String getDateFormats(FormatType formatType, Locale locale)
    {
        dateFormat=new DateFormatImp();
        return is_persian ? dateFormat.getDateFormat(this.getTimeStamp(), new Locale("fa", "IR"), formatType)
                : dateFormat.getDateFormat(this.getTimeStamp(), locale, formatType);
    }

    public static class TimeHandlerBuilder
    {
        private ServerTime serverTime;
        private TimeManipulate timeManipulate = new TimeManipulateImp();
        private NtpTime ntpTime;
        private ConvertTimestamp convertTimestamp;
        private Timestamp timeStamp;
        private boolean is_persian = false;


        public TimeHandlerBuilder(SourceTimeType sourceTimeType, NtpType ntpType)
        {
            switch (sourceTimeType)
            {
                case SERVERTIME:
                    serverTime = new ServerTimeImp();
                    this.timeStamp = serverTime.getDateFromServerTime();
                    break;
                case NTPTIME:
                    if(ntpType!=null)
                    {
                        switch (ntpType)
                        {
                            case JAVANET:
                                ntpTime = new NtpTimeImpJavaNet();
                                this.timeStamp = ntpTime.getDateFromNtp(NtpEnum.POOL.getUrl());
                                break;
                            case APACHENET:
                                ntpTime = new NtpTimeImpApacheNet();
                                this.timeStamp = ntpTime.getDateFromNtp(NtpEnum.POOL.getUrl());
                                break;
                            default:
                                throw new RuntimeException("type Not Found");
                        }
                    }
                    else throw new RuntimeException("ntpType is null");

                    break;

                default:
                    throw new RuntimeException("type Not Found");
            }

        }

        public TimeHandlerBuilder(PersianCalendar persianCalendar, int hour, int minute, int second)
        {
            convertTimestamp = new ConvertTimestampImp();
            this.timeStamp = convertTimestamp.convertPersianCalendarToTimestamp(persianCalendar, hour, minute, second);
        }

        public TimeHandlerBuilder(Timestamp timestamp)
        {
            this.timeStamp = timestamp;
        }

        public TimeHandlerBuilder toPersianDate()
        {
            this.is_persian = true;
            return this;
        }

        public TimeHandlerBuilder toGregorianDate()
        {
            this.is_persian = false;
            return this;
        }

        public TimeHandlerBuilder plusTimeStamp(int amount, TimeType type)
        {
            this.timeStamp = Time4jUtil.convertPlainTimestampToTimestamp(timeManipulate.plusTimeStamp(Time4jUtil.convertTimeStampToPlainTimestamp(this.timeStamp), amount, type));
            return this;
        }

        public TimeHandlerBuilder minusTimeStamp(int amount, TimeType type)
        {
            this.timeStamp = Time4jUtil.convertPlainTimestampToTimestamp(timeManipulate.minusTimeStamp(Time4jUtil.convertTimeStampToPlainTimestamp(this.timeStamp), amount, type));
            return this;
        }

        public TimeHandler build()
        {
            return new TimeHandler(this);
        }
    }

}
