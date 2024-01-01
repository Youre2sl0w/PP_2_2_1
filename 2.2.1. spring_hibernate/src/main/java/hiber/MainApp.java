package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.persistence.NoResultException;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Oleg", "Olegov", "olegolegov@mail.ru");
      Car car1 = new Car("Zhiguli", 1998);

      User user2 = new User("Artem", "Artemov", "artemartemov@mail.ru");
      Car car2 = new Car("Tesla", 2018);

      User user3 = new User("Maksim", "Maksimov", "maksimmaksimov@mail.ru");
      Car car3 = new Car("Porsche", 2003);

      User user4 = new User("Danil", "Danilov", "danildanilov@mail.ru");
      Car car4 = new Car("Koryto", 1871);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));

      for (User user : userService.listUsers()) {
         System.out.print(user.getId() + "-й ");
         System.out.println(user + " = " + user.getCar());
      }

      try {
         System.out.println(userService.getUserByCar("Koryto", 1871));
      } catch (NoResultException e) {
         System.out.println("нет пользователя с такой машиной");
      }

      context.close();
   }
}