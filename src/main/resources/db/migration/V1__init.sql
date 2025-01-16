CREATE TABLE IF NOT EXISTS restaurant (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS branch (
    id_branch VARCHAR(36) PRIMARY KEY,
    address VARCHAR(255) NOT NULL,
    available_seats INT NOT NULL,
    id_restaurant VARCHAR(36),
    FOREIGN KEY (id_restaurant) REFERENCES restaurant(id)
);

CREATE TABLE IF NOT EXISTS schedule (
    id_schedule VARCHAR(36) PRIMARY KEY,
    day VARCHAR(20) NOT NULL,
    start_hour TIME NOT NULL,
    end_hour TIME NOT NULL,
    id_branch VARCHAR(36),
    FOREIGN KEY (id_branch) REFERENCES branch(id_branch)
);

CREATE TABLE IF NOT EXISTS client (
    id VARCHAR(36) PRIMARY KEY,
    cell_phone_number BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS reservation (
    id_reservation VARCHAR(36) PRIMARY KEY,
    day_taken ENUM('Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday') NOT NULL,
    hour_taken TIME NOT NULL,
    number_people INT NOT NULL,
    id_branch VARCHAR(36),
    id_client VARCHAR(36),
    FOREIGN KEY (id_branch) REFERENCES branch(id_branch),
    FOREIGN KEY (id_client) REFERENCES client(id)
    );


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
