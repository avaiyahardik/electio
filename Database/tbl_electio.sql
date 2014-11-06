-- phpMyAdmin SQL Dump
-- version 2.11.6
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 06, 2014 at 02:02 PM
-- Server version: 5.0.51
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


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

CREATE TABLE `tbl_election` (
  `id` int(11) NOT NULL auto_increment,
  `election_commissioner_email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text,
  `requirements` text,
  `type_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `nomination_start` timestamp NOT NULL default '0000-00-00 00:00:00',
  `nomination_end` timestamp NOT NULL default '0000-00-00 00:00:00',
  `withdrawal_start` timestamp NOT NULL default '0000-00-00 00:00:00',
  `withdrawal_end` timestamp NOT NULL default '0000-00-00 00:00:00',
  `voting_start` timestamp NOT NULL default '0000-00-00 00:00:00',
  `voting_end` timestamp NOT NULL default '0000-00-00 00:00:00',
  `petition_duration` int(2) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tbl_election`
--

INSERT INTO `tbl_election` (`id`, `election_commissioner_email`, `name`, `description`, `requirements`, `type_id`, `created_at`, `nomination_start`, `nomination_end`, `withdrawal_start`, `withdrawal_end`, `voting_start`, `voting_end`, `petition_duration`, `status`) VALUES
(1, 'avaiyahm@yahoo.com', 'Best mobile', 'choose which mobile is best considering features and price.', '<p>Should be smart phone</p>\r\n<p>&nbsp;</p>', 2, '2014-11-01 22:31:35', '2014-10-20 00:10:00', '2014-10-20 00:20:00', '2014-10-23 17:23:00', '2014-10-20 00:20:00', '2014-10-20 16:20:00', '2014-10-20 11:50:00', 5, 0),
(2, 'avaiyahm@yahoo.com', 'Class Representative 2013', 'Choose CR for ur 2013 batch', '<p>No backlog</p>', 1, '2014-11-02 18:23:30', '2014-10-09 10:30:00', '2014-10-20 00:20:00', '2014-10-20 16:20:00', '2014-10-20 00:20:00', '2014-10-20 00:50:00', '2014-10-20 11:50:00', 2, 0);
