package ru.practicum.task_tracker.task;

import java.util.*;

public class Epic extends Task {

    private final List<Integer> subTasksInEpic;

    public Epic(String name, String body) {
        super(name, body);
        this.subTasksInEpic = new ArrayList<>();
    }

    public Epic(Integer id, String name, String body, List<Integer> tasksInEpic) {
        super(id, name, body);
        this.subTasksInEpic = tasksInEpic;
    }

    public List<Integer> getSubTasksInEpic() {
        return subTasksInEpic;
    }

    public void setSubTasksInEpic(Integer subTaskId) {
        subTasksInEpic.add(subTaskId);
    }

    public void clearSubtasks() {
        subTasksInEpic.clear();
    }

    public void deleteSingleSubtask(Integer id) {
        for (int i = 0; i < subTasksInEpic.size(); i++) {
            if (subTasksInEpic.get(i).equals(id)) {
                subTasksInEpic.remove(id);
            }
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", body='" + super.getBody() + '\'' +
                ", state=" + super.getState() + ", subTasksInEpic=" + subTasksInEpic +
                '}';
    }
}