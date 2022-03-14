# Spring-MovieDB

Populate a DB with entries from The Movie Database API - https://developers.themoviedb.org/3/getting-started/introduction

<div class="filebrowser-readme-content markup-content markup">
<h4>Movie application</h4>
<ul><li><a rel="nofollow" href="#features">Features</a>
<ul><li><a rel="nofollow" href="#Movie">Movie</a></li>
<li><a rel="nofollow" href="#genre">Genre</a></li>
</ul>
</li></ul>
<h2>Features</h2>
<h3>Movie</h3>
<table>
<thead><tr><th>Operation</th><th>Description</th>
<th>Example</th><th>Request body</th>
</tr></thead><tbody>
<tr>
<td>GET</td>
<td>Get all movies</td>
<td>localhost:8080/movies/all</td>
<td>-</td></tr><tr><td>GET</td>
<td>Get a movie by title</td>
<td>localhost:8080/movies/searchByTitle?movieTitle=Harry Potter</td>
<td>-</td></tr><tr><td>GET</td>
<td>Get a movie by title and genre</td>
<td>localhost:8080/movies/searchByTitleAndGenre?movieTitle=spider&genre=action</td>
<td>-</td></tr></tbody></table>
<h3>Genre</h3>
<table>
<thead><tr><th>Operation</th><th>Description</th>
<th>Example</th><th>Request body</th>
</tr></thead><tbody><tr><td>GET</td><td>Get a genre by name</td>
<td>localhost:8080/genres/searchByName?genreName=documentary</td>
<td>-</td></tr></tbody></table></div>