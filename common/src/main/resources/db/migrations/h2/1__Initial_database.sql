-- H2 database
CREATE TABLE IF NOT EXISTS `<prefix>players` (
	`uuid`			VARCHAR(255) NOT NULL PRIMARY KEY,
	`name`			VARCHAR(255) DEFAULT '',
	`lastLogin`		INT DEFAULT 0 NOT NULL,
	`lastLogout`	INT DEFAULT 0 NOT NULL
);