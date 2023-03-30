import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static java.awt.Graphics.*;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura de imagem usando a interface imputStream ( polimorfismo )

        // InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
       BufferedImage imagemOriginal = ImageIO.read(inputStream);
           // cria nova imagem em memoria com transparencia e com tamanho novo

        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaaltura = altura + 200;

        BufferedImage novaImagem = new BufferedImage(largura, novaaltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para uma nova imagem (em memoria)

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,0, 0, null);

        // configurar a fonte

        Font font = new Font(Font.SANS_SERIF,Font.BOLD,100);
        graphics.setColor(Color.ORANGE);
        graphics.setFont(font);
        // escrever uma frase na imagem

        graphics.drawString("T O P -- 1 0 ", 0, novaaltura-100);

        // escrever a nova imagem em um arquivo

        ImageIO.write(novaImagem,"png", new File(nomeArquivo));
    }

}

