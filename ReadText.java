package BI9202;
import java.nio.file.*;
public class ReadText {
    public static String ReadFile(String fileName) throws Exception {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }
}
