package TaskManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TaskManagerApp {
    private static ServiceClass serviceClass = new ServiceClass();
    public TaskManagerApp (ServiceClass serviceClass){
        this.serviceClass = serviceClass;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 4) {
            printMenu();
            System.out.print("Введите номер действия: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Введите название задачи: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите описание задачи: ");
                    String description = scanner.nextLine();
                    LocalDateTime startTime = null;
                    LocalDateTime endTime = null;
                    boolean validInput = false;
                    while (!validInput) {
                        try {
                            System.out.print("Введите дату и время начала выполнения задачи (yyyy-MM-dd HH:mm): ");
                            startTime = LocalDateTime.parse(scanner.nextLine());
                            System.out.print("Введите дату и время окончания выполнения задачи (yyyy-MM-dd HH:mm): ");
                            endTime = LocalDateTime.parse(scanner.nextLine());
                            validInput = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Ошибка ввода даты и времени, попробуйте еще раз");
                        }
                    }
                    Task task1 = new Task(title, description, startTime, endTime);
                    serviceClass.addTask(task1);
                    System.out.println("Задача успешно добавлена");
                    break;
                case 2:
                    System.out.print("Введите название задачи для удаления: ");
                    String titleToRemove = scanner.nextLine();
                    boolean taskRemoved = false;
                    for (Task id : serviceClass.getTasks()) {
                        if (id.getTitle().equals(titleToRemove)) {
                            serviceClass.removeTaskById(String.valueOf(id));
                            System.out.println("Задача успешно удалена");
                            taskRemoved = true;
                            break;
                        }
                    }
                    if (!taskRemoved) {
                        System.out.println("Задача с таким названием не найдена");
                    }
                    break;
                case 3:
                    System.out.println("Список задач на завтра:");
                    for (Task t : serviceClass.getTasksForTomorrow()) {
                        System.out.println(t.getTitle());
                    }
                    break;
                case 4:
                    System.out.println("Список архивных задач");
                    for (Task task :serviceClass.getArchivedTasks())
                        System.out.println(task.getTitle());
                case 5:
                    System.out.println("Список сгруппированный по датам");
                    serviceClass.getTasksForDate();
                case 6:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Неверный номер действия, попробуйте еще раз");
            }
        }
    }
    private static void printMenu() {
        System.out.println("""
                1. Добавить задачу \n
                2. Редактировать задачу \n
                3. Удалить задачу \n
                4. Получить задачи на указанный день \n
                5. Получить архивные задачи \n
                6. Получить сгруппированные по датам задачи \n
                0. Выход"""
        );
    }
}
