package org.example.dao.daoImpl;

import org.example.cofig.Config;
import org.example.dao.JobDao;
import org.example.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {

    public void createJobTable() {
        String sql = "create table if not exists jobs(" +
                "id serial primary key," +
                "positionn varchar," +
                "profession varchar," +
                "description varchar," +
                "experience int);";

        try(Connection connection = Config.connectionToDatabase();
            Statement statement = connection.createStatement()){
            statement.executeQuery(sql);
            System.out.println("Tables successfully created!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addJob(Job job) {
        String sql = "insert into jobs(positionn,profession,description,experience)" +
                "values (?,?,?,?);";

        try(Connection connection = Config.connectionToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,job.getPosition());
            preparedStatement.setString(2,job.getProfession());
            preparedStatement.setString(3,job.getDescription());
            preparedStatement.setInt(4,job.getExperience());
            System.out.println("New jobs successfully added to job table");
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public Job getJobById(Long jobId) {
        Job job = null;
        String sql = "select * from jobs where id = ?;";
        try(Connection connection = Config.connectionToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1,jobId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("positionn"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return job;
    }

    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobs = new ArrayList<>();
        String orderBy = "ASC";
        if (ascOrDesc.equalsIgnoreCase("desc")) {
            orderBy = "DESC";
        }
        String sql ="select * from jobs order by experience " + orderBy;
        try(Connection connection = Config.connectionToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("positionn"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                jobs.add(job);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return jobs;
    }

    public Job getJobByEmployeeId(Long employeeId) {
        Job job = null;
        String sql = "select * from jobs as j join employees as e on j.id = e.job_id where j.id = ?;";
       try(Connection connection = Config.connectionToDatabase();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
           preparedStatement.setLong(1,employeeId);
           ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
               job = new Job();
               job.setId(resultSet.getLong("id"));
               job.setPosition(resultSet.getString("positionn"));
               job.setProfession(resultSet.getString("profession"));
               job.setDescription(resultSet.getString("description"));
               job.setExperience(resultSet.getInt("experience"));
           }
       }catch (SQLException e){
           System.out.println(e.getMessage());
       }
        return job;
    }

    public void deleteDescriptionColumn() {
        String sql = "alter table jobs drop column description;";
        try(Connection connection = Config.connectionToDatabase();
           PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.executeUpdate();
            System.out.println("Column description successfully deleted!!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }
}
