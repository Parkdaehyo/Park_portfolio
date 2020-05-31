//백업을 하기위해서 sql문을 작성한다고?

CREATE TABLE new_user(
	account VARCHAR(80) PRIMARY KEY,
	password VARCHAR(80) NOT NULL,
	name VARCHAR(100) NOT NULL,
	reg_date TIMESTAMP DEFAULT NOW()
	

);

-- 자동로그인 관련 컬럼 추가
-- none은 자동로그인을 하지않은 사람을 의미함.
ALTER TABLE new_user ADD COLUMN session_id VARCHAR(80) NOT NULL DEFAULT 'none';
ALTER TABLE new_user ADD COLUMN limit_time TIMESTAMP;
