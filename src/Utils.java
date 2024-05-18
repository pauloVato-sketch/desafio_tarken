import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Classe de funções de utilidade, como abertura e leitura do arquivo.
 */
public class Utils {

    private String filename;
    private BufferedImage img;

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public Utils(String filename) {
        this.filename = filename;
    }

    public void loadImage() {
        try {
            File meteorFile = new File(this.filename);

            img = ImageIO.read(meteorFile);
        } catch (FileNotFoundException file_e) {
            System.err.println("Erro ao encontrar o arquivo " + this.filename);
            file_e.printStackTrace();
        } catch (IOException image_e) {
            System.err.println("Erro ao ler a imagem: ImageIO");
            image_e.printStackTrace();
        }

    }

}