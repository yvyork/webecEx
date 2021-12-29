INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES ('Nordfluegel','EG01','Musterstrasse 1', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Ostfluegel','EG01','Musterstrasse 1', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Suedfluegel','EG01','Musterstrasse 2', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Westfluegel','EG01','Musterstrasse 2', '3000 Bern');

INSERT IGNORE INTO Status (name)
VALUES('new');
INSERT IGNORE INTO Status (name)
VALUES('assigned');
INSERT IGNORE INTO Status (name)
VALUES('broken');

INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('ABC', '13"', 'Apple', '16GB', 'MacBook Air', 'Intel Chip', '2008-01-01', 1,1);
INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('DEF', '15"', 'Apple', '128GB', 'MacBook SuperMegaGeil', 'M5 Chip', '2008-01-01', 1,2);
INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('GHI', '13"', 'Apple', '8GB', 'MacBook Air', 'M1 Chip', '2008-01-01', 2,3);
INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('JKL', '13"', 'Apple', '16GB', 'MacBook Air', 'Intel Chip', '2008-01-01', 2,1);
INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('MNO', '13"', 'Apple', '8GB', 'iPad Pro Gen.4 2021', 'M1','2008-01-01', 3,3);


