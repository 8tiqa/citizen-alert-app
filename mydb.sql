-- phpMyAdmin SQL Dump
-- version 2.11.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 22, 2015 at 02:46 PM
-- Server version: 5.1.57
-- PHP Version: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `a3785520_mydb`
--

-- --------------------------------------------------------

--
-- Table structure for table `emergency`
--

CREATE TABLE `emergency` (
  `userid` varchar(11) NOT NULL,
  `emergencyid` int(11) NOT NULL,
  `emergencytype` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `locationname` varchar(20) NOT NULL,
  `peopleeffected` int(11) NOT NULL,
  `edate` varchar(20) NOT NULL,
  `lattitude` decimal(10,6) DEFAULT NULL,
  `longitude` decimal(10,6) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emergency`
--

INSERT INTO `emergency` VALUES('1', 18, 'fire', 'Fire caught in shop', 'Quetta Market', 5, '20/01/2015', 30.192017, 67.005912);
INSERT INTO `emergency` VALUES('1', 3, 'health', 'help', 'ghazali', 70, '232', 33.642806, 72.986216);
INSERT INTO `emergency` VALUES('1', 17, 'fire', 'Fire caught in shop', 'Quetta Market', 5, '20/01/2015', 30.192017, 67.005912);
INSERT INTO `emergency` VALUES('1', 16, 'health', 'Bomb blast', 'SeaView Port', 25, '15/01/2014', 24.892454, 67.001499);
INSERT INTO `emergency` VALUES('1', 20, 'fire', '', '', 0, '', 33.669847, 73.141992);
INSERT INTO `emergency` VALUES('8', 21, 'fire', 'Help!', 'Gulberg', 17, '17/01/2015', 31.542048, 74.305564);
INSERT INTO `emergency` VALUES('1', 11, 'crime', 'Bomb blast', 'Niazi Express ', 15, '20/01/2015', 33.688366, 73.034396);
INSERT INTO `emergency` VALUES('8', 14, 'crime', 'Bank Robbery', 'Bhawalpur', 15, '18/01/2015', 29.372841, 71.673415);
INSERT INTO `emergency` VALUES('8', 13, 'health', 'Need Urgent Assistance. Bus Crash.', 'Near Multan', 8, '19/01/2015', 30.290478, 71.503513);
INSERT INTO `emergency` VALUES('1', 19, 'crime', 'Robbery in progress.', 'Rahim Yar Khan', 4, '12/01/2015', 28.430813, 70.293039);
INSERT INTO `emergency` VALUES('8', 22, 'health', 'test', '', 10, '', 31.663358, 70.997109);
INSERT INTO `emergency` VALUES('8', 23, 'fire', '', '', 0, '', 33.644825, 72.992110);
INSERT INTO `emergency` VALUES('8', 24, 'crime', 'Help', 'Pakistan', 5, '', 28.636628, 68.347004);
INSERT INTO `emergency` VALUES('10', 25, 'health', 'Gas Pipeline Blast', 'Jacobabad', 10, '13/01/2015', 28.275985, 68.435450);
INSERT INTO `emergency` VALUES('10', 26, 'crime', 'Robbery', 'Gilgit', 12, '17/01/2015', 35.920275, 74.318582);
INSERT INTO `emergency` VALUES('8', 27, 'crime', 'hhaja', 'lahore', 7, '22/01/2015', 27.653805, 67.297019);
INSERT INTO `emergency` VALUES('8', 28, 'crime', 'All died', 'india', 1000000000, '12-12-2015', 23.022464, 79.442453);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `gender` varchar(20) NOT NULL,
  `email` varchar(28) NOT NULL,
  `cnic` int(20) NOT NULL,
  `phone` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` VALUES(1, 'umair', '1234', '', 'umairmindfreak', 121212, 121212);
INSERT INTO `users` VALUES(3, 'zahid', '1234', '', '12beseuahmad@seecs.e', 0, 23909);
INSERT INTO `users` VALUES(4, 'nauman', 'notics', '', 'notiharoon@noti.com', 12345542, 696969);
INSERT INTO `users` VALUES(5, 'saad', 'saadbhai', 'male', 'saadbhai@noti.com', 122134, 2147483647);
INSERT INTO `users` VALUES(6, 'fireintheh', 'ghq', 'scene', '232', 1221, 300);
INSERT INTO `users` VALUES(7, '', '', 'male', '', 0, 0);
INSERT INTO `users` VALUES(8, 'admin', 'admin', 'male', 'admin@example.com', 33111, 332649764);
INSERT INTO `users` VALUES(9, 'admin', 'admin', 'male', 'admin@example.com', 33111, 332649764);
INSERT INTO `users` VALUES(10, 'user', 'password', 'male', 'user@example.com', 37405, 2147483647);
