package com.example.a99;

public class infinconstructor {

    private int id;
    private String name;
    private int current_clicks;

    public infinconstructor(int id, String name, int current_clicks){
        this.id=id;
        this.name=name;
        this.current_clicks=current_clicks;
    }

    public infinconstructor(){
    }

    // necessary if you want to print data out into the logs
    @Override
    public String toString() {
        return "infinconstructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", current_clicks=" + current_clicks +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrent_clicks() {
        return current_clicks;
    }

    public void setCurrent_clicks(int current_clicks) {
        this.current_clicks = current_clicks;
    }
}
