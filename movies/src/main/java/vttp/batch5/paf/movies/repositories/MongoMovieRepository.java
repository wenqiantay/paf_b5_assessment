package vttp.batch5.paf.movies.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MongoMovieRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean isDataLoadedInMongoDB() {
        long count = mongoTemplate.count(new Query(), "imdb");
        return count > 0;
    }



 // TODO: Task 2.3
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
// //  db.movies.updateOne(
//   { "imdbid": "tt1234567" },
//   {
//     $set: {
//       "title": "Movie Title",
//       "directors": ["Director 1", "Director 2"],
//       "overview": "A great movie.",
//       "tagline": "The best movie ever.",
//       "genres": ["Action", "Thriller"],
//       "imdbRating": 7.8,
//       "imdbVotes": 150000
//     }
//   },
//   { upsert: true }
// );
 //
//  public void batchInsertMovies(JsonArray jsonArray) throws ParseException {
//     final int batchSize = 25;
//     List<Movie> batch = new ArrayList<>();

//     for (int i = 0; i < jsonArray.size(); i++) {
//         JsonObject jsonObject = jsonArray.getJsonObject(i);

//         Movie movie = new Movie();
//         movie.setImdbid(jsonObject.getString("imdb_id", null)); 
//         movie.setTitle(jsonObject.getString("title", ""));
        
//         String directors = jsonObject.getString("director", "");
//         movie.setDirectors(directors.isEmpty() ? Collections.emptyList() : new ArrayList<>(List.of(directors.split(","))));

//         movie.setOverview(jsonObject.getString("overview", ""));
//         movie.setTagline(jsonObject.getString("tagline", ""));
        
       
//         String genres = jsonObject.getString("genres", "");
//         movie.setGenres(genres.isEmpty() ? Collections.emptyList() : new ArrayList<>(List.of(genres.split(","))));

//         movie.setImdbRating(jsonObject.getJsonNumber("imdb_rating") != null ? jsonObject.getJsonNumber("imdb_rating").doubleValue() : 0.0);
//         movie.setImdbVotes(jsonObject.getInt("imdb_votes", 0)); 

//         batch.add(movie);

       
//         if (batch.size() == batchSize || i == jsonArray.size() - 1) {

//             for (Movie movieItem : batch) {
//                 Query query = new Query(Criteria.where("imdb_id").is(movieItem.getImdbid()));

//                 Update update = new Update()
//                         .set("title", movieItem.getTitle())
//                         .set("directors", movieItem.getDirectors())
//                         .set("overview", movieItem.getOverview())
//                         .set("tagline", movieItem.getTagline())
//                         .set("genres", movieItem.getGenres())
//                         .set("imdbRating", movieItem.getImdbRating())
//                         .set("imdbVotes", movieItem.getImdbVotes());

               
//                 mongoTemplate.upsert(query, update, Movie.class);
//             }

            
//             batch.clear();
//         }
//     }

    
    

    



 

 // TODO: Task 2.4
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //
 public void logError() {

 }

}

 // TODO: Task 3
 // Write the native Mongo query you implement in the method in the comments
 //
 //    db.movies.aggregate([
//   {
//     $group: {
//       _id: "$director",  
//       movie_count: { $sum: 1 },  
//       total_revenue: { $sum: "$revenue" },  
//       total_budget: { $sum: "$budget" } 
//     }
//   },
//   {
//     $project: {
//       movie_count: 1,  
//       total_revenue: 1,  
//       total_budget: 1, 
//       profit_or_loss: { $subtract: ["$total_revenue", "$total_budget"] }
//     }
//   },
//   {
//     $sort: { movie_count: -1 }
//   },
//   {
//     $limit: 5  // Limit the number of results to the specified limit
//   }
// ]);
//  public List<DirectorStats> getProlificDirectors(int limit) {
        
//         Aggregation aggregation = Aggregation.newAggregation(
//                 Aggregation.group("director")  
//                         .count().as("movie_count") 
//                         .sum("revenue").as("total_revenue") 
//                         .sum("budget").as("total_budget") 
//                         .project("movie_count", "total_revenue", "total_budget")
//                         .andExpression("total_revenue - total_budget").as("profit_or_loss"), 
//                 Aggregation.sort(Sort.by(Sort.Order.desc("movie_count"))), 
//                 Aggregation.limit(limit) 
//         );

//         // Execute the aggregation
//         AggregationResults<DirectorStats> result = mongoTemplate.aggregate(aggregation, "movies", DirectorStats.class);

//         // Return the results
//         return result.getMappedResults();
//     }
 


