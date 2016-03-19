-- tabla usuario (user)
CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	status VARCHAR(255) NOT NULL,
	PRIMARY Key(id),
	UNIQUE user_username_unique (username)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- tabla credenciales (login_credentials)
CREATE TABLE login_credentials (
	id INT NOT NULL AUTO_INCREMENT,
	session_key VARCHAR(255) NOT NULL,
	expires_date DATETIME NOT NULL,
	date_created DATETIME NOT NULL,
	user_id INT NOT NULL,
	PRIMARY Key(id),
	CONSTRAINT fk_login_credentials_user FOREIGN KEY (user_id)
	REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
