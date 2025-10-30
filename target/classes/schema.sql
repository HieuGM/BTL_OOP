-- Initial database schema for BTL_OOP project

-- Create Users table
CREATE TABLE users (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    email NVARCHAR(255) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    first_name NVARCHAR(100),
    last_name NVARCHAR(100),
    role NVARCHAR(20) NOT NULL DEFAULT 'USER',
    is_active BIT NOT NULL DEFAULT 1,
    created_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    updated_at DATETIME2 NOT NULL DEFAULT GETDATE()
);

-- Create indexes for better performance
CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_role ON users(role);
CREATE INDEX idx_users_is_active ON users(is_active);

-- Insert sample data
INSERT INTO users (username, email, password, first_name, last_name, role, is_active) VALUES
('admin', 'admin@btl.com', '$2a$10$encrypted_password', 'Admin', 'User', 'ADMIN', 1),
('user1', 'user1@btl.com', '$2a$10$encrypted_password', 'John', 'Doe', 'USER', 1),
('user2', 'user2@btl.com', '$2a$10$encrypted_password', 'Jane', 'Smith', 'USER', 1);

-- Additional tables can be added here based on project requirements
-- Examples:
-- Categories table
-- Products table
-- Orders table
-- etc.