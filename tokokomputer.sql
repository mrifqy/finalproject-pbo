-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2022 at 03:12 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tokokomputer`
--

-- --------------------------------------------------------

--
-- Table structure for table `keranjang`
--

CREATE TABLE `keranjang` (
  `id_product` varchar(5) NOT NULL,
  `product_name` varchar(120) NOT NULL,
  `jumlah` int(10) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id_product` varchar(5) NOT NULL,
  `product_name` varchar(120) NOT NULL,
  `stock` int(10) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id_product`, `product_name`, `stock`, `price`) VALUES
('A3212', 'ROG STRIX G G531GT', 22, 18000000),
('A3267', 'Asus Vivobook F16', 6, 13700000),
('A345', 'ROG STRIX G G531GE', 12, 17000000),
('A3450', 'Asus Zenbook Flip', 15, 19000000),
('A3678', 'ROG STRIX G G532GT', 13, 16000000),
('A8321', 'Asus Zenbook 14', 16, 14500000),
('A8374', 'Asus Vivobook F14', 8, 12500000),
('L4435', 'Lenovo Legion Pro 5', 9, 14000000),
('L7968', 'Lenovo Legion Y740', 6, 26000000),
('L8375', 'Lenovo Thinkpad P50', 7, 40700000),
('L8572', 'Lenovo Thinkpad L14', 4, 13500000),
('L8750', 'Lenovo Thinkbook 15', 7, 8670000),
('L9649', 'Lenovo Thinkpad Yoga 14', 3, 14700000),
('M8358', 'MSI GF63 Thin', 28, 13000000),
('N4435', 'HP Pavilion 14', 15, 7500000);

-- --------------------------------------------------------

--
-- Table structure for table `receipt`
--

CREATE TABLE `receipt` (
  `id_receipt` int(11) NOT NULL,
  `id_product` varchar(5) NOT NULL,
  `quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `receipt`
--

INSERT INTO `receipt` (`id_receipt`, `id_product`, `quantity`) VALUES
(1, 'AB123', 6),
(1, 'AB123', 2),
(1, 'AB123', 2),
(2, 'AB123', 4),
(2, 'AB123', 2),
(3, 'AB123', 4),
(4, 'AB123', 6),
(4, 'A1234', 3),
(5, 'AB123', 7),
(5, 'A1234', 4),
(5, 'B1222', 4),
(6, 'A1234', 2),
(6, 'B1222', 1),
(7, 'A1234', 4),
(8, 'A1234', 2),
(8, 'B1222', 3),
(9, 'A1234', 4),
(9, 'YA123', 5),
(10, 'C3213', 10),
(10, 'YA123', 2),
(11, 'A1234', 6),
(11, 'C3212', 4),
(12, 'A1234', 5),
(12, 'C3212', 6);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `status`) VALUES
('1', 'admin', '123', 'admin'),
('2', 'rifqy', '123', 'pembeli'),
('3', 'faras', '123', 'pembeli'),
('4', 'alam', '123', 'pembeli');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id_product`);

--
-- Indexes for table `receipt`
--
ALTER TABLE `receipt`
  ADD KEY `id_product` (`id_product`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
