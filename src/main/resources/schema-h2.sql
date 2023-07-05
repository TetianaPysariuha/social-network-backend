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

DROP TABLE IF EXISTS public.users_liked_posts CASCADE;
CREATE TABLE public.users_liked_posts (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       post_id INTEGER REFERENCES posts(id),
                       user_id INTEGER REFERENCES users(id)
);

DROP TABLE IF EXISTS public.users_reposted_posts CASCADE;
CREATE TABLE public.users_reposted_posts (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       post_id INTEGER REFERENCES posts(id),
                       user_id INTEGER REFERENCES users(id)
);

DROP TABLE IF EXISTS public.post_images CASCADE ;
CREATE TABLE public.post_images (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                               post_id INT REFERENCES posts(id),
                               img_url VARCHAR(255),
                               created_date TIMESTAMP NOT NULL,
                               updated_date TIMESTAMP NOT NULL,
                               created_by VARCHAR,
                               updated_by VARCHAR
);

