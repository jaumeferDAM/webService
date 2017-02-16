/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

/**
 *
 * @author Lluis_2
 */
public class Autobus {
    private String matricula;
    private String password;
    private String fecha;

    public Autobus() {
    }

    public Autobus(String fecha, String matricula, String password) {
        this.fecha = fecha;
        this.matricula = matricula;
        this.password = password;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getPassword() {
        return password;
    }
}
