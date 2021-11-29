SET SCHEMA PUBLIC;
INSERT IGNORE INTO Location (location_id, building_name, room_name, street_and_number, zip_city)
VALUES (1,'Nordflügel','EG01','Musterstrasse 1', '3000 Bern');
INSERT IGNORE INTO Location (location_id, building_name, room_name, street_and_number, zip_city)
VALUES(2,'Ostflügel','EG01','Musterstrasse 1', '3000 Bern');
INSERT IGNORE INTO Location (location_id, building_name, room_name, street_and_number, zip_city)
VALUES(3,'Südflügel','EG01','Musterstrasse 2', '3000 Bern');
INSERT IGNORE INTO Location (location_id, building_name, room_name, street_and_number, zip_city)
VALUES(4,'Westflügel','EG01','Musterstrasse 2', '3000 Bern');

INSERT IGNORE INTO Status (status_id, name)
VALUES(1, 'new');
INSERT IGNORE INTO Status (status_id, name)
VALUES(2, 'assigned');
INSERT IGNORE INTO Status (status_id, name)
VALUES(3, 'broken');