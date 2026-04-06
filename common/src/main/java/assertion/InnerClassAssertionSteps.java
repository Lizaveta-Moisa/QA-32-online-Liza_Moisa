package assertion;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InnerClassAssertionSteps<T> extends AssertionSteps {
    private final T outerStepsInstance;

    public T stepBack() {
        return outerStepsInstance;
    }
}
