package TaskManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ServiceClass {
    private static List<Task> tasks;

    public ServiceClass() {
        tasks = new ArrayList<>();
    }

    public  void addTask(String title, String description, Type type, TaskRepeat taskRepeat, LocalDateTime dateTime) {
        tasks.add(new Task(title, description, type, taskRepeat ,dateTime));
        System.out.println("Задача добавлена");
    }

    public void addTask(Task task1) {
        System.out.println("Задача добавлена");
    }




    public static void removeTaskById(String id) {
        tasks.removeIf(task -> Objects.equals(task.getId(), id));
        System.out.println("Задача удалена");
    }

    public static List<Task> getTasksForTomorrow() {
        LocalDateTime.parse(tasks);
        return (List<Task>) ;
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static List<Task> setTasks() {
        return tasks;
    }

    public static void removeTask(Task t ) {
        tasks.removeIf(task -> task ==t);
    }

    public static List<Task> getArchivedTasks () {
        for (Task task : ServiceClass.getTasks()) {
            System.out.println(task.getTitle());
        }
        return tasks;
    }


    public static List<Task> getTasksForDate() {
        List<Task> dueTasks = new ArrayList<>();
        for (Task task : tasks) {
            LocalDateTime nextDate = task.getNextDate();
            if (nextDate != null && nextDate.toLocalDate().equals(nextDate)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }


}
