DROP TABLE IF EXISTS public.posts CASCADE ;
CREATE TABLE public.posts (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                               user_id INT REFERENCES users(id),
                               post_type VARCHAR(255) NOT NULL,
                               content VARCHAR(255),
                               parent_id INT REFERENCES posts(id),
                               created_date TIMESTAMP NOT NULL,
                               updated_date TIMESTAMP NOT NULL,
                               created_by VARCHAR,
                               updated_by VARCHAR
);

