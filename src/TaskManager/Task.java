package TaskManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private int id;
    private String title;
    private  String description;
    private Type type;

    private TaskRepeat taskRepeat;
    public LocalDateTime dateTime;

    private LocalDateTime endDate;


    public Task(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.dateTime = startTime;
        this.dateTime = endTime;
    }

    public Task(int id,String title, String description, Type type, TaskRepeat taskRepeat, LocalDateTime dateTime) {
        this.id = Integer.parseInt(UUID.randomUUID().toString());
        this.title = title;
        this.description = description;
        this.type = type;
        this.taskRepeat = taskRepeat;
        this.dateTime = LocalDateTime.now();
    }

    public Task(String title, String description, Type type, TaskRepeat taskRepeat, LocalDateTime dateTime) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.taskRepeat = taskRepeat;
        this.dateTime = dateTime;
    }

    public Task(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean isArchived() {
        return LocalDateTime.now().isAfter(endDate);
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }



    public int getId() {
        return id;
    }


    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public TaskRepeat getTaskRepeat() {
        return taskRepeat;
    }

    public void setTaskRepeat(TaskRepeat taskRepeat) {
        this.taskRepeat = taskRepeat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getNextDate() {
        LocalDateTime now = LocalDateTime.now();
        switch (taskRepeat) {
            case DAILY:
                return now.plusDays(1).truncatedTo(ChronoUnit.MINUTES);
            case WEEKLY:
                return now.plusWeeks(1).truncatedTo(ChronoUnit.MINUTES);
            case MONTHLY:
                return now.plusMonths(1).truncatedTo(ChronoUnit.MINUTES);
            case YEARLY:
                return now.plusYears(1).truncatedTo(ChronoUnit.MINUTES);
            case ONE_TIME:
            default:
                return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(title, task.title) && Objects.equals(description, task.description) && type == task.type && taskRepeat == task.taskRepeat && Objects.equals(dateTime, task.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, type, taskRepeat, dateTime);
    }

    @Override
    public String toString() {
        return "TaskManager.Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", taskRepeat=" + taskRepeat +
                ", dateTime=" + dateTime +
                '}';
    }
}
