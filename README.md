# Spring-MovieDB

Populate a DB with entries from The Movie Database API - https://developers.themoviedb.org/3/getting-started/introduction

<div class="filebrowser-readme-content markup-content markup"><h4>Movie application</h4>
<ul><li><a rel="nofollow" href="#features">Features</a>
<ul><li><a rel="nofollow" href="#Movie">Movie</a></li>
<li><a rel="nofollow" href="#genre">Genre</a></li>
</ul>
</li></ul>
<h2>Features</h2>
<h3>Movie</h3>
<table>
<thead><tr><th>Operation</th><th align="center">Description</th>
<th align="right">Example</th><th align="right">Request body</th>
</tr></thead><tbody>
<tr>
<td>GET</td>
<td align="center">Get all movies</td>
<td align="right">localhost:8080/movies/all</td>
<td align="right">-</td></tr><tr><td>GET</td>
<td align="center">Get a movie by title</td>
<td align="right">localhost:8080/movies/searchByTitle?movieTitle=Harry Potter</td>
<td align="right">-</td></tr><tr><td>GET</td>
<td align="center">Get a movie by title and genre</td>
<td align="right">localhost:8080/movies/searchByTitleAndGenre?movieTitle=spider&genre=action</td>
<td align="right">-</td></tr></tbody></table>
<h3>Genre</h3>
<table>
<thead><tr><th>Operation</th><th align="center">Description</th>
<th align="right">Example</th><th align="right">Request body</th>
</tr></thead><tbody><tr><td>GET</td><td align="center">Get a genre by name</td>
<td align="right">localhost:8080/genres/searchByName?genreName=documentary</td>
<td align="right">-</td></tr></tbody></table></div>