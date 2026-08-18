/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 * Habitat del bioparque. Es un objeto de valor inmutable: para "cambiar"
 * el habitat de un animal se crea un Habitat nuevo y se usa
 * Animal.trasladarHabitat(nuevoHabitat).
 *
 * @author ASUS
 */
public class Habitat {
    private final String nombre;

    public Habitat(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del habitat es obligatorio.");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
