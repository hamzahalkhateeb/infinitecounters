package com.example.a99;

public class DESCconstructor {

    private int id;

    private String name;

    private int current_clicks;

    private int goal_clicks;

    public DESCconstructor(int id, String name, int current_clicks, int goal_clicks){
        this.id=id;
        this.name=name;
        this.current_clicks=current_clicks;
        this.goal_clicks=goal_clicks;
    }

    public DESCconstructor(){}

    @Override
    public String toString(){
        return "DESCconstructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", current_clicks=" + current_clicks +
                ", goal_clicks=" + goal_clicks +
                '}';
    }

    public int getId() {return id; }

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getcurrent_clicks() {return current_clicks;}

    public void setcurrent_clicks(int current_clicks) {this.current_clicks = current_clicks;}

    public int getgoal_clicks() {return goal_clicks;}

    public void setgoal_clicks(int goal_clicks) {this.goal_clicks = goal_clicks;}

}
