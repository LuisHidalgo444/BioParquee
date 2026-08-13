/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 *
 * @author ASUS
 */
public class Reptil extends Animal {

    public Reptil() {
        super();
    }

    public Reptil(String codigo, String nombre, int edad, double peso, String estadoSalud, String sexo,
            String fechaIngreso, Habitat habitat) {
        super(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
    }

    @Override
    public String getCategoria() {
        return "Reptil";
    }

    @Override
    public String comportamiento() {
        return "Se arrastra y toma sol para regular su temperatura";
    }
}
