package TaskManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ServiceClass {
    private final List<Task> tasks;

    public ServiceClass() {
        tasks = new ArrayList<>();
    }

    /*public  void addTask(String title, String description, Type type, TaskRepeat taskRepeat, LocalDateTime dateTime) {
        tasks.add(new Task(int id,title, description, type, taskRepeat ,dateTime));
        System.out.println("Задача добавлена");
    }*/

    public void  addTask(Task task1) {
        tasks.add(task1);
        System.out.println("Задача добавлена");

    }




    public  void removeTaskById(int id) {
        tasks.removeIf(task -> Objects.equals(task.getId(), id));
        System.out.println("Задача удалена");
    }

    public  List<Task> getTasksForTomorrow() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Task> tomorrowTask = new ArrayList<>();
        for (Task task2 : tasks) {
            LocalDateTime nextDate = task2.getNextDate();
            if (nextDate != null && nextDate.toLocalDate().isEqual(tomorrow)) {
                tomorrowTask.add(task2);
            }
        }
        return tomorrowTask;
    }

    public  List<Task> getTasks() {
        return tasks;
    }

    public void  editTask(int id,Task task) {
        Task findTask = taskFindById(id).orElseThrow(()->new IllegalArgumentException("Задача не найдена"));
        findTask.setTitle(task.getTitle());
        findTask.setDescription(task.getDescription());
        addTask(findTask);
    }

    public Optional<Task> taskFindById(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }

    public  void removeTask(Task t ) {
        tasks.removeIf(task -> task ==t);
    }

    public  List<Task> getArchivedTasks () {
        List<Task> archivedTask = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isArchived()) {
                archivedTask.add(task);
            }
        }
        return archivedTask;
    }




    public  List<Task> getTasksForDate(LocalDate date) {
        List<Task> dueTasks = new ArrayList<>();
        for (Task task : tasks) {
            LocalDateTime nextDate = task.getNextDate();
            if (nextDate != null && nextDate.toLocalDate().equals(date)) {
                dueTasks.add(task);
            }
        }
        return dueTasks;
    }

    public  Map<LocalDate,List<Task>> getGroupByDate() {
        Map<LocalDate,List<Task>> taskByGroup = new TreeMap<>();
        for (Task task : tasks) {
            LocalDateTime nextDate = task.getNextDate();
            if (nextDate != null) {
                LocalDate date = nextDate.toLocalDate();
                List<Task> tusksOnDate = taskByGroup.getOrDefault(date,new ArrayList<>());
                tusksOnDate.add(task);
                taskByGroup.put(date,tusksOnDate);
            }
        }
        return taskByGroup;
    }


}
