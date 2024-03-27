package edu.kirkwood.learnx.data;

import edu.kirkwood.learnx.model.Course;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CourseDAO extends Database {
    public static void main(String[] args) {
        List<Course> courses = get(5, 0, "", "");
        courses.forEach(System.out::println);
    }

    // get all
    public static List<Course> get(int limit, int offset, String categories, String skillLevel) {
        List<Course> courses = new ArrayList<>();
        try(Connection connection = getConnection();
            CallableStatement statement = connection.prepareCall("{CALL sp_get_courses(?,?,?,?)}");
        ) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            statement.setString(3, categories);
            statement.setString(4, skillLevel);
            try(ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String level = resultSet.getString("level");
                    String picture = resultSet.getString("picture");
                    String first_name = resultSet.getString("first_name");
                    String last_name = resultSet.getString("last_name");
                    int category_id = resultSet.getInt("category_id");
                    String category_name = resultSet.getString("category_name");
                    Course course = new Course(id, name, description, level, picture, first_name,
                            last_name, category_id, category_name);
                    courses.add(course);
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }
    // get single

    // add new

    // update

    // delete


}
