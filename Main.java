class SistemaReservaVuelos {
    public synchronized void reservarVuelo(Vuelo vuelo, Pasajero pasajero) {
        if (vuelo.reservarAsiento()) {
            System.out.println("Asiento reservado para " + pasajero.getNombre());
        } else {
            System.out.println("No hay asientos disponibles para " + pasajero.getNombre());
        }
    }
}

class Vuelo {
    private int asientosDisponibles;
    
    public Vuelo(int asientosDisponibles) {
        this.asientosDisponibles = asientosDisponibles;
    }
    
    public synchronized boolean reservarAsiento() {
        if (asientosDisponibles > 0) {
            asientosDisponibles--;
            return true;
        }
        return false;
    }
}

class Pasajero {
    private String nombre;
    
    public Pasajero(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
}

public class Main {
    public static void main(String[] args) {
        Vuelo vuelo = new Vuelo(2); // Solo 2 asientos disponibles
        SistemaReservaVuelos sistemaReserva = new SistemaReservaVuelos();
        
        // Crear varios hilos para simular múltiples pasajeros intentando reservar al mismo tiempo
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            Thread pasajero = new Thread(() -> sistemaReserva.reservarVuelo(vuelo, new Pasajero("Pasajero " + finalI)));
            pasajero.start();
        }
    }
}
