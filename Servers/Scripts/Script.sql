CREATE TABLE notice(
	notice_id NUMBER PRIMARY KEY,
	title varchar(100),
	writer varchar(20),
	content clob,
	regdate DATE DEFAULT sysdate,
	hit NUMBER DEFAULT 0
);

select notice_id, title, writer, content, regdate, hit from notice ORDER BY notice_id DESC;