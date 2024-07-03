-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ingsisweb
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `buoni`
--

DROP TABLE IF EXISTS `buoni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buoni` (
  `id_buono` int NOT NULL AUTO_INCREMENT,
  `codice` varchar(100) NOT NULL,
  `percentuale_sconto` int NOT NULL,
  `valido_da` date DEFAULT NULL,
  `valido_a` date DEFAULT NULL,
  `attivo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_buono`),
  UNIQUE KEY `codice` (`codice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buoni`
--

LOCK TABLES `buoni` WRITE;
/*!40000 ALTER TABLE `buoni` DISABLE KEYS */;
/*!40000 ALTER TABLE `buoni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrelli`
--

DROP TABLE IF EXISTS `carrelli`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carrelli` (
  `id_carrello` int NOT NULL AUTO_INCREMENT,
  `id_utente` int NOT NULL,
  `totale` decimal(10,2) DEFAULT '0.00',
  `tipo` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`id_carrello`),
  KEY `id_utente` (`id_utente`),
  CONSTRAINT `carrelli_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrelli`
--

LOCK TABLES `carrelli` WRITE;
/*!40000 ALTER TABLE `carrelli` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrelli` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `elementi_carrello`
--

DROP TABLE IF EXISTS `elementi_carrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `elementi_carrello` (
  `id_elemento_carrello` int NOT NULL AUTO_INCREMENT,
  `id_carrello` int NOT NULL,
  `id_prodotto` int NOT NULL,
  `quantita` int NOT NULL,
  `prezzo_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_elemento_carrello`),
  KEY `id_carrello` (`id_carrello`),
  KEY `id_prodotto` (`id_prodotto`),
  CONSTRAINT `elementi_carrello_ibfk_1` FOREIGN KEY (`id_carrello`) REFERENCES `carrelli` (`id_carrello`),
  CONSTRAINT `elementi_carrello_ibfk_2` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotti` (`id_prodotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `elementi_carrello`
--

LOCK TABLES `elementi_carrello` WRITE;
/*!40000 ALTER TABLE `elementi_carrello` DISABLE KEYS */;
/*!40000 ALTER TABLE `elementi_carrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzi`
--

DROP TABLE IF EXISTS `indirizzi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indirizzi` (
  `id_indirizzo` int NOT NULL AUTO_INCREMENT,
  `id_utente` int NOT NULL,
  `via` varchar(255) NOT NULL,
  `citta` varchar(255) NOT NULL,
  `stato` varchar(255) DEFAULT NULL,
  `codice_postale` varchar(10) DEFAULT NULL,
  `paese` varchar(100) NOT NULL,
  PRIMARY KEY (`id_indirizzo`),
  KEY `id_utente` (`id_utente`),
  CONSTRAINT `indirizzi_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id_utente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzi`
--

LOCK TABLES `indirizzi` WRITE;
/*!40000 ALTER TABLE `indirizzi` DISABLE KEYS */;
/*!40000 ALTER TABLE `indirizzi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prodotti`
--

DROP TABLE IF EXISTS `prodotti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotti` (
  `id_prodotto` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descrizione` text,
  `prezzo` decimal(10,2) NOT NULL,
  `quantita_disponibile` int NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_prodotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotti`
--

LOCK TABLES `prodotti` WRITE;
/*!40000 ALTER TABLE `prodotti` DISABLE KEYS */;
/*!40000 ALTER TABLE `prodotti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenti` (
  `id_utente` int NOT NULL AUTO_INCREMENT,
  `nome_utente` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `ruolo` enum('pubblico','registrato','amministratore') NOT NULL,
  PRIMARY KEY (`id_utente`),
  UNIQUE KEY `nome_utente` (`nome_utente`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-02 17:33:13
