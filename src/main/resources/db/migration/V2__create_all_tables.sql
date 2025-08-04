-- Create campaigns table
CREATE TABLE campaigns (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    title VARCHAR(40) NOT NULL,
    description VARCHAR(999) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create delivery_points table
CREATE TABLE delivery_points (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    street VARCHAR(100) NOT NULL,
    number INTEGER NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    zip_code VARCHAR(9) NOT NULL,
    opening_time TIME NOT NULL,
    closing_time TIME NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_zip_code_format CHECK (zip_code ~ '^\d{5}-\d{3}$'),
    CONSTRAINT chk_number_positive CHECK (number > 0)
);

-- Create items table
CREATE TABLE items (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(100) NOT NULL,
    quantity_goal INTEGER NOT NULL,
    quantity_received INTEGER DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_name_length CHECK (LENGTH(name) >= 2),
    CONSTRAINT chk_quantity_goal_positive CHECK (quantity_goal > 0),
    CONSTRAINT chk_quantity_received_non_negative CHECK (quantity_received >= 0)
);

-- Create donation_configs table
CREATE TABLE donation_configs (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    enabled BOOLEAN DEFAULT true,
    config_type VARCHAR(20) NOT NULL CHECK (config_type IN ('MONEY', 'ITEM')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create money_donation_configs table
CREATE TABLE money_donation_configs (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    goal INTEGER NOT NULL,
    use_description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_goal_positive CHECK (goal > 0)
);

-- Create item_donation_configs table
CREATE TABLE item_donation_configs (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create item_donation_config_items junction table
CREATE TABLE item_donation_config_items (
    id SERIAL PRIMARY KEY,
    item_donation_config_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (item_donation_config_id) REFERENCES item_donation_configs(id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
    UNIQUE(item_donation_config_id, item_id)
);

-- Create campaign_configs junction table
CREATE TABLE campaign_configs (
    id SERIAL PRIMARY KEY,
    campaign_id INTEGER NOT NULL,
    money_donation_config_id INTEGER,
    item_donation_config_id INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE,
    FOREIGN KEY (money_donation_config_id) REFERENCES money_donation_configs(id) ON DELETE SET NULL,
    FOREIGN KEY (item_donation_config_id) REFERENCES item_donation_configs(id) ON DELETE SET NULL,
    CONSTRAINT chk_at_least_one_config CHECK (
        money_donation_config_id IS NOT NULL OR item_donation_config_id IS NOT NULL
    )
);

-- Create donations table
CREATE TABLE donations (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    donation_type VARCHAR(20) NOT NULL CHECK (donation_type IN ('MONEY', 'ITEM')),
    user_id INTEGER,
    campaign_id INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE SET NULL
);

-- Create money_donations table
CREATE TABLE money_donations (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    donation_id INTEGER NOT NULL,
    amount BIGINT NOT NULL,
    payment_type VARCHAR(20) NOT NULL CHECK (payment_type IN ('PIX', 'CREDIT_CARD', 'DEBIT_CARD')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (donation_id) REFERENCES donations(id) ON DELETE CASCADE,
    CONSTRAINT chk_amount_minimum CHECK (amount >= 100)
);

-- Create item_donations table
CREATE TABLE item_donations (
    id SERIAL PRIMARY KEY,
    uuid VARCHAR(255) UNIQUE NOT NULL,
    donation_id INTEGER NOT NULL,
    delivery_point_id INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (donation_id) REFERENCES donations(id) ON DELETE CASCADE,
    FOREIGN KEY (delivery_point_id) REFERENCES delivery_points(id) ON DELETE SET NULL
);

-- Create item_donation_items junction table
CREATE TABLE item_donation_items (
    id SERIAL PRIMARY KEY,
    item_donation_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (item_donation_id) REFERENCES item_donations(id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE,
    CONSTRAINT chk_quantity_positive CHECK (quantity > 0)
); 