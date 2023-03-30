import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoIMDB implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudo(String json){

        // extrair só os dados que interessam ( Título, poster, classificação)

        JasonParser parser = new JasonParser();
        List<Map<String, String>> listadeConteudos = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        for(Map<String, String> atributos : listadeConteudos ){

            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image")
                    .replaceAll("(@+)(.*).jpg$","$1.jpg");
            // replaceAll remove a limitação do tamanho da imagem;

            Conteudo conteudo = new Conteudo(titulo, urlImagem);
            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
