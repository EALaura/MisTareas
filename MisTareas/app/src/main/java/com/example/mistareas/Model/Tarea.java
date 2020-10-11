package com.example.mistareas.Model;

public class Tarea {
    private Integer id;
    private String tarea;
    private Integer status;

    public Tarea(Integer id, String tarea, Integer status) {
        this.id = id;
        this.tarea = tarea;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
