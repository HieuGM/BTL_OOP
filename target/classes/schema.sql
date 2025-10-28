-- Initial database schema for BTL_OOP project

-- Create Users table
CREATE TABLE users (
    user_id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nickname NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    balance FLOAT() NOT NULL,
    role NVARCHAR(20) NOT NULL DEFAULT 'USER',
    is_active BIT NOT NULL DEFAULT 1,
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    updated_at DATETIME2 NOT NULL DEFAULT GETDATE()
);

-- Create indexes for better performance
CREATE INDEX idx_users_nickname ON users(nickname);

CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_is_active ON users(is_active);

-- Insert sample data
INSERT INTO users (nickname, password, balance, role, is_active) VALUES
('admin', 'admin', 1500, 'ADMIN', 1),
('user1', 'user1', 2000, 'USER',  1),
('user2', 'user2', 3000, 'USER',  1);

