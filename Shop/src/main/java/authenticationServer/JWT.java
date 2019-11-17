package authenticationServer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.api.scripting.JSObject;

public class JWT {
    private ObjectMapper mapper = new ObjectMapper();
    private Header h = new Header("HS512", "JWT");
    private String header = "{ \"alg\": \"HS256\", \"typ\": \"JWT\"}";
    private String jsonHeader;
    {
        try {
            jsonHeader = mapper.writeValueAsString(h);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
