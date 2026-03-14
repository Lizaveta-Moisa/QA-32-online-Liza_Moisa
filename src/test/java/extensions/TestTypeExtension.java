package extensions;

import annotations.TestType;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TestTypeExtension implements ExecutionCondition {

    private static final ConditionEvaluationResult ENABLED = ConditionEvaluationResult.enabled("Test type matches");
    private static final ConditionEvaluationResult DISABLED = ConditionEvaluationResult.disabled("Test type does not match");

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        Optional<Method> method = context.getTestMethod();
        Optional<Class<?>> clazz = context.getTestClass();

        Set<String> enabledTypes = Arrays.stream(System.getProperty("test.types", "").split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());

        if (enabledTypes.isEmpty()) return DISABLED;

        if (method.isPresent() && method.get().isAnnotationPresent(TestType.class)) {
            return matchesTypes(method.get().getAnnotation(TestType.class), enabledTypes);
        }

        if (clazz.isPresent() && clazz.get().isAnnotationPresent(TestType.class)) {
            return matchesTypes(clazz.get().getAnnotation(TestType.class), enabledTypes);
        }

        return DISABLED;
    }

    private ConditionEvaluationResult matchesTypes(TestType annotation, Set<String> enabledTypes) {
        for (TestType.Type type : annotation.value()) {
            if (enabledTypes.contains(type.name())) return ENABLED;
        }
        return DISABLED;
    }
}