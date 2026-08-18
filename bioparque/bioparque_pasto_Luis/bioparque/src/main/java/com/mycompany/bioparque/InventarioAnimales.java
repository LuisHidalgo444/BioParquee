/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bioparque;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Capa de interaccion e inventario. Aqui se centralizan la comprobacion de
 * codigo unico y las operaciones CRUD (Registrar/Listar/Buscar/Actualizar/
 * Retirar). Es tambien el lugar donde se capturan y muestran los errores
 * (IllegalArgumentException lanzadas por Animal y Habitat, o entradas
 * invalidas del usuario); los errores nunca se silencian, siempre se
 * informa al usuario con un mensaje claro.
 *
 * @author ASUS
 */
public class InventarioAnimales {
    private final Scanner lector = new Scanner(System.in);
    private final ArrayList<Animal> misAnimales = new ArrayList<>();

    // ================= CREATE =================

    /**
     * Registra un animal nuevo (opcion 1). Pide la categoria y los datos
     * comunes, valida que el codigo no este repetido y delega las reglas
     * de negocio (edad >= 0, peso > 0, campos obligatorios) al constructor
     * de Animal, capturando aqui cualquier IllegalArgumentException.
     */
    public void registrarAnimal() {
        int categoria = leerEntero("=== Seleccione la categoria del animal ===\n1. Ave\n2. Mamifero\n3. Reptil\nOpcion:");

        if (categoria < 1 || categoria > 3) {
            System.out.println("Categoria invalida. Registro cancelado.");
            return;
        }

        String codigo = leerTexto("Ingrese el codigo del animal:");

        if (existeCodigo(codigo)) {
            System.out.println("Ya existe un animal registrado con el codigo \"" + codigo + "\".");
            return;
        }

        String nombre = leerTexto("Ingrese el nombre:");
        int edad = leerEntero("Ingrese la edad (anios):");
        double peso = leerDecimal("Ingrese el peso (kg):");
        String estadoSalud = leerTexto("Ingrese el estado de salud inicial (ej. Sano):");
        String sexo = leerTexto("Ingrese el sexo (Macho/Hembra):");
        String fechaIngreso = leerTexto("Ingrese la fecha de ingreso (dd/mm/aaaa):");
        String nombreHabitat = leerTexto("Ingrese el habitat:");

        try {
            Habitat habitat = new Habitat(nombreHabitat);

            Animal miAnimal = switch (categoria) {
                case 1 -> new Ave(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
                case 2 -> new Mamifero(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
                case 3 -> new Reptil(codigo, nombre, edad, peso, estadoSalud, sexo, fechaIngreso, habitat);
                default -> null;
            };

            misAnimales.add(miAnimal);
            System.out.println("Animal registrado con exito");
        } catch (IllegalArgumentException e) {
            // Capturamos aqui (capa de interaccion) y avisamos al usuario, sin silenciar el error.
            System.out.println("No se pudo registrar el animal: " + e.getMessage());
        }
    }

    // ================= READ =================

    /**
     * Lista todos los animales (opcion 2)
     */
    public void listarAnimales() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados");
            return;
        }

        System.out.println("==== Listado de animales ====");
        for (Animal a : misAnimales) {
            System.out.println("------------------------------");
            a.mostrarInfo();
        }
        System.out.println("------------------------------");
    }

