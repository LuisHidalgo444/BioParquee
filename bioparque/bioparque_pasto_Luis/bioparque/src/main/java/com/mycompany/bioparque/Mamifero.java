/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 *
 * @author ASUS
 */
public class Mamifero extends Animal {

    public Mamifero(String codigo, String nombre, int edad, double peso, String estadoSalud, String sexo,
            String fechaIngreso, Habitat habitat) {
        super(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
    }

    @Override
    public String getCategoria() {
        return "Mamifero";
    }

    @Override
    public String comportamiento() {
        return "Se desplaza en tierra y amamanta a sus crias";
    }
}
