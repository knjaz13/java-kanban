package ru.practicum.task_tracker.history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.task_tracker.manager.Managers;
import ru.practicum.task_tracker.manager.TaskManager;
import ru.practicum.task_tracker.task.State;
import ru.practicum.task_tracker.task.Task;

import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.task_tracker.task.State.NEW;

class InMemoryHistoryManagerTest {

    TaskManager manager;

    @BeforeEach
    public void createManager() {
        manager = Managers.getDefault();
    }

    @Test
    void historyShouldNotChangeTaskObjectFields() {
        Task testTask = new Task("Первая", "Это первая задача", State.NEW);
        manager.addTask(testTask);
        manager.getSingleTask(1);
        Task historyTask = manager.getHistory().getFirst();
        assertEquals("Первая", historyTask.getName(), "Название не совпадает");
        assertEquals("Это первая задача", historyTask.getBody(), "Описание не совпадает");
        assertEquals(NEW, historyTask.getState(), "Статус не совпадает");
    }

    @Test
    void historyShouldBeNotNull() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW);
        manager.addTask(task);
        assertNotNull(manager.getHistory(), "Объект истории не создан");
        manager.getSingleTask(task.getId());
        assertEquals(1, manager.getHistory().size(), "Просмотр не увеличивает историю");
        assertEquals(manager.getHistory().getFirst(), task, "Вызванная задача и задача в истории не совпадают");
    }
}