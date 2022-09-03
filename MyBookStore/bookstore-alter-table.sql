ALTER TABLE `test`.`accounts` 
ADD COLUMN `salt` VARCHAR(64) NOT NULL AFTER `password`,
CHANGE COLUMN `password` `password` VARCHAR(64) NOT NULL ;