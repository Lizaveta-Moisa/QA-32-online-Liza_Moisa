package assertion;

import java.util.Collection;

import static assertion.SoftAssertionsStorage.softAssert;

public class AssertionSteps {
    public void assertAll() {
        softAssert().assertAll();
    }

    public AssertionSteps assertCollectionSize(int expectedSize,
                                               int actualSize) {
        softAssert().assertThat(expectedSize)
                .isEqualTo(actualSize);
        return this;
    }

    public <T> void assertCollectionsElementsInAnyOrder(Collection<T> actualCollection,
                                                        Collection<T> expectedCollection) {
    }
}
