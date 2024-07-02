package timeApi.ntp.contract;

import java.sql.Timestamp;

public interface NtpTime
{
    Timestamp getDateFromNtp(String ntpServerUrl);
}
