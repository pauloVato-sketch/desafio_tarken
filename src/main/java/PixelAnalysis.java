import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PixelAnalysis {
    private int numStars;
    private int numMeteors;
    private List<Position> stars;
    private List<Position> meteors;
    private List<Position> water;

    private String phrase;

    public String getPhrase() {
        return phrase;
    }

    public PixelAnalysis() {
        this.stars = new ArrayList<Position>();
        this.meteors = new ArrayList<Position>();
        this.water = new ArrayList<Position>();
        this.phrase = "";
    }

    public int getStars() {
        return numStars;
    }

    public int getMeteors() {
        return numMeteors;
    }

    public void countingPixels(BufferedImage img) throws IOException {
        int width = img.getWidth();
        int height = img.getHeight();
        int[] pixels = img.getRGB(0, 0, width, height, null, 0, width);
        Color temp;
        FileWriter myWriter = new FileWriter("output.txt");
        for (int lin = 0; lin < height; lin++) {
            for (int col = 0; col < width; col++) {
                temp = new Color(pixels[width * lin + col]);

                try {
                    myWriter.write(temp.toString() + "\n");
                } catch (IOException e) {
                    System.err.println("Erro ao escrever no arquivo de output.");
                    e.printStackTrace();
                }
                if (temp.equals(Color.WHITE)) {
                    this.numStars++;
                    this.stars.add(new Position(lin, col));
                } else if (temp.equals(Color.RED)) {
                    this.numMeteors++;
                    this.meteors.add(new Position(lin, col));
                } else if (temp.equals(Color.BLUE)) {
                    this.water.add(new Position(lin, col));
                }
            }
        }
        myWriter.close();
    }

    public List<Integer> unique = new ArrayList<>();

    public int getMeteorsOnWater() {
        int count_meteor = 0;
        boolean resultFromY = false;
        Position temp_meteorPosition;
        for (int i = 0; i < this.meteors.size(); i++) {
            temp_meteorPosition = this.meteors.get(i);
            resultFromY = findByY(temp_meteorPosition);
            if (resultFromY == true) {
                count_meteor++;
                resultFromY = false;
            }
        }
        return count_meteor;
    }

    public int getStarsOnWater() {
        int count_stars = 0;
        boolean resultFromY = false;
        Position temp_starPosition;
        for (int i = 0; i < this.stars.size(); i++) {
            temp_starPosition = this.stars.get(i);
            resultFromY = findByY(temp_starPosition);
            if (resultFromY == true) {
                count_stars++;
                resultFromY = false;
            }
        }
        return count_stars;
    }

    public boolean findByY(Position meteorPos) {
        return this.water.stream().filter(waterPixel -> waterPixel.getY() == meteorPos.getY()).findFirst().isPresent();
    }

}
