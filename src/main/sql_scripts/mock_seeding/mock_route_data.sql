INSERT INTO trailfinder_dev.route(route_id, author, name, length, altitude, location, description, duration) VALUES
     ('r001', 'u001', 'Rappenlochschlucht', 4.70, 236, 'Dornbirn, Austria', 'Out & Back', 1.39),
     ('r002', 'u001', 'Amannsbrücke', 4.20, 207, 'Dornbirn, Austria', 'Loop', 1.28),
     ('r003', 'u001', 'Rund um den Staufen', 5.60, 227, 'Dornbirn, Austria', 'Loop', 1.47),
     ('r004', 'u001', 'Aufs Gshol', 6.90, 483, 'Hohenems, Austria', 'Out & Back', 2.53),
     ('r005', 'u001', 'Leiterweg', 6.40, 354, 'Hohenems, Austria', 'Loop', 2.21),
     ('r006', 'u001', 'Waterfall Loop Alberschwende', 7.20, 251, 'Alberschwende, Austria', 'Loop', 2.09),
     ('r007', 'u001', 'Uferweg und Schützingerweg', 2.10, 26, 'Lindau, Germany', 'Loop', 0.27),
     ('r008', 'u001', 'Rorschach - Rheineck', 10.30, 125, 'Rorschach, Switzerland', 'Point to point', 2.15),
     ('r009', 'u001', 'Pfänderwanderung ab Lochau', 10.90, 627, 'Lochau, Austria', 'Loop', 4.07),
     ('r010', 'u001', 'Bödele und Hochälpelekopf', 9.20, 402, 'Schwarzenberg, Austria', 'Loop', 3.01);

INSERT INTO trailfinder_dev.coordinate(coordinate_id, route_id, sequence, longitude, latitude) VALUES
     ('c001', 'r001', 0, 47.385373401565694, 9.779697869864654 ),
     ('c002', 'r002', 0, 47.38394008611608, 9.789106368138826 ),
     ('c003', 'r003', 0, 47.38420374454702, 9.7500966264667 ),
     ('c004', 'r004', 0, 47.36068205233258, 9.696947103047693 ),
     ('c005', 'r005', 0, 47.36636723128827, 9.690909307760702 ),
     ('c006', 'r006', 0, 47.454351006022044, 9.831536329820517 ),
     ('c007', 'r007', 0, 47.549325141382475, 9.683296706774431 ),
     ('c008', 'r008', 0, 47.48878694113832, 9.49275575707386 ),
     ('c009', 'r009', 0, 47.536090354359146, 9.753080828522833 ),
     ('c010', 'r010', 0, 47.42624187325225, 9.809488675076931 );

