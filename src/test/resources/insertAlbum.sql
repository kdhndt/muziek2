insert into albums(naam, artiestid) values
('test', (select id from artiesten where artiesten.naam = 'test'));