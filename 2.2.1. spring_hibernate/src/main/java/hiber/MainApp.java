package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        Car car1 = new Car("Tesla", 6);
        Car car2 = new Car("Accord", 7);
        Car car3 = new Car("Diablo", 8);
        Car car4 = new Car("Civic", 9);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(String.format("Id = %d%nFirst Name = %s%nLast Name = %s%nEmail = %s%n",
                    user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));

        }
        User user = userService.findUserByCar("Diablo", 8);
        System.out.println(user.getFirstName() + " " + user.getLastName());


        context.close();
    }
}
