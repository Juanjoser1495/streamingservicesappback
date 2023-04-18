CREATE TABLE IF NOT EXISTS movie (
   idgenre serial PRIMARY KEY,
   genre varchar(10000),
);

CREATE TABLE IF NOT EXISTS serie (
   idserie serial PRIMARY KEY,
   nameserie varchar(10000),
   imageurl varchar(10000),
   genreid serial,
   likes int,
   FOREIGN KEY(genreid)
        REFERENCES genre(idgenre)
);

CREATE TABLE IF NOT EXISTS movie (
   idmovie serial PRIMARY KEY,
   moviename varchar(10000),
   imageurl varchar(10000),
   genreid serial,
   likes int,
   FOREIGN KEY(genreid)
        REFERENCES genre(idgenre)
);