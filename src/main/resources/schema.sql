CREATE USER ms_products_db_owner WITH PASSWORD 'db_password';

CREATE DATABASE ms_products_db WITH ENCODING 'UTF8' OWNER 'ms_products_db_owner';

GRANT CONNECT ON DATABASE ms_products_db TO ms_products_db_owner;

ALTER DEFAULT PRIVILEGES
FOR USER ms_products_db_owner
IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO ms_products_db_owner;
