DROP TABLE IF EXISTS public.users_reposted_posts CASCADE;
CREATE TABLE public.users_reposted_posts (
                                          id SERIAL PRIMARY KEY,
                                          post_id INTEGER REFERENCES posts(id),
                                          user_id INTEGER REFERENCES users(id)
);