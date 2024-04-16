package com.example.a99;


import java.util.List;

public class TaskConstructor {

    private int id;

    private String Task_name;

    private String Duedate;

    private int completed;

    public TaskConstructor(int id, String Task_name, String Duedate, int completed, List<SubtaskConstructor> subtasks){
        this.id=id;
        this.Task_name= Task_name;
        this.Duedate= Duedate;
        this.completed= completed;
    }

    public TaskConstructor(TaskList333 taskList333, int task_adapter){}

        @Override
                public String toString(){
            return "TaskConstructor{" +
                    "id=" + id +
                    ", Task_name=" + Task_name + '\''+
                    ", Duedate+" + Duedate +
                    ", completed+" + completed +
                    "}";
        }

        public int getId() {return id;}
        public void setId(int id) {this.id = id;}

        public String getTask_name() {return Task_name;}
        public void setTask_name(String Task_name) {this.Task_name = Task_name;}

        public String getDuedate() {return Duedate;}
        public void setDuedate() {this.Duedate = Duedate;}

        public int getCompleted() {return completed;}
        public void setCompleted() {this.completed = completed;}



    }


