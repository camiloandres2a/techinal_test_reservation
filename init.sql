USE myreservationreversi;

INSERT INTO restaurant ( id, name ) VALUES
( '744844fe-9e5e-41aa-9578-f3f7602a6511', 'McDonals' );

INSERT INTO branch ( id_branch, address, available_seats, id_restaurant ) VALUES
('f18b1cc5-d076-4421-a3d0-84f824fe5a2f', 'Bogotá', 30, '744844fe-9e5e-41aa-9578-f3f7602a6511');


INSERT INTO schedule (id_schedule, day, start_hour, end_hour, id_branch)
VALUES (UUID(), 'Tuesday', '09:00:00', '17:00:00', 'f18b1cc5-d076-4421-a3d0-84f824fe5a2f');

INSERT INTO schedule (id_schedule, day, start_hour, end_hour, id_branch)
VALUES (UUID(), 'Wednesday', '09:00:00', '17:00:00', 'f18b1cc5-d076-4421-a3d0-84f824fe5a2f');

INSERT INTO schedule (id_schedule, day, start_hour, end_hour, id_branch)
VALUES (UUID(), 'Thursday', '09:00:00', '17:00:00', 'f18b1cc5-d076-4421-a3d0-84f824fe5a2f');

INSERT INTO schedule (id_schedule, day, start_hour, end_hour, id_branch)
VALUES (UUID(), 'Friday', '09:00:00', '17:00:00', 'f18b1cc5-d076-4421-a3d0-84f824fe5a2f');

INSERT INTO schedule (id_schedule, day, start_hour, end_hour, id_branch)
VALUES (UUID(), 'Saturday', '10:00:00', '14:00:00', 'f18b1cc5-d076-4421-a3d0-84f824fe5a2f');

INSERT INTO schedule (id_schedule, day, start_hour, end_hour, id_branch)
VALUES (UUID(), 'Sunday', '10:00:00', '14:00:00', 'f18b1cc5-d076-4421-a3d0-84f824fe5a2f');
