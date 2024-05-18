import java.io.IOException;

public class StarterPoint {

    public static void main(String[] args) {
        Utils obj = new Utils("res/meteor_challenge_01.png");
        obj.loadImage();
        System.err.println("W: " + obj.getImg().getWidth() + ", H: " + obj.getImg().getHeight());
        PixelAnalysis pa = new PixelAnalysis();
        try {
            pa.countingPixels(obj.getImg());

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Number of Stars: " + pa.getStars() + "\n" +
                "Number of Meteors: " + pa.getMeteors() + "\n" +
                "Meteors falling on the Water: " + pa.getMeteorsOnWater() + "\n");
    }
}
