INSERT INTO public.posts (user_id, post_type, content, parent_id, created_date, updated_date)
VALUES
  (NULL, 'post', 'Привет, мир!', NULL, '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
  (NULL, 'comment', 'Это отличный пост!', NULL, '2023-06-25 10:05:00', '2023-06-25 10:05:00'),
  (NULL, 'post', 'Здесь ничего интересного...', NULL, '2023-06-25 10:10:00', '2023-06-25 10:10:00');

INSERT INTO public.users_liked_posts (post_id, user_id)
VALUES
  (1, null),
  (1, null),
  (2, null),
  (3, null);

INSERT INTO public.users_reposted_posts (post_id, user_id)
VALUES
  (2, null),
  (3, null);

INSERT INTO public.post_images (post_id, img_url, created_date, updated_date)
VALUES
  (1, 'https://example.com/image1.jpg', '2023-06-25 10:00:00', '2023-06-25 10:00:00'),
  (2, 'https://example.com/image2.jpg', '2023-06-25 10:05:00', '2023-06-25 10:05:00');


