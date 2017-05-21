-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2017 a las 06:19:43
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `softcake`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo`
--

CREATE TABLE `articulo` (
  `ID` int(11) NOT NULL,
  `Activo` tinyint(4) NOT NULL,
  `Codigo` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `Precio` decimal(14,2) NOT NULL,
  `IDUnidad` int(11) NOT NULL,
  `SMaximo` int(11) NOT NULL,
  `SMinimo` int(11) NOT NULL,
  `Existencia` int(11) NOT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaMod` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo_lote`
--

CREATE TABLE `articulo_lote` (
  `ID` int(11) NOT NULL,
  `IDArticulo` int(11) NOT NULL,
  `Activo` tinyint(4) NOT NULL,
  `Codigo` varchar(25) NOT NULL,
  `Cantidad` decimal(10,0) NOT NULL,
  `FechaElaboracion` date NOT NULL,
  `FecbaCaducidad` date NOT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaMod` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulo_unidad`
--

CREATE TABLE `articulo_unidad` (
  `ID` int(11) NOT NULL,
  `Activo` tinyint(4) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `NombreCorto` varchar(5) NOT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaMod` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `ID` int(11) NOT NULL,
  `Activo` tinyint(4) NOT NULL DEFAULT '1',
  `Nombre` varchar(50) NOT NULL,
  `ApellidoP` varchar(25) NOT NULL,
  `ApellidoM` varchar(25) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `Direccion` tinytext NOT NULL,
  `NumExt` varchar(10) NOT NULL,
  `NumInt` varchar(10) DEFAULT NULL,
  `CP` varchar(10) NOT NULL,
  `Colonia` varchar(25) NOT NULL,
  `Estado` varchar(25) NOT NULL,
  `Municipio` varchar(25) NOT NULL,
  `Telefono` varchar(25) NOT NULL,
  `Ext` varchar(10) DEFAULT NULL,
  `Celular` varchar(25) DEFAULT NULL,
  `Email` tinytext,
  `Facebook` tinytext,
  `Identificador` varchar(10) NOT NULL,
  `Usuario` varchar(25) NOT NULL,
  `Password` varchar(25) NOT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaMod` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`ID`, `Activo`, `Nombre`, `ApellidoP`, `ApellidoM`, `FechaNacimiento`, `Direccion`, `NumExt`, `NumInt`, `CP`, `Colonia`, `Estado`, `Municipio`, `Telefono`, `Ext`, `Celular`, `Email`, `Facebook`, `Identificador`, `Usuario`, `Password`, `FechaCreacion`, `FechaMod`) VALUES
(1, 1, 'Guillermo', 'Jiménez', 'García', '1992-08-07', 'Privada 6 de matamoros', '87', '', '91840', 'Empleados municipales', 'Veracruz', 'Veracruz', '2291287541', '', '', 'JIGA_1up@hotmail.com', '', '001', 'GUILLERMO', '12345', '2017-05-09 00:00:25', '2017-05-14 03:30:45'),
(2, 1, 'SERGIO', 'QUIROZ', 'PONCE', '1989-08-03', 'GUADALUPE', '638', 'A', '20059', 'GUADALUPE', 'Aguascalientes', 'AGUASCALIENTES', '9163008', '123', '4494630590', 'SERGIO@GMAIL.COM', 'HTTP://WWW.TUOFERTON.MX', '002', 'SQUIROZ', '12345', '2017-05-14 04:08:45', '2017-05-14 04:08:45'),
(3, 0, 'ASD', 'ASD', 'ASD', '2017-05-13', 'ASD', 'ASD', 'ASD', 'ASD', 'ASD', 'Querétaro', 'ASD', 'SD', 'S', 'ASD', 'ASD', 'ASD', '003', '123', '123', '2017-05-14 04:21:24', '2017-05-14 04:21:24');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Codigo` (`Codigo`),
  ADD KEY `IDUnidad` (`IDUnidad`);

--
-- Indices de la tabla `articulo_lote`
--
ALTER TABLE `articulo_lote`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDArticulo` (`IDArticulo`);

--
-- Indices de la tabla `articulo_unidad`
--
ALTER TABLE `articulo_unidad`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Usuario` (`Usuario`),
  ADD UNIQUE KEY `Identificador` (`Identificador`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `articulo_lote`
--
ALTER TABLE `articulo_lote`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `articulo_unidad`
--
ALTER TABLE `articulo_unidad`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `articulo`
--
ALTER TABLE `articulo`
  ADD CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`IDUnidad`) REFERENCES `articulo_unidad` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `articulo_lote`
--
ALTER TABLE `articulo_lote`
  ADD CONSTRAINT `articulo_lote_ibfk_1` FOREIGN KEY (`IDArticulo`) REFERENCES `articulo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
