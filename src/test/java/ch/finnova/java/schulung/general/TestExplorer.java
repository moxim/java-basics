package ch.finnova.java.schulung.general;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TestExplorer {

    int id = 666;

    private void loadTestData() {
        id = 4711;
    }

    @Test
    public void showOffAssumptions() throws Exception {
        // given
        loadTestData();
        assumeTrue(id == 4711);
        // oder
        Assumptions.assumeTrue(id == 4711);

        // when

        // then

    }

    @Test
    public void showOffAssertions() throws Exception {
        // given
        loadTestData();

        // when
        id = 0x0815;

        // then
        assertNotEquals(id, 4711, "Java is broken!");
    }

    @Test
    public void showOffFail() throws Exception {
        // given
        id = 666;

        // when
        if (id == 666) {
            // then we may have forgotten to load the test data
            fail();
        }
    }
}
