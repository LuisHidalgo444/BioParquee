/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 * Un Mamifero si participa de programas de enriquecimiento ambiental,
 * por eso implementa la interfaz Enriquecible.
 *
 * @author ASUS
 */
public class Mamifero extends Animal implements Enriquecible {

    public Mamifero(String codigo, String nombre, int edad, double peso, String estadoSalud, String sexo,
            String fechaIngreso, Habitat habitat) {
        super(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
    }

    @Override
    public String getCategoria() {
        return "Mamifero";
    }

    @Override
    public String emitirSonido() {
        return "Rugido/gruñido/ladrido (segun la especie)";
    }

    @Override
    public String describirCuidados() {
        return "Control veterinario periodico, dieta balanceada y espacio suficiente para desplazarse";
    }

    @Override
    public String realizarEnriquecimiento() {
        return "Se ofrecen juguetes, olores nuevos y sesiones cortas de entrenamiento cognitivo";
    }
}
