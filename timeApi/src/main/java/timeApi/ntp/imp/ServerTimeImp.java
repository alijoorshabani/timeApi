package timeApi.ntp.imp;

import org.springframework.stereotype.Component;
import timeApi.ntp.contract.ServerTime;
import java.sql.Timestamp;

@Component
public class  ServerTimeImp implements ServerTime
{
    @Override
    public Timestamp getDateFromServerTime()
    {
        long currentTimeMillis = System.currentTimeMillis();
        return new Timestamp(currentTimeMillis);
    }
}
