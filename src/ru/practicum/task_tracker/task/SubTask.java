package ru.practicum.task_tracker.task;

public class SubTask extends Task {
    private Integer epicId;

    public SubTask(String name, String body, State state, Integer epicId) {
        super(name, body, state);
        this.epicId = epicId;
    }

    public SubTask(Integer id, String name, String body, State state, Integer epicId) {
        super(id, name, body, state);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public void setEpicId(Integer epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + super.getId() +
                ", name='" + super.getName() + '\'' +
                ", body='" + super.getBody() + '\'' +
                ", state=" + super.getState() + ", epicId=" + epicId +
                '}';
    }
}