-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2023 at 11:10 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shop`
--

-- --------------------------------------------------------

--
-- Table structure for table `buyers`
--

CREATE TABLE `buyers` (
  `buyer_id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `balance` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `buyers`
--

INSERT INTO `buyers` (`buyer_id`, `name`, `balance`) VALUES
(13, 'Zeljko Kamanagic', 22978),
(14, 'Dalibor Maric', 64309),
(15, 'Marko Kosovic', 66000),
(17, 'Rajko Kalezic', 220000),
(18, 'Hamza Igumanovic', 120000),
(19, 'Zorica Slavnic', 33000),
(20, 'Hasan Bobaru', 75000),
(21, 'Teodora Popovic', 33000),
(22, 'Milos Stojnic', 95000),
(23, 'Stanko Kadija', 112000),
(24, 'Sima Simic', 55000);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `product_id` int(11) NOT NULL,
  `label` varchar(40) NOT NULL,
  `price` int(40) NOT NULL,
  `stock` int(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`product_id`, `label`, `price`, `stock`) VALUES
(21, 'Graficka rx6700', 44, 77000),
(23, 'Graficka kartica rx6800 ', 26, 85000),
(24, 'Graficka kartica Nvidia 4060ti', 15, 44000),
(25, 'Graficka kartica Nvidia 4090ti', 11, 180000),
(26, 'Graficka kartica Nvidia 4080', 22, 110000),
(27, 'Procesor Ryzen 3600', 23, 11000),
(28, 'Procesor Ryzen 5600x', 12, 22000),
(29, 'Procesor Ryzen 7 3700x', 21, 28000),
(30, 'Procesor Ryzen 3 3300x', 44, 8600),
(31, 'Procesor Intel i5 12400f', 6, 12000),
(32, 'Procesor Intel i5 10400f', 12, 10400),
(33, 'Procesor i9 10900', 6, 43000),
(34, 'Procesor Intel i7 14700k', 63, 53000),
(35, 'AOC 27B2H Black IPS - Monitor 27', 33, 14877),
(36, 'DELL S2721HN FreeSync IPS', 18, 22000),
(37, 'DELL S2421HN FreeSync IPS', 3, 16000),
(38, 'DELL 34 S3422DW WQHD FreeSync ', 21, 55000),
(39, 'Maticna AMD ASROCK X570 PHANTOM GAMING', 6, 22000),
(40, 'Maticna AMD ASUS PRIME A320M-K', 8, 6500),
(41, 'Maticna AMD ASUS PRIME A520M-K', 55, 7500),
(42, 'Maticna Intel ASUS ROG STRIX Z790-F ', 3, 55000),
(43, ' MATICNA Intel GIGABYTE B760M ', 33, 25000),
(44, 'Maticna Intel ASROCK H310CM-DVS', 12, 31000),
(45, 'HDD SEAGATE 2TB ', 33, 8100),
(46, 'HDD SEAGATE 6TB ST6000VX009 ', 5, 21000),
(47, 'HDD WESTERN DIGITAL 1TB WD10EZRZ Blue', 6, 12000),
(48, 'HDD SEAGATE 1TB SSD	', 33, 17000),
(49, 'Memorija Kingston 4GB 1600MHz', 12, 4500),
(50, 'Memorija  16GB DDR4 2666Mhz Patriot', 120, 5100),
(51, 'Memorija Kingston DIMM DDR4 16GB', 32, 8000);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `sales_id` int(11) NOT NULL,
  `buyer_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `product_amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Triggers `sales`
--
DELIMITER $$
CREATE TRIGGER `update_balance_after_purchase` AFTER INSERT ON `sales` FOR EACH ROW BEGIN
    UPDATE buyers
    SET balance = balance - (SELECT price FROM products WHERE product_id = NEW.product_id) * NEW.product_amount
    WHERE buyer_id = NEW.buyer_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_stock_after_sale` AFTER INSERT ON `sales` FOR EACH ROW BEGIN
    UPDATE products
    SET stock = stock - NEW.product_amount
    WHERE product_id = NEW.product_id;
END
$$
DELIMITER ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buyers`
--
ALTER TABLE `buyers`
  ADD PRIMARY KEY (`buyer_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sales_id`),
  ADD KEY `fk_buyer` (`buyer_id`),
  ADD KEY `fk_products` (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buyers`
--
ALTER TABLE `buyers`
  MODIFY `buyer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `sales_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `fk_buyer` FOREIGN KEY (`buyer_id`) REFERENCES `buyers` (`buyer_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_products` FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
