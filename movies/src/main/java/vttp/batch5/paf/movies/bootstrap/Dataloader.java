package vttp.batch5.paf.movies.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vttp.batch5.paf.movies.services.MovieService;

@Component
public class Dataloader implements CommandLineRunner{

  @Autowired
  private MovieService movieService;
  //TODO: Task 2

  @Override
  public void run(String ...args) throws Exception{
    System.out.println("Trying to load data...");
    movieService.checkAndLoadMoviesDatainSql();
    // movieService.checkAndLoadMoviesDatainMongo();
    System.out.println("successful");
  }

}
