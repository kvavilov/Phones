-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Хост: localhost
-- Время создания: Апр 30 2017 г., 19:50
-- Версия сервера: 5.6.35
-- Версия PHP: 5.5.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `Phones`
--
CREATE DATABASE IF NOT EXISTS `Phones` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `Phones`;

-- --------------------------------------------------------

--
-- Структура таблицы `Departments`
--

DROP TABLE IF EXISTS `Departments`;
CREATE TABLE IF NOT EXISTS `Departments` (
  `Id` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Region` int(11) NOT NULL,
  `IsNotUsed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `Departments_contacts`
--

DROP TABLE IF EXISTS `Departments_contacts`;
CREATE TABLE IF NOT EXISTS `Departments_contacts` (
  `Department_id` int(5) NOT NULL,
  `Info_type` int(1) NOT NULL,
  `Info_value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `Employers`
--

DROP TABLE IF EXISTS `Employers`;
CREATE TABLE IF NOT EXISTS `Employers` (
  `ID` int(10) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `MiddleName` varchar(255) NOT NULL,
  `LastName` varchar(255) NOT NULL,
  `Department_ID` int(5) NOT NULL,
  `IsNotUsed` tinyint(1) NOT NULL,
  `IsChief` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `Employers_contacts`
--

DROP TABLE IF EXISTS `Employers_contacts`;
CREATE TABLE IF NOT EXISTS `Employers_contacts` (
  `Employer_ID` int(10) NOT NULL,
  `Info_type` int(1) NOT NULL,
  `Info_value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `Regions`
--

DROP TABLE IF EXISTS `Regions`;
CREATE TABLE IF NOT EXISTS `Regions` (
  `ID` int(11) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `IsNotUsed` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Структура таблицы `Users`
--

DROP TABLE IF EXISTS `Users`;
CREATE TABLE IF NOT EXISTS `Users` (
  `ID` int(5) NOT NULL,
  `UserName` varchar(50) NOT NULL,
  `UserDescription` varchar(255) NOT NULL,
  `IsInactive` tinyint(1) NOT NULL,
  `PasswordHash` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `Departments`
--
ALTER TABLE `Departments`
  ADD PRIMARY KEY (`Id`,`Region`);

--
-- Индексы таблицы `Departments_contacts`
--
ALTER TABLE `Departments_contacts`
  ADD PRIMARY KEY (`Department_id`,`Info_type`),
  ADD UNIQUE KEY `department_id` (`Department_id`,`Info_type`);

--
-- Индексы таблицы `Employers`
--
ALTER TABLE `Employers`
  ADD PRIMARY KEY (`ID`,`IsFired`,`Department_ID`);

--
-- Индексы таблицы `Employers_contacts`
--
ALTER TABLE `Employers_contacts`
  ADD PRIMARY KEY (`Employer_ID`,`Info_type`);

--
-- Индексы таблицы `Regions`
--
ALTER TABLE `Regions`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `Departments`
--
ALTER TABLE `Departments`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `Employers`
--
ALTER TABLE `Employers`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `Regions`
--
ALTER TABLE `Regions`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT для таблицы `Users`
--
ALTER TABLE `Users`
  MODIFY `ID` int(5) NOT NULL AUTO_INCREMENT;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
