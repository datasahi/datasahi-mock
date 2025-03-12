CREATE DATABASE IF NOT EXISTS crm;
use crm;

CREATE TABLE IF NOT EXISTS `lead_tracker` (
    `id` varchar(32) NOT NULL,
    `name` varchar(255) NOT NULL,
    `age` int NOT NULL,
    `status` varchar(16) NOT NULL,
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,
    `received_at` datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    PRIMARY KEY (`id`),
    KEY `lead_tracker_id_key` (`id`)
);

