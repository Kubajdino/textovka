package prikazy;

import hra.Hra;

public class Konec implements Command{

    public Konec() {
    }

    @Override
    public String execute(String argument, Hra hra) {
        System.out.println("Hra ukončena.");
        System.exit(0);
        return null;
    }
}
