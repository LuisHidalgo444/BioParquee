/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class GestionarAnimales {
    Scanner lector = new Scanner(System.in);
    ArrayList<Animal> misAnimales = new ArrayList<>();

    /**
     * Metodo para registrar un animal (opcion 1)
     * Primero se pregunta la categoria y luego se piden los datos
     * comunes (heredados de Animal). Segun la categoria se instancia
     * la subclase correspondiente (Ave, Mamifero o Reptil).
     */
    public void registrarAnimal() {
        System.out.println("=== Seleccione la categoria del animal ===");
        System.out.println("1. Ave");
        System.out.println("2. Mamifero");
        System.out.println("3. Reptil");
        System.out.print("Opcion: ");
        int categoria = Integer.parseInt(lector.nextLine());

        if (categoria < 1 || categoria > 3) {
            System.out.println("Categoria invalida. Registro cancelado.");
            return;
        }

        System.out.println("Ingrese el codigo del animal");
        String codigo = lector.nextLine();

        if (buscarPorCodigo(codigo) != null) {
            System.out.println("Ya existe un animal registrado con ese codigo.");
            return;
        }

        System.out.println("Ingrese el nombre");
        String nombre = lector.nextLine();
        System.out.println("Ingrese la edad (anios)");
        int edad = Integer.parseInt(lector.nextLine());
        System.out.println("Ingrese el peso (kg)");
        double peso = Double.parseDouble(lector.nextLine());
        System.out.println("Ingrese el estado de salud (Sano, En tratamiento, Cuarentena)");
        String estadoSalud = lector.nextLine();
        System.out.println("Ingrese el sexo (Macho/Hembra)");
        String sexo = lector.nextLine();
        System.out.println("Ingrese la fecha de ingreso (dd/mm/aaaa)");
        String fechaIngreso = lector.nextLine();
        System.out.println("Ingrese el habitat");
        String nombreHabitat = lector.nextLine();
        Habitat habitat = new Habitat(nombreHabitat);

        Animal miAnimal = null;

        // se crea el objeto segun la categoria seleccionada
        switch (categoria) {
            case 1 -> miAnimal = new Ave(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
            case 2 -> miAnimal = new Mamifero(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
            case 3 -> miAnimal = new Reptil(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
        }

        // insertar el objeto en la contenedora
        misAnimales.add(miAnimal);

        // mensajeria
        System.out.println("Animal registrado con exito");
    }

    /**
     * Metodo para listar todos los animales (opcion 2)
     */
    public void listarAnimales() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados");
            return;
        } else {
            System.out.println("==== Listado de animales ====");
            for (Animal a : misAnimales) {
                System.out.println("------------------------------");
                a.mostrarInfo();
            }
            System.out.println("------------------------------");
        }
    }

    /**
     * Metodo para buscar un animal por codigo (opcion 3)
     */
    public void buscarAnimalPorCodigo() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados");
            return;
        }

        System.out.println("Ingrese el codigo del animal a buscar:");
        String codigoBuscado = lector.nextLine();

        Animal encontrado = buscarPorCodigo(codigoBuscado);

        if (encontrado != null) {
            System.out.println("==== Animal encontrado ====");
            encontrado.mostrarInfo();
        } else {
            System.out.println("No se encontro ningun animal con el codigo: " + codigoBuscado);
        }
    }

    /**
     * Metodo para actualizar los datos de un animal (opcion 4)
     */
    public void actualizarDatosAnimal() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados para actualizar.");
            return;
        }

        System.out.println("Ingrese el codigo del animal que desea actualizar:");
        String codigoBuscado = lector.nextLine();

        Animal a = buscarPorCodigo(codigoBuscado);

        if (a == null) {
            System.out.println("No se encontro ningun animal con el codigo ingresado: " + codigoBuscado);
            return;
        }

        // Menu de opciones para actualizar
        int opcion;
        do {
            System.out.println("=== ¿Que dato desea actualizar? ===");
            System.out.println("1. Nombre");
            System.out.println("2. Edad");
            System.out.println("3. Peso");
            System.out.println("4. Estado de salud");
            System.out.println("5. Sexo");
            System.out.println("6. Fecha de ingreso");
            System.out.println("7. Habitat");
            System.out.println("8. Salir de actualizaciones");
            System.out.println("Ingrese una opcion:");

            opcion = Integer.parseInt(lector.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre: ");
                    a.setNombre(lector.nextLine());
                    System.out.println("¡Nombre actualizado con exito!");
                    break;
                case 2:
                    System.out.println("Ingrese la nueva edad: ");
                    a.setEdad(Integer.parseInt(lector.nextLine()));
                    System.out.println("¡Edad actualizada con exito!");
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo peso: ");
                    a.setPeso(Double.parseDouble(lector.nextLine()));
                    System.out.println("¡Peso actualizado con exito!");
                    break;
                case 4:
                    System.out.println("Ingrese el nuevo estado de salud: ");
                    a.setEstadoSalud(lector.nextLine());
                    System.out.println("¡Estado de salud actualizado con exito!");
                    break;
                case 5:
                    System.out.println("Ingrese el nuevo sexo: ");
                    a.setSexo(lector.nextLine());
                    System.out.println("¡Sexo actualizado con exito!");
                    break;
                case 6:
                    System.out.println("Ingrese la nueva fecha de ingreso: ");
                    a.setFechaIngreso(lector.nextLine());
                    System.out.println("¡Fecha de ingreso actualizada con exito!");
                    break;
                case 7:
                    System.out.println("Ingrese el nuevo habitat: ");
                    String nombreHabitat = lector.nextLine();
                    a.setHabitat(new Habitat(nombreHabitat));
                    System.out.println("¡Habitat actualizado con exito!");
                    break;
                case 8:
                    System.out.println("Saliendo del menu de actualizacion...");
                    break;
                default:
                    System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (opcion != 8);
    }

    /**
     * Metodo para retirar (eliminar) un animal por codigo (opcion 5)
     */
    public void retirarAnimal() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados para retirar.");
            return;
        }

        System.out.println("Ingrese el codigo del animal que desea retirar:");
        String codigoBuscado = lector.nextLine();

        boolean encontrado = false;

        for (int i = 0; i < misAnimales.size(); i++) {
            Animal a = misAnimales.get(i);

            if (a.getCodigo().equalsIgnoreCase(codigoBuscado)) {
                encontrado = true;
                misAnimales.remove(i);
                System.out.println("¡Animal retirado con exito!");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontro ningun animal con el codigo ingresado: " + codigoBuscado);
        }
    }

    /**
     * Metodo auxiliar para buscar un animal por codigo dentro de la lista
     */
    private Animal buscarPorCodigo(String codigo) {
        for (Animal a : misAnimales) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }
}
