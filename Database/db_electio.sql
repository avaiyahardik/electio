-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 31, 2014 at 07:19 PM
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tbl_election`
--

INSERT INTO `tbl_election` (`id`, `election_commissioner_email`, `name`, `description`, `requirements`, `type_id`, `created_at`, `nomination_start`, `nomination_end`, `withdrawal_start`, `withdrawal_end`, `voting_start`, `voting_end`, `petition_duration`) VALUES
(1, 'avaiyahm@yahoo.com', 'Moviee Chooser', 'Desc', '<p>Req</p>', 2, '2014-10-30 08:01:52', '2014-10-30 09:00:00', '2014-10-30 09:00:00', '2014-10-30 09:00:00', '2014-10-30 09:00:00', '2014-10-30 10:00:00', '2014-10-30 10:30:00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_candidate`
--

CREATE TABLE IF NOT EXISTS `tbl_election_candidate` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `requirements_file` text NOT NULL,
  `votes` int(11) NOT NULL,
  `manifesto` text,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_election_candidate`
--

INSERT INTO `tbl_election_candidate` (`email`, `election_id`, `requirements_file`, `votes`, `manifesto`) VALUES
('nikita@birmi.com', 1, 'requirements_files\\1414660880004.pdf', 0, 'no manifesto');

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
  `password` text NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_election_commissioner`
--

INSERT INTO `tbl_election_commissioner` (`email`, `firstname`, `lastname`, `mobile`, `organization_id`, `password`) VALUES
('avaiyahm@yahoo.com', 'Hardik', 'Avaiya', '9737808095', 18, '68RwbksDp7kBJ6Nk7p3kunMKQG0=');

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
('akashvirani174@gmail.com', 9, 'requirements_files\\1414657575359..pdf', 0),
('nikita@birmi.com', 1, 'requirements_files\\1414660880004.pdf', 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `tbl_organization`
--

INSERT INTO `tbl_organization` (`id`, `name`, `address`, `about`) VALUES
(18, 'DA-IICT', 'Gandhinagar', 'Best College'),
(19, 'DA-IICT', '464, Barpara street', 'Best'),
(20, 'DA-IICT', 'Gandhinagar', 'Best');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user_info`
--

CREATE TABLE IF NOT EXISTS `tbl_user_info` (
  `email` varchar(255) NOT NULL,
  `firstname` varchar(64) NOT NULL,
  `lastname` varchar(64) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `mobile` char(10) NOT NULL,
  `organization_id` int(11) NOT NULL,
  `image` text NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user_info`
--

INSERT INTO `tbl_user_info` (`email`, `firstname`, `lastname`, `gender`, `mobile`, `organization_id`, `image`, `password`) VALUES
('akashvirani174@gmail.com', 'Hardik', 'Avaiya', 0, '9197378080', 19, 'user_images\\1414657575317..png', 'a'),
('nikita@birmi.com', 'Niki', 'Birmi', 0, '9999999999', 20, 'user_images\\1414660880003.png', 'n');

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

--
-- Dumping data for table `tbl_voter`
--

INSERT INTO `tbl_voter` (`email`, `election_id`, `password`, `status`) VALUES
('sen.daiict@gmail.com', 1, 'XS45M&cg', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
