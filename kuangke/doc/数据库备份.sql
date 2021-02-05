DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '����',
  `sex` varchar(2) DEFAULT NULL COMMENT '�Ա�',
  `age` varchar(2) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- �û���
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT,
  `UESRNAME` VARCHAR(11) DEFAULT NULL COMMENT '�û���',
  `PASSWORD` VARCHAR(100) DEFAULT NULL COMMENT '����',
  `STATUS` VARCHAR(2) DEFAULT NULL COMMENT '״̬',
  PRIMARY KEY (`ID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;


-- �첽���ݱ�
CREATE TABLE `student_controller` (
  `id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(100) DEFAULT NULL COMMENT '����',
  `sex` varchar(2) DEFAULT NULL COMMENT '�Ա�',
  `age` varchar(2) DEFAULT NULL COMMENT '����',
  `memo` varchar(255) DEFAULT NULL COMMENT '��ע',
  `mdtime` varchar(20) DEFAULT NULL COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;