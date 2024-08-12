package my.demo.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Cmd {
    public static void main(String[] args) throws IOException {
        displayEnvironmentalInformation();
//        cmd1();
    }

    public static void cmd1() throws IOException {
//        String currentDir = System.getenv().getProperty("user.dir");

        //String command = "/usr/local/bin/python -V";
        //String command = "bash --help";
        //Process process = Runtime.getRuntime().exec(command);

        String[] cmdarray = {"bash", "-c", ". ~/.profile; python -V"};
        Process process = Runtime.getRuntime().exec(cmdarray);

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

    public static void displayEnvironmentalInformation() {
        System.out.println("===== ===== ===== env - start ===== ===== =====");
        Map<String, String> env = System.getenv();
        for (Map.Entry<String, String> entry : env.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.printf("%s=%s%n", key, value);
        }
        System.out.println("===== ===== ===== env - end   ===== ===== =====");

        System.out.println();
        System.out.println("===== ===== ===== properties - start ===== ===== =====");
        Properties properties = System.getProperties();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            System.out.printf("%s=%s%n", key, value);
        }
        System.out.println("===== ===== ===== properties - end   ===== ===== =====");

    }
}
