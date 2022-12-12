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