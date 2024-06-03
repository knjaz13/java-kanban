package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.history.HistoryManager;
import ru.practicum.task_tracker.history.InMemoryHistoryManager;

public  class Managers<T extends TaskManager> {

    public static TaskManager getDefault(){
return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory(){
        return new InMemoryHistoryManager();
    }

}
