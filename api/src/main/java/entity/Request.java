package entity;

import io.restassured.config.EncoderConfig;
import io.restassured.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String url;
    private EncoderConfig config;
    private String path;
    private Map<String, String> params;
    private Map<String, String> headers;
    private Object body;
    private String schemaName;
    private Class<?> clazz;
    private String jsonPath;
    private Method method;
    private Boolean urlEncodingEnabled;
}
