-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 28 nov. 2024 à 10:12
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db5`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `address`, `created_at`, `email`, `first_name`, `last_name`, `phone`, `status`) VALUES
(1, 'Grand Rapids,USA', '2024-11-26 16:57:57.000000', 'cloe.mills@gmail.com', 'Chloe', 'Mills', '1-785-387-9835', 'New'),
(2, 'Frederic, USA', '2024-11-26 16:57:57.000000', 'adrea.may@outlook.com', 'Andrea', 'May', '1-556-905-1765', 'Lead'),
(3, 'New York, USA', '2024-11-26 16:57:57.000000', 'sam.grant@gmail.com', 'Sam', 'Grant', '1-585-897-0773', 'Occasional'),
(4, 'Rochester,USA', '2024-11-26 16:57:57.000000', 'jan.lambert@yahoo.com', 'Jan', 'Lambert', '1-339-932-9375', 'Permanent'),
(5, 'Indianapolis,USA', '2024-11-26 16:57:57.000000', 'peter.harris@gamil.com', 'Peter', 'Harris', '1-441-498-4573', 'Lead'),
(6, 'Jonesboro,USA', '2024-11-26 16:57:57.000000', 'simon.walsh@outlook.com', 'Simon', 'Walsh', '1-785-387-9840', 'Occasional'),
(7, 'Indianapolis,USA', '2024-11-26 16:57:57.000000', 'harry.russell@gamil.com', 'Harry', 'Russell', '1-566-905-1745', 'Lead'),
(8, 'Grand Rapids,USA', '2024-11-26 16:57:57.000000', 'angela.knox@yahoo.com', 'Angela', 'Knox', '1-585-897-0754', 'Inactive'),
(9, 'Frederick,USA', '2024-11-26 16:57:57.000000', 'maria.paterson@gamil.com', 'Maria', 'Paterson', '1-339-932-9342', 'Lead'),
(10, 'New York,USA', '2024-11-26 16:57:57.000000', 'vanessa.lambert@gmail.com', 'Vanessa', 'Lambert', '1-441-498-4553', 'Occasional'),
(11, 'Rochester,USA', '2024-11-26 16:57:57.000000', 'frank.hodges@outlook.com', 'Frank', 'Hodges', '1-785-387-9880', 'Permanent'),
(12, 'Idianapolis,USA', '2024-11-26 16:57:57.000000', 'ave.wallace@gmail.com', 'Ava', 'Wallace', '1-566-905-1715', 'Lead'),
(13, 'Jonesboro,USA', '2024-11-26 16:57:57.000000', 'neil.wallace@yahoo.com', 'Neil', 'Wallace', '1-585-897-0746', 'Inactive'),
(14, 'Florida,USA', '2024-11-26 16:57:57.000000', 'sebastien.grant@outlook.com', 'Sebastien', 'Grant', '1-339-932-9323', 'Inactive'),
(16, 'Indianapolis, USA', '2024-11-27 07:10:11.000000', 'adam@yahoo.com', 'Adam', 'Gray', '1-785-387-9800', 'Permanent');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKsrv16ica2c1csub334bxjjb59` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
