package my.demo.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cmd {
    public static void main(String[] args) throws IOException {
        cmd2();
    }

    public static void cmd1() throws IOException {
        //        String command = String.format("/usr/local/bin/python -V");
        String command = String.format("/usr/local/bin/bash -c \"python -V\"");
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

    public static void cmd2() throws IOException {
        String currentDir = System.getProperty("user.dir");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("/usr/local/bin/python", "-V");
        //processBuilder.command("/usr/local/bin/python -V");
        Map<String, String> environment = processBuilder.environment();
        environment.put("LD_LIBRARY_PATH", "/opt/occt762/lib");
        Process process = processBuilder.start();

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
