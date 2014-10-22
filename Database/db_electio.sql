-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 22, 2014 at 05:09 PM
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
-- Table structure for table `tbl_election`
--

CREATE TABLE IF NOT EXISTS `tbl_election` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `election_commissioner_email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `requirements` text,
  `type_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `nomination_start` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `nomination_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `withdrawal_start` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `withdrawal_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `voting_start` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `voting_end` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `petition_duration` int(2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `tbl_election`
--

INSERT INTO `tbl_election` (`id`, `election_commissioner_email`, `name`, `description`, `requirements`, `type_id`, `created_at`, `nomination_start`, `nomination_end`, `withdrawal_start`, `withdrawal_end`, `voting_start`, `voting_end`, `petition_duration`) VALUES
(7, 'avaiyahm@yahoo.com', 'Election1', 'Desc1', '<p>Hi there, if you wanna apply as a candidate you must satisfy all requiremets :D :D :D</p>', 1, '2014-10-22 14:07:01', '2014-10-22 08:50:00', '2014-10-22 08:50:00', '2014-10-19 19:00:00', '2014-10-22 08:50:00', '2014-10-19 19:20:00', '2014-10-20 06:10:00', 6),
(8, 'avaiyahm@yahoo.com', 'Election5', 'Description5', '<p>Requirements5</p>', 1, '2014-10-22 14:12:24', '2014-10-18 15:30:00', '2014-10-20 10:50:00', '2014-10-22 15:30:00', '2014-10-20 10:50:00', '2014-10-22 16:30:00', '2014-10-22 16:30:00', 9);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_candidate`
--

CREATE TABLE IF NOT EXISTS `tbl_election_candidate` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `requirements_file` text NOT NULL,
  `votes` int(11) NOT NULL,
  `manifesto` text NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_commissioner`
--

CREATE TABLE IF NOT EXISTS `tbl_election_commissioner` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(64) NOT NULL,
  `lastname` varchar(64) NOT NULL,
  `mobile` char(10) NOT NULL,
  `organization_id` int(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_election_commissioner`
--

INSERT INTO `tbl_election_commissioner` (`email`, `firstname`, `lastname`, `mobile`, `organization_id`, `password`) VALUES
('avaiyahm@yahoo.com', 'Hardik', 'Avaiya', '9737808095', 4, 'hardik');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_nominee`
--

CREATE TABLE IF NOT EXISTS `tbl_election_nominee` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `requirements_file` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_election_nominee`
--

INSERT INTO `tbl_election_nominee` (`email`, `election_id`, `requirements_file`, `status`) VALUES
('avaiyahardik@gmail.com', 7, 'requirements_path', 0),
('keval@gmail.com', 8, 'requirements_path', 0);

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
-- Table structure for table `tbl_organization`
--

CREATE TABLE IF NOT EXISTS `tbl_organization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `address` text NOT NULL,
  `about` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `tbl_organization`
--

INSERT INTO `tbl_organization` (`id`, `name`, `address`, `about`) VALUES
(1, 'DA-IICT', 'Gandhinagar', 'Best'),
(2, 'DA-IICT', 'Gandhinagar', 'Best'),
(3, 'DA-IICT', 'Gandhinagar', 'Best'),
(4, 'DA-IICT', '464, Barpara street', 'Best'),
(5, 'DA-IICT', '76, Krishnapark Society', 'Best'),
(6, 'DA-IICT', '76, Krishnapark Society', 'Best'),
(7, 'org_name', '76, Krishnapark Society', 'Organization'),
(8, 'org_name', '76, Krishnapark Society', 'Organization');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user_info`
--

CREATE TABLE IF NOT EXISTS `tbl_user_info` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(64) NOT NULL,
  `lastname` varchar(64) NOT NULL,
  `mobile` char(10) NOT NULL,
  `organization_id` int(11) NOT NULL,
  `image` text NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user_info`
--

INSERT INTO `tbl_user_info` (`email`, `firstname`, `lastname`, `mobile`, `organization_id`, `image`, `password`) VALUES
('avaiyahardik@gmail.com', 'Avaiya', 'Maheshbhai', '9737808095', 6, 'Image_path', 'a'),
('keval@gmail.com', 'Keval', 'Himmatbhai', '9737808095', 8, 'Image_path', 'k');

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
