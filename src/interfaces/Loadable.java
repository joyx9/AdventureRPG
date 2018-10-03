package interfaces;


import model.ListOfPlayers;

import java.io.IOException;

public interface Loadable {
    public void load(String fileName) throws IOException;
}
