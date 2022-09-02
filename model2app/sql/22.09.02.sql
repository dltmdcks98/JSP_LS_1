CREATE TABLE gallery(
	gallery_id NUMBER PRIMARY KEY,
	title varchar(100),
	writer varchar(30),
	content clob,
	regdate DATE DEFAULT sysdate,
	hit NUMBER DEFAULT 0,
	filename varchar(30)
);

CREATE SEQUENCE seq_gallery
INCREMENT BY 1
START WITH 1;

