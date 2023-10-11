package manager;

import model.History;
import model.Worker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Manager {
    Validate validate = new Validate();
   
// Allow user to add a worker
public void addWorker(ArrayList<Worker> lw) {
    System.out.print("Enter code: ");
    String id;
    while (true) {
        id = validate.checkInputString();

        // Validate if the ID is numeric and has a length between 1 and 2 digits
        if (!id.matches("\\d{1,2}")) {
            System.err.println("Invalid ID. Please enter a numeric ID with a length between 1 and 2 digits.");
            System.err.print("Enter again:");
        } else if (validate.checkIdExist(lw, id)) {
            System.err.println("Id already exists. Please enter a different id.");
            System.err.print("Enter again:");
        } else {
            break;
        }
    }
    System.out.print("Enter name: ");
    String name = validate.checkInputString();
    System.out.print("Enter age: ");
    int age = validate.checkInputIntLimit(18, 50);
    System.out.print("Enter salary: ");
    int salary = validate.checkInputSalary();
    System.out.print("Enter work location: ");
    String workLocation = validate.checkInputString();
    if (!validate.checkWorkerExist(lw, id, name, age, salary, workLocation)) {
        System.err.println("Duplicate worker entry.");
    } else {
        lw.add(new Worker(id, name, age, salary, workLocation));
        System.err.println("Add success.");
    }
}

// Allow user to increase or decrease the salary of a worker
public void changeSalary(ArrayList<Worker> lw, ArrayList<History> lh, int status) {
    if (lw.isEmpty()) {
        System.err.println("List is empty.");
        return;
    }
    System.out.print("Enter code: ");
    String id = validate.checkInputString();
    Worker worker = getWorkerByCode(lw, id);
    if (worker == null) {
        System.err.println("Worker does not exist.");
    } else {
        int salaryCurrent = worker.getSalary();
        int salaryUpdate;
        // Check if the user wants to update the salary
        if (status == 1) {
            System.out.print("Enter salary: ");
            // Loop until the user inputs a salary update greater than the current salary
            while (true) {
                salaryUpdate = validate.checkInputSalary();
                // Check if the user inputs a salary update greater than the current salary
                if (salaryUpdate <= salaryCurrent) {
                    System.err.println("Must be greater than the current salary.");
                    System.out.print("Enter again: ");
                } else {
                    break;
                }
            }
            lh.add(new History("UP", getCurrentDate(), worker.getId(),
                    worker.getName(), worker.getAge(), salaryUpdate,
                    worker.getWorkLocation()));
        } else {
            System.out.print("Enter salary: ");
            // Loop until the user inputs a salary update smaller than the current salary
            while (true) {
                salaryUpdate = validate.checkInputSalary();
                // Check if the user inputs a salary update smaller than the current salary
                if (salaryUpdate >= salaryCurrent) {
                    System.err.println("Must be smaller than the current salary.");
                    System.out.print("Enter again: ");
                } else {
                    break;
                }
            }
            lh.add(new History("DOWN", getCurrentDate(), worker.getId(),
                    worker.getName(), worker.getAge(), salaryUpdate,
                    worker.getWorkLocation()));
        }
        worker.setSalary(salaryUpdate);
        System.err.println("Update success.");
    }
}

// Allow user to print the history of salary adjustments
public void printListHistory(ArrayList<History> lh) {
    if (lh.isEmpty()) {
        System.err.println("List is empty.");
        return;
    }
    System.out.printf("%-5s%-15s%-5s%-10s%-10s%-20s\n", "Code", "Name", "Age",
            "Salary", "Status", "Date");
    Collections.sort(lh);
    // Print history from first to last in the list
    for (History history : lh) {
        printHistory(history);
    }
}

// Get worker by code
public Worker getWorkerByCode(ArrayList<Worker> lw, String id) {
    for (Worker worker : lw) {
        if (id.equalsIgnoreCase(worker.getId())) {
            return worker;
        }
    }
    return null;
}

// Get current date 
public static String getCurrentDate() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Calendar calendar = Calendar.getInstance();
    return dateFormat.format(calendar.getTime());
}

// Print history
public static void printHistory(History history) {
    System.out.printf("%-5s%-15s%-5d%-10d%-10s%-20s\n", history.getId(),
            history.getName(), history.getAge(), history.getSalary(),
            history.getStatus(), history.getDate());
}
}
