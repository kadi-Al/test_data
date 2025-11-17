public interface UserDao {
    ResultSet getUserById(int userId) throws SQLException;
}
import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ResultSet getUserById(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = dataSource.getConnection();
            ps = conn.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            return rs;
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (ps != null) try { ps.close(); } catch (SQLException e) {}
            if (conn != null) try { conn.close(); } catch (SQLException e) {}
        }
    }
}
xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close">
        <!-- Set your database connection properties here -->
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/mydatabase"/>
        <property name="username" value="user"/>
        <property name="password" value="pass"/>
    </bean>

    <!-- UserDao bean definition -->
    <bean id="userDao" class="com.example.UserDaoImpl">
        <property name="dataSource" ref="dataSource"/>
    </bean>
</beans>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public ResultSet getUser(int userId) {
        try {
            return userDao.getUserById(userId);
        } catch (SQLException e) {
            // Handle or log the exception as necessary
            throw new RuntimeException("Error fetching user", e);
        }
    }
}
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.ResultSet;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public String getUser(@RequestParam("id") int userId, Model model) {
        ResultSet rs = userService.getUser(userId);
        // Process the result set and add it to the model for rendering in a view
        return "userView";
    }
}