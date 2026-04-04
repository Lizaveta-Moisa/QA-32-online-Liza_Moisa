package service;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import entity.Request;
import io.restassured.config.EncoderConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.Method;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;


import static io.restassured.RestAssured.given;

public class ApiService {
    protected final HttpClientConfig httpClientConfig = CurlRestAssuredConfigFactory.createConfig().getHttpClientConfig();

    protected final static String BASE_URL = "http://localhost:5030";

    protected <T> T get(Request request) {
        return execute(Method.GET, request);
    }

    protected <T> T post(Request request) {
        return execute(Method.POST, request);
    }

    protected <T> T put(Request request) {
        return execute(Method.PUT, request);
    }

    protected <T> T patch(Request request) {
        return execute(Method.PATCH, request);
    }

    protected <T> T delete(Request request) {
        return execute(Method.DELETE, request);
    }

    private RequestSpecification getRequestSpecification(String url,
                                                         EncoderConfig config,
                                                         Map<String, String> params,
                                                         Map<String, String> headers,
                                                         Object body,
                                                         Boolean urlEncodingEnabled) {
        RequestSpecification requestSpecification = given()
                .log()
                .all()
                .baseUri(url)
                .filters(List.of(new AllureRestAssured()))
                .config(Objects.nonNull(config)
                        ? RestAssuredConfig.config().httpClient(httpClientConfig).encoderConfig(config)
                        : RestAssuredConfig.config().httpClient(httpClientConfig))
                .queryParams(Optional.ofNullable(params).orElse(Collections.emptyMap()))
                .headers(Optional.ofNullable(headers).orElse(Collections.emptyMap()))
                .urlEncodingEnabled(Objects.nonNull(urlEncodingEnabled)
                        ? urlEncodingEnabled
                        : true);
        return specifyBody(requestSpecification, body);
    }

    private RequestSpecification specifyBody(RequestSpecification spec,
                                             Object body) {
        if (Objects.isNull(body)) {
            return spec.body(StringUtils.EMPTY);
        }
        return switch (body) {
            case byte[] bytes -> spec.body(bytes);
            case String str -> spec.body(str);
            case File file -> spec.body(file);
            case InputStream is -> spec.body(is);
            default -> spec.body(body);
        };
    }

    @SuppressWarnings("unchecked")
    private <T> T execute(Method method,
                          Request request) {
        ValidatableResponse validatableResponse = getValidatableResponse(method, request);
        Class<?> clazz = request.getClazz();
        if (Objects.isNull(clazz) || clazz == ValidatableResponse.class) {
            return (T) validatableResponse;
        }
        return Objects.isNull(request.getJsonPath())
                ? (T) extractResponse(validatableResponse, request.getSchemaName(), clazz)
                : (T) extractResponseByJsonPath(validatableResponse, request.getJsonPath(), clazz);
    }

    protected <T> T extractResponse(ValidatableResponse validatableResponse,
                                    String schemaName,
                                    Class<T> clazz) {
        if (Objects.nonNull(schemaName)) {
            validatableResponse.body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaName));
        }
        return validatableResponse.extract()
                .as(clazz);
    }

    protected <T> T extractResponseByJsonPath(ValidatableResponse validatableResponse,
                                              String jsonPath,
                                              Class<T> clazz) {
        return validatableResponse.extract()
                .jsonPath()
                .getObject(Objects.nonNull(jsonPath)
                                ? jsonPath
                                : StringUtils.EMPTY,
                        clazz
                );
    }

    protected ValidatableResponse getValidatableResponse(Method method,
                                                         Request request) {
        RequestSpecification requestSpecification =
                getRequestSpecification(request.getUrl(), request.getConfig(), request.getParams(), request.getHeaders(),
                        request.getBody(), request.getUrlEncodingEnabled());
        ValidatableResponse validatableResponse = switch (method) {
            case Method.GET -> requestSpecification.get(getPath(request.getPath())).then();
            case Method.POST -> requestSpecification.post(getPath(request.getPath())).then();
            case Method.PUT -> requestSpecification.put(getPath(request.getPath())).then();
            case Method.PATCH -> requestSpecification.patch(getPath(request.getPath())).then();
            case Method.DELETE -> requestSpecification.delete(getPath(request.getPath())).then();
            case HEAD, TRACE, OPTIONS -> null;
        };
        assert validatableResponse != null;
        validatableResponse.log().all();
        return validatableResponse;
    }

    private String getPath(String path) {
        return Objects.requireNonNullElse(path, StringUtils.EMPTY);
    }
}
