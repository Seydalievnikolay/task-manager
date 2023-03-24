import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceClass {
    private static List<Task> tasks;

    public ServiceClass() {
        tasks = new ArrayList<>();
    }

    public static void addTask(String title, String description, Type type, TaskRepeat taskRepeat, LocalDateTime dateTime) {
        tasks.add(new Task(title, description, type, taskRepeat ,dateTime));
        System.out.println("Задача добавлена");
    }
    public static void addTask(Task task ) {
        tasks.add(task);
        System.out.println("Задача добавлена");
    }


    public static void removeTaskById(String id) {
        tasks.removeIf(task -> Objects.equals(task.getId(), id));
        System.out.println("Задача удалена");
    }

    public static List<Task> getTasksForTomorrow() {
        //Date tomorrowDate = new Date(new Date().getTime()+86400000);
        LocalDateTime currentDT = LocalDateTime.now().plusDays(1);

        return tasks.stream().filter(item -> item.dateTime.compareTo(currentDT)==0).toList();
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static void removeTask(Task t ) {
        tasks.removeIf(task -> task ==t);
    }



    public static List<Task> getTasksForDate(LocalDate date) {
        List<Task> dueTasks = new ArrayList<>();
        for (Task task : tasks) {
            LocalDateTime nextDate = task.getNextDate();
            if (nextDate != null && nextDate.toLocalDate().equals(date)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }

}
