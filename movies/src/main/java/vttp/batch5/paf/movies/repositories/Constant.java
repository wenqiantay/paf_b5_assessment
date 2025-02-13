package vttp.batch5.paf.movies.repositories;

public class Constant {

    public static final String SQL_INSERT= "INSERT INTO imdb (imdb_id, vote_average, vote_count, release_date, budget, revenue, runtime) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?) " +
                 "ON DUPLICATE KEY UPDATE " +
                 "vote_average = VALUES(vote_average), " +
                 "vote_count = VALUES(vote_count), " +
                 "release_date = VALUES(release_date), " +
                 "budget = VALUES(budget), " +
                 "revenue = VALUES(revenue), " +
                 "runtime = VALUES(runtime)";
    
}
