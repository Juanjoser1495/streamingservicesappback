package com.selfdevelopment.streamingapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.selfdevelopment.streamingapp.entity.customqueries.RankingTop10;
import com.selfdevelopment.streamingapp.entity.database.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	@Query(value ="select unionTables.idmovie, unionTables.moviename,unionTables.imageurl,g.genre, unionTables.likes "
			+ "ROW_NUMBER () OVER (ORDER BY unionTables.likes desc) as position from "
			+ "(select m.idmovie, m.moviename,m.imageurl,m.genreid,m.likes  from movie m "
			+ "union "
			+ "select s.idserie, s.nameserie,s.imageurl,s.genreid,s.likes  from serie s) unionTables "
			+ "inner join genre g on unionTables.genreid = g.idgenre "
			+ "where upper(g.genre) = upper(:category)"
			+ "order by unionTables.likes desc, unionTables.moviename "
			+ "limit 10", nativeQuery = true)
	List<RankingTop10> findTop10ByGenre(@Param("category") String category);
	
	@Query(value="select unionTables.idmovie, unionTables.moviename,unionTables.imageurl,g.genre, unionTables.likes, "
			+ "ROW_NUMBER () OVER (ORDER BY unionTables.likes desc) as position from"
			+ "(select m.idmovie, m.moviename,m.imageurl,m.genreid,m.likes  from movie m "
			+ "union "
			+ "select s.idserie, s.nameserie,s.imageurl,s.genreid,s.likes  from serie s) unionTables "
			+ "inner join genre g on unionTables.genreid = g.idgenre "
			+ "order by unionTables.likes desc, unionTables.moviename "
			+ "limit 10",nativeQuery = true)
	List<RankingTop10> findTop10AllGenre();

}
