CREATE TABLE ALBUM (ID INTEGER NOT NULL, ANLANSARE INTEGER, NUMEALBUM VARCHAR(255), ARTIST_ID INTEGER, PRIMARY KEY (ID))
CREATE TABLE PERSOANA (ID INTEGER NOT NULL, DTYPE VARCHAR(31), DATANASTERII DATE, NATIONALITATE VARCHAR(255), NUMEPERSOANA VARCHAR(255), ANDEBUT INTEGER, NUMESCENA VARCHAR(255), NICKNAME VARCHAR(255), PRIMARY KEY (ID))
CREATE TABLE MELODIE (ID INTEGER NOT NULL, DURATAMIN FLOAT, GENMELODIE VARCHAR(255), NUMEMELODIE VARCHAR(255), ALBUM_ID INTEGER, PRIMARY KEY (ID))
ALTER TABLE ALBUM ADD CONSTRAINT FK_ALBUM_ARTIST_ID FOREIGN KEY (ARTIST_ID) REFERENCES PERSOANA (ID)
ALTER TABLE MELODIE ADD CONSTRAINT FK_MELODIE_ALBUM_ID FOREIGN KEY (ALBUM_ID) REFERENCES ALBUM (ID)
CREATE TABLE SEQUENCE (SEQ_NAME VARCHAR(50) NOT NULL, SEQ_COUNT DECIMAL(38), PRIMARY KEY (SEQ_NAME))
INSERT INTO SEQUENCE(SEQ_NAME, SEQ_COUNT) values ('SEQ_GEN', 0)
