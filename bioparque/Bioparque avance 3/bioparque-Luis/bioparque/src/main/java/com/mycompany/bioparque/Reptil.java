/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 * Reptil NO implementa Enriquecible: en este dominio, los programas de
 * enriquecimiento conductual no se aplican a esta categoria.
 *
 * @author ASUS
 */
public class Reptil extends Animal {

    public Reptil(String codigo, String nombre, int edad, double peso, String estadoSalud, String sexo,
            String fechaIngreso, Habitat habitat) {
        super(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
    }

    @Override
    public String getCategoria() {
        return "Reptil";
    }

    @Override
    public String emitirSonido() {
        return "Silbido/siseo";
    }

    @Override
    public String describirCuidados() {
        return "Control de temperatura y humedad del terrario, y zona de sol controlada";
    }
}
