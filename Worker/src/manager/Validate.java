package manager;

import model.Worker;

import java.util.ArrayList;
import java.util.Scanner;

public class Validate {

    Scanner in = new Scanner(System.in);

    // Check user input number limit
    public int checkInputIntLimit(int min, int max) {
        // Loop until user inputs correctly
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a number in the range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    // Check user input string
    public String checkInputString() {
        // Loop until user inputs correctly
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Cannot be empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    // Check if the id already exists in the database
    public boolean checkIdExist(ArrayList<Worker> lw, String id) {
        // Check from first to last list if the worker's id already exists
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return true;
            }
        }
        return false;
    }

    // Check salary (must be greater than 0)
    public int checkInputSalary() {
        // Loop until user inputs correctly
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < 0) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Salary must be greater than 0");
                System.out.print("Enter again: ");
            }
        }
    }

    // Check if the worker already exists (based on id, name, age, salary, and work location)
    public boolean checkWorkerExist(ArrayList<Worker> lw, String id, String name, int age, int salary, String workLocation) {
        // Check from first to last list if the worker already exists
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId()) && name.equalsIgnoreCase(worker.getName())
                    && age == worker.getAge() && salary == worker.getSalary()
                    && workLocation.equalsIgnoreCase(worker.getWorkLocation())) {
                return false;
            }
        }
        return true;
    }
}
