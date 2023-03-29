import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        // fazer uma conexão HTPP e requisitar um serviço:

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam ( Título, poster, classificação)

        JasonParser parser = new JasonParser();
        List<Map<String, String>> listaFilmes = parser.parse(body);
        System.out.println(listaFilmes.size());
        System.out.println(listaFilmes.get(9));

        // exibir e manipular os dados:

        for (Map<String, String> filme : listaFilmes) {
            String urlImagem = filme.get("image");
            String titulo = filme.get("title");
            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = titulo + ".png";
            GeradoraDeFigurinhas geradoraDeFigurinhas = new GeradoraDeFigurinhas();
            geradoraDeFigurinhas.cria(inputStream,nomeArquivo);
            System.out.println(filme.get("title"));

        }
    }
}