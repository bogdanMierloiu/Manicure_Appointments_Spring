CREATE TABLE `manicurist`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `first_name`   varchar(255) DEFAULT NULL,
    `hire_date`    datetime(6)  DEFAULT NULL,
    `last_name`    varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `customer`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `active`       bit(1)       DEFAULT NULL,
    `birth_date`   date         DEFAULT NULL,
    `email`        varchar(255) DEFAULT NULL,
    `first_name`   varchar(255) DEFAULT NULL,
    `last_name`    varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `nails_care`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `price`        int NOT NULL,
    `service_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE `appointment`
(
    `id`                    int NOT NULL AUTO_INCREMENT,
    `appointment_date_time` datetime(6) DEFAULT NULL,
    `customer_id`           int         DEFAULT NULL,
    `manicurist_id`         int         DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FKmyowslj1th8d9j6j3wlbwrtoe` (`customer_id`),
    KEY `FKnwu14viswahnbsre2xq95eg8m` (`manicurist_id`),
    CONSTRAINT `FKmyowslj1th8d9j6j3wlbwrtoe` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `FKnwu14viswahnbsre2xq95eg8m` FOREIGN KEY (`manicurist_id`) REFERENCES `manicurist` (`id`)
);

CREATE TABLE `appointment_services`
(
    `appointment_id` int NOT NULL,
    `service_id`     int NOT NULL,
    PRIMARY KEY (`appointment_id`, `service_id`),
    KEY `FK7vl9tc6b73bafjb9sayecmfln` (`service_id`),
    CONSTRAINT `FK7vl9tc6b73bafjb9sayecmfln` FOREIGN KEY (`service_id`) REFERENCES `nails_care` (`id`),
    CONSTRAINT `FKkv6gwfscv4td54g96ra0p0gn0` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`)
);

CREATE TABLE `user`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `email`      varchar(45) NOT NULL,
    `first_name` varchar(30) NOT NULL,
    `last_name`  varchar(30) NOT NULL,
    `password`   varchar(64) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
);