-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-06-2017 a las 20:56:18
-- Versión del servidor: 10.1.19-MariaDB
-- Versión de PHP: 7.0.13

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
  `Codigo` varchar(25) NOT NULL,
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

--
-- Volcado de datos para la tabla `articulo`
--

INSERT INTO `articulo` (`ID`, `Activo`, `Codigo`, `Nombre`, `Descripcion`, `Precio`, `IDUnidad`, `SMaximo`, `SMinimo`, `Existencia`, `FechaCreacion`, `FechaMod`) VALUES
(1, 1, '123', 'PASTEL DE FRESA123', 'PASTEL DE FRESA', '2000.00', 2, 3123, 5221, 18, '2017-05-20 21:44:56', '2017-05-27 03:12:50'),
(2, 1, '1233', 'REBANADA DE VAINILLA', 'REBANADA DE VAINILLA', '12.00', 2, 20, 1, 10, '2017-05-27 20:29:52', '2017-05-27 22:27:48');

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

--
-- Volcado de datos para la tabla `articulo_lote`
--

INSERT INTO `articulo_lote` (`ID`, `IDArticulo`, `Activo`, `Codigo`, `Cantidad`, `FechaElaboracion`, `FecbaCaducidad`, `FechaCreacion`, `FechaMod`) VALUES
(1, 1, 1, 'L001', '15', '2017-05-20', '2018-05-20', '2017-05-20 21:45:57', '2017-05-20 21:45:57'),
(2, 1, 1, 'L002', '3', '2017-05-23', '2017-05-31', '2017-05-23 17:24:27', '2017-05-23 17:24:27'),
(4, 2, 1, 'L01', '5', '2017-05-27', '2017-05-31', '2017-05-27 21:50:59', '2017-05-27 22:06:25'),
(5, 2, 0, 'L928', '3', '2017-05-27', '2017-05-28', '2017-05-27 21:52:21', '2017-05-27 21:56:49'),
(6, 2, 0, 'L021', '5', '2017-05-28', '2017-05-29', '2017-05-27 21:56:07', '2017-05-27 21:56:42'),
(7, 2, 1, 'L98', '5', '2017-05-27', '2017-05-31', '2017-05-27 22:05:52', '2017-05-27 22:05:52');

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

--
-- Volcado de datos para la tabla `articulo_unidad`
--

INSERT INTO `articulo_unidad` (`ID`, `Activo`, `Descripcion`, `NombreCorto`, `FechaCreacion`, `FechaMod`) VALUES
(1, 1, 'Kilogramo', 'Kg', '2017-05-20 21:42:48', '2017-05-27 22:25:55'),
(2, 1, 'Rebanada', 'Rbz', '2017-05-27 02:55:50', '2017-05-27 20:16:30'),
(3, 1, 'Pieza', 'Pz', '2017-05-27 20:12:33', '2017-05-27 20:16:20'),
(4, 1, 'Otro', 'otro', '2017-05-27 20:17:06', '2017-05-27 20:17:06'),
(5, 1, 'Litros', 'Lt', '2017-05-27 22:10:34', '2017-05-27 22:10:34');

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
(2, 1, 'SERGIO', 'QUIROZ', 'PONCE', '1989-08-03', 'GUADALUPE', '638', 'A', '20059', 'GUADALUPE', 'Aguascalientes', 'AGUASCALIENTES', '9163008', '123', '4494630590', 'SERGIO@GMAIL.COM', 'HTTP://WWW.TUOFERTON.MX', '002', 'SQUIROZ', '12345', '2017-05-14 04:08:45', '2017-05-27 15:23:50'),
(3, 0, 'ASD', 'ASD', 'ASD', '2017-05-13', 'ASD', 'ASD', 'ASD', 'ASD', 'ASD', 'Querétaro', 'ASD', 'SD', 'S', 'ASD', 'ASD', 'ASD', '003', '123', '123', '2017-05-14 04:21:24', '2017-05-14 04:21:24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `ID` int(11) NOT NULL,
  `Activo` int(11) NOT NULL DEFAULT '1',
  `EstadoDoc` varchar(25) NOT NULL,
  `Cliente` varchar(50) NOT NULL,
  `RFC` varchar(15) NOT NULL,
  `Domicilio` varchar(255) NOT NULL,
  `NumExt` varchar(10) NOT NULL,
  `NumInt` varchar(10) NOT NULL DEFAULT '0',
  `CP` varchar(10) NOT NULL,
  `Colonia` varchar(255) NOT NULL,
  `Telefono` varchar(15) NOT NULL,
  `SubTotal` decimal(6,2) NOT NULL,
  `IVA` decimal(6,2) NOT NULL,
  `Total` decimal(6,2) NOT NULL,
  `FechaElaboracion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaMod` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_concepto`
--

CREATE TABLE `venta_concepto` (
  `ID` int(11) NOT NULL,
  `IDVenta` int(11) NOT NULL,
  `IDArticulo_Lote` int(11) NOT NULL,
  `Activo` int(11) NOT NULL,
  `ProdCodigo` varchar(25) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Drescripcion` varchar(255) NOT NULL,
  `PrecioUnitario` decimal(6,2) NOT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaMod` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_mp`
--

CREATE TABLE `venta_mp` (
  `ID` int(11) NOT NULL,
  `Codigo` varchar(10) NOT NULL,
  `Descripcion` varchar(25) NOT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaMod` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_pago`
--

CREATE TABLE `venta_pago` (
  `ID` int(11) NOT NULL,
  `IDVenta` int(11) NOT NULL,
  `Activo` int(11) NOT NULL,
  `Monto` decimal(6,2) NOT NULL,
  `IDVenta_MP` int(11) NOT NULL,
  `FechaPago` date NOT NULL,
  `FechaCreacion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FechaMod` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `venta_concepto`
--
ALTER TABLE `venta_concepto`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDVenta` (`IDVenta`),
  ADD KEY `IDArticulo_Lote` (`IDArticulo_Lote`);

--
-- Indices de la tabla `venta_mp`
--
ALTER TABLE `venta_mp`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `venta_pago`
--
ALTER TABLE `venta_pago`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `IDVenta` (`IDVenta`),
  ADD KEY `IDVenta_MP` (`IDVenta_MP`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `articulo`
--
ALTER TABLE `articulo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `articulo_lote`
--
ALTER TABLE `articulo_lote`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `articulo_unidad`
--
ALTER TABLE `articulo_unidad`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `venta_concepto`
--
ALTER TABLE `venta_concepto`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `venta_mp`
--
ALTER TABLE `venta_mp`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `venta_pago`
--
ALTER TABLE `venta_pago`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
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
