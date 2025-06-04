package guru.qa.demoqa.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JavaScriptReader {

    public static String readJavaScriptCodeFromFile(String path) {
        String jsCode = "";
        try {
            jsCode = Files.readString(Path.of(path));
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return jsCode;
    }

}
