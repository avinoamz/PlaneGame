
import java.awt.image.BufferedImage;
import static java.awt.image.ImageObserver.ABORT;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author avino
 */
public class ImageHandler {

    private BufferedImage boatImage;
    private BufferedImage planeImage;
    private BufferedImage parachutistImage;
    private BufferedImage seaImage;
    private BufferedImage backgroundImage;

    private String BOAT_IMAGE = "/boat.png";
    private String PLANE_IMAGE = "/plane.png";
    private String PARACHUTIST_IMAGE = "/parachutist.png";
    private String SEA_IMAGE = "/sea.png";
    private String BACKGROUND_IMAGE = "/background.png";

    ImageHandler() {
        try {
            boatImage = ImageIO.read(ImageHandler.class.getResourceAsStream(BOAT_IMAGE));
            planeImage = ImageIO.read(ImageHandler.class.getResourceAsStream(PLANE_IMAGE));
            parachutistImage = ImageIO.read(ImageHandler.class.getResourceAsStream(PARACHUTIST_IMAGE));
            seaImage = ImageIO.read(ImageHandler.class.getResourceAsStream(SEA_IMAGE));
            backgroundImage = ImageIO.read(ImageHandler.class.getResourceAsStream(BACKGROUND_IMAGE));

        } catch (IOException e) {
            System.out.println("Error loading images");
            System.exit(ABORT);
        }
    }

    public void repaint() {
        GameData.getInstance().getScreenManager().repaint();
    }

    public BufferedImage getImageByType(GameObject gameObject) {
        switch (gameObject.getType()) {
            case PARACHUTIST:
                return parachutistImage;
            case BOAT:
                return boatImage;
            case PLANE:
                return planeImage;
            default:
                System.out.println("Unknown type");
                return null;
        }
    }

    public BufferedImage getBoatImage() {
        return boatImage;
    }

    public void setBoatImage(BufferedImage boatImage) {
        this.boatImage = boatImage;
    }

    public BufferedImage getPlaneImage() {
        return planeImage;
    }

    public void setPlaneImage(BufferedImage planeImage) {
        this.planeImage = planeImage;
    }

    public BufferedImage getParachutistImage() {
        return parachutistImage;
    }

    public void setParachutistImage(BufferedImage parachutistImage) {
        this.parachutistImage = parachutistImage;
    }

    public BufferedImage getSeaImage() {
        return seaImage;
    }

    public void setSeaImage(BufferedImage seaImage) {
        this.seaImage = seaImage;
    }

    public BufferedImage getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

}
