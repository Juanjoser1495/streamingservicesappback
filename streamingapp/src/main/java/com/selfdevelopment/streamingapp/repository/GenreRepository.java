package com.selfdevelopment.streamingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.selfdevelopment.streamingapp.entity.customqueries.RankingTop10;
import com.selfdevelopment.streamingapp.entity.database.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	@Query(value ="select unionTables.moviename,unionTables.imageurl,g.genre from (select m.moviename,m.imageurl,m.genreid  from movie m "
			+ "union "
			+ "select s.nameserie,s.imageurl,s.genreid  from serie s) unionTables "
			+ "inner join genre g on unionTables.genreid = g.idgenre "
			+ "where upper(g.genre) = upper(:category);", nativeQuery = true)
	List<RankingTop10> findTop10ByGenre(@Param("category") String category);

}
