package com.example.a99;

public class SubtaskConstructor {

    private int id;
    private int mainTaskId; // ID of the main task this subtask is associated with
    private String subtaskName;
    private int completed;

    public SubtaskConstructor(int id, int mainTaskId,  String subtaskName, int completed) {
        this.id = id;
        this.mainTaskId = mainTaskId;
        this.subtaskName = subtaskName;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {this.id = id;}



    public int getMainTaskId() {
        return mainTaskId;
    }
    public void setMainTaskId(int id) {this.id = id;}

    public String getSubtaskName() {
        return subtaskName;
    }
    public void setSubtaskName(String subtaskName) {this.subtaskName = subtaskName;}

    public int isCompleted() {
        return completed;
    }
    public void setCompleted(int completed) {this.completed = completed;}
}

