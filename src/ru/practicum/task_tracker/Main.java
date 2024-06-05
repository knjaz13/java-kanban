package ru.practicum.task_tracker;

import ru.practicum.task_tracker.manager.Managers;
import ru.practicum.task_tracker.manager.TaskManager;
import ru.practicum.task_tracker.task.*;

import java.util.ArrayList;
import java.util.List;

import static ru.practicum.task_tracker.task.State.NEW;

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

        System.out.println("Проверю, что в эпик нельзя добавить сам эпик");
        List<Integer> testList = new ArrayList<>();
        testList.add(1);
        testList.add(2);
        testList.add(testEpic2.getId());
        testEpic2 = new Epic(testEpic2.getId(), "Второй", "Первый-первый, я второй", testList);
        manager.updateEpic(testEpic2);
        System.out.println(manager.getSingleEpic(testEpic2.getId()));
        System.out.println();

        System.out.println("Проверю, что что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера");
        System.out.println(manager.getSingleTask(1));
        Task genTask = new Task(1, "Test body", "Test description", State.NEW);
        manager.addTask(genTask);
        System.out.println(manager.getTasks());
        System.out.println();

        SubTask subTask = new SubTask("Тест субтаск", "Тест тело субтаски", NEW, 11);
        manager.addSubTask(subTask);
        manager.getSingleSubtask(1);

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