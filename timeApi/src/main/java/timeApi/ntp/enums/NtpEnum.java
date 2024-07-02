package timeApi.ntp.enums;

public enum NtpEnum
{
    POOL("pool.ntp.org"),
    GOOGLE("time.google.com"),
    WINDOWS("time.windows.com"),
    APPLE("time.apple.com"),
    PERSIAN(".me.pool.ntp.org"),
    PERSIAN1("ir.pool.ntp.org");

    private final String url;

    NtpEnum(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
}
