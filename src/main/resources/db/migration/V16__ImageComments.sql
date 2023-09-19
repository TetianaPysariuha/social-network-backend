DROP TABLE IF EXISTS public.user_images  CASCADE ;
CREATE TABLE public.user_images (
                                    id SERIAL PRIMARY KEY ,
                                    created_date TIMESTAMP NOT NULL,
                                    updated_date TIMESTAMP NOT NULL,
                                    created_by VARCHAR,
                                    updated_by VARCHAR,
                                    img_url VARCHAR(255),
                                    user_id INT,
                                    image_user_id INT,
                                    FOREIGN KEY (image_user_id) REFERENCES users(id)
);
INSERT INTO user_images (created_date, updated_date, created_by, updated_by, img_url, user_id,image_user_id)
VALUES
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'John', 'John', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225330/comedy3_g1zcyx.jpg', 1,1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Alice', 'Alice', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225184/pexels-photo-460775_oca6pi.jpg', 2,2),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Bob', 'Bob', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225087/images_cnmzed.jpg', 3,3),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'Emma', 'Emma', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225058/images_v1xobs.jpg', 1,1),
    (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'David', 'David', 'https://res.cloudinary.com/dhbq0uk5g/image/upload/v1693225184/pexels-photo-460775_oca6pi.jpg', 2,2);






DROP TABLE IF EXISTS public.comments CASCADE ;
CREATE TABLE public.comments (
                                      id SERIAL PRIMARY KEY,
                                      author_id INT NOT NULL,
                                      image_id INT NOT NULL ,
                                      content VARCHAR NOT NULL ,
                                      foreign key (author_id) references users(id),
                                      foreign key (image_id) references user_images(id),
                                      created_date TIMESTAMP NOT NULL ,
                                      updated_date TIMESTAMP NOT NULL,
                                      created_by VARCHAR,
                                      updated_by VARCHAR



);
INSERT INTO public.comments(author_id,image_id,content,created_date,updated_date) VALUES
                                                                                                                 (1,1,'Beautiful picture','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                (2,2,'I like that','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                 (4,3,'How beautiful','2016-07-26 05:15:58','2016-07-26 05:15:58'),
                                                                                                                 (3,4,'Very interesting','2016-07-26 05:15:58','2016-07-26 05:15:58');
