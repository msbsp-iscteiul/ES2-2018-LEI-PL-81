DROP TABLE IF EXISTS file_upload;

-- uploadFile Table
CREATE TABLE file_upload (
        id INT NOT NULL AUTO_INCREMENT,
        session_id VARCHAR(100) NOT NULL,
        file_path VARCHAR(100) NOT NULL,
        created_at TIMESTAMP NOT NULL,
        CONSTRAINT PRIMARY KEY (id)
        -- CONSTRAINT UNIQUE (session_id)
);



