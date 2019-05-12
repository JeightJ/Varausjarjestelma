-- --------------------------------------------------------
-- Verkkotietokone:              127.0.0.1
-- Palvelinversio:               10.3.12-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Versio:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for vp
CREATE DATABASE IF NOT EXISTS `vp` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `vp`;

-- Dumping structure for taulu vp.asiakas
CREATE TABLE IF NOT EXISTS `asiakas` (
  `asiakas_id` int(11) NOT NULL,
  `etunimi` varchar(20) DEFAULT NULL,
  `sukunimi` varchar(40) DEFAULT NULL,
  `lahiosoite` varchar(40) DEFAULT NULL,
  `postitoimipaikka` varchar(30) DEFAULT NULL,
  `postinro` char(5) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `puhelinnro` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`asiakas_id`),
  KEY `Asiakas_sukunimi_index` (`sukunimi`),
  KEY `Asiakas_etunimi_index` (`etunimi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table vp.asiakas: ~3 rows (suunnilleen)
/*!40000 ALTER TABLE `asiakas` DISABLE KEYS */;
INSERT INTO `asiakas` (`asiakas_id`, `etunimi`, `sukunimi`, `lahiosoite`, `postitoimipaikka`, `postinro`, `email`, `puhelinnro`) VALUES
	(1, 'Jyrki', 'Luuserinen', 'Daijukatu 45', 'Joensuu', '80200', 'jyrki.luuserinen@suomi24.fi', '0403456799'),
	(2, 'Jamie', 'Oliver', 'Huvilakatu 2', 'Imatra', '55100', 'jamieoliveri@gmail.com', '0451079344'),
	(3, 'Matti', 'Möttönen', 'Sepänkatu 3 A 9', 'Jyväskylä', '40200', 'mottosenmatti@gmail.com', '0504653211');
/*!40000 ALTER TABLE `asiakas` ENABLE KEYS */;

-- Dumping structure for taulu vp.lasku
CREATE TABLE IF NOT EXISTS `lasku` (
  `lasku_id` int(11) NOT NULL,
  `varaus_id` int(11) DEFAULT NULL,
  `asiakas_id` int(11) DEFAULT NULL,
  `nimi` varchar(60) DEFAULT NULL,
  `lahiosoite` varchar(40) DEFAULT NULL,
  `postitoimipaikka` varchar(30) DEFAULT NULL,
  `postinro` char(5) DEFAULT NULL,
  `summa` double(8,2) NOT NULL,
  `alv` double(8,2) NOT NULL,
  PRIMARY KEY (`lasku_id`),
  UNIQUE KEY `Lasku_varaus_id_index` (`varaus_id`),
  KEY `Lasku_nimi_index` (`nimi`),
  KEY `Lasku_asiakas_id_index` (`asiakas_id`),
  CONSTRAINT `lasku_ibfk_1` FOREIGN KEY (`varaus_id`) REFERENCES `varaus` (`varaus_id`) ON DELETE CASCADE,
  CONSTRAINT `lasku_ibfk_2` FOREIGN KEY (`asiakas_id`) REFERENCES `asiakas` (`asiakas_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table vp.lasku: ~3 rows (suunnilleen)
/*!40000 ALTER TABLE `lasku` DISABLE KEYS */;
INSERT INTO `lasku` (`lasku_id`, `varaus_id`, `asiakas_id`, `nimi`, `lahiosoite`, `postitoimipaikka`, `postinro`, `summa`, `alv`) VALUES
	(1, 1, 1, 'Jyrki Luuserinen', 'Daijukuja 45', 'Joensuu', '80200', 960.00, 5.00),
	(2, 2, 3, 'Jamie Oliver', 'Huvilakatu 2', 'Imatra', '55100', 940.00, 4.50),
	(3, 3, 2, 'Matti Möttönen', 'Sepänkatu 3 A 9', 'Jyväskylä', '40200', 410.00, 2.60);
/*!40000 ALTER TABLE `lasku` ENABLE KEYS */;

-- Dumping structure for taulu vp.palvelu
CREATE TABLE IF NOT EXISTS `palvelu` (
  `palvelu_id` int(11) NOT NULL,
  `toimipiste_id` int(11) DEFAULT NULL,
  `nimi` varchar(40) DEFAULT NULL,
  `tyyppi` int(11) DEFAULT NULL,
  `kuvaus` varchar(255) DEFAULT NULL,
  `hinta` double(8,2) NOT NULL,
  `alv` double(8,2) NOT NULL,
  PRIMARY KEY (`palvelu_id`),
  KEY `Palvelu_nimi_index` (`nimi`),
  KEY `palvelu_toimipiste_id_index` (`toimipiste_id`),
  CONSTRAINT `palvelu_ibfk_1` FOREIGN KEY (`toimipiste_id`) REFERENCES `toimipiste` (`toimipiste_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table vp.palvelu: ~4 rows (suunnilleen)
/*!40000 ALTER TABLE `palvelu` DISABLE KEYS */;
INSERT INTO `palvelu` (`palvelu_id`, `toimipiste_id`, `nimi`, `tyyppi`, `kuvaus`, `hinta`, `alv`) VALUES
	(1, 1, 'Moottorikelkkasafari', NULL, '1 hlö/kelkka', 140.00, 5.00),
	(2, 1, 'Hieronta', NULL, 'Klassinen hieronta 60min', 65.00, 5.00),
	(3, 3, 'Paintball', NULL, '1 hlö. Sisältää aseen vuokran', 30.00, 2.00),
	(4, 2, 'Intiaaninuotio', NULL, 'Sisältää makkarat.', 10.00, 1.00);
/*!40000 ALTER TABLE `palvelu` ENABLE KEYS */;

-- Dumping structure for taulu vp.toimipiste
CREATE TABLE IF NOT EXISTS `toimipiste` (
  `toimipiste_id` int(11) NOT NULL,
  `nimi` varchar(40) DEFAULT NULL,
  `lahiosoite` varchar(40) DEFAULT NULL,
  `postitoimipaikka` varchar(30) DEFAULT NULL,
  `postinro` char(5) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `puhelinnro` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`toimipiste_id`),
  KEY `Toimipiste_nimi_index` (`nimi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table vp.toimipiste: ~3 rows (suunnilleen)
/*!40000 ALTER TABLE `toimipiste` DISABLE KEYS */;
INSERT INTO `toimipiste` (`toimipiste_id`, `nimi`, `lahiosoite`, `postitoimipaikka`, `postinro`, `email`, `puhelinnro`) VALUES
	(1, 'Lomamaja', 'Lomamajankuja 69', 'Tahko', '73311', 'lomamaja@tahko.fi', '017 458 400'),
	(2, 'Tiipiikylä', 'Intiaanitie 32', 'Tahko', '73310', 'tiipiitonkivoja@tahko.fi', '017 484 401'),
	(3, 'Mökkimäki', 'Mökkimäentie 2', 'Tahko', '73310', 'mokkimaki@tahko.fi', '017 481 400');
/*!40000 ALTER TABLE `toimipiste` ENABLE KEYS */;

-- Dumping structure for taulu vp.varauksen_palvelut
CREATE TABLE IF NOT EXISTS `varauksen_palvelut` (
  `varaus_id` int(11) NOT NULL,
  `palvelu_id` int(11) NOT NULL,
  `lkm` int(11) NOT NULL,
  PRIMARY KEY (`palvelu_id`,`varaus_id`),
  KEY `vp_varaus_id_index` (`varaus_id`),
  KEY `vp_palvelu_id_index` (`palvelu_id`),
  CONSTRAINT `varauksen_palvelut_ibfk_1` FOREIGN KEY (`varaus_id`) REFERENCES `varaus` (`varaus_id`) ON DELETE CASCADE,
  CONSTRAINT `varauksen_palvelut_ibfk_2` FOREIGN KEY (`palvelu_id`) REFERENCES `palvelu` (`palvelu_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table vp.varauksen_palvelut: ~3 rows (suunnilleen)
/*!40000 ALTER TABLE `varauksen_palvelut` DISABLE KEYS */;
INSERT INTO `varauksen_palvelut` (`varaus_id`, `palvelu_id`, `lkm`) VALUES
	(2, 1, 1),
	(1, 3, 2),
	(3, 4, 1);
/*!40000 ALTER TABLE `varauksen_palvelut` ENABLE KEYS */;

-- Dumping structure for taulu vp.varaus
CREATE TABLE IF NOT EXISTS `varaus` (
  `varaus_id` int(11) NOT NULL,
  `asiakas_id` int(11) DEFAULT NULL,
  `toimipiste_id` int(11) DEFAULT NULL,
  `varattu_pvm` datetime DEFAULT NULL,
  `vahvistus_pvm` datetime DEFAULT NULL,
  `varattu_alkupvm` datetime DEFAULT NULL,
  `varattu_loppupvm` datetime DEFAULT NULL,
  PRIMARY KEY (`varaus_id`),
  KEY `varaus_toimipiste_id_index` (`toimipiste_id`),
  KEY `varaus_asiakas_id_index` (`asiakas_id`),
  CONSTRAINT `varaus_ibfk_1` FOREIGN KEY (`toimipiste_id`) REFERENCES `toimipiste` (`toimipiste_id`) ON DELETE CASCADE,
  CONSTRAINT `varaus_ibfk_2` FOREIGN KEY (`asiakas_id`) REFERENCES `asiakas` (`asiakas_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table vp.varaus: ~3 rows (suunnilleen)
/*!40000 ALTER TABLE `varaus` DISABLE KEYS */;
INSERT INTO `varaus` (`varaus_id`, `asiakas_id`, `toimipiste_id`, `varattu_pvm`, `vahvistus_pvm`, `varattu_alkupvm`, `varattu_loppupvm`) VALUES
	(1, 1, 3, '2019-05-10 15:19:18', '2019-05-10 15:30:45', '2019-05-20 15:00:00', '2019-05-24 12:00:00'),
	(2, 3, 1, '2019-02-02 17:32:20', '2019-02-02 17:40:04', '2019-06-03 15:00:00', '2019-06-09 12:00:00'),
	(3, 2, 2, '2019-03-10 12:03:32', '2019-03-10 14:00:12', '2019-05-10 15:00:00', '2019-05-12 12:00:00');
/*!40000 ALTER TABLE `varaus` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