    /**
     * Busca un animal por codigo (opcion 3)
     */
    public void buscarAnimalPorCodigo() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados");
            return;
        }

        String codigoBuscado = leerTexto("Ingrese el codigo del animal a buscar:");
        Animal encontrado = buscarPorCodigo(codigoBuscado);

        if (encontrado != null) {
            System.out.println("==== Animal encontrado ====");
            encontrado.mostrarInfo();
        } else {
            System.out.println("No se encontro ningun animal con el codigo: " + codigoBuscado);
        }
    }

    // ================= UPDATE =================

    /**
     * Actualiza los datos de un animal (opcion 4). Solo se exponen los
     * cambios con sentido de dominio: peso, habitat y estado de salud.
     * El codigo, nombre, edad, sexo y fecha de ingreso son inmutables.
     */
    public void actualizarDatosAnimal() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados para actualizar.");
            return;
        }

        String codigoBuscado = leerTexto("Ingrese el codigo del animal que desea actualizar:");
        Animal a = buscarPorCodigo(codigoBuscado);

        if (a == null) {
            System.out.println("No se encontro ningun animal con el codigo ingresado: " + codigoBuscado);
            return;
        }

        int opcion;
        do {
            opcion = leerEntero("=== ¿Que desea hacer con el animal " + a.getCodigo() + "? ===\n"
                    + "1. Actualizar peso\n"
                    + "2. Trasladar de habitat\n"
                    + "3. Poner en observacion\n"
                    + "4. Dar de alta (marcar como sano)\n"
                    + "5. Salir de actualizaciones\n"
                    + "Opcion:");

            try {
                switch (opcion) {
                    case 1 -> {
                        double nuevoPeso = leerDecimal("Ingrese el nuevo peso (kg):");
                        a.actualizarPeso(nuevoPeso);
                        System.out.println("¡Peso actualizado con exito!");
                    }
                    case 2 -> {
                        String nombreHabitat = leerTexto("Ingrese el nuevo habitat:");
                        a.trasladarHabitat(new Habitat(nombreHabitat));
                        System.out.println("¡Habitat actualizado con exito!");
                    }
                    case 3 -> {
                        a.ponerEnObservacion();
                        System.out.println("El animal quedo en observacion.");
                    }
                    case 4 -> {
                        a.darDeAlta();
                        System.out.println("El animal fue dado de alta (Sano).");
                    }
                    case 5 -> System.out.println("Saliendo del menu de actualizacion...");
                    default -> System.out.println("Opcion invalida. Intente de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                // Se captura y se informa; nunca se silencia el error.
                System.out.println("No se pudo actualizar: " + e.getMessage());
            }
        } while (opcion != 5);
    }

    // ================= DELETE =================

    /**
     * Retira (elimina) un animal por codigo (opcion 5)
     */
    public void retirarAnimal() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados para retirar.");
            return;
        }

        String codigoBuscado = leerTexto("Ingrese el codigo del animal que desea retirar:");
        Animal a = buscarPorCodigo(codigoBuscado);

        if (a == null) {
            System.out.println("No se encontro ningun animal con el codigo ingresado: " + codigoBuscado);
            return;
        }

        a.retirar();
        misAnimales.remove(a);
        System.out.println("¡Animal retirado con exito!");
    }

    // ================= Utilidades centralizadas =================

    /**
     * Busca un animal por codigo dentro de la lista. Punto centralizado
     * usado por buscar/actualizar/retirar y por la comprobacion de
     * codigo unico en el registro.
     */
    private Animal buscarPorCodigo(String codigo) {
        for (Animal a : misAnimales) {
            if (a.getCodigo().equalsIgnoreCase(codigo)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Comprobacion centralizada de codigo unico, usada al registrar.
     */
    private boolean existeCodigo(String codigo) {
        return buscarPorCodigo(codigo) != null;
    }

    /**
     * Lee un texto obligatorio (no vacio) desde consola, repitiendo la
     * pregunta hasta recibir un valor valido. Evita que datos obligatorios
     * lleguen vacios al dominio.
     */
    private String leerTexto(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String valor = lector.nextLine().trim();
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("Este dato es obligatorio, no puede quedar vacio.");
        }
    }

    /**
     * Lee un entero desde consola, capturando NumberFormatException y
     * mostrando un mensaje claro en vez de dejar que el programa se caiga.
     */
    private int leerEntero(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            try {
                return Integer.parseInt(lector.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Debe ingresar un numero entero.");
            }
        }
    }

    /**
     * Lee un numero decimal desde consola, capturando NumberFormatException
     * y mostrando un mensaje claro en vez de dejar que el programa se caiga.
     */
    private double leerDecimal(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            try {
                return Double.parseDouble(lector.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Debe ingresar un numero (use punto decimal).");
            }
        }
    }
}
