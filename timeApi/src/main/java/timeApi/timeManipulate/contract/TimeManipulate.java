package timeApi.timeManipulate.contract;


import net.time4j.PlainTimestamp;
import timeApi.timeManipulate.enums.TimeType;

public interface TimeManipulate
{

    PlainTimestamp plusTimeStamp(PlainTimestamp timestamp, int amount, TimeType type);
    PlainTimestamp minusTimeStamp(PlainTimestamp timestamp,int amount,TimeType type);
    Long differentBetweenTwoPlainTimeStamp(PlainTimestamp firstTime, PlainTimestamp secondTime, TimeType type);
    int get(PlainTimestamp timestamp, TimeType type);
}
