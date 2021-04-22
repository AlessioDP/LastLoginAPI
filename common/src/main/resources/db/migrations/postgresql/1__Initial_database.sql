-- PostgreSQL database
CREATE TABLE IF NOT EXISTS <prefix>players (
	"uuid"			CHAR(36) NOT NULL PRIMARY KEY,
	"name"			VARCHAR(255) DEFAULT '',
	"lastLogin"		INTEGER DEFAULT 0 NOT NULL,
	"lastLogout"	INTEGER DEFAULT 0 NOT NULL
);