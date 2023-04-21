package org.example.dao.daoImpl;

import org.example.cofig.Config;
import org.example.dao.EmployeeDao;
import org.example.model.Employee;
import org.example.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl  implements EmployeeDao {

    public void createEmployee() {
        String sql = "create table if not exists employees(" +
                "id serial primary key," +
                "first_name varchar," +
                "last_name varchar," +
                "age int," +
                "email varchar," +
                "job_id int references jobs(id));";

        try(Connection connection = Config.connectionToDatabase();
            Statement statement = connection.createStatement()){
            statement.executeQuery(sql);
            System.out.println("Tables successfully created!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void addEmployee(Employee employee) {
        String sql = "insert into employees(first_name,last_name,age,email,job_id)" +
                "values(?,?,?,?,?);";
        
        try(Connection connection = Config.connectionToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setInt(5,employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println(employee.getFirstName()+" successfully added to employees!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void dropTable() {
        String sql = "drop table employees;";
        try(Connection connection = Config.connectionToDatabase();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Table successfully dropped!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void cleanTable() {
        String sql = "truncate table employees";
        try(Connection connection = Config.connectionToDatabase();
            Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Table successfully cleared!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateEmployee(Long id, Employee employee) {
        String sql = "update employees " +
                "set first_name = ?," +
                "last_name = ?," +
                "age = ?," +
                "email = ?  where id = ?";
        try(Connection connection = Config.connectionToDatabase();
           PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.setString(1, employee.getFirstName());
           preparedStatement.setString(2,employee.getLastName());
           preparedStatement.setInt(3,employee.getAge());
           preparedStatement.setString(4,employee.getEmail());
           preparedStatement.setLong(5,id);
           preparedStatement.execute();
            System.out.println("employee with id: "+id+" successfully added!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employees;";
        try(Connection connection = Config.connectionToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                employees.add(new Employee(
                   resultSet.getLong("id"),
                   resultSet.getString("first_name"),
                   resultSet.getString("last_name"),
                   resultSet.getInt("age"),
                   resultSet.getString("email"),
                        resultSet.getInt("job_id")));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }

    public Employee findByEmail(String email) {
        Employee employee = null;
        String sql = "select * from employees where email = ?";
        try(Connection connection = Config.connectionToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));

            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employee;
    }

    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        Map<Employee,Job> employeeJobMap = new HashMap<>();
         String sql = "select * from employees as e join jobs as j on e.job_id = j.id where e.id = ?";

         try(Connection connection = Config.connectionToDatabase();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)){
             preparedStatement.setLong(1,employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
             while (resultSet.next()){
                 Employee employee = new Employee();
                 employee.setId(resultSet.getLong("id"));
                 employee.setFirstName(resultSet.getString("first_name"));
                 employee.setLastName(resultSet.getString("last_name"));
                 employee.setAge(resultSet.getInt("age"));
                 employee.setEmail(resultSet.getString("email"));
                 employee.setJobId(resultSet.getInt("job_id"));
                 Job job = new Job();
                 job.setId(resultSet.getLong("id"));
                 job.setPosition(resultSet.getString("positionn"));
                 job.setProfession(resultSet.getString("profession"));
                 job.setExperience(resultSet.getInt("experience"));
                 employeeJobMap.put(employee,job);
             }
         }catch (SQLException e){
             System.out.println(e.getMessage());
         }
         return employeeJobMap;
    }

    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employees = new ArrayList<>();
        String sql = "select * from employees as e join jobs as j on e.job_id = j.id where positionn = ?";
        try(Connection connection = Config.connectionToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,position);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee  employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("job_id"));
                employees.add(employee);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }
}
