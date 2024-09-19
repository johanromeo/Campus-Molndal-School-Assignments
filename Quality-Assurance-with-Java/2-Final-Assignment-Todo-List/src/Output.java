package org.campusmolndal;

import java.util.List;

public class Output {

    public static void showTodoMenu() {
        System.out.println("""
                1. Create a new todo
                2. Read todo by id
                3. Update todo text
                4. Update todo done status
                5. Delete todo by id
                6. Print all todos
                7. Exit
                \nPlease enter your choice: """);
    }

    public static void printAll(ToDoFacade toDoFacade) {
        System.out.println("All documents in the collection:");
        List<ToDo> todos = toDoFacade.getAllTodos();
        todos.forEach(System.out::println);
    }

    public static void printById(ToDoFacade toDoFacade, Input input) {
        System.out.println("Enter the id of the todo you want to see: ");
        System.out.println(toDoFacade.getTodoById(input.readCorrectIntFromUser()));
    }
    public static void promptUserForCorrectInt() {
        System.out.println("Please enter a valid integer");
    }
    public static void promptUserToSetIdOnTodo() {
        System.out.println("Set an integer id on your todo: ");
    }
    public static void promptUserToSetTextOnTodo() {
        System.out.println("Set a text to your todo: ");
    }
   public static void askUserWichId() {
       System.out.println("What id has the todo?: ");
   }
    public static void askUserToUpdateDoneStatusInTodo() {
        System.out.println("Enter the id on the todo you want to set to done");
    }
    public static void askUserWichTodoToDelete() {
        System.out.println("Enter the id on the todo you want to delete");
    }
    public static void tellUserWrongInput() {
        System.out.println("Wrong input");
    }
    public static void tellUserEmptyString() {
        System.out.println("Empty string");
    }

}
