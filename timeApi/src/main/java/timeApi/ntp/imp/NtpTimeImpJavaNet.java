package timeApi.ntp.imp;

import org.springframework.stereotype.Component;
import timeApi.ntp.contract.NtpTime;
import java.net.*;
import java.sql.Timestamp;

@Component
public class NtpTimeImpJavaNet implements NtpTime
{
    @Override
    public Timestamp getDateFromNtp(String ntpServerUrl)
    {
        final int NTP_PACKET_SIZE = 48;
        final int NTP_SERVER_PORT = 123;
        final int TIMEOUT_MS = 5000;

        try (DatagramSocket socket = new DatagramSocket())
        {
            socket.setSoTimeout(TIMEOUT_MS); // Set socket timeout

            byte[] request = new byte[NTP_PACKET_SIZE];
            request[0] = 0x1B;

            InetAddress address = InetAddress.getByName(ntpServerUrl);
            DatagramPacket requestPacket = new DatagramPacket(request, request.length, address, NTP_SERVER_PORT);
            socket.send(requestPacket);

            byte[] response = new byte[NTP_PACKET_SIZE];
            DatagramPacket responsePacket = new DatagramPacket(response, response.length);
            socket.receive(responsePacket);

            long secondsSince1900 = getSeconds(response, 32);
            long javaEpochTime = secondsSince1900 - 2208988800L; // Convert to Java epoch time

            return new Timestamp(javaEpochTime * 1000);
        }
        catch (Exception e)
        {
            System.err.println("An error occurred while fetching time from NTP server: " + e.getMessage());
            return null;
        }
    }

    private long getSeconds(byte[] buffer, int offset)
    {
        return ((long) (buffer[offset] & 0xFF) << 24) +
                ((long) (buffer[offset + 1] & 0xFF) << 16) +
                ((long) (buffer[offset + 2] & 0xFF) << 8) +
                (buffer[offset + 3] & 0xFF);
    }
}
