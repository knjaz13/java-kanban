package ru.practicum.task_tracker;

import ru.practicum.task_tracker.manager.TaskManager;
import ru.practicum.task_tracker.task.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        System.out.println();

        TaskManager manager = new TaskManager();

        /*Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.*/
        Task testTask1 = new Task("Первая", "Это первая задача", State.NEW);
        Task testTask2 = new Task("Вторая", "А это вторая задача", State.IN_PROGRESS);
        Epic testEpic1 = new Epic("Первый", "Первый эпик с двумя", new ArrayList<>());
        SubTask testSub1 = new SubTask("Первая подзадача", "Тело первой подзадачи", State.NEW, 3);
        SubTask testSub2 = new SubTask("Вторая подзадача", "Это уже тело второй подзадачи", State.NEW, 3);
        Epic testEpic2 = new Epic("Второй", "Первый-первый, я второй", new ArrayList<>());
        SubTask testSub3 = new SubTask("Подзадача второго эпика", "Тело подзадачи второго эпика", State.NEW, 6);

        manager.addTask(testTask1);
        manager.addTask(testTask2);
        manager.addEpic(testEpic1);
        manager.addSubTask(testSub1);
        manager.addSubTask(testSub2);
        manager.addEpic(testEpic2);
        manager.addSubTask(testSub3);


        /*Распечатайте списки эпиков, задач и подзадач*/
        System.out.println("Список эпиков");
        System.out.println(manager.getEpics());
        System.out.println();

        System.out.println("Список задач");
        System.out.println(manager.getTasks());
        System.out.println();

        System.out.println("Список подзадач");
        System.out.println(manager.getSubTasks());
        System.out.println();

        /*Измените статусы созданных объектов, распечатайте их. Проверьте, что статус задачи и подзадачи сохранился, а
        статус эпика рассчитался по статусам подзадач.*/
        testTask1 = new Task(1, "Первая", "Это первая задача", State.NEW);
        testTask2 = new Task(2, "Вторая", "А это вторая задача", State.DONE);
        testSub1 = new SubTask(4,"Первая подзадача", "Тело первой подзадачи", State.DONE, 3);
        testSub2 = new SubTask(5,"Вторая подзадача", "Это уже тело второй подзадачи", State.NEW, 3);
        testSub3 = new SubTask(7,"Подзадача второго эпика", "Тело подзадачи второго эпика", State.DONE, 6);


        manager.updateTask(testTask1);
        manager.updateTask(testTask2);
        manager.updateSubtask(testSub1);
        manager.updateSubtask(testSub2);
        manager.updateSubtask(testSub3);


        System.out.println("Изменены статусы:");
        System.out.println("Список эпиков");
        System.out.println(manager.getEpics());
        System.out.println();

        System.out.println("Список задач");
        System.out.println(manager.getTasks());
        System.out.println();

        System.out.println("Список подзадач");
        System.out.println(manager.getSubTasks());
        System.out.println();

        /*И, наконец, попробуйте удалить одну из задач и один из эпиков.*/
        System.out.println("Удаляю эпик");
        manager.deleteSingleEpic(3);
        System.out.println("Удаляю задачу");
        manager.deleteTask(2);

        System.out.println("Список эпиков");
        System.out.println(manager.getEpics());
        System.out.println();

        System.out.println("Список задач");
        System.out.println(manager.getTasks());
        System.out.println();

        System.out.println("Список подзадач");
        System.out.println(manager.getSubTasks());
        System.out.println();
    }
}