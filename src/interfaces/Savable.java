package interfaces;


import java.io.File;
import java.io.IOException;

public interface Savable {
    public void saveGame(String fileName) throws IOException;
}
