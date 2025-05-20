create database scoula_db;
use scoula_db;

CREATE USER 'scoula'@'%' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON scoula_db.* TO 'scoula'@'%';

FLUSH PRIVILEGES;