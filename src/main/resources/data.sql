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
VALUES ('C02FX13MQ6W9', 'No Information', 'Apple', '8GB', 'Mac Mini', 'M1','2020-05-08', 1,1);
INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('C07F1CXPQ6NV', '13"', 'Apple', '16GB', 'MacBook Air', 'M1','2020-11-10', 1,2);
INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('C07CL0GFPJH7', '13"', 'Apple', '16GB', 'MacBook Pro', 'Intel', '2019-06-08', 2,3);
INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('C02TN07YJ1GH', '13"', 'Apple', '8GB', 'iPad Air Gen.4', 'M1', '2021-05-06', 2,1);
INSERT IGNORE INTO Device (serial_Number, display_size, manufacturer, memory, model, processor, purchase_date, location_id, status_id)
VALUES ('XWWTP6C4F4', '13"', 'Apple', '8GB', 'iPad Pro Gen.4', 'M1','2021-01-01', 3,3);


