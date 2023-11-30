package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        CarService carService = context.getBean(CarService.class);

        Car car1 = new Car("BMV1", 525);
        carService.add(car1);
        Car car2 = new Car("BMV2", 525);
        carService.add(car2);
        Car car3 = new Car("BMV3", 525);
        carService.add(car3);
        Car car4 = new Car("BMV4", 525);
        carService.add(car4);

        userService.add(new User("Ivan1", "Ivanovich1", "1van2gmail.com", car1));
        userService.add(new User("Ivan2", "Ivanovich2", "2van2gmail.com", car2));
        userService.add(new User("Ivan3", "Ivanovich3", "3van2gmail.com", car3));
        userService.add(new User("Ivan4", "Ivanovich4", "4van2gmail.com", car4));

        List<User> userList = userService.listUsers();
        for (User user : userList) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        System.out.println("Пользователь с авто BMV1 525");
        System.out.println(userService.foundUser("BMV1", 525));
        context.close();
    }
}
