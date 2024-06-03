package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.Epic;
import ru.practicum.task_tracker.task.SubTask;
import ru.practicum.task_tracker.task.Task;

import java.util.ArrayList;
import java.util.List;

public interface TaskManager {
    /*Методы для обычных тасок*/
    Task addTask(Task task);

    ArrayList<Task> getTasks();

    Task getSingleTask(Integer id);

    Task updateTask(Task task);

    boolean deleteTask(Integer id);

    boolean deleteAllTasks();

    /*методы для эпиков*/
    Epic addEpic(Epic epic);

    ArrayList<Epic> getEpics();

    Epic getSingleEpic(Integer id);

    boolean deleteEpics();

    boolean deleteSingleEpic(Integer id);

    ArrayList<SubTask> getEpicSubtasks(Integer id);

    Epic updateEpic(Epic epic);

    /*Методы для подзадач*/
    SubTask addSubTask(SubTask subTask);

    SubTask updateSubtask(SubTask subTask);

    ArrayList<SubTask> getSubTasks();

    boolean deleteAllSubtasks();

    SubTask getSingleSubtask(Integer id);

    boolean deleteSingleSubtask(Integer id);

    /*Получение списка последних просмотренных задач */
    List<Task> getHistory();
}
