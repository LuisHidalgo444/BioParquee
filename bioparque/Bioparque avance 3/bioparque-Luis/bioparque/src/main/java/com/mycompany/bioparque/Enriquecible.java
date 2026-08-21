/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

/**
 * Capacidad transversal (reto 17): un animal es "Enriquecible" cuando el
 * bioparque le aplica un programa de enriquecimiento ambiental/conductual
 * (juguetes, comida escondida, dispensadores, estimulos nuevos, etc.).
 *
 * Esta capacidad NO se modela como otro metodo abstracto en Animal porque
 * no todas las categorias la necesitan de la misma forma: Ave y Mamifero
 * si participan de programas de enriquecimiento conductual, pero Reptil
 * (por su fisiologia y comportamiento) no lo implementa en este dominio.
 * Por eso se separa en una interfaz: el "puede ser enriquecido" no es una
 * propiedad de TODO Animal (no es "es-un"), sino una capacidad que algunas
 * categorias "pueden hacer" (interfaz, no herencia).
 *
 * @author ASUS
 */
public interface Enriquecible {

    /**
     * Ejecuta una actividad de enriquecimiento ambiental y devuelve una
     * descripcion de lo realizado.
     */
    String realizarEnriquecimiento();
}
