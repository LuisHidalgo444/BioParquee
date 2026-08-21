/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.bioparque;

import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Bioparque {

    public static void main(String[] args) {
        InventarioAnimales inventario = new InventarioAnimales();
        Scanner lector = new Scanner(System.in);
        boolean activo = true;
        int opc;
        //construir el menu
        do {
            System.out.println("=== INVENTARIO DE ANIMALES - BIOPARQUE PASTO ===");
            System.out.println("1. Registrar animal");
            System.out.println("2. Listar animales");
            System.out.println("3. Buscar animal por codigo");
            System.out.println("4. Actualizar datos de un animal");
            System.out.println("5. Retirar animal");
            System.out.println("6. Filtrar por categoria o estado");
            System.out.println("7. Ejecutar comportamientos");
            System.out.println("8. Ver resumen del inventario");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcionLeida;
            try {
                opcionLeida = Integer.parseInt(lector.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Debe ingresar un numero.");
                continue;
            }
            opc = opcionLeida;

            //construir el conmutador
            switch (opc) {
                case 1 -> inventario.registrarAnimal();
                case 2 -> inventario.listarAnimales();
                case 3 -> inventario.buscarAnimalPorCodigo();
                case 4 -> inventario.actualizarDatosAnimal();
                case 5 -> inventario.retirarAnimal();
                case 6 -> inventario.filtrarPorCategoriaOEstado();
                case 7 -> inventario.ejecutarComportamientos();
                case 8 -> inventario.verResumenInventario();
                case 0 -> {
                    activo = false;
                    System.out.println("Usted ha salido del sistema");
                }
                default -> System.out.println("Opcion invalida. Intente de nuevo.");
            }
        } while (activo);
    }
}
