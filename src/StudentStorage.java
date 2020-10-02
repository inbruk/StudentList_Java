import java.util.HashMap;

public class StudentStorage {
    private HashMap<String, Student> storage;

    public StudentStorage() {
        storage = new HashMap<>();
    }

    public void addStudent(String data) {
        try
        {
            String[] components = data.split("\\s+");
            String name = components[0] + " " + components[1];
            storage.put(name, new Student(name, components[3], components[2]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("In addStudent() array components[] is out of bound: " + e);
            System.out.println("addStudent() declined because of exception !");
        }
    }

    public void listStudent() {
        storage.values().forEach(System.out::println);
    }

    public void removeStudent(String name) {
        try
        {
            storage.remove(name);
        } catch (NullPointerException  e) {
            System.err.println("In removeStudent() parameter name is null: " + e);
            System.out.println("removeStudent() declined because of exception !");
        }
    }

    public Student getStudentByName(String name) {
        try
        {
            return storage.get(name);
        } catch (NullPointerException  e) {
            System.err.println("In getStudentByName() parameter name is null: " + e);
            System.out.println("getStudentByName() declined because of exception !");
            return null;
        }
    }

    public int getCount() {
        return storage.size();
    }
}