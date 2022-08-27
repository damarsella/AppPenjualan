-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: Aug 27, 2022 at 04:44 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `barang`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kodebarang` varchar(6) NOT NULL,
  `namabarang` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `belibarang`
--

CREATE TABLE `belibarang` (
  `kodebeli` varchar(18) NOT NULL,
  `kodestock` varchar(12) NOT NULL,
  `namasupplier` varchar(20) NOT NULL,
  `namakategori` varchar(20) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `namasatuan` varchar(20) NOT NULL,
  `jumlahbarang` int(12) NOT NULL,
  `hargabeli` int(12) NOT NULL,
  `total` int(12) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `datauntung`
--

CREATE TABLE `datauntung` (
  `kodeuntung` int(11) NOT NULL,
  `namabarang` int(11) NOT NULL,
  `jumlahjual` int(11) NOT NULL,
  `untung` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `jualbarang`
--

CREATE TABLE `jualbarang` (
  `faktur` varchar(21) NOT NULL,
  `kodepalanggan` varchar(6) NOT NULL,
  `namapelanggan` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `janiskelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `nomortelepon` varchar(12) NOT NULL,
  `kodebarang` varchar(12) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `namasatuan` varchar(12) NOT NULL,
  `hargajual` int(12) NOT NULL,
  `jumlahjual` int(4) NOT NULL,
  `hargajualtotal` int(12) NOT NULL,
  `bayar` int(12) NOT NULL,
  `kembali` int(12) NOT NULL,
  `untung` int(12) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `kodekategori` varchar(6) NOT NULL,
  `namakategori` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

CREATE TABLE `login` (
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `jenis_kelamin` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `alamat` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`, `jenis_kelamin`, `email`, `no_telp`, `alamat`) VALUES
('admin', 'admin', 'Laki-laki', 'admin@gmail.com', '0892829292', 'Jakarta');

-- --------------------------------------------------------

--
-- Table structure for table `pelanggan`
--

CREATE TABLE `pelanggan` (
  `kodepelanggan` varchar(6) NOT NULL,
  `namapelanggan` varchar(20) NOT NULL,
  `jeniskelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `nomortelepon` varchar(12) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `jenis_kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `email` varchar(20) NOT NULL,
  `no_telp` varchar(12) NOT NULL,
  `agama` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`username`, `password`, `jenis_kelamin`, `email`, `no_telp`, `agama`, `alamat`) VALUES
('admin', 'admin', 'Laki-Laki', 'admin@gmail.com', '081248666660', 'Islam', 'Gedong');

-- --------------------------------------------------------

--
-- Table structure for table `satuan`
--

CREATE TABLE `satuan` (
  `kodesatuan` varchar(6) NOT NULL,
  `namasatuan` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `kodestock` varchar(6) NOT NULL,
  `namakategori` varchar(20) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `namasatuan` varchar(50) NOT NULL,
  `jumlahbarang` int(12) NOT NULL,
  `hargabeli` int(12) NOT NULL,
  `total` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `kodesupplier` varchar(6) NOT NULL,
  `namasupplier` varchar(20) NOT NULL,
  `alamat` varchar(50) NOT NULL,
  `nomortelepon` varchar(12) NOT NULL,
  `norekening` varchar(12) NOT NULL,
  `bank` varchar(10) NOT NULL,
  `email` varchar(20) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kodebarang`);

--
-- Indexes for table `belibarang`
--
ALTER TABLE `belibarang`
  ADD PRIMARY KEY (`kodebeli`);

--
-- Indexes for table `jualbarang`
--
ALTER TABLE `jualbarang`
  ADD PRIMARY KEY (`faktur`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`kodekategori`),
  ADD UNIQUE KEY `namakategori` (`namakategori`);

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`kodepelanggan`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `satuan`
--
ALTER TABLE `satuan`
  ADD PRIMARY KEY (`kodesatuan`),
  ADD UNIQUE KEY `namasatuan` (`namasatuan`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`kodestock`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`kodesupplier`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
