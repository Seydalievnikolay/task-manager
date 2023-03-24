import java.time.LocalDateTime;
import java.util.Scanner;

public class TaskManagerApp {
    private ServiceClass serviceClass;
    public TaskManagerApp (ServiceClass serviceClass){
        this.serviceClass = serviceClass;
    }

    public void run () {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (choice != 4) {
            System.out.println("1. Добавить задачу");
            System.out.println("2. Удалить задачу");
            System.out.println("3. Получить список задач на завтра");
            System.out.println("4. Выйти из программы");
            System.out.print("Введите номер действия: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Введите название задачи: ");
                    String title = scanner.nextLine();
                    System.out.print("Введите описание задачи: ");
                    String description = scanner.nextLine();
                    System.out.print("Введите дату и время начала выполнения задачи (yyyy-MM-dd HH:mm): ");
                    LocalDateTime startTime = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("Введите дату и время окончания выполнения задачи (yyyy-MM-dd HH:mm): ");
                    LocalDateTime endTime = LocalDateTime.parse(scanner.nextLine());
                    Task task = new Task(title, description, startTime, endTime);
                    ServiceClass.addTask(task);
                    System.out.println("Задача успешно добавлена");
                    break;
                case 2:
                    System.out.print("Введите название задачи для удаления: ");
                    String titleToRemove = scanner.nextLine();
                    for (Task t : serviceClass.getTasks()) {
                        if (t.getTitle().equals(titleToRemove)) {
                            serviceClass.removeTask(t);
                            System.out.println("Задача успешно удалена");
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Список задач на завтра:");
                    for (Task t : serviceClass.getTasksForTomorrow()) {
                        System.out.println(t.getTitle());
                    }
                    break;
                case 4:
                    System.out.println("До свидания!");
                    break;
                default:
                    System.out.println("Неверный номер действия, попробуйте еще раз");
            }
        }
    }
}
    }
    private static void printMenu() {
        System.out.println("""
                1. Добавить задачу
                2. Редактировать задачу
                3. Удалить задачу
                4. Получить задачи на указанный день
                5. Получить архивные задачи
                6. Получить сгруппированные по датам задачи
                0. Выход"""
            );
        }

}
