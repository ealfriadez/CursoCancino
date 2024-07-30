CREATE TABLE `categoria` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `nombre` varchar(100) NOT NULL,
 `slug` varchar(100) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB   DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci



CREATE TABLE `productos` (
 `id` int unsigned NOT NULL AUTO_INCREMENT,
 `categoria_id` int unsigned NOT NULL,
 `nombre` varchar(100) NOT NULL,
 `slug` varchar(100) NOT NULL,
 `descripcion` text NOT NULL,
 `precio` int NOT NULL,
 `foto` varchar(100) NOT NULL,
 PRIMARY KEY (`id`),
 KEY `fk_productos_categoria_id` (`categoria_id`),
 CONSTRAINT `fk_productos_categoria_id` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
