-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `Sid` varchar(32) NOT NULL COMMENT '书籍编号',
  `BookName` varchar(32) NOT NULL,
  `Author` varchar(32) NOT NULL COMMENT '作者',
  `Categories` varchar(32) NOT NULL DEFAULT '未知' COMMENT '书籍类别',
  `Price` float NOT NULL COMMENT '书籍价格',
  `State` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Sid`),
  UNIQUE KEY `book_Sid_uindex` (`Sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('B01','奥德赛','荷马','人民文学出版社',82,0),('B02','汤姆叔叔的小屋','斯托夫人','商务印书馆',28,0),('B03','算法设计与分析','王红梅','清华大学出版社',66.6,0),('B04','1984','乔治·奥威尔','上海译文出版社',57,0),('B05','这个世界土崩瓦解了','钦努阿·阿契贝','Penguin Classics',44,0),('B06','堂吉诃德','塞万提斯','人民文学出版社',66,0),('B07','哈姆雷特','威廉·莎士比亚','人民文学出版社',33,0),('B08','神曲','但丁','人民文学出版社',89,0),('B09','简·爱','夏洛特·布朗特','人民文学出版社',45,0),('B10','红楼梦','曹雪芹','人民文学出版社',59.7,0),('B11','三国演义','罗贯中','人民文学出版社',39.5,0),('B12','水浒传','施耐庵','人民文学出版社',50.6,0),('B13','西游记','吴承恩','人民文学出版社',47.2,0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrowing`
--

DROP TABLE IF EXISTS `borrowing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrowing` (
  `Oid` int NOT NULL AUTO_INCREMENT,
  `Sid` varchar(32) NOT NULL,
  `Uid` varchar(32) NOT NULL,
  `StartTime` datetime NOT NULL,
  `EndTime` datetime DEFAULT NULL,
  `planEndTime` datetime NOT NULL,
  PRIMARY KEY (`Oid`),
  UNIQUE KEY `borrowing_Oid_uindex` (`Oid`)
) ENGINE=InnoDB AUTO_INCREMENT=100017 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrowing`
--

LOCK TABLES `borrowing` WRITE;
/*!40000 ALTER TABLE `borrowing` DISABLE KEYS */;
INSERT INTO `borrowing` VALUES (100000,'Test','Test','1970-01-01 00:00:00',NULL,'3023-05-30 16:31:41');
/*!40000 ALTER TABLE `borrowing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `Uid` varchar(32) NOT NULL,
  `UserName` varchar(32) NOT NULL,
  `Password` varchar(32) NOT NULL DEFAULT '000000',
  `Tel` varchar(11) DEFAULT '未设置',
  `Type` int NOT NULL DEFAULT '1' COMMENT '身份类别，0为管理员，1为普通用户',
  PRIMARY KEY (`Uid`),
  UNIQUE KEY `user_uid_uindex` (`Uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('000000','管理员','123456',NULL,0),('1951300301','王念','000000','未设置',1),('1951300302','卢芳','000000','未设置',1),('1951300303','唐晓婷','000000','未设置',1),('1951300304','周怡君','000000','未设置',1),('1951300305','史佳星','000000','未设置',1),('1951300306','刘景怡','000000','未设置',1),('1951300309','于天霖','000000','未设置',1),('1951300310','邓旭波','000000','未设置',1),('1951300311','鲁和丰','000000','未设置',1),('1951300312','杨凯杰','000000','未设置',1),('1951300313','罗炜雄','000000','未设置',1),('1951300314','龚胜伟','000000','未设置',1),('1951300315','徐显奇','000000','未设置',1),('1951300316','郑椅民','000000','未设置',1),('1951300317','谭旺成','000000','未设置',1),('1951300318','冯诗其','000000','未设置',1),('1951300319','谢铭哲','000000','未设置',1),('1951300320','蔡文淦','000000','未设置',1),('1951300321','郑先铭','000000','未设置',1),('1951300322','冻世龙','000000','未设置',1),('1951300323','黄炳松','000000','未设置',1),('1951300324','王剑波','000000','未设置',1),('1951300325','蒙俊良','000000','未设置',1),('1951300326','刘世龙','000000','未设置',1),('1951300327','邱金明','000000','未设置',1),('1951300328','古已兴','000000','未设置',1),('1951300329','罗渊','000000','18577106779',1),('1951300330','曹阳','000000','未设置',1),('1951300331','李思明','000000','未设置',1),('1951300332','林子葳','000000','未设置',1),('1951300333','杨明冈','000000','未设置',1),('1951300334','凌熙','000000','未设置',1),('1951300335','谭家柽','000000','未设置',1),('1951300336','谢鹏','000000','未设置',1),('1951300337','庞博友','000000','未设置',1),('1954300129','朱子翰','000000','未设置',1),('1954300225','林昌龙','000000','未设置',1),('1956410126','王子航','000000','未设置',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-24 21:28:52
