package prikazy;

import hra.Hra;

public interface Command {
    public String execute(String argument, Hra hra);
}
