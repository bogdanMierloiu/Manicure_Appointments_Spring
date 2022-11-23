# CREATE TABLE manicurist
# (
#     id           int NOT NULL AUTO_INCREMENT,
#     first_name   varchar(255) DEFAULT NULL,
#     hire_date    datetime(6)  DEFAULT NULL,
#     last_name    varchar(255) DEFAULT NULL,
#     phone_number varchar(255) DEFAULT NULL,
#     PRIMARY KEY (id)
# );
#
# CREATE TABLE customer
# (
#     id           int NOT NULL AUTO_INCREMENT,
#     first_name   varchar(255) DEFAULT NULL,
#     last_name    varchar(255) DEFAULT NULL,
#     phone_number varchar(255) DEFAULT NULL,
#     PRIMARY KEY (id)
# );
#
# CREATE TABLE appointment
# (
#     id               int NOT NULL AUTO_INCREMENT,
#     appointment_date date DEFAULT NULL,
#     appointment_time time DEFAULT NULL,
#     customer_id      int  DEFAULT NULL,
#     manicurist_id    int  DEFAULT NULL,
#     PRIMARY KEY (id),
#     CONSTRAINT FOREIGN KEY (customer_id) REFERENCES customer (id),
#     CONSTRAINT FOREIGN KEY (manicurist_id) REFERENCES manicurist (id)
# );
#
# CREATE TABLE nails_service
# (
#     id           int NOT NULL AUTO_INCREMENT,
#     price        int          DEFAULT NULL,
#     service_name varchar(255) DEFAULT NULL,
#     PRIMARY KEY (id)
# );
#
# CREATE TABLE appointment_services
# (
#     appointment_id int NOT NULL,
#     service_id     int NOT NULL,
#     CONSTRAINT FOREIGN KEY (service_id) REFERENCES nails_service (id),
#     CONSTRAINT FOREIGN KEY (appointment_id) REFERENCES appointment (id)
# );
#
#
