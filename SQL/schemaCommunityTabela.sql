CREATE TABLE `SVT_Baza`.`community` (
	`community_id` int(3) NOT NULL,
    `name` varchar(40) DEFAULT NULL,
    `description` varchar(40) DEFAULT NULL,   
    `creation_data` varchar(40) DEFAULT NULL,    
    `is_suspended` boolean DEFAULT NULL,
    `suspended_reason` varchar(40) DEFAULT NULL,
    PRIMARY KEY (`community_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
