CREATE TABLE notice(
	notice_id NUMBER PRIMARY KEY,
	title varchar(100),
	writer varchar(20),
	content clob,
	regdate DATE DEFAULT sysdate,
	hit NUMBER DEFAULT 0
);

CREATE SEQUENCE seq_notice
INCREMENT BY 1
START WITH 1;