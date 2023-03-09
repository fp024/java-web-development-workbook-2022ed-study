-- https://mariadb.com/kb/en/drop-table/
-- MariaDB에서도 CASCADE 동작은 아직 지원하지 않는 것 같다. 현재 DB의 전체 테이블 삭제 프로시저를 추가했다.
CALL drop_all_tables();

CREATE TABLE tbl_todo (
  tno         INT AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(100) NOT NULL,
  dueDate     DATE NOT NULL,
  finished    TINYINT DEFAULT 0
);

INSERT INTO tbl_todo (title, dueDate, finished)
VALUES ('Test...', '2022-12-31', 1);
INSERT INTO tbl_todo (title, dueDate, finished)
VALUES ('Test...', '2022-12-31', 1);
INSERT INTO tbl_todo (title, dueDate, finished)
VALUES ('Test...', '2022-12-31', 1);
INSERT INTO tbl_todo (title, dueDate, finished)
VALUES ('Test...', '2022-12-31', 1);
INSERT INTO tbl_todo (title, dueDate, finished)
VALUES ('Test...', '2022-12-31', 1);