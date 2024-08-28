/*!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.8-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: otrsdb_textos_prontos
-- ------------------------------------------------------
-- Server version	10.11.8-MariaDB-ubu2204

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `problema`
--

LOCK TABLES `problema` WRITE;
/*!40000 ALTER TABLE `problema` DISABLE KEYS */;
INSERT INTO `problema` VALUES
(1,'Gabinete'),
(2,'Monitor'),
(3,'Mouse'),
(4,'Teclado');
/*!40000 ALTER TABLE `problema` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `descricao_problema`
--

LOCK TABLES `descricao_problema` WRITE;
/*!40000 ALTER TABLE `descricao_problema` DISABLE KEYS */;
INSERT INTO `descricao_problema` VALUES
(1,'Liga mas não apresenta imagem','Computador liga, porém não apresenta imagem na tela. Power LED (azul) acende e HDD Led (vermelho) não acende. LEDs do teclado não respondem.',1),
(2,'Não liga não acende nenhum LED','Computador não liga, Power LED (azul) e HDD Led (vermelho) não estão acendendo. Não há nenhum tipo de feedback ao pressionar o botão de Power para ligar a máquina.',1),
(3,'Tela Piscando','Monitor está com defeito. Não apresentando imagem, apenas a tela piscando.',2),
(4,'Problema no Scroll','Mouse está apresentando problema no scroll, solicitamos a substituição do mesmo.',3),
(5,'Problema na Ventoinha do Processador','Máquina liga e é solicitado pressionar F1 para continuar, com a mensagem: Erro na Ventoinha do processador. \nFoi verificado na BIOS e a rotação do cooler do processador está como N/A.',1),
(6,'Teclas Sem Funcionar','Teclado está com várias teclas sem funcionar. Solicitamos a substituição.',4),
(7,'Problema no Conector Frontal de Áudio','Problema no conector de áudio frontal. Som muito baixo e com problema de mixagem, por exemplo, ao reproduzir uma música, a voz fica muito baixa. Ao conectar o fone de ouvido ou caixa de som no conector traseiro do gabinete, o áudio reproduz normalmente.',1),
(8,'Botão de Power Quebrado','Ao tentar ligar o equipamento, o botão de Power “caiu” para dentro do gabinete.',1),
(9,'Liga mas LED de Power não acende','Computador ligando e funcionando normalmente, porém o led (azul) do botão Power não acende, possível problema no painel frontal, solicitamos verificação.',1),
(10,'Desligando Constantemente','Durante o uso normal, o computador (apenas o gabinete) desliga total.\nAo pressionar o botão de power para tentar religar, nenhum LED acende nenhuma ventoinha liga.\nPossível problema na fonte, solicitamos verificação.',1),
(11,'Erro de Memória ao Ligar','Máquina está apresentando erro de memória diferente (através de uma mensagem da BIOS) sempre que é ligada, como se a memória fosse trocada. O problema persiste mesmo reiniciando a máquina, possível problema na placa, solicitamos verificação.',1),
(12,'Problema na Placa de Rede','Placa de rede da máquina não é reconhecida, testamos tanto no Windows quanto no Linux. Não sendo problema de falta de driver ou cabo de rede. Possível problema na placa de rede da máquina, solicitamos verificação.',1),
(14,'Ventoinha(s) Fazendo Barulho','O computador está fazendo um barulho, como se algum fio ou cabo estivesse prendendo ou encostando na ventoinha do processador ou na frontal do gabinete, solicitamos verificação.',1),
(15,'Tela com listras cinzas','Monitor liga, mas a imagem fica com listras cinzas. ',2);
/*!40000 ALTER TABLE `descricao_problema` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-28 13:19:43
