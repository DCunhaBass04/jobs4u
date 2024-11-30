package utils;

import java.io.*;

public class Utils {
    public static String readFromInputStream(String fileName)
            throws IOException {
        InputStream inputStream = new FileInputStream(fileName);
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
