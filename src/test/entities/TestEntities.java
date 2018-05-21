package entities;

import com.java.project.model.domain.Role;
import com.java.project.model.domain.User;

public class TestEntities {

    public static User getTestUser() {
        return new User(Role.ADMIN, "romaniukv", "YnhKhE8/dy/YGgtqezqJGapWwOeoMHVO",
                "romaniukv255@gmail.com", "Vika", "Romaniuk");
    }

    public static User getTestUser2() {
        return new User(Role.USER, "romaniukv2", "password",
                "romaniukv255@gmail.com", "Vika", "Romaniuk");
    }
}
