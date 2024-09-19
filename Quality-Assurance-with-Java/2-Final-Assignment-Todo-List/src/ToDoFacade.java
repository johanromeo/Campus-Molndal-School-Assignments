package org.campusmolndal;

import java.util.List;

public class ToDoFacade {
    private DatabaseOperations databaseOperations;

    public ToDoFacade(DatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    public void createTodo(ToDo todo) {
        databaseOperations.create(todo);
    }

    public ToDo getTodoById(int id) {
        return databaseOperations.getTodoById(id);
    }

    public void updateTodoText(int id, String newText) {
        ToDo todo = databaseOperations.getTodoById(id);
        if (todo != null) {
            todo.setText(newText);
            databaseOperations.updateTodoText(todo);
        }
    }

    public void updateTodoDone(int id, boolean newDoneStatus) {
        ToDo todo = databaseOperations.getTodoById(id);
        if (todo != null) {
            todo.setDone(newDoneStatus);
            databaseOperations.updateTodoDone(todo);
        }
    }

    public void deleteTodoById(int id) {
        databaseOperations.delete(id);
    }

    public List<ToDo> getAllTodos() {
        return databaseOperations.getAllTodos();
    }
}
