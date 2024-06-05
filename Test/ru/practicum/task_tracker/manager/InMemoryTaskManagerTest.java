package ru.practicum.task_tracker.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.task_tracker.task.State.*;

class TaskTest {
    TaskManager manager;

    @BeforeEach
    public void createManager() {
        manager = Managers.getDefault();
    }

    /* Проверим, что экземпляр manager не null */
    @Test
    void managerShouldNotBeNull() {
        assertNotNull(manager, "Объект менеджера не создан");
    }

    /*Проверка функционала обычных тасок */
    @Test
    public void addNewTask() {

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

    /*Проверка эпиков */
    @Test
    public void addNewEpic() {
        Epic testEpic = new Epic("Первый", "Первый эпик");
        manager.addEpic(testEpic);
        final int epicId = testEpic.getId();

        final Epic savedEpic = manager.getSingleEpic(epicId);
        testEpic = new Epic(epicId, "Обновленный эпик", "Его тело", null);

        assertNotNull(savedEpic, "Эпик не найден");
        assertEquals(savedEpic, testEpic, "Задачи не совпадают");
    }

    @Test
    void epicShouldNotBeAddedAsASubtask() {
        Epic testEpic = new Epic("Test epic", "Test epic body");
        manager.addEpic(testEpic);
        List<Integer> testList = new ArrayList<>();
        testList.add(testEpic.getId());
        testEpic = new Epic(testEpic.getId(), "Test epic", "Test epic body", testList);
        manager.updateEpic(testEpic);
        assertEquals(0, manager.getSingleEpic(testEpic.getId()).getSubTasksInEpic().size());
    }

    /*Подзадачи*/
    @Test
    void subTasksShouldBeEqual() {
        Epic testEpic = new Epic("Первый", "Первый эпик");
        manager.addEpic(testEpic);

        SubTask subTask = new SubTask("Тест субтаск", "Тест тело субтаски", NEW, 1);
        manager.addSubTask(subTask);
        final SubTask savedSubTask = manager.getSingleSubtask(subTask.getId());
        subTask = new SubTask(2, "Тест субтаск 2", "Тест тело субтаски 2", NEW, 1);
        assertEquals(subTask, savedSubTask, "Подзадачи не совпадают");

    }

    @Test
    void subTaskShouldNotBeAddedInItself() {
        SubTask subTask = new SubTask("Тест субтаск", "Тест тело субтаски", NEW, 1);
        manager.addSubTask(subTask);
        assertNull(manager.getSingleSubtask(1), "Подзадача добавилась в эпик с этим же айди");
    }

    @Test
    void inMemoryTaskManagerShouldAddObjectsAndFindThemById() {
        Task testTask1 = new Task("Первая", "Это первая задача", State.NEW);
        Task testTask2 = new Task("Вторая", "А это вторая задача", State.IN_PROGRESS);
        Epic testEpic1 = new Epic("Первый", "Первый эпик с двумя");
        SubTask testSub1 = new SubTask("Первая подзадача", "Тело первой подзадачи", State.NEW, 3);
        SubTask testSub2 = new SubTask("Вторая подзадача", "Это уже тело второй подзадачи", State.NEW, 3);
        Epic testEpic2 = new Epic("Второй", "Первый-первый, я второй");
        SubTask testSub3 = new SubTask("Подзадача второго эпика", "Тело подзадачи второго эпика", State.NEW, 6);

        manager.addTask(testTask1);
        manager.addTask(testTask2);
        manager.addEpic(testEpic1);
        manager.addSubTask(testSub1);
        manager.addSubTask(testSub2);
        manager.addEpic(testEpic2);
        manager.addSubTask(testSub3);

        assertEquals(2, manager.getTasks().size(), "Не все задачи добавились");
        assertEquals(2, manager.getEpics().size(), "Не все эпики добавились");
        assertEquals(3, manager.getSubTasks().size(), "Не все подзадачи добавились");

        assertNotNull(manager.getSingleTask(1), "Задача не найдена по id");
        assertNotNull(manager.getSingleEpic(3), "Эпик не найден по id");
        assertNotNull(manager.getSingleSubtask(4), "Подзадача не найдена по id");
    }

    @Test
    void taskWithIdShouldNotConflictWithCurrentTasks() {
        Task testTask1 = new Task("Первая", "Это первая задача", State.NEW);
        manager.addTask(testTask1);
        Task testTask2 = new Task(1, "Вторая", "Вторая тело", NEW);
        manager.addTask(testTask2);
        assertNotEquals(manager.getSingleTask(1), manager.getSingleTask(2), "Конфликтуют id при добавлении");
    }

    @Test
    void fieldsShouldNotBeChangedAfterAdding() {
        Task testTask = new Task("Первая", "Это первая задача", State.NEW);
        manager.addTask(testTask);

        assertEquals("Первая", testTask.getName(), "Название не совпадает");
        assertEquals("Это первая задача", testTask.getBody(), "Описание не совпадает");
        assertEquals(NEW, testTask.getState(), "Статус не совпадает");
    }


}