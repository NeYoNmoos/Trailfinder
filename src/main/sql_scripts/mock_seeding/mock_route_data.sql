

INSERT INTO trailfinder_dev.coordinate(coordinate_id, route_id, sequence, longitude, latitude) VALUES
     (1, 1, 0, 9.775992718176056, 47.391678657852516),
     (2, 1, 1, 9.77736770696476, 47.37893453043227),
     (3, 1, 2, 9.775992718176056, 47.391678657852516),
     (4, 2, 0, 9.775992718176056, 47.391678657852516),
     (5, 2, 1, 9.799877951138697, 47.3816087168998),
     (6, 2, 2, 9.775992718176056, 47.391678657852516),
     (7, 3, 0, 9.746430202763596, 47.36742307461866),
     (8, 3, 1, 9.763410269177234, 47.378180415423884),
     (9, 3, 2, 9.746430202763596, 47.36742307461866),
     (10, 4, 0, 9.697075189711784, 47.35830163389344),
     (11, 4, 1, 9.6985245769916, 47.350523706794476),
     (12, 5, 0, 9.691213642617338, 47.36441490065213),
     (13, 5, 1, 9.704716027522831, 47.37346437639706),
     (14, 5, 2, 9.691213642617338, 47.36441490065213);



INSERT INTO trailfinder_dev.route(route_id, author, name, length, altitude, location, description, duration, attribute_id, months, active, created_at) VALUES
     (1, NULL, 'Rappenlochschlucht', 4.70, 236, 'Dornbirn, Austria', 'Entdecke diesen 4,7-Kilometer langen Hin- und Rückweg in der Nähe von Dornbirn, Vorarlberg. Die Route wird grundsätzlich als moderat eingestuft und kann zumeist in 1 Std 39 Min bewältigt werden. Da sich der Weg bestens zum Wandern eignet, bist du selten allein unterwegs und wirst während deines Ausflugs auch auf andere Abenteurer treffen. Hunde sind erlaubt, müssen aber angeleint sein.', 1.39, 1, 1020, true, NULL),
     (2, NULL, 'Amannsbrücke', 4.20, 207, 'Dornbirn, Austria', 'Erlebe diesen 4,2-Kilometer langen Rundweg in der Nähe von Dornbirn, Vorarlberg. Die Route wird grundsätzlich als moderat eingestuft und kann zumeist in 1 Std 28 Min bewältigt werden. Obwohl sich der Weg bestens zum Wandern eignet und oftmals gut besucht ist, kannst du hier zur richtigen Tageszeit ein bisschen Ruhe genießen. Die Route ist das ganze Jahr über zugänglich und zu jeder Jahreszeit einen Ausflug wert.', 1.28, 2, 1020, true, CURRENT_TIMESTAMP),
     (3, NULL, 'Rund um den Staufen', 5.60, 227, 'Dornbirn, Austria', 'Erforsche diesen 5,6-Kilometer langen Rundweg in der Nähe von Dornbirn, Vorarlberg. Die Route wird grundsätzlich als moderat eingestuft und kann zumeist in 1 Std 47 Min bewältigt werden. Obwohl sich der Weg bestens zum Wandern eignet und oftmals gut besucht ist, kannst du hier zur richtigen Tageszeit ein bisschen Ruhe genießen. Hunde sind erlaubt und können teilweise sogar unangeleint laufen.', 1.47, 3, 1020, true, CURRENT_TIMESTAMP),
     (4, NULL, 'Aufs Gshol', 6.90, 483, 'Hohenems, Austria', 'Erlebe diesen 6,9-Kilometer langen Hin- und Rückweg in der Nähe von Hohenems, Vorarlberg. Die Route wird grundsätzlich als schwierig eingestuft und kann zumeist in 2 Std 53 Min bewältigt werden. Da sich der Weg bestens zum Wandern eignet, bist du selten allein unterwegs und wirst während deines Ausflugs auch auf andere Abenteurer treffen.', 2.53, 4, 1020, true, CURRENT_TIMESTAMP),
     (5, NULL, 'Leiterweg', 6.40, 354, 'Hohenems, Austria', 'Genieße diesen 6,4-Kilometer langen Rundweg in der Nähe von Hohenems, Vorarlberg. Die Route wird grundsätzlich als moderat eingestuft und kann zumeist in 2 Std 21 Min bewältigt werden. Obwohl sich der Weg bestens zum Wandern eignet und oftmals gut besucht ist, kannst du hier zur richtigen Tageszeit ein bisschen Ruhe genießen.', 2.21, 5, 1020, true, CURRENT_TIMESTAMP);

INSERT INTO trailfinder_dev.route(route_id, author, name, length, altitude, location, description, duration, attribute_id, months, active, created_at) VALUES
     (32, 1, 'Rappenlochschlucht', 4.70, 236, 'Dornbirn, Austria', 'Entdecke diesen 4,7-Kilometer langen Hin- und Rückweg in der Nähe von Dornbirn, Vorarlberg. Die Route wird grundsätzlich als moderat eingestuft und kann zumeist in 1 Std 39 Min bewältigt werden. Da sich der Weg bestens zum Wandern eignet, bist du selten allein unterwegs und wirst während deines Ausflugs auch auf andere Abenteurer treffen. Hunde sind erlaubt, müssen aber angeleint sein.', 1.39, 1, 1020, true, NULL);

SELECT * FROM trailfinder_dev.route;


INSERT INTO trailfinder_dev.attribute(attribute_id, strength, condition, scenery, experience) VALUES
     (1, 3, 3, 4, 3),
     (2, 3, 3, 4, 2),
     (3, 3, 3, 3, 2),
     (4, 4, 3, 4, 3),
     (5, 3, 2, 4, 4);

DELETE FROM trailfinder_dev.route WHERE route_id BETWEEN 1 AND 20;
DELETE FROM trailfinder_dev.coordinate WHERE route_id BETWEEN 1 AND 20;
DELETE FROM trailfinder_dev.attribute WHERE attribute_id BETWEEN 1 AND 20;






/*
 ergänzungen:
  (6, 'u001', 'Waterfall Loop Alberschwende', 7.20, 251, 'Alberschwende, Austria', 'Loop', 2.09, 6, 1020, true),
  (7, 'u001', 'Uferweg und Schützingerweg', 2.10, 26, 'Lindau, Germany', 'Loop', 0.27, 7, 2044, true),
  (8, 'u001', 'Rorschach - Rheineck', 10.30, 125, 'Rorschach, Switzerland', 'Point to point', 2.15, 8, 1022, true),
  (9, 'u001', 'Pfänderwanderung ab Lochau', 10.90, 627, 'Lochau, Austria', 'Loop', 4.07, 9, 1022, true),
  (10, 'u001', 'Bödele und Hochälpelekopf', 9.20, 402, 'Schwarzenberg, Austria', 'Loop', 3.01, 10, 1022, true);
 */
