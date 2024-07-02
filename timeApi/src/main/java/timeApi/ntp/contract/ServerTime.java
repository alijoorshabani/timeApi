package timeApi.ntp.contract;


import java.sql.Timestamp;

public interface ServerTime {

    Timestamp getDateFromServerTime();

}
