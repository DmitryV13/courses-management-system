INSERT INTO users (username, email, password)
VALUES
    ('user', '789test9870@gmail.com','$2a$10$DX.v/ux0WLZ9BLZhNjD9Duw1rlwv2wZ.vHxUVsCgqLftYuSn/A.wG'),
    ('teacher', '789test9870@gmail.com','$2a$10$DX.v/ux0WLZ9BLZhNjD9Duw1rlwv2wZ.vHxUVsCgqLftYuSn/A.wG'),
    ('trainee', '789test9870@gmail.com','$2a$10$DX.v/ux0WLZ9BLZhNjD9Duw1rlwv2wZ.vHxUVsCgqLftYuSn/A.wG'),
    ('admin', '789test9870@gmail.com','$2a$10$DX.v/ux0WLZ9BLZhNjD9Duw1rlwv2wZ.vHxUVsCgqLftYuSn/A.wG');

INSERT INTO users_authorities (user_id, authority_name)
    SELECT id, 'STUDENT' FROM users WHERE username = 'user'
    UNION
    SELECT id, 'TEACHER' FROM users WHERE username = 'teacher'
    UNION
    SELECT id, 'TRAINEE' FROM users WHERE username = 'trainee'
    UNION
    SELECT id, 'ADMIN' FROM users WHERE username = 'admin';

