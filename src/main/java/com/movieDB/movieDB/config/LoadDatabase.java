package com.movieDB.movieDB.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.*;
import com.movieDB.movieDB.model.*;
import com.movieDB.movieDB.repositories.GenreRepository;
import com.movieDB.movieDB.repositories.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Configuration
class LoadDatabase {
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    private final String API_KEY = "77832adee6b2234e1c947a27955ac49e";

    private String movieURI = "https://api.themoviedb.org/3/discover/movie?language=en-US&sort_by=popularity.desc&" +
            "include_video=false&vote_count.gte=100&vote_average.gte=8&with_original_language=en&include_adult=false&" +
            "with_watch_monetization_types=flatrate&api_key=" + API_KEY + "&page=1";

    private String genreURI = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + API_KEY + "&language=en-US";

    @Bean
    CommandLineRunner initDatabase(MovieRepository movieRepository, GenreRepository genreRepository) {
        return args -> {
//            populate genre table
            GenreListAPIResponse genreList = REST_TEMPLATE.getForObject(genreURI, GenreListAPIResponse.class);
            for ( GenreAPIResponse genreResponse : genreList.getGenres()) {
                if (!(genreResponse == null)) {
                    Genre genre = Genre.builder()
                            .id(genreResponse.getId())
                            .name(genreResponse.getName())
                            .build();
                    genreRepository.saveAndFlush(genre);
                }
            }


//            populate movie table
            MovieListAPIResponse resultListFirstPage = REST_TEMPLATE.getForObject(movieURI + 1, MovieListAPIResponse.class);

            for (int i = 1; i <= Objects.requireNonNull(resultListFirstPage).getTotal_pages(); i++) {
                String movieURIPage = movieURI + i;
                MovieListAPIResponse resultList = REST_TEMPLATE.getForObject(movieURIPage, MovieListAPIResponse.class);

                for (MovieAPIResponse movieResponse : resultList.getResults()) {
                    if (!(movieResponse == null)) {

                        List<Genre> movieGenreList = new ArrayList<>();
                        for (int j = 0; j < movieResponse.getGenre_ids().size(); j++) {
                            movieGenreList.add(genreRepository.findById(movieResponse.getGenre_ids().get(j)).get());
                        }
                        System.out.println("---------------------");
                        System.out.println(movieResponse.getVoteCount());
                        System.out.println(movieResponse.getVoteAverage());
                        System.out.println(movieResponse.getPopularity());
                        System.out.println(movieResponse.getTitle());
                        System.out.println(movieResponse);
                        System.out.println("---------------------");
                        Movie movie = Movie.builder()
                                .language(movieResponse.getOriginal_language())
                                .overview(movieResponse.getOverview().replaceAll("[^a-zA-Z0-9 ]", ""))
                                .title(movieResponse.getTitle().replaceAll("[^a-zA-Z0-9 ]", ""))
                                .voteCount(Integer.parseInt(String.valueOf(movieResponse.getVoteCount())))
                                .voteAverage(movieResponse.getVoteAverage())
                                .releaseDate(movieResponse.getRelease_date())
                                .popularity(Integer.parseInt(String.valueOf(movieResponse.getPopularity())))
                                .genres(movieGenreList)
                                .build();
                        movieRepository.saveAndFlush(movie);
                    }
                }
            }
        };
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        objectMapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(DeserializationFeature.ACCEPT_FLOAT_AS_INT);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        return objectMapper;
    }

}