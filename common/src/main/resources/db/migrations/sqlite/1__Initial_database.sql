-- SQLite database
CREATE TABLE IF NOT EXISTS `<prefix>players` (
	`uuid`			VARCHAR NOT NULL PRIMARY KEY,
	`name`			VARCHAR DEFAULT '',
	`lastLogin`		INTEGER DEFAULT 0 NOT NULL,
	`lastLogout`	INTEGER DEFAULT 0 NOT NULL
);