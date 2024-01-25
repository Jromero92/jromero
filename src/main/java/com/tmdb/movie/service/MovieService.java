import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MovieService {
    @Value("${tmdb.api.key}") // Asegúrate de configurar esta propiedad en tu application.properties o application.yml
    private String tmdbApiKey;

    private final WebClient.Builder webClientBuilder;

    public MovieService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Cacheable("movies")
    public Movie getMovieByName(String name) {
        String apiUrl = "https://api.themoviedb.org/3/search/movie";
        return webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("api_key", tmdbApiKey)
                        .queryParam("query", name)
                        .build())
                .retrieve()
                .bodyToMono(Movie.class)
                .block();
    }
}
