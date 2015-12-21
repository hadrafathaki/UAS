-- phpMyAdmin SQL Dump
-- version 2.10.3
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Dec 07, 2011 at 05:06 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

-- 
-- Database: `dbcounterhp`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `barang`
-- 

CREATE TABLE `barang` (
  `kd_barang` varchar(6) NOT NULL,
  `nm_barang` varchar(20) NOT NULL,
  `no_seri` varchar(15) NOT NULL,
  `harga` varchar(30) NOT NULL,
  `stok` varchar(30) NOT NULL,
  PRIMARY KEY  (`kd_barang`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `barang`
-- 

INSERT INTO `barang` VALUES ('x001', 'Nokia N900', 'N900', '2300000', '1');
INSERT INTO `barang` VALUES ('K002', 'Blackberry Dakota', 'Dakota', '5600000', '2');

-- --------------------------------------------------------

-- 
-- Table structure for table `pelanggan`
-- 

CREATE TABLE `pelanggan` (
  `kd_pelanggan` varchar(6) NOT NULL,
  `nm_pelanggan` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `notlp` varchar(13) NOT NULL,
  PRIMARY KEY  (`kd_pelanggan`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `pelanggan`
-- 

INSERT INTO `pelanggan` VALUES ('001', 'Heru Rahmat', 'Padang', '9503650');
INSERT INTO `pelanggan` VALUES ('P002', 'Jeni', 'Cendana', '8908293');

-- --------------------------------------------------------

-- 
-- Table structure for table `pembelian`
-- 

CREATE TABLE `pembelian` (
  `no_transaksi` varchar(30) NOT NULL,
  `tgl_transaksi` datetime NOT NULL,
  `kd_supplier` varchar(30) NOT NULL,
  `kd_barang` varchar(30) NOT NULL,
  `harga` varchar(20) NOT NULL,
  `jumlah` varchar(20) NOT NULL,
  `totalharga` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `pembelian`
-- 


-- --------------------------------------------------------

-- 
-- Table structure for table `penjualan`
-- 

CREATE TABLE `penjualan` (
  `no_transaksi` varchar(30) NOT NULL,
  `tgl_transaksi` datetime NOT NULL,
  `kd_barang` varchar(30) NOT NULL,
  `harga` varchar(20) NOT NULL,
  `jumlah` varchar(20) NOT NULL,
  `totalharga` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `penjualan`
-- 

INSERT INTO `penjualan` VALUES ('123', '2011-12-07 00:00:00', 'K002', '5600000', '2', '11200000');

-- --------------------------------------------------------

-- 
-- Table structure for table `supplier`
-- 

CREATE TABLE `supplier` (
  `kd_supplier` varchar(6) NOT NULL,
  `nm_supplier` varchar(30) NOT NULL,
  `alamat` varchar(30) NOT NULL,
  `notlp` varchar(13) NOT NULL,
  PRIMARY KEY  (`kd_supplier`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- 
-- Dumping data for table `supplier`
-- 

INSERT INTO `supplier` VALUES ('P001', 'Nokia', 'Irlandia', '89892830');
INSERT INTO `supplier` VALUES ('S002', 'Blackberry', 'Amerika', '98989903');
