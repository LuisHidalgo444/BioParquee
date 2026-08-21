/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 * Un Ave si participa de programas de enriquecimiento ambiental,
 * por eso implementa la interfaz Enriquecible.
 *
 * @author ASUS
 */
public class Ave extends Animal implements Enriquecible {

    public Ave(String codigo, String nombre, int edad, double peso, String estadoSalud, String sexo,
            String fechaIngreso, Habitat habitat) {
        super(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
    }

    @Override
    public String getCategoria() {
        return "Ave";
    }

    @Override
    public String emitirSonido() {
        return "Canto/trino";
    }

    @Override
    public String describirCuidados() {
        return "Revision de plumaje, dieta a base de semillas/frutas e insectos, y espacio para volar";
    }

    @Override
    public String realizarEnriquecimiento() {
        return "Se esconde comida en distintos puntos del habitat y se cuelgan juguetes para estimular el vuelo";
    }
}
