package org.example;

import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.EmployeeService;
import org.example.service.JobService;
import org.example.service.serviceImpl.EmployeeServiceImpl;
import org.example.service.serviceImpl.JobServiceImpl;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
           EmployeeService employeeService = new EmployeeServiceImpl();
           JobService jobService = new JobServiceImpl();
//         employeeService.createEmployee();
//         jobService.createJobTable();
//         employeeService.addEmployee(new Employee("Ruslan","Manasbek u",21,"rusi@gmaail.com",1));
//         employeeService.addEmployee(new Employee("Azimbek","Ashimov",13,"azimm@gmaail.com",2));
//         jobService.addJob(new Job("Mentor","Java","Backend developer",2));
//         jobService.addJob(new Job("Teacher","History","teach history ",7));
//         System.out.println(jobService.getJobById(1L));
//         System.out.println(jobService.getJobByEmployeeId(1L));
//         System.out.println(jobService.sortByExperience("desc"));
//         jobService.deleteDescriptionColumn();
//         employeeService.cleanTable();
//         employeeService.dropTable();
//         employeeService.updateEmployee(3L,new Employee("Erbol","Sudurov",14,"erbo.@gmail.com"));
//         System.out.println(employeeService.getAllEmployees());
//         System.out.println(employeeService.findByEmail("erbo.@gmail.com"));
//         System.out.println(employeeService.getEmployeeByPosition("Mentor"));
//        System.out.println(employeeService.getEmployeeById(4L));


    }
}
