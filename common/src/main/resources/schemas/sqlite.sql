-- Start and end placeholders are used to obtain right queries

/*START_PLAYERS*/
CREATE TABLE '{table_players}' (
	'uuid'			VARCHAR({varcharsize}) NOT NULL PRIMARY KEY,
	'name'			VARCHAR({varcharsize}) DEFAULT '',
	'lastLogin'		INTEGER DEFAULT 0 NOT NULL,
	'lastLogout'	INTEGER DEFAULT 0 NOT NULL);
/*END_PLAYERS*/

/*START_VERSIONS*/
CREATE TABLE '{table_versions}' (
	'name'		VARCHAR({varcharsize}) NOT NULL PRIMARY KEY,
	'version'	INTEGER NOT NULL);
/*END_VERSIONS*/