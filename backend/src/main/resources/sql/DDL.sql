DROP TABLE IF EXISTS file_upload;
DROP TABLE IF EXISTS optimization_configuration;
DROP TABLE IF EXISTS optimization_job_executions;
DROP TABLE IF EXISTS optimization_configuration_variables;
DROP TABLE IF EXISTS optimization_configuration_objectives;
DROP TABLE IF EXISTS optimization_configuration_restrictions;
DROP TABLE IF EXISTS optimization_configuration_algorithms;
DROP TABLE IF EXISTS optimization_configuration_user_solutions;


-- File Upload Table
CREATE TABLE file_upload (
  id INT NOT NULL AUTO_INCREMENT,
  session_id VARCHAR(100) NOT NULL,
  file_path VARCHAR(100) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id)
  -- CONSTRAINT UNIQUE (session_id)
);

CREATE TABLE optimization_configuration (
  id INT NOT NULL AUTO_INCREMENT,
  problem_name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL,
  file_path VARCHAR(100) NOT NULL,
  variables_quantity INT NOT NULL,
  objectives_quantity INT NOT NULL,
  restrictions_quantity INT NOT NULL,
  algorithms_quantity INT NOT NULL,
  execution_max_wait_time INT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id)
);

CREATE TABLE optimization_job_executions (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  start_date DATETIME NOT NULL,
  end_date DATETIME NULL,
  state enum('Ready', 'Running', 'Finished') null,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_job_executions(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE TABLE optimization_job_solutions (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_job_executions INT NOT NULL,
  algorithm_name varchar(255) not null,
  solution text not null,
  solution_quality text not null,
  created_at TIMESTAMP NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_job_solutions(id_optimization_job_executions)
  REFERENCES optimization_job_executions(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE INDEX idx_fk_optimization_job_solutions ON optimization_job_solutions (id_optimization_job_executions);

CREATE INDEX idx_fk_optimization_job_executions ON optimization_job_executions (id_optimization_configuration);

-- Variables Table
CREATE TABLE optimization_configuration_variables (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  `index` int not null,
  name VARCHAR(100) NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_variables(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT idx_optimization_configuration_variables_order UNIQUE (id_optimization_configuration, `index`)
);

-- Objectives Table
CREATE TABLE optimization_configuration_objectives (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  `index` int not null,
  name VARCHAR(100) NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_objectives(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT idx_optimization_configuration_objectives_order UNIQUE (id_optimization_configuration, `index`)
);

-- Restrictions Table
CREATE TABLE optimization_configuration_restrictions (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  `index` int not null,
  name VARCHAR(100) NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_restrictions(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT idx_optimization_configuration_objectives_order UNIQUE (id_optimization_configuration, `index`)
);

-- Algorithms Table
CREATE TABLE optimization_configuration_algorithms (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  name VARCHAR(100) NOT NULL,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_algorithms(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE INDEX idx_optimization_configuration_algorithms ON optimization_configuration_algorithms (id);

-- User Solutions Table
CREATE TABLE optimization_configuration_user_solutions (
  id INT NOT NULL AUTO_INCREMENT,
  id_optimization_configuration INT NOT NULL,
  objectives_quality VARCHAR(100) NOT NULL,
  solution_quality text not null,
  CONSTRAINT PRIMARY KEY (id),
  CONSTRAINT FOREIGN KEY fk_optimization_configuration_user_solutions(id_optimization_configuration)
  REFERENCES optimization_configuration(id)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

CREATE INDEX idx_optimization_configuration_user_solutions ON optimization_configuration_user_solutions (id_optimization_configuration);








# create table OptimizationConfigurations
# (
#   id int auto_increment
#     primary key,
#   CreatedAt datetime not null,
#   Email varchar(255) not null,
#   ProblemName varchar(255) not null,
#   ProblemPath varchar(255) not null,
#   ObjectiveQuantity int not null,
#   RestrictionsQuantity int not null,
#   VarQuantity int not null,
#   AlgorithmQuantity int not null,
#   ExecutionMaxWaitingTime int not null
# )
# ;

# create table OptimizationConfigurationAlgorithms
# (
#   id int auto_increment
#     primary key,
#   OptimizationConfigurationId int not null,
#   Name int not null,
#   constraint configuration_algorithm
#   foreign key (OptimizationConfigurationId) references OptimizationConfigurations (id)
#     on update cascade on delete cascade
# )
# ;
#
# create index configuration_algorithm
#   on OptimizationConfigurationAlgorithms (OptimizationConfigurationId)
# ;

# create table OptimizationConfigurationObjectives
# (
#   id int auto_increment
#     primary key,
#   OptimizationConfigurationId int not null,
#   `Index` int not null,
#   Name varchar(255) null,
#   constraint configuration_objective_order
#   unique (OptimizationConfigurationId, `Index`),
#   constraint VariableJobConfiguration
#   foreign key (OptimizationConfigurationId) references OptimizationConfigurations (id)
#     on update cascade on delete cascade
# )
# ;

# create table OptimizationConfigurationUserSolutions
# (
#   id int auto_increment
#     primary key,
#   OptimizationConfigurationId int not null,
#   Name varchar(255) not null,
#   SolutionQuality text not null,
#   constraint configuration_user_solutions
#   foreign key (OptimizationConfigurationId) references OptimizationConfigurations (id)
# )
# ;
#
# create index configuration_user_solutions
#   on OptimizationConfigurationUserSolutions (OptimizationConfigurationId)
# ;

# create table OptimizationConfigurationVariables
# (
#   id int auto_increment
#     primary key,
#   OptimizationConfigurationId int not null,
#   `Index` int not null,
#   Name int not null,
#   constraint variable_order
#   unique (OptimizationConfigurationId, `Index`),
#   constraint variable_job_configuration
#   foreign key (OptimizationConfigurationId) references OptimizationConfigurations (id)
#     on update cascade on delete cascade
# )
# ;

create table OptimizationJobs
(
  id int auto_increment
    primary key,
  OptimizationConfigurationId int not null,
  StartDateTime datetime not null,
  FinishDateTime datetime null,
  State enum('Ready', 'Running', 'Finished') null,
  constraint OptimizationJobExecutions_OptimizationConfigurations_id_fk
  foreign key (OptimizationConfigurationId) references OptimizationConfigurations (id)
    on update cascade on delete cascade
)
;

create table OptimizationJobSolutions
(
  id int auto_increment
    primary key,
  OptimizationJobExecutionId int not null,
  AlgorithmName varchar(255) not null,
  Solution text not null,
  SolutionQuality text not null,
  constraint OptimizationJobSolutions_OptimizationJobExecutions_id_fk
  foreign key (OptimizationJobExecutionId) references OptimizationJobs (id)
    on update cascade on delete cascade
)
;

create index OptimizationJobSolutions_OptimizationJobExecutions_id_fk
  on OptimizationJobSolutions (OptimizationJobExecutionId)
;

create index OptimizationJobExecutions_OptimizationConfigurations_id_fk
  on OptimizationJobs (OptimizationConfigurationId)
;
