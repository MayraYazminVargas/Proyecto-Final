package Clases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

    public boolean id(String id) {
        Pattern pat = Pattern.compile("^([A-Z]{1}[0-9]{4})");
        Matcher mat = pat.matcher(id);
        if (id.length() == 5) {
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean nombre(String nombre) { //funciona en apellidos
        Pattern pat = Pattern.compile("^[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s]+$");
        Matcher mat = pat.matcher(nombre);
        if (nombre.length() <= 50) {
            if (mat.find()) {
                return true;
            } else if (nombre.equals("X � A-12")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean correo(String correo) {
        if (correo.equals("")) {
            return true;
        }
        Pattern pat = Pattern.compile("^[A-Z_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[A-Z_a-z0-9-]+)*(.[A-Z_a-z]{2,4})$");
        Matcher mat = pat.matcher(correo);
        if (correo.length() <= 50) {
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean direccion(String direccion) { //funciona en colonia, ciudad tal vrz estado
        Pattern pat = Pattern.compile("([A-Za-z0-9áéíóúÁÉÍÚÓñÑ#.]+[\\s]*)+$");
        Matcher mat = pat.matcher(direccion);
        if (direccion.length() <= 50) {
            if (mat.matches()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean tel(String tel) {

        long telefono = 0;
        try {
            telefono = Long.parseLong(tel);
            if (tel.length() == 12 || tel.length() == 10) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean numero(String num) {

        if ((num).length() < 5) {

            return true;
        }
        return false;
    }

    public boolean fecha(String f) {
        if (f.length() <= 8) {
            return true;
        }
        return false;
    }

    public boolean dinero(String money) { //funciona en total, subtotal. precio
        try {
            Float.parseFloat(money);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public boolean cantidad(String scant) { //existencia y cantidad
        int cant;
        try {
            cant = Integer.parseInt(scant);
            if (cant <= 5000 && cant >= 0) {
                return true;
            }
        } catch (NumberFormatException nfe) {
        }
        return false;
    }

    public boolean existencia(String e) { //existencia y cantidad
        int cant;
        try {
            cant = Integer.parseInt(e);
            if (cant <= 5000 && cant >= 0) {
                return true;
            }
        } catch (NumberFormatException nfe) {
        }
        return false;
    }

}
