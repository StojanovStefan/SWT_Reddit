CREATE TABLE `SVT_Baza`.`Post` (
	`post_id` int(3) AUTO_INCREMENT,
    `title` varchar(40) DEFAULT NULL,
    `text` varchar(40) DEFAULT NULL,    
    `creation_data` date DEFAULT NULL,
    `image_path` varchar(40) DEFAULT NULL,
    PRIMARY KEY (`post_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
