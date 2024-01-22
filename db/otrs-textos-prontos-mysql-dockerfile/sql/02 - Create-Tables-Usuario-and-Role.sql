 CREATE TABLE IF NOT EXISTS roles (
     id           TINYINT UNSIGNED   PRIMARY KEY AUTO_INCREMENT,
     name         VARCHAR(55)        UNIQUE NOT NULL
 );
 
 CREATE TABLE IF NOT EXISTS users (
     id           SMALLINT UNSIGNED  PRIMARY KEY AUTO_INCREMENT,
     username     VARCHAR(55)        UNIQUE NOT NULL,
     password     VARCHAR(100)       NOT NULL
 );
 
 CREATE TABLE IF NOT EXISTS users_roles (
	fk_user_id SMALLINT UNSIGNED NOT NULL,
	fk_role_id TINYINT UNSIGNED  NOT NULL
  );
 
ALTER TABLE users_roles ADD FOREIGN KEY(fk_user_id) REFERENCES users(id);
ALTER TABLE users_roles ADD FOREIGN KEY(fk_role_id) REFERENCES roles(id);

DESC roles;
DESC users;
