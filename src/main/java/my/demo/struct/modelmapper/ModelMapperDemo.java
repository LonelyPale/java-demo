package my.demo.struct.modelmapper;

import my.demo.struct.model.User;
import my.demo.struct.model.UserDto;
import org.modelmapper.ModelMapper;

import java.time.Duration;
import java.time.Instant;

public class ModelMapperDemo {
    public static void main(String[] args) {
        example();
    }

    public static void example() {
        ModelMapper modelMapper = new ModelMapper();
        for (int i = 0; i < 12; i++) {
            User po = new User(i + 1, String.valueOf(i + 1));
            convert(modelMapper, po);
        }
    }

    public static void convert(ModelMapper modelMapper, User po) {
        Instant start = Instant.now();
        UserDto dto = modelMapper.map(po, UserDto.class);

        Duration duration = Duration.between(start, Instant.now());
        System.out.println("ModelMapperDemo 运行时间: " + duration.toMillis() + " 毫秒");
        System.out.println("ModelMapperDemo 运行时间: " + duration.toNanos() + " 纳秒");
        System.out.println("po:  " + po);
        System.out.println("dto: " + dto);
        System.out.println("===== ===== ===== ===== =====");
    }
}
