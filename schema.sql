------用户表--------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
--  Records of `users`
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('test', 'test', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

-------角色表--------------
DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `authority` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
--  Records of `authorities`
-- ----------------------------
BEGIN;
INSERT INTO `authorities` VALUES ('test', 'ADMIN');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

--------

-- ----------------------------
--  Records of `oauth_client_details` 和 app/src/main/resources/application.yaml中的相关配置对比查看
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('web', '1', 'web_secret', 'read,write', 'authorization_code,password, implicit, refresh_token', '', 'ADMIN,USER', null, null, null, '2018-07-15 14:55:14', '0', '1', '1');
COMMIT;