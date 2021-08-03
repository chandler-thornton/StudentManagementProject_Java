CREATE TABLE `jdbc_studentmanagment`.`students` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `studentID` INT UNSIGNED NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `year` INT UNSIGNED NULL,
  `courses` VARCHAR(100) NULL,
  `balance` INT NULL,
  PRIMARY KEY (`id`));
