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