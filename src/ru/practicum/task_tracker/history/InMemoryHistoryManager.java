package ru.practicum.task_tracker.history;

import ru.practicum.task_tracker.task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int MAX_TASKS = 10;
    private final List<Task> lastTasks = new ArrayList<>(MAX_TASKS);

    @Override
    public void add(Task task) {
        if (lastTasks.size() >= MAX_TASKS) {
            lastTasks.removeFirst();
        }
        lastTasks.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return List.copyOf(lastTasks);
    }
}
