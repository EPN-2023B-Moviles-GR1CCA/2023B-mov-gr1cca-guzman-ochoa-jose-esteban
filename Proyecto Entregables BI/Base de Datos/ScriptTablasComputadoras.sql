CREATE TABLE Marca_computadora (
    id INT PRIMARY KEY,
    nombre VARCHAR(30),
    pais VARCHAR(30),
    fecha_fundacion DATE,
    extranjera BOOLEAN
);

CREATE TABLE Computadora (
    id INT PRIMARY KEY,
    nombre VARCHAR(30),
    descripcion VARCHAR(100),
    precio FLOAT,
    fecha_compra DATE,
    garantia BOOLEAN,
    marca_id INT,
    FOREIGN KEY (marca_id) REFERENCES Marca_computadora(id)
);