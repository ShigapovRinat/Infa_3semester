package server;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args{
    @Parameter(names = { "--port"})
    private Integer port;

    @Parameter(names = "--properties")
    private String propertiesPath;

    public Integer getPort() {
        return port;
    }

    public String getPropertiesPath() {
        return propertiesPath;
    }
}