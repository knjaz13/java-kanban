package ru.practicum.task_tracker.task;

import java.util.*;

public class Epic extends Task {

    private final List<Integer> tasksInEpic;

    public Epic(String name, String body, List<Integer> tasksInEpic) {
        super(name, body);
        this.tasksInEpic = tasksInEpic;
    }

    public Epic(Integer id, String name, String body, List<Integer> tasksInEpic) {
        super(id, name, body);
        this.tasksInEpic = tasksInEpic;
    }

    public List<Integer> getTasksInEpic() {
        return tasksInEpic;
    }

    public void setTasksInEpic(Integer subTaskId) {
        tasksInEpic.add(subTaskId);
    }

    public void clearSubtasks() {
        tasksInEpic.clear();
    }

    public void deleteSingleSubtask(Integer id) {
        for (int i = 0; i < tasksInEpic.size(); i++) {
            if (tasksInEpic.get(i).equals(id)) {
                tasksInEpic.remove(id);
            }
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", body='" + super.getBody() + '\'' +
                ", state=" + super.getState() + ", tasksInEpic=" + tasksInEpic +
                '}';
    }
}