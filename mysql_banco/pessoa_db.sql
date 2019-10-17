-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Tempo de geração: 16/10/2019 às 22:18
-- Versão do servidor: 10.1.38-MariaDB-0ubuntu0.18.04.1
-- Versão do PHP: 7.2.15-0ubuntu0.18.04.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `pessoa_db`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `pessoa_tb`
--

CREATE TABLE `pessoa_tb` (
  `id` int(13) NOT NULL,
  `nome` text NOT NULL,
  `endereco` text NOT NULL,
  `complemento` text NOT NULL,
  `numero` text NOT NULL,
  `cep` text NOT NULL,
  `tel` text NOT NULL,
  `cpf` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Fazendo dump de dados para tabela `pessoa_tb`
--

INSERT INTO `pessoa_tb` (`id`, `nome`, `endereco`, `complemento`, `numero`, `cep`, `tel`, `cpf`, `timestamp`) VALUES
(1, 'Testando', 'Endereco', 'complemento', '52', '4234234', '3123123232', '454343423', '0000-00-00 00:00:00');

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `pessoa_tb`
--
ALTER TABLE `pessoa_tb`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de tabelas apagadas
--

--
-- AUTO_INCREMENT de tabela `pessoa_tb`
--
ALTER TABLE `pessoa_tb`
  MODIFY `id` int(13) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
