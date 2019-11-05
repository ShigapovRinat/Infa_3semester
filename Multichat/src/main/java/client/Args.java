package client;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args{
    @Parameter(names = { "--server-port"})
    private Integer port;

    @Parameter(names ={ "--server-ip"})
    private String ip;

    public Integer getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }
}