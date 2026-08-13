/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 * Clase abstracta que representa un animal del bioparque.
 * Es la superclase de Ave, Mamifero y Reptil.
 * @author ASUS
 */
public abstract class Animal {
    protected String codigo;
    protected String nombre;
    protected int edad;
    protected double peso;
    protected String estadoSalud;
    protected String sexo;
    protected String fechaIngreso;
    protected Habitat habitat;

    public Animal() {
    }

    public Animal(String codigo, String nombre, int edad, double peso, String estadoSalud, String sexo,
            String fechaIngreso, Habitat habitat) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.estadoSalud = estadoSalud;
        this.sexo = sexo;
        this.fechaIngreso = fechaIngreso;
        this.habitat = habitat;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public void setEstadoSalud(String estadoSalud) {
        this.estadoSalud = estadoSalud;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }

    /**
     * Cada subclase debe indicar a que categoria pertenece (Ave, Mamifero, Reptil)
     */
    public abstract String getCategoria();

    /**
     * Cada subclase debe definir su propio comportamiento
     */
    public abstract String comportamiento();

    /**
     * Muestra en consola los datos generales del animal.
     */
    public void mostrarInfo() {
        System.out.println("Codigo: " + codigo);
        System.out.println("Nombre: " + nombre);
        System.out.println("Categoria: " + getCategoria());
        System.out.println("Edad: " + edad + " anios");
        System.out.println("Peso: " + peso + " kg");
        System.out.println("Estado de salud: " + estadoSalud);
        System.out.println("Sexo: " + sexo);
        System.out.println("Fecha de ingreso: " + fechaIngreso);
        System.out.println("Habitat: " + (habitat != null ? habitat.toString() : "Sin asignar"));
    }
}
