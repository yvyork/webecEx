INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES ('Nordflügel','EG01','Musterstrasse 1', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Ostflügel','EG01','Musterstrasse 1', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Südflügel','EG01','Musterstrasse 2', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Westflügel','EG01','Musterstrasse 2', '3000 Bern');

INSERT IGNORE INTO Status (name)
VALUES('new');
INSERT IGNORE INTO Status (name)
VALUES('assigned');
INSERT IGNORE INTO Status (name)
VALUES('broken');