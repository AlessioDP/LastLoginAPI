-- Start and end placeholders are used to obtain right queries

/*START_PLAYERS*/
CREATE TABLE `{table_players}` (
	`uuid`			VARCHAR({varcharsize}) NOT NULL,
	`name`			VARCHAR({varcharsize}) DEFAULT '',
	`lastLogin`		BIGINT DEFAULT 0 NOT NULL,
	`lastLogout`	BIGINT DEFAULT 0 NOT NULL,
	PRIMARY KEY (`uuid`))
 DEFAULT CHARSET='{charset}';
/*END_PLAYERS*/

/*START_VERSIONS*/
CREATE TABLE `{table_versions}` (
	`name`		VARCHAR({varcharsize}) NOT NULL,
	`version`	INT NOT NULL,
	PRIMARY KEY (`name`))
 DEFAULT CHARSET='{charset}';
/*END_VERSIONS*/