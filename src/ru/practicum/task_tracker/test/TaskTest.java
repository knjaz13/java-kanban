package ru.practicum.task_tracker.test;

import org.junit.jupiter.api.Test;
import ru.practicum.task_tracker.manager.Managers;
import ru.practicum.task_tracker.manager.TaskManager;
import ru.practicum.task_tracker.task.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.task_tracker.task.State.DONE;
import static ru.practicum.task_tracker.task.State.NEW;

class TaskTest {

    @Test
    public void addNewTask() {
        TaskManager manager = Managers.getDefault();

        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        manager.addTask(task);
        final int taskId = task.getId();

        final Task savedTask = manager.getSingleTask(taskId);
        task = new Task(savedTask.getId(), "Test updateNewTask", "Test updateNewTask description", DONE);
        manager.updateTask(task);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = manager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");

    }

}