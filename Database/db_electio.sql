-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 03, 2014 at 11:58 AM
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tbl_election`
--

INSERT INTO `tbl_election` (`id`, `election_commissioner_email`, `name`, `description`, `requirements`, `type_id`, `created_at`, `nomination_start`, `nomination_end`, `withdrawal_start`, `withdrawal_end`, `voting_start`, `voting_end`, `petition_duration`) VALUES
(1, 'avaiyahm@yahoo.com', 'Best mobile', 'choose which mobile is best considering features and price.', '<p>Should be smart phone</p>\r\n<p>&nbsp;</p>', 2, '2014-11-01 17:01:35', '2014-10-19 18:40:00', '2014-10-19 18:50:00', '2014-10-23 11:53:00', '2014-10-19 18:50:00', '2014-10-20 10:50:00', '2014-10-20 06:20:00', 5),
(2, 'avaiyahm@yahoo.com', 'Class Representative 2013', 'Choose CR for ur 2013 batch', '<p>No backlog</p>', 1, '2014-11-02 12:53:30', '2014-10-09 05:00:00', '2014-10-19 18:50:00', '2014-10-20 10:50:00', '2014-10-19 18:50:00', '2014-10-19 19:20:00', '2014-10-20 06:20:00', 2);

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
  `petition_filed` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_election_candidate`
--

INSERT INTO `tbl_election_candidate` (`email`, `election_id`, `requirements_file`, `votes`, `manifesto`, `petition_filed`) VALUES
('avaiyahardik@gmail.com', 1, 'requirements_files\\1414863678404.pdf', 0, 'no manifesto', 0),
('sen.daiict@gmail.com', 1, 'requirements_files\\1414861505802.pdf', 0, 'no manifesto', 0);

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
('avaiyahm@yahoo.com', 'Hardik', 'Avaiya', '9737808095', 1, 'hardik');

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
('201312011@daiict.ac.in', 1, 'requirements_files\\1414912290227.pdf', 0),
('avaiyahardik@gmail.com', 1, 'requirements_files\\1414863678404.pdf', 1),
('sen.daiict@gmail.com', 1, 'requirements_files\\1414861505802.pdf', 1);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `tbl_organization`
--

INSERT INTO `tbl_organization` (`id`, `name`, `address`, `about`) VALUES
(1, 'DA-IICT', 'Gandhinagar', 'Best'),
(2, 'DA-IICT', 'China', 'samsung organization'),
(3, 'DA-IICT', 'China', 'samsung organization'),
(4, 'DA-IICT', 'China', 'about'),
(5, 'DA-IICT', 'China', 'about'),
(6, 'DA-IICT', 'Gandhinagar', 'Best'),
(7, 'DA-IICT', 'Gandhinagar', 'Best');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_probable_nominee`
--

CREATE TABLE IF NOT EXISTS `tbl_probable_nominee` (
  `election_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`election_id`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rejected_nominee`
--

CREATE TABLE IF NOT EXISTS `tbl_rejected_nominee` (
  `election_id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `reason` text NOT NULL,
  PRIMARY KEY (`election_id`,`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
('201312011@daiict.ac.in', 'Moto G', '2nd Gen', 0, '9737808095', 6, 'user_images\\1414912290131.png', 'g'),
('avaiyahardik@gmail.com', 'Redmi', '1S', 0, '9737808095', 5, 'user_images\\1414863678401.png', 'redmi'),
('sen.daiict@gmail.com', 'Samsung', 'Core 2 Dual', 0, '9737808095', 3, 'user_images\\1414861505801.png', 'samsung');

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
('avaiyahardik@gmail.com', 1, 'WD74TLu$', 0),
('sen.daiict@gmail.com', 1, 'pwdpwdpwd', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
