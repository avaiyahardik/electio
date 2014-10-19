-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 19, 2014 at 07:18 AM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_electio`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_candidate`
--

CREATE TABLE IF NOT EXISTS `tbl_candidate` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `firstname` varchar(64) NOT NULL,
  `lastname` varchar(64) NOT NULL,
  `criteria` text NOT NULL,
  `password` varchar(20) NOT NULL,
  `votes` int(11) NOT NULL,
  `mobile` char(10) NOT NULL,
  `image` text NOT NULL,
  `manifesto` text NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election`
--

CREATE TABLE IF NOT EXISTS `tbl_election` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `election_commissioner_email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `criteria` text NOT NULL,
  `type_id` int(11) NOT NULL,
  `nomination_start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nomination_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `withdrawal_start` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `withdrawal_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `voting_start` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `voting_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `petition_duration` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_commissioner`
--

CREATE TABLE IF NOT EXISTS `tbl_election_commissioner` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(64) NOT NULL,
  `lastname` varchar(64) NOT NULL,
  `mobile` char(10) NOT NULL,
  `organization` text NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_type`
--

CREATE TABLE IF NOT EXISTS `tbl_election_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tbl_election_type`
--

INSERT INTO `tbl_election_type` (`type_id`, `type`, `description`) VALUES
(1, 'Preferential', ''),
(2, 'Weighted', '');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_nominator`
--

CREATE TABLE IF NOT EXISTS `tbl_nominator` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `firstname` varchar(64) NOT NULL,
  `lastname` varchar(64) NOT NULL,
  `mobile` char(10) NOT NULL,
  `criteria` text NOT NULL,
  `password` varchar(20) NOT NULL,
  `image` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_voter`
--

CREATE TABLE IF NOT EXISTS `tbl_voter` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
