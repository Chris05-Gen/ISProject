CREATE DATABASE  IF NOT EXISTS `stellar_library` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `stellar_library`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: stellar_library
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carrello`
--

DROP TABLE IF EXISTS `carrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrello` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDUtente` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDUtente` (`IDUtente`),
  CONSTRAINT `carrello_ibfk_1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrello`
--

LOCK TABLES `carrello` WRITE;
/*!40000 ALTER TABLE `carrello` DISABLE KEYS */;
INSERT INTO `carrello` VALUES (28,NULL),(30,NULL),(32,NULL),(33,NULL),(34,NULL),(35,NULL),(36,NULL),(37,NULL),(38,NULL),(39,NULL),(59,NULL),(60,NULL),(68,NULL),(69,NULL),(70,NULL),(71,NULL),(72,NULL),(73,NULL),(74,NULL),(75,NULL),(76,NULL),(77,NULL),(78,NULL),(79,NULL),(80,NULL),(81,NULL),(82,NULL),(83,NULL),(84,NULL),(85,NULL),(87,NULL),(88,NULL),(89,NULL),(94,NULL),(1,1),(2,2),(64,3),(12,4),(53,6),(54,7),(65,8),(67,9),(96,10);
/*!40000 ALTER TABLE `carrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contiene`
--

DROP TABLE IF EXISTS `contiene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contiene` (
  `IDCarrello` int NOT NULL,
  `ISBNCodice` varchar(20) NOT NULL,
  `Quantita` int DEFAULT '1',
  PRIMARY KEY (`IDCarrello`,`ISBNCodice`),
  KEY `ISBNCodice` (`ISBNCodice`),
  CONSTRAINT `contiene_ibfk_1` FOREIGN KEY (`IDCarrello`) REFERENCES `carrello` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `contiene_ibfk_2` FOREIGN KEY (`ISBNCodice`) REFERENCES `libro` (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contiene`
--

