package annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TestType {
    Type[] value();

    enum Type {
        SMOKE,
        REGRESSION,
        UI
    }
}