ALTER TABLE ALBUM DROP CONSTRAINT FK_ALBUM_ARTIST_ID
ALTER TABLE MELODIE DROP CONSTRAINT FK_MELODIE_ALBUM_ID
DROP TABLE ALBUM CASCADE
DROP TABLE PERSOANA CASCADE
DROP TABLE MELODIE CASCADE
DELETE FROM SEQUENCE WHERE SEQ_NAME = 'SEQ_GEN'