LOCK TABLES `contiene` WRITE;
/*!40000 ALTER TABLE `contiene` DISABLE KEYS */;
INSERT INTO `contiene` VALUES (1,'9780261102217',1),(1,'9780553386790',2),(1,'9788804668231',2),(1,'9788817060964',1),(2,'9780261102385',1),(2,'9788807880044',1),(12,'9780261102217',1),(12,'9788822795380',1),(12,'9788830455929',1),(28,'9780553386790',4),(32,'9780307277671',1),(33,'9780307277671',1),(35,'9780307277671',1),(38,'9780307277671',1),(64,'9788830455929',1),(64,'9791281656314',3),(71,'9780261102217',1),(72,'9788822795380',1),(73,'9788807880044',1),(74,'9788830455929',1),(75,'9788822795380',1),(75,'9788830455929',1),(76,'9788830455929',1),(77,'9788830455929',1),(78,'9788830455929',1),(79,'9788830455929',1),(80,'9788830455929',1),(81,'9788830455929',1),(82,'9788830455929',1),(83,'9788830455929',1),(84,'9788850256310',1),(85,'9788830455929',1),(88,'9788822795380',1);
/*!40000 ALTER TABLE `contiene` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genere`
--

DROP TABLE IF EXISTS `genere`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genere` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Nome` (`Nome`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genere`
--

LOCK TABLES `genere` WRITE;
/*!40000 ALTER TABLE `genere` DISABLE KEYS */;
INSERT INTO `genere` VALUES (1,'Classico'),(8,'Distopico'),(4,'Fantascienza'),(2,'Fantasy'),(9,'Meditazione'),(10,'Motivazionale'),(7,'Narrativa'),(3,'Post-apocalittico'),(6,'Saggistica'),(5,'Storico'),(11,'Thriller');
/*!40000 ALTER TABLE `genere` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzo`
--

DROP TABLE IF EXISTS `indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzo` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDUtente` int NOT NULL,
  `Via` varchar(100) DEFAULT NULL,
  `Citta` varchar(50) DEFAULT NULL,
  `CAP` varchar(10) DEFAULT NULL,
  `Provincia` varchar(50) DEFAULT NULL,
  `Nazione` varchar(50) DEFAULT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDUtente` (`IDUtente`),
  CONSTRAINT `indirizzo_ibfk_1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo`
--

LOCK TABLES `indirizzo` WRITE;
/*!40000 ALTER TABLE `indirizzo` DISABLE KEYS */;
INSERT INTO `indirizzo` VALUES (3,1,'Via Roma 10','Napoli','80100','NA','Italia','+39 0811234567'),(4,2,'Corso Italia 5','Salerno','84121','SA','Italia','+39 0897654321'),(5,4,'Tito Livio 3','Arpaia','82011','Benevento','Italy','3921602728'),(6,1,'Via Roma 10','Milano','20100','MI','Italia','0245691234'),(7,2,'Piazza Navona 5','Roma','00186','RM','Italia','066854321'),(8,3,'Tito Livio 3','Arpaia','82011','Benevento','Italy','3921602728'),(9,8,'Tito Livio 3','Arpaia','82011','Benevento','Italy','3921602728'),(10,9,'Via Carlo Alberto Dalla Chiesa 2','Arpaia','82011','Benevento','Italy','3921602728'),(11,4,'Via Carlo Alberto Dalla Chiesa 2','Arpaia','82011','Benevento','Italy','3921602728'),(12,10,'Via Carlo Alberto Dalla Chiesa 2','Arpaia','82011','Benevento','Italy','3921602728');
/*!40000 ALTER TABLE `indirizzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `ISBN` varchar(20) NOT NULL,
  `Titolo` varchar(255) NOT NULL,
  `Autore` varchar(100) DEFAULT NULL,
  `CasaEditrice` varchar(100) DEFAULT NULL,
  `Pagine` int DEFAULT NULL,
  `Copertina` varchar(255) DEFAULT NULL,
  `AnnoPubblicazione` int DEFAULT NULL,
  `Prezzo` decimal(6,2) NOT NULL,
  `IDGenere` int DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `fk_libro_genere` (`IDGenere`),
  CONSTRAINT `fk_libro_genere` FOREIGN KEY (`IDGenere`) REFERENCES `genere` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES ('9780061120084','To Kill a Mockingbird','Harper Lee','Harper Perennial',336,'https://m.media-amazon.com/images/I/81c6aew79KL._SY466_.jpg',1960,14.99,1),('9780261102217','Il Signore degli Anelli','J.R.R. Tolkien','Bompiani',1216,'https://m.media-amazon.com/images/I/81RabOfQcNL._SY466_.jpg',1954,52.25,2),('9780261102385','Lo Hobbit','J.R.R. Tolkien','Bompiani',310,'https://m.media-amazon.com/images/I/71emctAoDYL._SY466_.jpg',1937,14.25,2),('9780307277671','The Road','Cormac McCarthy','Vintage',287,'https://m.media-amazon.com/images/I/51w9nWqxfxL._SY445_SX342_PQ40_.jpg',2006,12.35,3),('9780553386790','Foundation','Isaac Asimov','HarperCollins',255,'https://m.media-amazon.com/images/I/713dCi9ISwL._SY522_.jpg',1951,27.54,4),('9788804668231','Il nome della rosa','Umberto Eco','Bompiani',512,'https://m.media-amazon.com/images/I/61Aa9Yic8AL._SY425_.jpg',1980,16.50,5),('9788804681965','Sapiens. Da animali a dèi','Yuval Noah Harari','Bompiani',468,'https://m.media-amazon.com/images/I/71JvpJ2dMZL._SY385_.jpg',2011,19.90,6),('9788807880044','Norwegian Wood','Haruki Murakami','Einaudi',384,'https://m.media-amazon.com/images/I/41h8SU4eueL._SY445_SX342_PQ46_.jpg',1987,12.90,7),('9788817060964','1984','George Orwell','Mondadori',328,'https://m.media-amazon.com/images/I/51uSJ6RSDaL._SY445_SX342_PQ46_.jpg',1949,13.90,8),('9788822795380','La teoria di lasciare andare. The let them theory ','Mel Robbins','Newton Compton Editori',320,'https://m.media-amazon.com/images/I/8134n901J9L._SY466_.jpg',2025,12.36,9),('9788830455929','L’arte di essere fragili','Alessandro D\'Avenia','Mondadori',224,'https://m.media-amazon.com/images/I/41FwK5DXt-L._SY445_SX342_PQ46_.jpg',2016,10.90,10),('9788845298752','Homo Deus: Breve storia del futuro','Yuval Noah Harari','Bompiani',548,'https://m.media-amazon.com/images/I/61rJtiMs60L._SY466_.jpg',2018,17.10,5),('9788850256310','Il codice da Vinci','Dan Brown','Mondadori',592,'https://m.media-amazon.com/images/I/51slCtNhIlL._SY445_SX342_PQ46_.jpg',2003,14.20,11),('9791281656314','Isabella Nagg e il vaso di basilico','Oliver Darkshire','Mercurio Books',312,'https://m.media-amazon.com/images/I/71oF9GUoVwL._SL1500_.jpg',2025,15.00,2);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodopagamento`
--

DROP TABLE IF EXISTS `metodopagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodopagamento` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(50) DEFAULT NULL,
  `Circuito` varchar(50) DEFAULT NULL,
  `Descrizione` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodopagamento`
--

LOCK TABLES `metodopagamento` WRITE;
/*!40000 ALTER TABLE `metodopagamento` DISABLE KEYS */;
INSERT INTO `metodopagamento` VALUES (1,'Carta di credito','Visa','Carta Visa a 16 cifre'),(2,'Carta di credito','Mastercard','Carta Mastercard'),(3,'PayPal','PayPal','Pagamento tramite PayPal con email'),(4,'Carta di debito','Maestro','Circuito Maestro per carte di debito'),(5,'Carta prepagata','PostePay','Carta prepagata PostePay'),(6,'Pagamento digitale','Apple Pay','Apple Pay tramite dispositivi Apple'),(7,'Pagamento digitale','Google Pay','Google Pay tramite dispositivi Android'),(8,'Bonifico bancario','SEPA','Pagamento via bonifico bancario europeo (SEPA)'),(9,'Contrassegno','N/A','Pagamento alla consegna in contanti'),(10,'Satispay','Satispay','Pagamento via app mobile Satispay');
/*!40000 ALTER TABLE `metodopagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordine`
--

DROP TABLE IF EXISTS `ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordine` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDUtente` int NOT NULL,
  `IDIndirizzo` int NOT NULL,
  `IDMetodoPagamento` int NOT NULL,
  `DataOrdine` datetime DEFAULT CURRENT_TIMESTAMP,
  `Totale` decimal(8,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`ID`),
  KEY `IDUtente` (`IDUtente`),
  KEY `IDIndirizzo` (`IDIndirizzo`),
  KEY `IDMetodoPagamento` (`IDMetodoPagamento`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`),
  CONSTRAINT `ordine_ibfk_2` FOREIGN KEY (`IDIndirizzo`) REFERENCES `indirizzo` (`ID`),
  CONSTRAINT `ordine_ibfk_3` FOREIGN KEY (`IDMetodoPagamento`) REFERENCES `metodopagamento` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordine`
--

LOCK TABLES `ordine` WRITE;
/*!40000 ALTER TABLE `ordine` DISABLE KEYS */;
INSERT INTO `ordine` VALUES (5,1,3,1,'2025-06-20 14:30:00',0.00),(6,2,4,2,'2025-06-19 09:15:00',0.00),(7,4,5,2,'2025-06-26 16:18:47',0.00),(8,4,5,2,'2025-06-26 18:05:02',24.71),(9,4,5,2,'2025-06-26 18:05:57',24.71),(10,4,5,1,'2025-06-26 18:06:30',12.35),(11,4,5,1,'2025-06-26 18:20:49',12.35),(12,4,5,3,'2025-06-26 18:24:27',52.25),(16,4,5,7,'2025-06-28 11:37:01',52.64),(17,3,8,1,'2025-06-29 16:45:10',10.90),(18,8,9,7,'2025-07-17 18:34:34',12.36),(19,9,10,7,'2025-07-18 23:07:31',10.90),(20,9,10,1,'2025-07-18 23:10:47',12.36),(21,9,10,1,'2025-07-18 23:12:42',12.36),(22,9,10,1,'2025-07-18 23:18:43',23.26),(23,4,5,8,'2025-07-19 15:35:52',41.70),(24,10,12,3,'2025-07-23 09:37:40',28.00);
/*!40000 ALTER TABLE `ordine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDUtente` int NOT NULL,
  `ISBNCodice` varchar(20) NOT NULL,
  `Titolo` varchar(100) DEFAULT NULL,
  `Testo` text,
  `Valutazione` int DEFAULT NULL,
  `Data` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDUtente` (`IDUtente`),
  KEY `ISBNCodice` (`ISBNCodice`),
  CONSTRAINT `recensione_ibfk_1` FOREIGN KEY (`IDUtente`) REFERENCES `utente` (`ID`),
  CONSTRAINT `recensione_ibfk_2` FOREIGN KEY (`ISBNCodice`) REFERENCES `libro` (`ISBN`),
  CONSTRAINT `recensione_chk_1` CHECK (((`Valutazione` >= 1) and (`Valutazione` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES (1,1,'9780261102217','Favoloso!','Un viaggio epico, che mi ha emozionato.',5,'2025-06-18'),(2,2,'9780553386790','Interessante','Molto dettagliato e scorrevole.',4,'2025-06-17'),(8,4,'9791281656314','https://m.media-amazon.com/images/I/71oF9GUoVwL._S','https://m.media-amazon.com/images/I/71oF9GUoVwL._SL1500_.jpg\r\n',4,'2025-07-23');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `Cognome` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Tipo` enum('Admin','Cliente') NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Mario','Rossi','mario.rossi@example.com','password123','Cliente'),(2,'Anna','Bianchi','anna.bianchi@example.com','securepwd','Cliente'),(3,'Luca','Verdi','luca.verdi@example.com','6926c3fb312bd78d5f8961701bb31ee140ee8d2932b82ad80002e588c39b4692','Admin'),(4,'Christian Antonio','Genovese','christiangenovese05@gmail.com','1410d6dd2013750c43843a1160176a6f54fc657a5e6c227451a27cd4a85bc886','Cliente'),(6,'Marco','Rossi','marco.rossi@example.com','hashed_pwd1','Cliente'),(7,'Giulia','Bianchi','giulia.bianchi@example.com','hashed_pwd2','Cliente'),(8,'Christian Antonio','Genovese','christiangenovese06@gmail.com','35cb2845aad12fffd44f6ba0ac47b168ba88ffb19fef74d33f46bf1d2717f26c','Cliente'),(9,'Anna Maria','Iuliano','annaprova@gmail.com','735dee35e73df12a493eeb5a984a4b7649ec74414918e8ab569e2927614b50e8','Cliente'),(10,'Pippo','Pluto','pippo@pluto.it','2d40bbdb6c223756ca89c1b5ebb6148b26dfac1a2ccebc4e6ac7944d7a62ff3e','Cliente');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-04 17:43:12
