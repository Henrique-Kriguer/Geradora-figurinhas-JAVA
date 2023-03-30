import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTPP e requisitar um serviço:

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();
        //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2023-03-23&end_date=2023-03-25";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        ClienteHttp cliente = new ClienteHttp();
        String json = cliente.buscaDados(url);

        // exibir e manipular os dados:


        List<Conteudo> conteudos  = extrator.extraiConteudo(json);
        GeradoraDeFigurinhas geradoraDeFigurinhas = new GeradoraDeFigurinhas();

        for ( int i = 0; i < 3; i++){
               Conteudo conteudo = conteudos.get(i);
               InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();

                String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

                geradoraDeFigurinhas.cria(inputStream,nomeArquivo);
                System.out.println(conteudo.getTitulo());
        }
    }
}