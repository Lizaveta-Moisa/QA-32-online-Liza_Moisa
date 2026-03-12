package annotations;

import enums.TestCategory;
import extensions.TestTypeCondition;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(TestTypeCondition.class)
public @interface TestType {

    TestCategory[] value();
}
