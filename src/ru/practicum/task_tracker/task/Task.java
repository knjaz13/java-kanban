package ru.practicum.task_tracker.task;

import java.util.Objects;

/*Обычные таски*/
public class Task {

    private Integer id; //
    private String name;
    private String body;
    private State state;

    /*конструкторы*/
    public Task(String name, String body, State state) {
        this.state = state;
        this.name = name;
        this.body = body;
    }

    public Task(Integer id, String name, String body, State state) {
        this.id = id;
        this.name = name;
        this.body = body;
        this.state = state;
    }

    public Task(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public Task(Integer id, String name, String body) {
        this.id = id;
        this.name = name;
        this.body = body;
    }

    /*геттеры-сеттеры*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /*переопределяем toString и т.д. */
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", body='" + body + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}