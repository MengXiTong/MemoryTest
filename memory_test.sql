CREATE DATABASE `memory_test` DEFAULT CHARACTER SET utf8;

CREATE TABLE `memory_test`.`tblRanking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `score` int NOT NULL,
  `difficulty` int NOT NULL,
  `time` datetime NULL,
  PRIMARY KEY (`id`));
  
  INSERT INTO `memory_test`.`tblRanking`
(name,score,difficulty,time) VALUES ('zhangsan',90,0,'2017-6-7 08:44:23');
 INSERT INTO `memory_test`.`tblRanking`
(name,score,difficulty,time) VALUES ('lisi',123,0,'2017-6-7 08:50:23');