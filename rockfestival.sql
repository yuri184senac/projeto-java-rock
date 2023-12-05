-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 04/12/2023 às 21:43
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `rockfestival`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `banda`
--

CREATE TABLE `banda` (
  `id_banda` int(11) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `genero` varchar(30) DEFAULT NULL,
  `pais` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `banda`
--

INSERT INTO `banda` (`id_banda`, `nome`, `genero`, `pais`) VALUES
(5055, 'Iron Maiden', 'POWER METAL', 'Inglaterra');

-- --------------------------------------------------------

--
-- Estrutura para tabela `integrantes`
--

CREATE TABLE `integrantes` (
  `id_integrantes` int(11) NOT NULL,
  `id_banda` int(11) DEFAULT NULL,
  `vocalista` varchar(50) DEFAULT NULL,
  `baterista` varchar(50) DEFAULT NULL,
  `baixista` varchar(50) DEFAULT NULL,
  `guitarrista1` varchar(50) DEFAULT NULL,
  `guitarrista2` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `integrantes`
--

INSERT INTO `integrantes` (`id_integrantes`, `id_banda`, `vocalista`, `baterista`, `baixista`, `guitarrista1`, `guitarrista2`) VALUES
(7846, 5055, 'Brucke', 'Fulano', NULL, 'Beltrano2', 'Ciclano');

-- --------------------------------------------------------

--
-- Estrutura para tabela `shows`
--

CREATE TABLE `shows` (
  `id_show` int(11) NOT NULL,
  `id_banda` int(11) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `pais` varchar(30) DEFAULT NULL,
  `data_do_show` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `banda`
--
ALTER TABLE `banda`
  ADD PRIMARY KEY (`id_banda`);

--
-- Índices de tabela `integrantes`
--
ALTER TABLE `integrantes`
  ADD PRIMARY KEY (`id_integrantes`),
  ADD UNIQUE KEY `id_banda` (`id_banda`);

--
-- Índices de tabela `shows`
--
ALTER TABLE `shows`
  ADD PRIMARY KEY (`id_show`),
  ADD KEY `id_banda` (`id_banda`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `integrantes`
--
ALTER TABLE `integrantes`
  ADD CONSTRAINT `integrantes_ibfk_1` FOREIGN KEY (`id_banda`) REFERENCES `banda` (`id_banda`) ON DELETE CASCADE;

--
-- Restrições para tabelas `shows`
--
ALTER TABLE `shows`
  ADD CONSTRAINT `shows_ibfk_1` FOREIGN KEY (`id_banda`) REFERENCES `banda` (`id_banda`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
