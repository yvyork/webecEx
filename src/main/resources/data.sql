INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES ('Nordflügel','EG01','Musterstrasse 1', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Ostflügel','EG01','Musterstrasse 1', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Südflügel','EG01','Musterstrasse 2', '3000 Bern');
INSERT IGNORE INTO Location (building_name, room_name, street_and_number, zip_city)
VALUES('Westflügel','EG01','Musterstrasse 2', '3000 Bern');


INSERT IGNORE INTO Device (device_Id, serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_location_id, status_status_id)
VALUES (1, 'ABC', '13"', 'Apple', '16GB', 'MacBook Air', 'Intel Chip', DATE('2008-01-02'), 1,1);
INSERT IGNORE INTO Device (device_Id, serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_location_id, status_status_id)
VALUES (2, 'DEF', '15"', 'Apple', '128GB', 'MacBook SuperMegaGeil', 'M5 Chip', DATE('2008-01-02'), 1,2);
INSERT IGNORE INTO Device (device_Id, serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_location_id, status_status_id)
VALUES (3, 'GHI', '13"', 'Apple', '8GB', 'MacBook Air', 'M1 Chip', DATE('2008-01-02'), 2,3);
INSERT IGNORE INTO Device (device_Id, serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_location_id, status_status_id)
VALUES (4, 'JKL', '13"', 'Apple', '16GB', 'MacBook Air', 'Intel Chip', DATE('2008-01-02'), 2,1);
INSERT IGNORE INTO Device (device_Id, serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_location_id, status_status_id)
VALUES (5, 'MNO', '13"', 'Apple', '8GB', 'iPad Pro Gen.4 2021', 'M1', DATE ('2010-05-16'), 3,3);


INSERT IGNORE INTO Status (name)
VALUES('new');
INSERT IGNORE INTO Status (name)
VALUES('assigned');
INSERT IGNORE INTO Status (name)
VALUES('broken');