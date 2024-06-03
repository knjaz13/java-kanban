package ru.practicum.task_tracker;

import ru.practicum.task_tracker.manager.InMemoryTaskManager;
import ru.practicum.task_tracker.manager.Managers;
import ru.practicum.task_tracker.manager.TaskManager;
import ru.practicum.task_tracker.task.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        System.out.println();

        TaskManager manager = Managers.getDefault();



        /*Создайте две задачи, а также эпик с двумя подзадачами и эпик с одной подзадачей.*/
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

        printAllTasks(manager);

        System.out.println("Проверяю историю");
        manager.getSingleSubtask(7);
        System.out.println(manager.getHistory());
        manager.getSingleEpic(6);
        System.out.println(manager.getHistory());
        manager.getSingleTask(1);
        System.out.println(manager.getHistory());


    }
    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getEpics()) {
            System.out.println(epic);

            for (Task task : manager.getEpicSubtasks(epic.getId())) {
                System.out.println("--> " + task);
            }
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getSubTasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}