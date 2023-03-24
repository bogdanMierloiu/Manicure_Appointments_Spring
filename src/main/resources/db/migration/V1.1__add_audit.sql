CREATE TABLE `revinfo`
(
    `rev`      int NOT NULL AUTO_INCREMENT,
    `revtstmp` bigint DEFAULT NULL,
    PRIMARY KEY (`rev`)
);

CREATE TABLE `manicurist_aud`
(
    `id`           int NOT NULL,
    `rev`          int NOT NULL,
    `revtype`      tinyint      DEFAULT NULL,
    `first_name`   varchar(255) DEFAULT NULL,
    `hire_date`    date DEFAULT NULL,
    `last_name`    varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`),
    KEY            `FKojrf80ncxyp1ip6apw9lco1wx` (`rev`),
    CONSTRAINT `FKojrf80ncxyp1ip6apw9lco1wx` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);
CREATE TABLE `customer_aud`
(
    `id`           int NOT NULL,
    `rev`          int NOT NULL,
    `revtype`      tinyint      DEFAULT NULL,
    `active`       bit(1)       DEFAULT NULL,
    `birth_date`   date         DEFAULT NULL,
    `email`        varchar(255) DEFAULT NULL,
    `first_name`   varchar(255) DEFAULT NULL,
    `last_name`    varchar(255) DEFAULT NULL,
    `phone_number` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`),
    KEY            `FKgfwtmwfqmkddg7g0pg36luq54` (`rev`),
    CONSTRAINT `FKgfwtmwfqmkddg7g0pg36luq54` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);
CREATE TABLE `nails_care_aud`
(
    `id`           int NOT NULL,
    `rev`          int NOT NULL,
    `revtype`      tinyint      DEFAULT NULL,
    `price`        int          DEFAULT NULL,
    `service_name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`),
    KEY            `FK62kk2bcqxp3pqn4nd891x545f` (`rev`),
    CONSTRAINT `FK62kk2bcqxp3pqn4nd891x545f` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);

CREATE TABLE `appointment_aud`
(
    `id`                    int NOT NULL,
    `rev`                   int NOT NULL,
    `revtype`               tinyint DEFAULT NULL,
    `appointment_date_time` datetime(6) DEFAULT NULL,
    `customer_id`           int     DEFAULT NULL,
    `manicurist_id`         int     DEFAULT NULL,
    PRIMARY KEY (`id`, `rev`),
    KEY                     `FKlmkpkowitkecxb2u8obk10e56` (`rev`),
    CONSTRAINT `FKlmkpkowitkecxb2u8obk10e56` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);
CREATE TABLE `appointment_services_aud`
(
    `rev`            int NOT NULL,
    `appointment_id` int NOT NULL,
    `service_id`     int NOT NULL,
    `revtype`        tinyint DEFAULT NULL,
    PRIMARY KEY (`rev`, `appointment_id`, `service_id`),
    CONSTRAINT `FK3i8ecl9rmdr6uomu553tk5t8p` FOREIGN KEY (`rev`) REFERENCES `revinfo` (`rev`)
);


