

CREATE TABLE `subscriptor_web` (
  `idsubscriptor` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `firstname` varchar(45) DEFAULT NULL,
  `gender` varchar(45) DEFAULT NULL,
  `dateofbirth` date NOT NULL,
  `idnewsletter` int(11) DEFAULT NULL,
  `idampaing` int(11) DEFAULT NULL,
  PRIMARY KEY (`idsubscriptor`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;


# Stored procedure

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_subscriptor`( IN  email_in char(11),
firstname_in char,
gender_in char,
dateofbirth_in date,
idnewsletter_in int,
idampaing_in int, out id_out int)
BEGIN
 
 
IF NOT EXISTS (
            SELECT *
           FROM `customer_care`.`subscriptor_web`
            WHERE `email` = email_in
            LIMIT 1
        ) THEN begin
			INSERT INTO `customer_care`.`subscriptor_web`
			(`email`,`firstname`,`gender`,`dateofbirth`,`idnewsletter`,`idampaing`)
			VALUES
			(email_in,firstname_in,gender_in,dateofbirth_in,idnewsletter_in,idampaing_in);

			SET id_out = LAST_INSERT_ID();
            
		end;
ELSE begin
			SET id_out = -1;
		
		end;
END IF;


END$$
DELIMITER ;
