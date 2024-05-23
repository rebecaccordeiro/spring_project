CREATE TABLE `ngo_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `cnpj` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(150) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mo9rdnxo8xi72wq68drxkneql` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `job_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(500) NOT NULL,
  `remote` bit(1) NOT NULL,
  `title` varchar(150) NOT NULL,
  `ngo_id` bigint DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoqtt29xkbbgynogc7srqiw0do` (`ngo_id`),
  CONSTRAINT `FKoqtt29xkbbgynogc7srqiw0do` FOREIGN KEY (`ngo_id`) REFERENCES `ngo_table` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_table` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `birthday` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(150) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_eamk4l51hm6yqb8xw37i23kb5` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
