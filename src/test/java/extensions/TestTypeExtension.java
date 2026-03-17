package extensions;

import annotations.TestType;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TestTypeExtension implements ExecutionCondition {

    private static final ConditionEvaluationResult ENABLED =
            ConditionEvaluationResult.enabled("Test type matches");

    private static final ConditionEvaluationResult DISABLED =
            ConditionEvaluationResult.disabled("Test type does not match");

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        String raw = System.getProperty("test.types", "");
        Set<String> enabledTypes = Arrays.stream(raw.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
        if (enabledTypes.isEmpty()) {
            return ENABLED;
        }
        Optional<Method> methodOpt = context.getTestMethod();
        Optional<Class<?>> classOpt = context.getTestClass();
        if (methodOpt.isEmpty()) {
            return ENABLED;
        }
        Set<String> actualTypes = getStrings(classOpt, methodOpt);
        boolean matched = actualTypes.stream().anyMatch(enabledTypes::contains);
        return matched ? ENABLED : DISABLED;
    }

    private static Set<String> getStrings(Optional<Class<?>> classOpt, Optional<Method> methodOpt) {
        Set<String> actualTypes = new HashSet<>();
        if (classOpt.isPresent() && classOpt.get().isAnnotationPresent(TestType.class)) {
            for (TestType.Type type : classOpt.get().getAnnotation(TestType.class).value()) {
                actualTypes.add(type.name());
            }
        }
        Method method = methodOpt.get();
        if (method.isAnnotationPresent(TestType.class)) {
            for (TestType.Type type : method.getAnnotation(TestType.class).value()) {
                actualTypes.add(type.name());
            }
        }
        return actualTypes;
    }
}
