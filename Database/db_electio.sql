-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 13, 2014 at 10:05 AM
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
  `name` text NOT NULL,
  `description` text,
  `requirements` text,
  `type_id` int(1) NOT NULL,
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
(1, 'avaiyahm@yahoo.com', 'Class Representative 2014', 'This election is being carried out to choose class representative for MScIT 2014 batch', '<p>No backlogs</p>\r\n<p>Pointer criteria 6.0</p>\r\n<p>Should be student of MScIT 2014 batch</p>', 1, '2014-11-13 08:19:02', '2014-11-09 11:00:00', '2014-11-12 11:00:00', '2014-11-09 11:00:00', '2014-12-03 11:00:00', '2014-11-09 11:00:00', '2014-11-20 11:00:00', 2),
(2, 'avaiyahm@yahoo.com', 'Darshit World', 'this is darshit world', '<p>no requirements</p>', 2, '2014-11-13 08:19:40', '2014-10-15 09:30:00', '2015-05-06 09:30:00', '2014-11-11 09:30:00', '2014-11-11 09:30:00', '2014-11-11 09:30:00', '2015-01-07 09:30:00', 2);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_candidate`
--

CREATE TABLE IF NOT EXISTS `tbl_election_candidate` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `requirements_file` varchar(255) NOT NULL,
  `votes` int(11) NOT NULL,
  `manifesto` varchar(255) DEFAULT NULL,
  `petition_filed` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_election_candidate`
--

INSERT INTO `tbl_election_candidate` (`email`, `election_id`, `requirements_file`, `votes`, `manifesto`, `petition_filed`) VALUES
('201312011@daiict.ac.in', 1, 'requirements_files\\1415561617869.pdf', 40, 'manifestos/electio.pdf', 0),
('201312011@daiict.ac.in', 2, 'requirements_files\\1415793477536.pdf', 20, 'manifestos/electio.pdf', 0),
('201312031@daiict.ac.in', 2, 'requirements_files\\1415607386296.pdf', 30, 'manifestos/electio.pdf', 0),
('201312067@daiict.ac.in', 1, 'requirements_files\\1415561055540.pdf', 20, 'manifestos/electio.pdf', 0),
('avaiyahardik@gmail.com', 1, 'requirements_files\\1415537193958.pdf', 50, 'manifestos/electio.pdf', 0);

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
('201312038@daiict.ac.in', 'Darshit', 'Mashar', '4567895488', 24, 'c355ed137a60060d89c63e67c4e4acc4'),
('201312067@daiict.ac.in', 'Nirma', 'nirma', '5555555555', 24, 'c355ed137a60060d89c63e67c4e4acc4'),
('avaiyahardik@gmail.com', 'Avaiya', 'Maheshbhai', 'sad', 25, 'c355ed137a60060d89c63e67c4e4acc4'),
('avaiyahm@yahoo.com', 'Hardik', 'Avaiya', '9737809095', 24, 'c355ed137a60060d89c63e67c4e4acc4');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_nominee`
--

CREATE TABLE IF NOT EXISTS `tbl_election_nominee` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `requirements_file` varchar(255) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_election_nominee`
--

INSERT INTO `tbl_election_nominee` (`email`, `election_id`, `requirements_file`, `status`) VALUES
('201312011@daiict.ac.in', 1, 'requirements_files\\1415561617869.pdf', 1),
('201312011@daiict.ac.in', 2, 'requirements_files\\1415793477536.pdf', 1),
('201312031@daiict.ac.in', 2, 'requirements_files\\1415607386296.pdf', 1),
('201312067@daiict.ac.in', 1, 'requirements_files\\1415561055540.pdf', 1),
('avaiyahardik@gmail.com', 1, 'requirements_files\\1415537193958.pdf', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_election_type`
--

CREATE TABLE IF NOT EXISTS `tbl_election_type` (
  `type_id` int(1) NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=28 ;

--
-- Dumping data for table `tbl_organization`
--

INSERT INTO `tbl_organization` (`id`, `name`, `address`, `about`) VALUES
(24, 'DA-IICT', 'Gandhinagar1', 'Best'),
(25, 'PDPU', 'Gandhinagar', 'thoda kum achchha he'),
(26, 'Nirma', 'Gandhinagar', 'Thodi Ochhi best'),
(27, 'Nirma123', 'Gandhinagar123', 'Thodi Ochhi best123');

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

--
-- Dumping data for table `tbl_probable_nominee`
--

INSERT INTO `tbl_probable_nominee` (`election_id`, `email`, `status`) VALUES
(1, '201312031@daiict.ac.in', 1),
(1, '201312038@daiict.ac.in', 1),
(1, '201312064@daiict.ac.in', 1),
(1, '201312067@daiict.ac.in', 2),
(1, '201312083@daiict.ac.in', 1),
(1, 'avaiyahardik@gmail.com', 2),
(1, 'email1@domain.com', 1),
(1, 'email2@domain.com', 1),
(1, 'email3@domain.com', 1),
(1, 'email4@domain.com', 1),
(1, 'email5@domain.com', 1),
(1, 'email6@domain.com', 1),
(1, 'email7@domain.com', 1),
(1, 'email8@domain.com', 1);

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

--
-- Dumping data for table `tbl_rejected_nominee`
--

INSERT INTO `tbl_rejected_nominee` (`election_id`, `email`, `reason`) VALUES
(1, '201312011@daiict.ac.in', 'Aap ki photo pasand nai aai :P');

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
  `image` varchar(255) NOT NULL,
  `password` text NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user_info`
--

INSERT INTO `tbl_user_info` (`email`, `firstname`, `lastname`, `gender`, `mobile`, `organization_id`, `image`, `password`) VALUES
('201312011@daiict.ac.in', 'Pooja', 'Singn', 1, '7894562135', 26, 'user_images\\1415791930816.jpg', 'c355ed137a60060d89c63e67c4e4acc4'),
('201312031@daiict.ac.in', 'Vishal', 'Jain', 1, '5236541252', 26, 'user_images\\1415607386258.jpg', 'c355ed137a60060d89c63e67c4e4acc4'),
('201312067@daiict.ac.in', 'Hardik', 'Avaiya', 0, '9737808097', 24, 'user_images\\1415732145925.jpg', 'c355ed137a60060d89c63e67c4e4acc4'),
('avaiyahardik@gmail.com', 'Avaiya', 'Lalaeet', 0, '9737808095', 24, 'user_images\\1415537193956.jpg', 'c355ed137a60060d89c63e67c4e4acc4');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_voter`
--

CREATE TABLE IF NOT EXISTS `tbl_voter` (
  `email` varchar(255) NOT NULL,
  `election_id` int(11) NOT NULL,
  `password` text NOT NULL,
  `status` tinyint(1) NOT NULL,
  `link_status` tinyint(1) NOT NULL,
  PRIMARY KEY (`email`,`election_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_voter`
--

INSERT INTO `tbl_voter` (`email`, `election_id`, `password`, `status`, `link_status`) VALUES
('20131201@daiict.ac.in', 1, '1cca6b52a3f66f780c15844732c951e1', 0, 1),
('201312067@daiict.ac.in', 1, 'a768ea2aadd3e82bbabf7c6e65c506d5', 1, 1),
('201312083@daiict.ac.in', 1, 'efa14852379a451e25378c02c40e6a39', 0, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
