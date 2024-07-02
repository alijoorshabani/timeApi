package timeApi.ntp.imp;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.springframework.stereotype.Component;
import timeApi.ntp.contract.NtpTime;
import java.net.InetAddress;
import java.sql.Timestamp;

@Component
public class NtpTimeImpApacheNet implements NtpTime
{
    @Override
    public Timestamp getDateFromNtp(String ntpServerUrl)
    {

        try {
            NTPUDPClient client = new NTPUDPClient();
            client.setDefaultTimeout(2000);
            client.open();

            InetAddress inetAddress = InetAddress.getByName(ntpServerUrl);
            TimeInfo timeInfo = client.getTime(inetAddress);
            if (timeInfo != null)
            {
                timeInfo.computeDetails();
                Long offsetValue = timeInfo.getOffset();
                if (offsetValue != null)
                {
                    long currentTimeMillis = System.currentTimeMillis() + offsetValue;
                    return new Timestamp(currentTimeMillis);
                }
            }

            return null;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

