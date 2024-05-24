package ru.practicum.task_tracker.manager;

import ru.practicum.task_tracker.task.*;

import java.util.*;

public class TaskManager {

    private int idGenerator = 1;
    private final Map<Integer, Task> tasks = new HashMap<>();  //обычные таски
    private final Map<Integer, Epic> epics = new HashMap<>(); //эпики
    private final Map<Integer, SubTask> subTasks = new HashMap<>(); //подзадачи

    private int getNextId() { //сначала достаем, потом инкрементируем сквозной id
        return idGenerator++;
    }

    /*вычисляем статус эпика*/
    private State calcState(Epic epic) {
        List<Integer> subTasksIds = epic.getTasksInEpic();
        State state;
        List<State> listOfCurrentStates = new ArrayList<>();

        for (Integer subTasksId : subTasksIds) {
            SubTask subTask = subTasks.get(subTasksId);
            State currentState = subTask.getState();
            listOfCurrentStates.add(currentState);
        }
        if (subTasksIds.isEmpty()) {
            state = State.NEW;
        } else if (listOfCurrentStates.contains(State.NEW) && !listOfCurrentStates.contains(State.IN_PROGRESS) && !listOfCurrentStates.contains(State.DONE)) {
            state = State.NEW;
        } else if (listOfCurrentStates.contains(State.DONE) && !listOfCurrentStates.contains(State.NEW) && !listOfCurrentStates.contains(State.IN_PROGRESS)) {
            state = State.DONE;
        } else {
            state = State.IN_PROGRESS;
        }
        return state;
    }

    /*Методы для обычных тасок*/
    public Task addTask(Task task) {
        task.setId(getNextId());
        tasks.put(task.getId(), task);
        return task;
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public Task getSingleTask(Integer id) {
        if (!tasks.containsKey(id)) {
            return null;
        }
        return tasks.get(id);
    }

    public Task updateTask(Task task) {
        Integer taskId = task.getId();
        if (taskId == null || !tasks.containsKey(taskId)) {
            return null;
        }
        tasks.put(task.getId(), task);
        return task;
    }

    public boolean deleteTask(Integer id) {
        if (!tasks.containsKey(id)) {
            return false;
        }
        tasks.remove(id);
        return true;
    }

    public boolean deleteAllTasks() {
        tasks.clear();
        return true;
    }

    /*методы для эпиков*/
    public Epic addEpic(Epic epic) {
        epic.setId(getNextId());
        epic.setState(calcState(epic));
        epics.put(epic.getId(), epic);
        return epic;
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public Epic getSingleEpic(Integer id) {
        if (!epics.containsKey(id)) {
            return null;
        }
        return epics.get(id);
    }

    public boolean deleteEpics() {
        epics.clear();
        subTasks.clear();
        return true;
    }

    public boolean deleteSingleEpic(Integer id) {
        if (!epics.containsKey(id)) {
            return false;
        }
        Epic epic = epics.get(id);
        List<Integer> subTaskIds = epic.getTasksInEpic();
        for (Object subTaskId : subTaskIds) {
            subTasks.remove(subTaskId);
        }
        epics.remove(id);
        return true;
    }

    public ArrayList<SubTask> getEpicSubtasks(Integer id) {
        if (!epics.containsKey(id)) {
            return null;
        }
        ArrayList<SubTask> currentSubtasks = new ArrayList<>();
        Epic epic = epics.get(id);
        List<Integer> subTaskIds = epic.getTasksInEpic();
        for (Integer subTaskId : subTaskIds) {
            currentSubtasks.add(subTasks.get(subTaskId));
        }
        return currentSubtasks;
    }

    public Epic updateEpic(Epic epic) {
        if (epic.getId() == null || !epics.containsKey(epic.getId())) {
            return null;
        }
        epics.put(epic.getId(), epic);
        epic.setState(calcState(epic));
        return epic;
    }

    /*Методы для подзадач*/
    public SubTask addSubTask(SubTask subTask) {
        if (!epics.containsKey(subTask.getEpicId())) {
            return null;
        }
        subTask.setId(getNextId());
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        epic.setTasksInEpic(subTask.getId());
        epic.setState(calcState(epic));
        return subTask;
    }

    public SubTask updateSubtask(SubTask subTask) {
        if (subTask.getId() == null || !subTasks.containsKey(subTask.getId()) || !epics.containsKey(subTask.getEpicId())) {
            return null;
        }
        subTasks.put(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        epic.setState(calcState(epic));
        return subTask;
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public boolean deleteAllSubtasks() {
        subTasks.clear();
        epics.forEach((k, v) ->
                v.setState(State.NEW)
        );
        epics.forEach((k, v) ->
                v.clearSubtasks());
        return true;
    }

    public SubTask getSingleSubtask(Integer id) {
        if (!subTasks.containsKey(id)) {
            return null;
        }
        return subTasks.get(id);
    }

    public boolean deleteSingleSubtask(Integer id) {
        if (!subTasks.containsKey(id)) {
            return false;
        }
        SubTask subTask = subTasks.get(id);
        subTasks.remove(id);

        int epic_id = subTask.getEpicId();
        Epic epic = epics.get(epic_id);
        epic.deleteSingleSubtask(id);
        epic.setState(calcState(epic));
        return true;
    }
}