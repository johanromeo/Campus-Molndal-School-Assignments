package org.campusmolndal;

public class ToDo {
    private int id;
    private String text;
    private boolean done;

    public ToDo(int id, String text, boolean done) {
        this.id = id;
        this.text = text;
        this.done = done;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nText: " + text +
                "\nDone: " + done;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}

