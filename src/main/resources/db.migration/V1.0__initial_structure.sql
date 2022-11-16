CREATE TABLE `manicurist`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `first_name`   varchar(255) DEFAULT NULL,
    `hire_date`    datetime(6) DEFAULT NULL,
    `last_name`    varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)

CREATE TABLE `appointment`
(
    `id`               int NOT NULL AUTO_INCREMENT,
    `appointment_date` date DEFAULT NULL,
    `appointment_time` time DEFAULT NULL,
    `customer_id`      int  DEFAULT NULL,
    `manicurist_id`    int  DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY                `FKmyowslj1th8d9j6j3wlbwrtoe` (`customer_id`),
    KEY                `FKnwu14viswahnbsre2xq95eg8m` (`manicurist_id`),
    CONSTRAINT `FKmyowslj1th8d9j6j3wlbwrtoe` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
    CONSTRAINT `FKnwu14viswahnbsre2xq95eg8m` FOREIGN KEY (`manicurist_id`) REFERENCES `manicurist` (`id`)
)

CREATE TABLE `customer`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `first_name`   varchar(255) DEFAULT NULL,
    `last_name`    varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)

CREATE TABLE `nails_service`
(
    `id`           int NOT NULL AUTO_INCREMENT,
    `price`        int          DEFAULT NULL,
    `service_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)

CREATE TABLE `appointment_services`
(
    `appointment_id` int NOT NULL,
    `service_id`     int NOT NULL,
    KEY              `FK9g941kh7oaj4gj0wf3fnmp6x0` (`service_id`),
    KEY              `FKkv6gwfscv4td54g96ra0p0gn0` (`appointment_id`),
    CONSTRAINT `FK9g941kh7oaj4gj0wf3fnmp6x0` FOREIGN KEY (`service_id`) REFERENCES `nails_service` (`id`),
    CONSTRAINT `FKkv6gwfscv4td54g96ra0p0gn0` FOREIGN KEY (`appointment_id`) REFERENCES `appointment` (`id`)
)


