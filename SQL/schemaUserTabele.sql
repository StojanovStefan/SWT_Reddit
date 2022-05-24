CREATE TABLE `SVT_Baza`.`User` (
	`user_id` int(3) NOT NULL,
    `username` varchar(40) DEFAULT NULL,
    `password` varchar(40) DEFAULT NULL,    
    `email` varchar(40) DEFAULT NULL, 
    `avatar` varchar(40) DEFAULT NULL,    
    `registration_data` date DEFAULT NULL,
    `description` varchar(40) DEFAULT NULL,
    `display_name` varchar(40) DEFAULT NULL,    
    PRIMARY KEY (`user_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
