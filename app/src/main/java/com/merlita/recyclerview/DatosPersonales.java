package com.merlita.recyclerview;

public class DatosPersonales {
    String nombre;
    int edad;

    public DatosPersonales(String nombre, int edad) throws miExcepcion{
        if(edad!=-1 && !nombre.isEmpty()){
            this.edad = edad;
            this.nombre = nombre;
        }else{
            throw new miExcepcion("No puedes añadir un alumno con campos vacíos. ");
        }
        setNombre(nombre);
        setEdad(edad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
