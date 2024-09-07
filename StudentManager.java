import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentManager {
    private Map<String, Integer> students = new HashMap<>();
    private String filename = "students.txt";

    public void addStudent(String name, int marks) {
        students.put(name, marks);
        System.out.println("Student added.");
    }

    public void editStudent(String name, int marks) {
        if (students.containsKey(name)) {
            students.put(name, marks);
            System.out.println("Marks updated.");
        } else {
            System.out.println("Student not found.");
        }
    }

    public void saveStudents() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> entry : students.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
            System.out.println("Students saved.");
        } catch (IOException e) {
            System.out.println("Error saving students.");
        }
    }

    public void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                students.put(parts[0], Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.out.println("No existing data found.");
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        loadStudents();

        while (true) {
            System.out.println("\nOptions: add, edit, save, quit");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("add")) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter marks: ");
                int marks = Integer.parseInt(scanner.nextLine().trim());
                addStudent(name, marks);
            } else if (choice.equals("edit")) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter new marks: ");
                int marks = Integer.parseInt(scanner.nextLine().trim());
                editStudent(name, marks);
            } else if (choice.equals("save")) {
                saveStudents();
            } else if (choice.equals("quit")) {
                saveStudents();
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new StudentManager().menu();
    }
}
