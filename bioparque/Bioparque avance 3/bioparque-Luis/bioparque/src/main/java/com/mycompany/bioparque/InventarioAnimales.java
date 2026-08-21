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
     * Lista los animales ACTIVOS (opcion 2).
     *
     * Consecuencia de RN-05: como el retiro es logico, un animal retirado
     * sigue en el ArrayList pero se excluye de este listado.
     */
    public void listarAnimales() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados");
            return;
        }

        boolean hayActivos = false;
        System.out.println("==== Listado de animales activos ====");
        for (Animal a : misAnimales) {
            if ("Retirado".equalsIgnoreCase(a.getEstadoSalud())) {
                continue;
            }
            hayActivos = true;
            System.out.println("------------------------------");
            a.mostrarInfo();
        }

        if (!hayActivos) {
            System.out.println("No hay animales activos (todos estan retirados).");
        } else {
            System.out.println("------------------------------");
        }
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

        if ("Retirado".equalsIgnoreCase(a.getEstadoSalud())) {
            System.out.println("El animal " + a.getCodigo() + " esta retirado y no se puede actualizar.");
            return;
        }

        int opcion;
        do {
            opcion = leerEntero("=== ¿Que desea hacer con el animal " + a.getCodigo() + "? ===\n"
                    + "1. Actualizar peso\n"
                    + "2. Trasladar de habitat\n"
                    + "3. Poner en observacion\n"
                    + "4. Dar de alta (marcar como sano)\n"
                    + "5. Cumplir anios\n"
                    + "6. Salir de actualizaciones\n"
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
                    case 5 -> {
                        a.cumplirAnios();
                        System.out.println("¡Feliz cumpleanios! Ahora tiene " + a.getEdad() + " anios.");
                    }
                    case 6 -> System.out.println("Saliendo del menu de actualizacion...");
                    default -> System.out.println("Opcion invalida. Intente de nuevo.");
                }
            } catch (IllegalArgumentException e) {
                // Se captura y se informa; nunca se silencia el error.
                System.out.println("No se pudo actualizar: " + e.getMessage());
            }
        } while (opcion != 6);
    }

    // ================= DELETE (logico) =================

    /**
     * RN-05: Retira un animal por codigo (opcion 5).
     *
     * Politica: eliminacion LOGICA. El animal NO se borra del ArrayList,
     * solo cambia su estadoSalud a "Retirado" (ver Animal.retirar()).
     * Se conserva para trazabilidad/historial y para que buscarAnimalPorCodigo
     * lo siga encontrando. No puede retirarse dos veces.
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

        if ("Retirado".equalsIgnoreCase(a.getEstadoSalud())) {
            System.out.println("El animal " + a.getCodigo() + " ya se encuentra retirado.");
            return;
        }

        a.retirar();
        System.out.println("¡Animal retirado con exito! (permanece en el inventario con estado \"Retirado\")");
    }

    // ================= FILTRAR (opcion 6) =================

    /**
     * Filtra el inventario por categoria (Ave/Mamifero/Reptil) o por
     * estado de salud (Sano/En observacion/Retirado). Reto 18.
     */
    public void filtrarPorCategoriaOEstado() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados");
            return;
        }

        int modo = leerEntero("=== Filtrar por ===\n1. Categoria (Ave / Mamifero / Reptil)\n"
                + "2. Estado de salud (Sano / En observacion / Retirado)\nOpcion:");

        if (modo != 1 && modo != 2) {
            System.out.println("Opcion invalida.");
            return;
        }

        String criterio = (modo == 1)
                ? leerTexto("Ingrese la categoria (Ave, Mamifero o Reptil):")
                : leerTexto("Ingrese el estado (Sano, En observacion o Retirado):");

        boolean hayCoincidencias = false;
        System.out.println("==== Resultados del filtro ====");
        for (Animal a : misAnimales) {
            String valor = (modo == 1) ? a.getCategoria() : a.getEstadoSalud();
            if (valor.equalsIgnoreCase(criterio)) {
                hayCoincidencias = true;
                System.out.println("------------------------------");
                a.mostrarInfo();
            }
        }

        if (!hayCoincidencias) {
            System.out.println("No se encontraron animales que cumplan ese criterio.");
        } else {
            System.out.println("------------------------------");
        }
    }

    // ================= COMPORTAMIENTOS (opcion 7) =================

    /**
     * Recorre los animales activos y muestra su sonido y cuidados
     * (metodos abstractos, polimorfismo real por categoria) y, si el
     * animal es Enriquecible (Ave o Mamifero), tambien su actividad de
     * enriquecimiento. Reto 15 y 17 en accion.
     */
    public void ejecutarComportamientos() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados");
            return;
        }

        System.out.println("==== Comportamientos y cuidados ====");
        for (Animal a : misAnimales) {
            if ("Retirado".equalsIgnoreCase(a.getEstadoSalud())) {
                continue;
            }
            System.out.println("------------------------------");
            System.out.println(a.getCodigo() + " (" + a.getCategoria() + ") emite: " + a.emitirSonido());
            System.out.println("Cuidados: " + a.describirCuidados());
            if (a instanceof Enriquecible enriquecible) {
                System.out.println("Enriquecimiento: " + enriquecible.realizarEnriquecimiento());
            }
        }
        System.out.println("------------------------------");
    }

    // ================= RESUMEN (opcion 8) =================

    /**
     * Usa el metodo concreto comun resumenBasico() (Reto 16) para dar
     * una vista rapida de todo el inventario, activos y retirados.
     */
    public void verResumenInventario() {
        if (misAnimales.isEmpty()) {
            System.out.println("No hay animales registrados");
            return;
        }

        System.out.println("==== Resumen del inventario ====");
        int activos = 0;
        int retirados = 0;
        for (Animal a : misAnimales) {
            System.out.println(a.resumenBasico());
            if ("Retirado".equalsIgnoreCase(a.getEstadoSalud())) {
                retirados++;
            } else {
                activos++;
            }
        }
        System.out.println("------------------------------");
        System.out.println("Total registrados: " + misAnimales.size() + " | Activos: " + activos
                + " | Retirados: " + retirados);
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
