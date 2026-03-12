package extensions;

import annotations.TestType;
import enums.TestCategory;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class TestTypeCondition implements ExecutionCondition {

    private static final ConditionEvaluationResult ENABLED =
            ConditionEvaluationResult.enabled("Test type matches");

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

        String typesProperty = System.getProperty("test.types");

        if (typesProperty == null || typesProperty.isEmpty()) {
            return ConditionEvaluationResult.disabled("No test.types specified");
        }

        Set<String> allowedTypes = Arrays.stream(typesProperty.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());

        Optional<TestType> methodAnnotation =
                context.getElement().flatMap(el ->
                        Optional.ofNullable(el.getAnnotation(TestType.class)));

        Optional<TestType> classAnnotation =
                context.getTestClass()
                        .map(clazz -> clazz.getAnnotation(TestType.class));

        TestType annotation = methodAnnotation.orElse(classAnnotation.orElse(null));

        if (annotation == null) {
            return ConditionEvaluationResult.disabled("No @TestType annotation");
        }

        boolean match = Arrays.stream(annotation.value())
                .map(Enum::name)
                .anyMatch(allowedTypes::contains);

        if (match) {
            return ENABLED;
        }

        return ConditionEvaluationResult.disabled("Test type not included");
    }
}
