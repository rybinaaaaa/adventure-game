package rybina.Helpers.Animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Animation {
    public static class NodeImage {
        public BufferedImage image;
        public NodeImage prevNode;
        public NodeImage nextNode;

        public NodeImage(BufferedImage image) {
            this.image = image;
        }
    }

    public static NodeImage[] loadAnimation(String src, int width, int height, int row, int columns, int paddingX, int paddingY, Class cl, int count) {
        BufferedImage animation = null;
//        int count = row * columns;
        NodeImage[] animationList = new NodeImage[count];
        try {
            animation = ImageIO.read(cl.getResourceAsStream(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < row; i++) {
            assert animation != null;
            for (int j = 0; j < columns; j++) {
                int currentCount = i * columns + j + 1;
                NodeImage node = new NodeImage(animation.getSubimage(j * (width + paddingX * 2) + paddingX, i * (height + paddingY * 2) + paddingY, width, height));
                animationList[currentCount - 1] = node;
                if (currentCount == count) {
                    break;
                }
            }
        }

        for (int i = 0; i < count; i++) {
            if (i != 0) {
                animationList[i].prevNode = animationList[i - 1];
            } else {
                animationList[i].prevNode = animationList[count - 1];
            }
            if (i != count - 1) {
                animationList[i].nextNode = animationList[i + 1];
            } else {
                animationList[i].nextNode = animationList[0];
            }
        }
//        logger.info("Animations (src = "+ src +") for" + getClass().getName() + "has been loaded;");
        return animationList;
    }

    public static NodeImage[] loadAnimation(String src, int width, int height, int row, int columns, int paddingX, int paddingY, Class cl) {
        return loadAnimation(src, width, height, row, columns, paddingX, paddingY, cl, row * columns);
    }
}
