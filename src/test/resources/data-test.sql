INSERT INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES ('Nordflügel','EG01','Musterstrasse 1', '3000 Bern');
INSERT INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Ostflügel','EG01','Musterstrasse 1', '3000 Bern');
INSERT INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Südflügel','EG01','Musterstrasse 2', '3000 Bern');
INSERT INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Westflügel','EG01','Musterstrasse 2', '3000 Bern');

INSERT INTO Status (name)
VALUES('new');
INSERT INTO Status (name)
VALUES('assigned');
INSERT INTO Status (name)
VALUES('broken');
INSERT INTO STATUS (name)
VALUES('unassigned');


INSERT INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('ABC', '13"', 'Apple', '16GB', 'MacBook Air', 'Intel Chip', '2008-01-01', 1,1);
INSERT INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('DEF', '15"', 'Apple', '128GB', 'MacBook SuperMegaGeil', 'M5 Chip', '2008-01-01', 1,2);
INSERT INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('GHI', '13"', 'Apple', '8GB', 'MacBook Air', 'M1 Chip', '2008-01-01', 2,3);
INSERT INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('JKL', '13"', 'Apple', '16GB', 'MacBook Air', 'Intel Chip', '2008-01-01', 2,1);
INSERT INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('MNO', '13"', 'Apple', '8GB', 'iPad Pro Gen.4 2021', 'M1','2008-01-01', 3,3);