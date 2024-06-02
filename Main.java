public class SistemaReservaVuelos {
    private Vuelo vuelo;
    
    public SistemaReservaVuelos(Vuelo vuelo) {
        this.vuelo = vuelo;
    }
    
    public synchronized void reservarVuelo(Vuelo vuelo, Pasajero pasajero) {
        if (vuelo.reservarAsiento()) {
            System.out.println("Asiento reservado para " + pasajero.getNombre());
        } else {
            System.out.println("No hay asientos disponibles para " + pasajero.getNombre());
        }
    }
}

public class Vuelo {
    private int numeroVuelo;
    private int asientosDisponibles;
    
    public Vuelo(int numeroVuelo, int asientosDisponibles) {
        this.numeroVuelo = numeroVuelo;
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

public class Pasajero {
    private String nombre;
    private String numeroPasaporte;
    
    public Pasajero(String nombre, String numeroPasaporte) {
        this.nombre = nombre;
        this.numeroPasaporte = numeroPasaporte;
    }
    
    public String getNombre() {
        return nombre;
    }
}

public class Main {
    public static void main(String[] args) {
        Vuelo vuelo = new Vuelo(123, 50);
        SistemaReservaVuelos sistemaReserva = new SistemaReservaVuelos(vuelo);
        
        Thread pasajero1 = new Thread(() -> sistemaReserva.reservarVuelo(vuelo, new Pasajero("Alice", "ABCD123")));
        Thread pasajero2 = new Thread(() -> sistemaReserva.reservarVuelo(vuelo, new Pasajero("Bob", "EFGH456")));
        
        pasajero1.start();
        pasajero2.start();
    }
}
