package com.example.dao;

import org.jdbi.v3.core.Jdbi;

public class JdbiConnection {
    private Jdbi jdbi;
    private static JdbiConnection instance;

    private JdbiConnection() {
        //ovo ispod u getenv su nase varijable koje smo kreirali da bi ih getovali u ovoj klasi
        //znaci navedemo ime i vrednost u enviroments variables ...Mogu proveriti...
        String jdbc_str = System.getenv("JDBI_CONNECTION") == null ?
                "jdbc:mysql://localhost:3306/test" : System.getenv("JDBI_CONNECTION");
        //znaci ako nemamo konekciju prvo a u suprotnom pozovemo ovu vrednost(drugo)...

        String jdbc_user = System.getenv("JDBI_USER") == null ?
                "root" : System.getenv("JDBI_USER");
        String jdbc_password = System.getenv("JDBI_PASSWORD") == null ?
                "" : System.getenv("JDBI_PASSWORD");

        this.jdbi = Jdbi.create(jdbc_str, jdbc_user, jdbc_password);
        //znaci iako koristimo Jadmin ("1") user account ovde ostavljamo default vresnosti i bazu test koju ce
        //napraviti kao default ako bude sta vec
    }

    public static Jdbi get() {
        //vraca nam Jdbi
        if(instance == null) {
            //ako nismo kreiralli nikakvu instancu sad je treba kreirati
            instance = new JdbiConnection();
        }
        return instance.jdbi;
        //this.jdbi   iznad valjda ima nekee veze
    }
}
