package org.campusmolndal;

import java.util.Scanner;

/**
 * The Application class represents the main entry point of the program.
 * It provides a menu-driven interface for interacting with a ToDo list.
 */
public class Application {

    private DatabaseOperations databaseOperations;
    private ToDoFacade toDoFacade;
    private Input input;

    /**
     * Constructs an Application object with the specified database operations and ToDo facade.
     *
     * @param databaseOperations the database operations object used for interacting with the database
     * @param toDoFacade the ToDo facade object used for managing the ToDo list
     */
    public Application(DatabaseOperations databaseOperations, ToDoFacade toDoFacade) {
        this.databaseOperations = databaseOperations;
        this.toDoFacade = toDoFacade;
        input = new Input(new Scanner(System.in));
    }

    /**
     * Runs the program by displaying the menu and handling user inputs.
     */
    public void runProgram() {
        boolean isDone = false;

        while (!isDone) {
            Output.showTodoMenu();
            switch (input.readCorrectIntFromUser()) {
                case 1 -> letUserCreateTodo();
                case 2 -> Output.printById(toDoFacade, input);
                case 3 -> letUserUpdateToDoText();
                case 4 -> letUserUpdateTodoDone();
                case 5 -> letUserDeleteTodo();
                case 6 -> Output.printAll(toDoFacade);
                case 7 -> isDone = true;
            }
        }
    }

    private void letUserCreateTodo() {
        Output.promptUserToSetIdOnTodo();
        int id = input.readCorrectIntFromUser();
        input.consumeNewLine();
        Output.promptUserToSetTextOnTodo();
        String text = input.readStringFromUser();
        ToDo todo = new ToDo(id, text, false);
        toDoFacade.createTodo(todo);
    }

    private void letUserUpdateToDoText() {
        Output.askUserWichId();
        int id = input.readCorrectIntFromUser();
        input.consumeNewLine();
        Output.promptUserToSetTextOnTodo();
        String newText = input.readStringFromUser();
        toDoFacade.updateTodoText(id, newText);
    }

    private void letUserUpdateTodoDone() {
        Output.askUserToUpdateDoneStatusInTodo();
        toDoFacade.updateTodoDone(input.readCorrectIntFromUser(), true);
    }

    private void letUserDeleteTodo() {
        Output.askUserWichTodoToDelete();
        toDoFacade.deleteTodoById(input.readCorrectIntFromUser());
    }
}
