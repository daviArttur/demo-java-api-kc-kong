-- V1__Create_initial_schema.sql
-- Initial database schema creation for the donation campaign system

-- Create enum types
CREATE TYPE payment_type AS ENUM ('PIX', 'CREDIT_CARD', 'DEBIT_CARD');

-- Create users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Create campaigns table
CREATE TABLE campaigns (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    title VARCHAR(40) NOT NULL,
    description VARCHAR(999) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Create donation_configs table (base table for both money and item donation configs)
CREATE TABLE donation_configs (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Create money_donation_configs table
CREATE TABLE money_donation_configs (
    id BIGSERIAL PRIMARY KEY,
    campaign_id BIGINT NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL,
    goal BIGINT NOT NULL,
    use_description VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE
);
-- Create item_donation_configs table
CREATE TABLE item_donation_configs (
    id BIGSERIAL PRIMARY KEY,
    campaign_id BIGINT NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE
);

-- Create items table
CREATE TABLE items (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    quantity_goal INTEGER NOT NULL,
    quantity_received INTEGER NOT NULL DEFAULT 0,
    item_donation_config_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    FOREIGN KEY (item_donation_config_id) REFERENCES item_donation_configs(id) ON DELETE CASCADE
);

-- Create delivery_points table
CREATE TABLE delivery_points (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    street VARCHAR(100) NOT NULL,
    number INTEGER NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code VARCHAR(9) NOT NULL,
    opening_time TIME NOT NULL,
    closing_time TIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Create donations table (base table for both money and item donations)
CREATE TABLE donations (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Create money_donations table
CREATE TABLE money_donations (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    amount BIGINT NOT NULL,
    payment_type payment_type NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Create item_donations table
CREATE TABLE item_donations (
    id BIGSERIAL PRIMARY KEY,
    uuid VARCHAR(36) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Create campaign_donation_configs junction table to link campaigns with their donation configs
CREATE TABLE campaign_money_donation_configs (
    campaign_id BIGINT NOT NULL,
    money_donation_config_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    PRIMARY KEY (campaign_id, money_donation_config_id),
    FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE,
    FOREIGN KEY (money_donation_config_id) REFERENCES money_donation_configs(id) ON DELETE CASCADE
);

CREATE TABLE campaign_item_donation_configs (
    campaign_id BIGINT NOT NULL,
    item_donation_config_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    PRIMARY KEY (campaign_id, item_donation_config_id),
    FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE,
    FOREIGN KEY (item_donation_config_id) REFERENCES item_donation_configs(id) ON DELETE CASCADE
);

-- Create indexes for better performance
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_uuid ON users(uuid);
CREATE INDEX idx_campaigns_uuid ON campaigns(uuid);
CREATE INDEX idx_campaigns_title ON campaigns(title);
CREATE INDEX idx_items_name ON items(name);
CREATE INDEX idx_items_config_id ON items(item_donation_config_id);
CREATE INDEX idx_delivery_points_location ON delivery_points(city, state);
CREATE INDEX idx_donations_uuid ON donations(uuid);
CREATE INDEX idx_money_donations_amount ON money_donations(amount);
CREATE INDEX idx_money_donations_payment_type ON money_donations(payment_type);

-- Add constraints
ALTER TABLE campaigns ADD CONSTRAINT chk_campaign_title_length CHECK (LENGTH(title) >= 4 AND LENGTH(title) <= 40);
ALTER TABLE campaigns ADD CONSTRAINT chk_campaign_description_length CHECK (LENGTH(description) >= 4 AND LENGTH(description) <= 999);
ALTER TABLE items ADD CONSTRAINT chk_item_name_length CHECK (LENGTH(name) >= 2 AND LENGTH(name) <= 100);
ALTER TABLE items ADD CONSTRAINT chk_item_quantity_goal CHECK (quantity_goal > 0);
ALTER TABLE items ADD CONSTRAINT chk_item_quantity_received CHECK (quantity_received >= 0);
ALTER TABLE money_donation_configs ADD CONSTRAINT chk_money_goal CHECK (goal > 0);
ALTER TABLE money_donations ADD CONSTRAINT chk_donation_amount CHECK (amount >= 100);
ALTER TABLE delivery_points ADD CONSTRAINT chk_delivery_number CHECK (number > 0);
ALTER TABLE delivery_points ADD CONSTRAINT chk_delivery_state_length CHECK (LENGTH(state) = 2);
ALTER TABLE delivery_points ADD CONSTRAINT chk_delivery_zip_code CHECK (zip_code ~ '^\d{5}-\d{3}$');
ALTER TABLE delivery_points ADD CONSTRAINT chk_delivery_times CHECK (opening_time < closing_time);

-- Create updated_at trigger function
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Create triggers for updated_at columns
CREATE TRIGGER update_users_updated_at BEFORE UPDATE ON users FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_campaigns_updated_at BEFORE UPDATE ON campaigns FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_donation_configs_updated_at BEFORE UPDATE ON donation_configs FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_money_donation_configs_updated_at BEFORE UPDATE ON money_donation_configs FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_item_donation_configs_updated_at BEFORE UPDATE ON item_donation_configs FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_items_updated_at BEFORE UPDATE ON items FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_delivery_points_updated_at BEFORE UPDATE ON delivery_points FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_donations_updated_at BEFORE UPDATE ON donations FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_money_donations_updated_at BEFORE UPDATE ON money_donations FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
CREATE TRIGGER update_item_donations_updated_at BEFORE UPDATE ON item_donations FOR EACH ROW EXECUTE FUNCTION update_updated_at_column(); 