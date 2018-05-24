DROP TABLE IF EXISTS file_upload;
DROP TABLE IF EXISTS optimization_job_solutions;
DROP TABLE IF EXISTS optimization_job_executions;
DROP TABLE IF EXISTS optimization_configuration_variables;
DROP TABLE IF EXISTS optimization_configuration_objectives;
DROP TABLE IF EXISTS optimization_configuration_restrictions;
DROP TABLE IF EXISTS optimization_configuration_algorithms;
DROP TABLE IF EXISTS optimization_configuration_user_solutions;
DROP TABLE IF EXISTS optimization_configuration;

-- File Upload Table
CREATE TABLE file_upload (
  id INT NOT NULL AUTO_INCREMENT,
  session_id VARCHAR(100) NOT NULL,
  file_path VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id)
  -- CONSTRAINT UNIQUE (session_id)
);

-- Optimization Configuration Table
CREATE TABLE optimization_configuration (
  id INT NOT NULL AUTO_INCREMENT,
  problem_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  file_path VARCHAR(100) NOT NULL,
  variables_quantity INT NOT NULL,
  objectives_quantity INT NOT NULL,
  restrictions_quantity INT NULL,
  algorithms_quantity INT NOT NULL,
  execution_max_wait_time INT NOT NULL,
  algorithm_choice_method ENUM('Mixed', 'Automatic', 'Manual') NOT NULL,
  description VARCHAR(500) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id)
);

-- Optimization Job Executions Table
CREATE TABLE optimization_job_executions (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  start_date DATETIME NOT NULL,
  end_date DATETIME NULL,
  state ENUM('Ready', 'Running', 'Finished', 'Failed') NULL,
  latex_path VARCHAR(100) NULL,
  r_path VARCHAR(100) NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_job_executions(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

-- Optimization Job Solutions Table
CREATE TABLE optimization_job_solutions (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_job_executions INT NOT NULL,
  algorithm_name VARCHAR(255) NOT NULL,
  solution TEXT NOT NULL,
  solution_quality TEXT NOT NULL,
  quality VARCHAR(255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_job_solutions(id_optimization_job_executions)
  REFERENCES optimization_job_executions(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE INDEX idx_fk_optimization_job_solutions ON optimization_job_solutions (id_optimization_job_executions);

CREATE INDEX idx_fk_optimization_job_executions ON optimization_job_executions (id_optimization_configuration);

-- Optimization Configuration Variables Table
CREATE TABLE optimization_configuration_variables (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  -- index_optimization_configuration INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_variables(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
  -- CONSTRAINT idx_optimization_configuration_variables_order UNIQUE (id_optimization_configuration, index_optimization_configuration)
);

-- Optimization Configuration Objectives Table
CREATE TABLE optimization_configuration_objectives (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  -- index_optimization_configuration INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_objectives(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
  -- CONSTRAINT idx_optimization_configuration_objectives_order UNIQUE (id_optimization_configuration, index_optimization_configuration)
);

-- Optimization Configuration Restrictions Table
CREATE TABLE optimization_configuration_restrictions (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  -- index_optimization_configuration INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_restrictions(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
  -- CONSTRAINT idx_optimization_configuration_objectives_order UNIQUE (id_optimization_configuration, index_optimization_configuration)
);

-- Optimization Configuration Algorithms Table
CREATE TABLE optimization_configuration_algorithms (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_algorithms(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE INDEX idx_optimization_configuration_algorithms ON optimization_configuration_algorithms (id);

-- Optimization Configuration User Solutions Table
CREATE TABLE optimization_configuration_user_solutions (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  solution_quality TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_user_solutions(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE INDEX idx_optimization_configuration_user_solutions ON optimization_configuration_user_solutions (id_optimization_configuration);
