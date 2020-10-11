package com.example.mistareas.Utilities;

public class Utilities {

    //Constantes campos Tabla usuario
    public static final String TABLA_TAREA = "tareas";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_TAREA = "tarea";
    public static final String CAMPO_STATUS = "status";

    public static final String CREAR_TABLA_TAREA = "CREATE TABLE " + TABLA_TAREA + " ("+ CAMPO_ID+" INTEGER, "+ CAMPO_TAREA+" TEXT, "+ CAMPO_STATUS +" INTEGER)";

}

