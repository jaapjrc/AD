-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-10-2023 a las 10:52:14
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.1.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `examennapoleon`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `conquistas`
--

CREATE TABLE `conquistas` (
  `id` int(11) NOT NULL,
  `territorio` varchar(50) NOT NULL,
  `fecha_conquista` date NOT NULL,
  `antiguo_poseedor` varchar(50) NOT NULL,
  `descripcion_conquista` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `conquistas`
--

INSERT INTO `conquistas` (`id`, `territorio`, `fecha_conquista`, `antiguo_poseedor`, `descripcion_conquista`) VALUES
(1, 'Piamonte', '1796-04-12', 'Austria', 'Conquista rápida y sorpresiva'),
(2, 'Norte de Italia', '1796-05-23', 'Austria', 'Más rápida y más fácil');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `conquistas`
--
ALTER TABLE `conquistas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `conquistas`
--
ALTER TABLE `conquistas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
