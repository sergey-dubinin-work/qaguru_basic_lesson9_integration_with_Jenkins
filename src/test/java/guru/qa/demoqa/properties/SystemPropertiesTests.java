package guru.qa.demoqa.properties;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {

//    System.getProperty("key");
//    System.setProperty("key", "value");

    // get empty value
    @Test
    void test1() {
        String someValue = System.getProperty("someKey");
        System.out.println(someValue); // null
    }

    // set and get value
    @Test
    void test2() {
        System.setProperty("someKey", "Google Chrome");
        String someValue = System.getProperty("someKey");
        System.out.println(someValue); // "Google Chrome"
    }

    // get default value
    @Test
    void test3() {
        String someValue = System.getProperty("someKey", "Opera");
        System.out.println(someValue); // Opera
    }

    // get property from gradle (from command line) - gradle clean properties_test -Dbrowser=chrome
    @Test
    @Tag("properties")
    void test4() {
        String browser = System.getProperty("browser");
        System.out.println(browser); // Opera
    }

}
