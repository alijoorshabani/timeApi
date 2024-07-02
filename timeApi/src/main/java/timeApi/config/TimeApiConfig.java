package timeApi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import timeApi.ntp.contract.NtpTime;
import timeApi.ntp.imp.NtpTimeImpApacheNet;
import timeApi.ntp.imp.NtpTimeImpJavaNet;

@Configuration
@ComponentScan(basePackages = "timeApi")
public class TimeApiConfig
{
    @Bean
    @Qualifier("ntpTimeImpApacheNet")
    public NtpTime ntpTimeImpApacheNet()
    {
        NtpTimeImpApacheNet ntpTimeImpApacheNet = new NtpTimeImpApacheNet();
        return ntpTimeImpApacheNet;
    }


    @Bean
    @Qualifier("ntpTimeImpJavaNet")
    public NtpTime ntpTimeImpJavaNet()
    {
        NtpTimeImpJavaNet ntpTimeImpJavaNet = new NtpTimeImpJavaNet();
        return ntpTimeImpJavaNet;
    }

}
