package view;

import java.util.ArrayList;
import manager.Manager;
import manager.Validate;
import model.History;
import model.Worker;

public class Menu {
        public void Menu() {
            Manager manager=new Manager();
        ArrayList<Worker> lw = new ArrayList<>();
        ArrayList<History> lh = new ArrayList<>();
        //loop until user want to exit
        while (true) {
            int choice = menu();
            switch (choice) {
                case 1:
                    manager.addWorker(lw);
                    break;
                case 2:
                    manager.changeSalary(lw, lh, 1);
                    break;
                case 3:
                    manager.changeSalary(lw, lh, 2);
                    break;
                case 4:
                    manager.printListHistory(lh);
                    break;
                case 5:
                    return;
            }
        }
    }
// Show menu
public int menu() {
    Validate validate = new Validate();
    System.out.println("1. Add worker.");
    System.out.println("2. Increase salary for worker.");
    System.out.println("3. Decrease salary for worker");
    System.out.println("4. Show adjusted salary worker information");
    System.out.println("5. Exit");
    System.out.print("Enter your choice: ");
    int choice = validate.checkInputIntLimit(1, 5);
    return choice;
}

}
