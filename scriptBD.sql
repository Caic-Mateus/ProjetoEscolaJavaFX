-- Create the database
CREATE DATABASE escola;

-- Create the Professor table
CREATE TABLE Professor (
    CPF VARCHAR(20) PRIMARY KEY,
    Nome VARCHAR(255),
    Sexo CHAR(1),
    Email VARCHAR(255),
    RG VARCHAR(20),
    Salario VARCHAR(20),
    Banco VARCHAR(255),
    Agencia VARCHAR(20),
    Conta VARCHAR(20)
);
-- Create the Login table
CREATE TABLE Login (
    CPF VARCHAR(20) PRIMARY KEY,
    Senha VARCHAR(255),
    Tipo VARCHAR(20)
);
drop table CLASSE 
-- Create the Classe table
CREATE TABLE Classe (
    Identificacao VARCHAR PRIMARY KEY,
    Periodo VARCHAR(255)
	);
	
	

-- Create the Aluno table
CREATE TABLE Aluno (
    CPF VARCHAR(20) PRIMARY KEY,
    Nome VARCHAR(255),
    Sexo CHAR(1),
    Responsavel VARCHAR(255),
    Email VARCHAR(255),
    RG VARCHAR(20),
    identificacao varchar,
    Periodo VARCHAR(255),
    RA VARCHAR(20) UNIQUE,
    FOREIGN KEY (identificacao) REFERENCES Classe(identificacao)
);

-- Adição da tabela Disciplina
CREATE TABLE Disciplina (
    ID INT PRIMARY KEY,
    Nome VARCHAR(255),
    Professor_CPF VARCHAR(20),
    FOREIGN KEY (Professor_CPF) REFERENCES Professor(CPF)
);

-- Adição da tabela Matricula
CREATE TABLE Matricula (
    ID INT PRIMARY KEY,
    Aluno_CPF VARCHAR(20),
    Disciplina_ID INT,
    Nota FLOAT,
    FOREIGN KEY (Aluno_CPF) REFERENCES Aluno(CPF),
    FOREIGN KEY (Disciplina_ID) REFERENCES Disciplina(ID)
);

