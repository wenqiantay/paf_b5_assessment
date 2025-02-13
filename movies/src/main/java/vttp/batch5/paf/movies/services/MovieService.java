package vttp.batch5.paf.movies.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;
import vttp.batch5.paf.movies.repositories.MongoMovieRepository;
import vttp.batch5.paf.movies.repositories.MySQLMovieRepository;

@Service
public class MovieService {

  @Autowired
  private MySQLMovieRepository mySqlMovieRepository;

  @Autowired
  private MongoMovieRepository mongoMovieRepository;

  // TODO: Task 2
  private static final String MOVIE_FILE_PATH = "data/movies_post_2010.zip";

  public void checkAndLoadMoviesDatainSql() throws Exception{
    if (!mySqlMovieRepository.isDataLoadedInSQL()) {
      JsonArray filteredMoviesArray= unzipAndReadMoviesFile();
      mySqlMovieRepository.batchInsertMovies(filteredMoviesArray);
      System.out.println("inserted into MySql");
      
    } else {
      System.out.println("Data already loaded in Sql.");
    }
  }

  // public void checkAndLoadMoviesDatainMongo() throws Exception{
  //   if (!mongoMovieRepository.isDataLoadedInMongoDB() ){
  //     JsonArray filteredMoviesArray= unzipAndReadMoviesFile();
  //     mongoMovieRepository.batchInsertMovies(filteredMoviesArray);
  //     System.out.println("inserted into mongo ");
      
  //   } else {
  //     System.out.println("Data already loaded in Mongo.");
  //   }
  // }

  

  private JsonArray unzipAndReadMoviesFile() throws Exception {

    JsonArrayBuilder moviesArrayBuilder = Json.createArrayBuilder();

    String zipFile = "../data/movies_post_2010.zip";
    try (ZipFile zip = new ZipFile(zipFile)) {
      int i = 0;
      for (Enumeration<? extends ZipEntry> e = zip.entries(); e.hasMoreElements();) {
        ZipEntry entry = (ZipEntry) e.nextElement();
        System.out.println(entry);
        System.out.println(i);

        InputStream in = zip.getInputStream(entry);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = reader.readLine()) != null) {
          
          JsonObject movie = Json.createReader(new StringReader(line)).readObject();
          JsonObject processedMovie = imputeMissingValues(movie);

    
          String releaseDateString = processedMovie.getString("release_date", "");
          if (!releaseDateString.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate releaseDate = LocalDate.parse(releaseDateString, formatter);
            if (releaseDate.getYear() >= 2018) {
              moviesArrayBuilder.add(processedMovie);
            }
          }
        }

      }

    }

    return moviesArrayBuilder.build();

  }

  private JsonObject imputeMissingValues(JsonObject movie) {

    JsonObjectBuilder updatedMovie = Json.createObjectBuilder();
    for (String key : movie.keySet()) {
      
      if (movie.isNull(key)) {
        updatedMovie.add(key, getDefaultValueForType(key));
      } else {
        updatedMovie.add(key, movie.get(key));
      }
    }

    return updatedMovie.build();
  }

  private JsonValue getDefaultValueForType(String key) {

    switch (key) {
      case "release_date":
      case "title":
      case "overview":
      case "genres":
      case "spoken_languages":
      case "casts":
      case "director":
      case "tagline":
        return Json.createValue("");
      case "vote_average":
      case "vote_count":
      case "revenue":
      case "runtime":
      case "budget":
      case "popularity":
      case "imdb_rating":
      case "imdb_votes":
        return Json.createValue(0);
      case "status":
      case "original_language":
        return Json.createValue(""); 
      case "is_active": 
        return Json.createValue("false");
      default:
        return Json.createValue("");
    }
  }

  // TODO: Task 3
  // You may change the signature of this method by passing any number of
  // parameters
  // and returning any type
  public void getProlificDirectors() {

  }

  // TODO: Task 4
  // You may change the signature of this method by passing any number of
  // parameters
  // and returning any type
  public void generatePDFReport() {

  }

}
