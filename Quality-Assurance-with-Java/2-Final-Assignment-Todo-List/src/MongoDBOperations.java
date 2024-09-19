package org.campusmolndal;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoDBOperations implements DatabaseOperations {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> todoCollection;

    public MongoDBOperations() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("johanToDo");
        todoCollection = database.getCollection("ToDos");
    }

    @Override
    public void create(ToDo todo) {
        checkingUniquenessOfId(todo);

        Document doc = new Document("id", todo.getId())
                .append("text", todo.getText())
                .append("done", todo.isDone());

        todoCollection.insertOne(doc);
    }

    @Override
    public ToDo getTodoById(int id) {
        Document doc = todoCollection.find(Filters.eq("id", id)).first();
        if (doc != null) {
            String text = doc.getString("text");
            boolean done = doc.getBoolean("done");

            return new ToDo(id, text, done);
        }
        return null;
    }

    @Override
    public void updateTodoText(ToDo todo) {
        todoCollection.updateOne(Filters.eq("id", todo.getId()), Updates.set("text", todo.getText()));
    }

    @Override
    public void updateTodoDone(ToDo todo) {
        todoCollection.updateOne(Filters.eq("id", todo.getId()), Updates.set("done", todo.isDone()));
    }

    @Override
    public void delete(int id) {
        todoCollection.deleteOne(Filters.eq("id", id));
    }

    @Override
    public List<ToDo> getAllTodos() {
        List<ToDo> todos = new ArrayList<>();

        for (Document doc : todoCollection.find()) {
            int id = doc.getInteger("id");
            String text = doc.getString("text");
            boolean done = doc.getBoolean("done");

            todos.add(new ToDo(id, text, done));
        }
        return todos;
    }

    // Help from ChatGPT. If an id is set that already exists in the database -> increment it by one
    private void checkingUniquenessOfId(ToDo todo) {
        int id = todo.getId();
        if (todoCollection.find(Filters.eq("id", id)).first() != null) {
            do {
                id++;
            } while (todoCollection.find(Filters.eq("id", id)).first() != null);
        }
        todo.setId(id);
    }
}


