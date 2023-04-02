package TaskManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TaskManagerApp {
    private final Scanner scr = new Scanner(System.in);
    private final ServiceClass serviceClass = new ServiceClass();

    public void run() {
        while (true) {
            System.out.println("Выберите действие");
            System.out.println("1. Добавить задачу");
            System.out.println("2. Редактировать задачу");
            System.out.println("3. Удалить задачу");
            System.out.println("4. Показать список задач");
            System.out.println("5. Показать список задач на завтра");
            System.out.println("6. Показать список задач на определённую дату");
            System.out.println("7. Показать список архивных задач");
            System.out.println("8. Показать задачи сгруппированные по дате");
            System.out.println("9. Выйти");

            int choice = scr.nextInt();
            scr.nextLine();
            switch (choice){
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    removeTask();
                    break;
                case 4:
                    showTasks();
                    break;
                case 5:
                    showTaskForTomorrow();
                    break;
                case 6:
                    showTaskForDate();
                    break;
                case 7:
                    showArchivedTask();
                    break;
                case 8:
                    showGroupTask();
                    break;
                case 9:
                    System.out.println("До свидания");
                    return;
                default:
                    System.out.println("Вы ввели неправильное число");
                    break;
            }
        }
    }

    private void addTask() {
        System.out.println("Введите заголовок задачи");
        String title = scr.nextLine();
        System.out.println("Введите описание задачи");
        String description = scr.nextLine();
        System.out.println("Введите тип задачи");
        Type type = Type.valueOf(scr.nextLine().toUpperCase());
        System.out.println("Введите периодичность задачи");
        TaskRepeat taskRepeat = TaskRepeat.valueOf(scr.nextLine().toUpperCase());
        LocalDateTime dateTime = null;
        if(type == Type.WORK){
            System.out.println("Введите дату и время");
            dateTime = LocalDateTime.of(LocalDate.parse(scr.nextLine()), LocalTime.parse(scr.nextLine()));
        }
        Task task = new Task(title,description,type,taskRepeat,dateTime);
        serviceClass.addTask(task);
    }

    private void editTask() {
        System.out.println("Введите  id задачи для удаления");
        int id = scr.nextInt();
        scr.nextLine();
        Task task = serviceClass.taskFindById(id).orElseThrow(()-> new IllegalArgumentException("Задача не найдена"));
        System.out.println("Введите заголовок задачи");
        String title = scr.nextLine();
        task.setTitle(title);
        System.out.println("Введите описание задачи");
        String description = scr.nextLine();
        task.setDescription(description);
        serviceClass.editTask(id,task);
    }

    private void removeTask() {
        System.out.println("Введите id");
        int taskId = scr.nextInt();
        serviceClass.removeTaskById(taskId);
    }

    private void showTasks() {
        List<Task> taskList = serviceClass.getTasks();
        if (taskList.isEmpty()) {
            System.out.println("Нет задачи");
        } else {
            System.out.println("Список задач");
            for(Task task : taskList){
                System.out.println(task);
            }
        }
    }

    private void showTaskForTomorrow() {
        List<Task> taskListForTomorrow = serviceClass.getTasksForTomorrow();
        if (taskListForTomorrow.isEmpty()) {
            System.out.println("Нет задач на завтра");
        } else {
            System.out.println("Список задач на завтра");
            for(Task task : taskListForTomorrow){
                System.out.println(taskListForTomorrow);
            }
        }
    }

    private void showTaskForDate() {
        System.out.println("Введите дату в формате yyyy-mm-dd");
        String dateStr = scr.nextLine();
        try {
            LocalDate date = LocalDate.parse(dateStr);
            List<Task> taskListForDate = serviceClass.getTasksForDate(date);
            if (taskListForDate.isEmpty()) {
                System.out.println("Нет задач на выбранную дату");
            } else {
                System.out.println("Список задач на выбранную дату");
                for (Task task : taskListForDate) {
                    System.out.println(task);
                }
            }
        } catch (DateTimeParseException e){
            System.out.println("Неверный формат даты" + e);
        }
    }

    private void showArchivedTask() {
        List<Task> taskListArchived = serviceClass.getArchivedTasks();
        if (taskListArchived.isEmpty()) {
            System.out.println("Нет архивных задач ");
        } else {
            System.out.println("Список архивных задач ");
            for (Task task : taskListArchived) {
                System.out.println(taskListArchived);
            }
        }
    }

    private void showGroupTask() {
        Map<LocalDate,List<Task>> listMap = serviceClass.getGroupByDate();
        for (Map.Entry<LocalDate, List<Task>> listEntry : listMap.entrySet()) {
            System.out.println("Дата " + listEntry.getKey());
            for(Task task : listEntry.getValue()){
                System.out.println(task);
            }
        }
    }

}
