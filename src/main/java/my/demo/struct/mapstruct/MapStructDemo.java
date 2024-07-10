package my.demo.struct.mapstruct;

import my.demo.struct.User;
import my.demo.struct.UserDto;
import org.modelmapper.ModelMapper;

import java.time.Instant;
import java.time.Duration;

public class MapStructDemo {
    public static void main(String[] args) {
        example();
    }

    public static void example() {
        for (int i = 0; i < 12; i++) {
            User po = new User(i + 1, String.valueOf(i + 1));
            convert(po);
        }
    }

    public static void convert(User po) {
        Instant start = Instant.now();
        UserDto dto = UserMapper.INSTANCE.toDto(po);

        Duration duration = Duration.between(start, Instant.now());
        System.out.println("MapStructDemo 运行时间: " + duration.toMillis() + " 毫秒");
        System.out.println("po:  " + po);
        System.out.println("dto: " + dto);
        System.out.println("===== ===== ===== ===== =====");
    }
}
