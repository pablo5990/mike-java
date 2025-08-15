import java.util.*;

class Libro {
    String titulo, autor, codigo;
    boolean disponible = true;

    public Libro(String titulo, String autor, String codigo) {
        this.titulo = titulo; this.autor = autor; this.codigo = codigo;
    }

    void mostrarDatos() {
        System.out.println(titulo + " | " + autor + " | " + codigo + " | " + (disponible ? "Disponible" : "Prestado"));
    }

    void marcarPrestado() { disponible = false; }
    void marcarDisponible() { disponible = true; }
}

class Usuario {
    String nombre, id;
    ArrayList<Libro> prestados = new ArrayList<>();

    public Usuario(String nombre, String id) {
        this.nombre = nombre; this.id = id;
    }

    void mostrarDatos() {
        System.out.println(nombre + " | ID: " + id + " | Libros prestados: " + prestados.size());
    }

    boolean agregarPrestamo(Libro libro) {
        if (prestados.size() >= 3) {
            System.out.println("❌ Máximo de 3 libros prestados.");
            return false;
        }
        prestados.add(libro);
        return true;
    }

    void devolverLibro(Libro libro) { prestados.remove(libro); }
}

class Prestamo {
    Usuario usuario; Libro libro;
    Date fechaLimite;

    public Prestamo(Usuario usuario, Libro libro) {
        this.usuario = usuario; this.libro = libro;
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7); // 7 días de préstamo
        fechaLimite = cal.getTime();
    }

    long calcularRetraso() {
        long diff = new Date().getTime() - fechaLimite.getTime();
        return Math.max(diff / (1000 * 60 * 60 * 24), 0);
    }

    void mostrarDatos() {
        System.out.println(usuario.nombre + " tiene '" + libro.titulo + "' hasta " + fechaLimite);
    }
}

class Biblioteca {
    ArrayList<Libro> libros = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();
    ArrayList<Prestamo> prestamos = new ArrayList<>();

    void registrarLibro(String t, String a, String c) {
        libros.add(new Libro(t, a, c));
    }

    void registrarUsuario(String n, String id) {
        usuarios.add(new Usuario(n, id));
    }

    void mostrarLibrosDisponibles() {
        for (Libro l : libros) if (l.disponible) l.mostrarDatos();
    }

    void mostrarUsuarios() {
        for (Usuario u : usuarios) u.mostrarDatos();
    }

    void prestarLibro(String idUsuario, String codigoLibro) {
        Usuario u = buscarUsuario(idUsuario);
        Libro l = buscarLibro(codigoLibro);
        if (u == null || l == null || !l.disponible) {
            System.out.println("❌ Usuario o libro no válido.");
            return;
        }
        if (u.agregarPrestamo(l)) {
            l.marcarPrestado();
            prestamos.add(new Prestamo(u, l));
            System.out.println("✅ Libro prestado.");
        }
    }

    void devolverLibro(String idUsuario, String codigoLibro) {
        Usuario u = buscarUsuario(idUsuario);
        Libro l = buscarLibro(codigoLibro);
        Prestamo p = buscarPrestamo(u, l);
        if (u == null || l == null || p == null) {
            System.out.println("❌ Datos incorrectos.");
            return;
        }
        long diasRetraso = p.calcularRetraso();
        if (diasRetraso > 0) System.out.println("⚠ Multa: $" + (diasRetraso * 500));
        u.devolverLibro(l);
        l.marcarDisponible();
        prestamos.remove(p);
        System.out.println("✅ Libro devuelto.");
    }

    void mostrarHistorialPrestamos() {
        for (Prestamo p : prestamos) p.mostrarDatos();
    }

    Usuario buscarUsuario(String id) {
        for (Usuario u : usuarios) if (u.id.equals(id)) return u;
        return null;
    }

    Libro buscarLibro(String codigo) {
        for (Libro l : libros) if (l.codigo.equals(codigo)) return l;
        return null;
    }

    Prestamo buscarPrestamo(Usuario u, Libro l) {
        for (Prestamo p : prestamos) if (p.usuario == u && p.libro == l) return p;
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca b = new Biblioteca();

        // 📌 Datos iniciales
        b.registrarLibro("El Quijote", "Cervantes", "L1");
        b.registrarLibro("Cien Años de Soledad", "Gabo", "L2");
        b.registrarLibro("El Principito", "Saint-Exupéry", "L3");
        b.registrarUsuario("Juan Pablo", "U1");
        b.registrarUsuario("Laura", "U2");

        int op;
        do {
            System.out.println("\n--- Menú Biblioteca ---");
            System.out.println("1. Mostrar libros disponibles");
            System.out.println("2. Mostrar usuarios");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar historial de préstamos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1: b.mostrarLibrosDisponibles(); break;
                case 2: b.mostrarUsuarios(); break;
                case 3:
                    System.out.print("ID Usuario: "); String idU = sc.nextLine();
                    System.out.print("Código Libro: "); String codL = sc.nextLine();
                    b.prestarLibro(idU, codL); break;
                case 4:
                    System.out.print("ID Usuario: "); idU = sc.nextLine();
                    System.out.print("Código Libro: "); codL = sc.nextLine();
                    b.devolverLibro(idU, codL); break;
                case 5: b.mostrarHistorialPrestamos(); break;
                case 0: System.out.println("👋 Saliendo..."); break;
                default: System.out.println("❌ Opción inválida.");
            }
        } while (op != 0);
    }
}