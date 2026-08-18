/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 * Clase abstracta que representa un animal del bioparque.
 * Es la superclase de Ave, Mamifero y Reptil.
 *
 * Los atributos son privados. El codigo, nombre, edad, sexo y fecha de
 * ingreso son inmutables una vez creado el animal (solo consulta). El peso,
 * el habitat y el estado de salud si pueden cambiar, pero unicamente a
 * traves de metodos con intencion de dominio (actualizarPeso,
 * trasladarHabitat, ponerEnObservacion, darDeAlta, retirar), nunca con
 * setters genericos.
 *
 * @author ASUS
 */
public abstract class Animal {
    private final String codigo;
    private final String nombre;
    private final int edad;
    private double peso;
    private String estadoSalud;
    private final String sexo;
    private final String fechaIngreso;
    private Habitat habitat;

    public Animal(String codigo, String nombre, int edad, double peso, String estadoSalud, String sexo,
            String fechaIngreso, Habitat habitat) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo es obligatorio.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero.");
        }
        if (sexo == null || sexo.isBlank()) {
            throw new IllegalArgumentException("El sexo es obligatorio.");
        }
        if (fechaIngreso == null || fechaIngreso.isBlank()) {
            throw new IllegalArgumentException("La fecha de ingreso es obligatoria.");
        }
        if (habitat == null) {
            throw new IllegalArgumentException("El habitat es obligatorio.");
        }

        this.codigo = codigo;
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.estadoSalud = (estadoSalud == null || estadoSalud.isBlank()) ? "Sano" : estadoSalud;
        this.sexo = sexo;
        this.fechaIngreso = fechaIngreso;
        this.habitat = habitat;
    }

    // ---- Consulta (getters). El codigo es inmutable: no existe setCodigo() ----

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public double getPeso() {
        return peso;
    }

    public String getEstadoSalud() {
        return estadoSalud;
    }

    public String getSexo() {
        return sexo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    // ---- Metodos con intencion de dominio (unica forma de modificar el estado) ----

    /**
     * Actualiza el peso del animal, por ejemplo tras un control veterinario.
     */
    public void actualizarPeso(double nuevoPeso) {
        if (nuevoPeso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que cero.");
        }
        this.peso = nuevoPeso;
    }

    /**
     * Traslada al animal a un nuevo habitat dentro del bioparque.
     */
    public void trasladarHabitat(Habitat nuevoHabitat) {
        if (nuevoHabitat == null) {
            throw new IllegalArgumentException("El habitat no puede ser nulo.");
        }
        this.habitat = nuevoHabitat;
    }

    /**
     * Marca al animal como en observacion (por ejemplo, ante un sintoma).
     */
    public void ponerEnObservacion() {
        this.estadoSalud = "En observacion";
    }

    /**
     * Marca al animal como sano, dandolo de alta tras un tratamiento u observacion.
     */
    public void darDeAlta() {
        this.estadoSalud = "Sano";
    }

    /**
     * Marca al animal como retirado del inventario activo.
     */
    public void retirar() {
        this.estadoSalud = "Retirado";
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
