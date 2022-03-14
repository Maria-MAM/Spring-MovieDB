package com.movieDB.movieDB.config;

import com.movieDB.movieDB.exception.GenreNotFoundException;
import com.movieDB.movieDB.exception.MovieNotFoundException;
import com.movieDB.movieDB.model.Genre;
import com.movieDB.movieDB.model.Movie;
import org.springframework.data.domain.Page;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopulatePageableResponseForRequest<T> {

    public T t;

    public Map<String, Object> populateResponse(Page<T> page) {

        List<T> items = page.getContent();

        Map<String, Object> response = new HashMap<>();
        response.put("entries", items);
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());

        // TODO: check type of items to return proper exception
//        if (items.size() == 0) {
//            if ( items.get(0) instanceof Movie) {
//                throw new MovieNotFoundException();
//            } else if (items.get(0) instanceof Genre) {
//                throw new GenreNotFoundException();
//            }
//        }

        return response;
    }

}
