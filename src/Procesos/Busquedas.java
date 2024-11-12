package Procesos;

import Modelo.*;
import java.util.Arrays;
import java.util.Comparator;

public class Busquedas {

    public static Mascota buscarMascotaPorCodigo(Mascota[] mascotas, String codigo) {
        // Filtrar elementos nulos
        Mascota[] mascotasFiltradas = Arrays.stream(mascotas)
                .filter(mascota -> mascota != null)
                .toArray(Mascota[]::new);

        // Ordenar el arreglo
        Arrays.sort(mascotasFiltradas, Comparator.comparing(Mascota::getCodigo));

        // Llamar a la búsqueda binaria recursiva
        return busquedaBinaria(mascotasFiltradas, codigo, 0, mascotasFiltradas.length - 1);
    }

    private static Mascota busquedaBinaria(Mascota[] mascotas, String codigo, int inicio, int fin) {
        if (inicio > fin) {
            return null; // No encontrado
        }

        int medio = inicio + (fin - inicio) / 2;
        int comparacion = mascotas[medio].getCodigo().compareTo(codigo);

        if (comparacion == 0) {
            return mascotas[medio]; // Encontrado
        } else if (comparacion < 0) {
            return busquedaBinaria(mascotas, codigo, medio + 1, fin);
        } else {
            return busquedaBinaria(mascotas, codigo, inicio, medio - 1);
        }
    }

//    public static void main(String[] args) {
//        // Ejemplo de uso
//        Mascota[] mascotas = {
//            new Mascota("001", "Firulais", "Pastor Alemán", 5, "Juan Pérez", "2022-01-01"),
//            new Mascota("002", "Pelusa", "Persa", 3, "María Gómez", "2023-02-01"),
//            new Mascota("003", "Rex", "Bulldog", 2, "Carlos Díaz", "2021-03-01")
//        };
//
//        Mascota buscada = buscarMascotaPorCodigo(mascotas, "00s2");
//        if (buscada != null) {
//            System.out.println("Mascota encontrada: " + buscada.getNombre());
//        } else {
//            System.out.println("Mascota no encontrada.");
//        }
//    }

    public static Mascota buscarMascotaPorCodigo_SecuencialRecursiva(Mascota[] mascotas, String codigo) {
        return buscarMascotaPorCodigo_SecuencialRecursiva(mascotas, codigo, 0);
    }

    private static Mascota buscarMascotaPorCodigo_SecuencialRecursiva(Mascota[] mascotas, String codigo, int indice) {
        if (indice >= mascotas.length) {
            return null; // No encontrado
        }

        Mascota mascota = mascotas[indice];

        if (mascota != null && codigo.equals(mascota.getCodigo())) {
            return mascota; // Encontrado
        }

        return buscarMascotaPorCodigo_SecuencialRecursiva(mascotas, codigo, indice + 1);
    }
    
}//fin de la clase
