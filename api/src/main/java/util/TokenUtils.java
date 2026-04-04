package util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenUtils {

    public String createBearer(String token){
        return "Bearer %s".formatted(token);
    }
}
