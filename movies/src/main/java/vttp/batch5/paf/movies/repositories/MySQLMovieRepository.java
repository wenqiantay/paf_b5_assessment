package vttp.batch5.paf.movies.repositories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

@Repository
public class MySQLMovieRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public boolean isDataLoadedInSQL() {
    String sqlCheckQuery = "SELECT COUNT(*) FROM imdb";
    Integer count = jdbcTemplate.queryForObject(sqlCheckQuery, Integer.class);
    return count != null && count > 0;
}


  // TODO: Task 2.3
  // You can add any number of parameters and return any type from the method
  public void batchInsertMovies(JsonArray jsonArray) throws ParseException {

    String sql = Constant.SQL_INSERT;


        final int batchSize = 25;
        List<Object[]> batchArgs = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        

        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject jsonObject = jsonArray.getJsonObject(i);

            Date releaseDate = new Date(dateFormat.parse(jsonObject.getString("release_date")).getTime());
            Object[] movieParams = new Object[]{
              jsonObject.getString("imdb_id"),
              (float) jsonObject.getJsonNumber("vote_average").doubleValue(), 
              jsonObject.getInt("vote_count"),
              new java.sql.Date(releaseDate.getTime()), 
              jsonObject.getJsonNumber("budget").doubleValue(),
              jsonObject.getJsonNumber("revenue").doubleValue(),
              jsonObject.getInt("runtime")
          };
  
          batchArgs.add(movieParams);
  
            if (batchArgs.size() == batchSize || i == jsonArray.size() - 1) {
              try {
                
                jdbcTemplate.batchUpdate(sql, batchArgs);
                
              } catch (Exception e) {

                e.printStackTrace();

              } finally {

                batchArgs.clear();
              }
            }
        }
   
      }
    }
  
  // TODO: Task 3



