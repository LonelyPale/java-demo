package my.demo.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Cmd {
    public static void main(String[] args) throws IOException {
        String command = String.format("/usr/local/bin/python -V");
        Process process = Runtime.getRuntime().exec(command);

        String line = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        // handle process error log and output
        List<String> lines = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            lines.add(line);
        }
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = reader1.readLine()) != null) {
            System.out.println(line);
        }
        process.destroy();

    }
}
