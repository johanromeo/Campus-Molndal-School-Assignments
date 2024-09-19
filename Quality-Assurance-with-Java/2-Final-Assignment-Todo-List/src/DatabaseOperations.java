package org.campusmolndal;

import java.util.List;

public interface DatabaseOperations {

    void create(ToDo todo);

    ToDo getTodoById(int id);

    void updateTodoText(ToDo todo);

    void updateTodoDone(ToDo todo);

    void delete(int id);

    List<ToDo> getAllTodos();
}
