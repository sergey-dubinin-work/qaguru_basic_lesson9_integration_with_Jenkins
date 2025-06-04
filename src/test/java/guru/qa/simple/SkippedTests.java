package guru.qa.simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkippedTests {

    @Test
    @Disabled("With any reason")
    void test00() {
        assertTrue(true);
    }

    @Test
    @Disabled("With any reason")
    void test01() {
        assertTrue(true);
    }

}
