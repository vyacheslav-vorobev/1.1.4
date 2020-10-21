package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService  = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Вячеслав","Воробьев", (byte)23);
        userService.saveUser("НеВячеслав","НеВоробьев", (byte)64);
        userService.saveUser("Vyacheslav","V", (byte)56);
        userService.saveUser("ИмяЧеловека","Фамилия", (byte)43);
        List<User> list = userService.getAllUsers();
        for (User user: list){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
